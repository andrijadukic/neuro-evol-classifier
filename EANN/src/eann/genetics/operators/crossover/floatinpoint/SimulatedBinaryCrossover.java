package eann.genetics.operators.crossover.floatinpoint;

import eann.genetics.chromosomes.util.Pair;
import eann.rand.SourceOfRandomness;

import java.util.Optional;
import java.util.Random;

public class SimulatedBinaryCrossover extends AbstractFloatingPointCrossover {

    @Override
    protected Pair<double[], Optional<double[]>> mateRaw(double[] first, double[] second) {
        int length = first.length;

        double[] child = new double[length];

        Random random = SourceOfRandomness.getSource();
        for (int i = 0; i < length; i++) {
            double a = first[i];
            double b = second[i];
            double r = random.nextDouble();
            child[i] = r * a + (1 - r) * b;
        }

        return new Pair<>(child, Optional.empty());
    }
}
