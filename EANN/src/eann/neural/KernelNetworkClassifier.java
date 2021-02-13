package eann.neural;

import eann.genetics.algorithms.GeneticAlgorithm;
import eann.genetics.algorithms.conditions.StoppingCondition;

public class KernelNetworkClassifier extends KernelNetwork {

    public KernelNetworkClassifier(int[] neuronsPerLayer, GeneticAlgorithm geneticAlgorithm, int populationSize, StoppingCondition stoppingCondition) {
        super(neuronsPerLayer, geneticAlgorithm, populationSize, stoppingCondition);
    }

    @Override
    public double[] predict(double[] input) {
        double[] output = super.predict(input);
        for (int i = 0, n = output.length; i < n; i++) {
            output[i] = output[i] < 0.5 ? 0. : 1.;
        }
        return output;
    }
}
