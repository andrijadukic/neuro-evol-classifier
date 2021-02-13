package eann.genetics.operators.mutation.floatingpoint;

import eann.rand.SourceOfRandomness;

public class UniformReplacementMutation extends AbstractFloatingPointSimpleMutation {

    private final double lb;
    private final double range;

    public UniformReplacementMutation(double pm, double lb, double ub) {
        super(pm);
        this.lb = lb;
        range = ub - lb;
    }

    public UniformReplacementMutation(double pm, double bound) {
        this(pm, -bound, bound);
    }

    @Override
    protected double getMutatedValue(double original) {
        return lb + SourceOfRandomness.getSource().nextDouble() * range;
    }
}
