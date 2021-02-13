package eann.neural;

import eann.genetics.chromosomes.floatingpoint.FloatingPointChromosome;
import eann.genetics.chromosomes.floatingpoint.FloatingPointGenotype;

import java.util.Objects;
import java.util.function.DoubleSupplier;
import java.util.stream.DoubleStream;

public class ParameterChromosome extends FloatingPointChromosome {

    private final FitnessFunction fitnessFunction;

    public ParameterChromosome(FloatingPointGenotype genotype, FitnessFunction fitnessFunction) {
        super(genotype);
        this.fitnessFunction = Objects.requireNonNull(fitnessFunction);
    }

    public ParameterChromosome(int n, DoubleSupplier supplier, FitnessFunction fitnessFunction) {
        this(new FloatingPointGenotype(DoubleStream.generate(supplier).limit(n).toArray()), fitnessFunction);
    }

    @Override
    protected double evaluate() {
        return fitnessFunction.score(genotype.getRaw());
    }

    @Override
    public ParameterChromosome newInstance(FloatingPointGenotype genotype) {
        return new ParameterChromosome(genotype, fitnessFunction);
    }
}
