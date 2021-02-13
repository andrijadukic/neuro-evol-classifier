package eann.genetics.operators.mutation.floatingpoint;

import eann.genetics.chromosomes.floatingpoint.FloatingPointGenotype;
import eann.rand.SourceOfRandomness;

import java.util.Random;

public abstract class AbstractFloatingPointSimpleMutation extends AbstractFloatingPointMutation {

    private final double pm;

    protected AbstractFloatingPointSimpleMutation(double pm) {
        this.pm = pm;
    }

    @Override
    protected final FloatingPointGenotype mutate(FloatingPointGenotype genotype) {
        double[] raw = genotype.getRaw();
        Random random = SourceOfRandomness.getSource();
        for (int i = 0, n = raw.length; i < n; i++) {
            if (random.nextDouble() <= pm) {
                raw[i] = getMutatedValue(raw[i]);
            }
        }
        return genotype;
    }

    protected abstract double getMutatedValue(double original);
}
