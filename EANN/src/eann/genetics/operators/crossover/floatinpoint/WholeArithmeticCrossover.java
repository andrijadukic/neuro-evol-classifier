package eann.genetics.operators.crossover.floatinpoint;

import eann.genetics.chromosomes.util.Pair;

import java.util.Optional;

public class WholeArithmeticCrossover extends AbstractArithmeticCrossover {

    public WholeArithmeticCrossover() {
    }

    public WholeArithmeticCrossover(double alpha) {
        super(alpha);
    }

    @Override
    protected Pair<double[], Optional<double[]>> mateRaw(double[] first, double[] second) {
        int length = first.length;

        double[] child = new double[length];
        for (int i = 0; i < length; i++) {
            child[i] = alpha * (first[i] + second[i]);
        }

        return new Pair<>(child, Optional.empty());
    }
}
