package eann.neural.activations;

public interface ActivationFunction {

    double valueAt(double net);

    double gradientAt(double net);
}
