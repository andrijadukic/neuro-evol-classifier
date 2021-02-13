package eann.genetics.operators.crossover.floatinpoint;

import eann.genetics.chromosomes.util.Pair;
import eann.rand.SourceOfRandomness;

import java.util.Optional;
import java.util.Random;

public class BLXAlphaCrossover extends AbstractFloatingPointCrossover {

    private final double alpha;

    public BLXAlphaCrossover(double alpha) {
        this.alpha = alpha;
    }

    @Override
    protected Pair<double[], Optional<double[]>> mateRaw(double[] first, double[] second) {
        int length = first.length;

        double[] child = new double[length];

        Random random = SourceOfRandomness.getSource();
        for (int i = 0; i < length; i++) {
            double max = Math.max(first[i], second[i]);
            double min = Math.min(first[i], second[i]);
            double d = max - min;
            double lb = min - d * alpha;
            double ub = max + d * alpha;
            child[i] = random.nextDouble() * (ub - lb) + lb;
        }

        return new Pair<>(child, Optional.empty());
    }
}
