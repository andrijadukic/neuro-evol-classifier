package eann.genetics.operators.crossover.floatinpoint;

import eann.genetics.chromosomes.util.Pair;
import eann.rand.SourceOfRandomness;

import java.util.Optional;


public class SimpleArithmeticCrossover extends AbstractArithmeticCrossover {

    public SimpleArithmeticCrossover() {
    }

    public SimpleArithmeticCrossover(double alpha) {
        super(alpha);
    }

    @Override
    protected Pair<double[], Optional<double[]>> mateRaw(double[] first, double[] second) {
        int length = first.length;

        double[] child = new double[length];

        int rind = SourceOfRandomness.getSource().nextInt(length);

        System.arraycopy(first, 0, child, 0, rind);

        for (int i = rind; i < length; i++) {
            double val = alpha * (first[i] + second[i]);
            child[i] = val;
        }

        return new Pair<>(child, Optional.empty());
    }
}

