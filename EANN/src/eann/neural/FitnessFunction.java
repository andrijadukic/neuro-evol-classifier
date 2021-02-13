package eann.neural;

@FunctionalInterface
public interface FitnessFunction {

    double score(double[] x);
}
