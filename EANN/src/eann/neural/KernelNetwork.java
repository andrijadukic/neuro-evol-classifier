package eann.neural;

import eann.genetics.algorithms.GeneticAlgorithm;
import eann.genetics.algorithms.conditions.StoppingCondition;
import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.population.ListBasedPopulation;
import eann.genetics.chromosomes.population.Population;
import eann.ml.MachineLearningModel;
import eann.ml.exceptions.InputDimensionMismatch;
import eann.ml.exceptions.ModelNotFittedException;
import eann.ml.loss.LossFunctions;
import eann.ml.sampling.Sample;
import eann.neural.activations.ActivationFunction;
import eann.neural.activations.Activations;
import eann.rand.SourceOfRandomness;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class KernelNetwork implements MachineLearningModel {

    private final int[] neuronsPerLayer;

    private final GeneticAlgorithm geneticAlgorithm;
    private final int populationSize;
    private final StoppingCondition stoppingCondition;

    private double[] coef;
    private boolean isFitted;

    public KernelNetwork(int[] neuronsPerLayer, GeneticAlgorithm geneticAlgorithm, int populationSize, StoppingCondition stoppingCondition) {
        this.neuronsPerLayer = Objects.requireNonNull(neuronsPerLayer);
        this.geneticAlgorithm = Objects.requireNonNull(geneticAlgorithm);
        this.populationSize = populationSize;
        this.stoppingCondition = Objects.requireNonNull(stoppingCondition);
    }

    @Override
    public KernelNetwork fit(List<Sample> samples) {
        Chromosome fittest = geneticAlgorithm.run(generateInitialPopulation(samples, populationSize), stoppingCondition).getFittest();

        if (!(fittest instanceof ParameterChromosome solution)) throw new IllegalStateException();

        coef = solution.getGenotype().getRaw();
        isFitted = true;

        return this;
    }

    private Population generateInitialPopulation(List<Sample> samples, int populationSize) {
        return new ListBasedPopulation(
                Population.generate(
                        () -> new ParameterChromosome(
                                getCoefCount(),
                                () -> -1 + 2 * SourceOfRandomness.getSource().nextDouble(),
                                coef -> -LossFunctions.MSE().score(input -> forwardPass(input, coef), samples)
                        ),
                        populationSize
                )
        );
    }

    private int getCoefCount() {
        int total = neuronsPerLayer[0] * neuronsPerLayer[1] * 2;
        int layersCount = neuronsPerLayer.length - 1;
        for (int i = 1, n = layersCount - 1; i < n; i++) {
            total += (neuronsPerLayer[i] + 1) * neuronsPerLayer[i + 1];
        }
        total += (neuronsPerLayer[layersCount - 1] + 1) * neuronsPerLayer[layersCount];
        return total;
    }

    @Override
    public double[] predict(double[] input) {
        if (!isFitted) throw new ModelNotFittedException(getClass());
        return forwardPass(input, coef);
    }

    private double[] forwardPass(double[] input, double[] coef) {
        int prevLayerNeuronCount = neuronsPerLayer[0];
        if (input.length != prevLayerNeuronCount) throw new InputDimensionMismatch(prevLayerNeuronCount, input.length);

        int neuronCount = neuronsPerLayer[1];
        double[] output = new double[neuronCount];

        int offset = 0;
        for (int i = 0; i < neuronCount; i++) {
            double y = 1.;
            for (int j = 0; j < prevLayerNeuronCount; j++) {
                y += Math.abs(input[j] - coef[offset + 2 * j]) / Math.abs(coef[offset + 2 * j + 1]);
            }
            output[i] = 1. / y;

            offset += 2 * prevLayerNeuronCount;
        }

        ActivationFunction sigmoid = Activations.sigmoid();
        for (int k = 1, l = neuronsPerLayer.length - 1; k < l; k++) {
            prevLayerNeuronCount = neuronsPerLayer[k];
            neuronCount = neuronsPerLayer[k + 1];
            input = output;
            output = new double[neuronCount];
            for (int i = 0; i < neuronCount; i++) {
                double net = coef[offset++];
                for (int j = 0; j < prevLayerNeuronCount; j++) {
                    net += input[j] * coef[offset++];
                }
                output[i] = sigmoid.valueAt(net);
            }
        }

        return output;
    }

    public void save(String path) throws IOException {
        if (!isFitted) throw new ModelNotFittedException(getClass());
        try (var writer = Files.newBufferedWriter(Path.of(path))) {
            writer.write(Arrays.stream(coef).mapToObj(String::valueOf).collect(Collectors.joining(",")));
        }
    }

    public void load(String path) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(path));
        if (lines.size() != 1) throw new InvalidParameterException();

        String[] coefSplit = lines.get(0).split(",");
        if (coefSplit.length != getCoefCount()) throw new InvalidParameterException();

        coef = Arrays.stream(lines.get(0).split(",")).mapToDouble(Double::parseDouble).toArray();
        if (coef.length != getCoefCount()) throw new InvalidParameterException();

        isFitted = true;
    }
}
