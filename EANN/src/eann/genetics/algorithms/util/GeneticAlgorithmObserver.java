package eann.genetics.algorithms.util;

@FunctionalInterface
public interface GeneticAlgorithmObserver {

    void update(IntermediateResult intermediateResult);
}
