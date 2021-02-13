package eann.genetics.operators.crossover.floatinpoint;

import eann.genetics.chromosomes.util.Pair;
import eann.rand.SourceOfRandomness;

import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.Random;

public class FloatingPointMultiplePointCrossover extends AbstractFloatingPointCrossover {

    private final int crossovers;

    public FloatingPointMultiplePointCrossover(int crossovers) {
        if (crossovers <= 0) throw new InvalidParameterException();
        this.crossovers = crossovers;
    }

    @Override
    protected Pair<double[], Optional<double[]>> mateRaw(double[] first, double[] second) {
        int length = first.length;

        double[] child1 = new double[length];
        double[] child2 = new double[length];

        int lastCrossover = 0;
        Random random = SourceOfRandomness.getSource();
        for (int i = 0; i < crossovers; i++) {
            int crossover = 1 + lastCrossover + random.nextInt(length - lastCrossover - (crossovers - i));
            for (int j = lastCrossover; j < crossover; j++) {
                child1[j] = first[j];
                child2[j] = second[j];
            }

            var temp = child1;
            child1 = child2;
            child2 = temp;

            lastCrossover = crossover;
        }

        for (int i = lastCrossover; i < length; i++) {
            child1[i] = first[i];
            child2[i] = second[i];
        }

        return new Pair<>(child1, Optional.of(child2));
    }
}
