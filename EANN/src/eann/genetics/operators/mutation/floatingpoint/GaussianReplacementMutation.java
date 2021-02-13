package eann.genetics.operators.mutation.floatingpoint;

import eann.rand.SourceOfRandomness;

public class GaussianReplacementMutation extends AbstractFloatingPointSimpleMutation {

    private final double mean;
    private final double stddev;

    public GaussianReplacementMutation(double pm, double mean, double stddev) {
        super(pm);
        this.mean = mean;
        this.stddev = stddev;
    }

    @Override
    protected double getMutatedValue(double original) {
        return mean + SourceOfRandomness.getSource().nextGaussian() * stddev;
    }
}
