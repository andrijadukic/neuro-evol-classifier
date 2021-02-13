package eann.genetics.algorithms.util;

import java.util.Objects;

public class NthIterationObserver implements GeneticAlgorithmObserver {

    private final GeneticAlgorithmObserver observer;
    private final int step;

    public NthIterationObserver(GeneticAlgorithmObserver observer, int step) {
        this.observer = Objects.requireNonNull(observer);
        this.step = step;
    }

    @Override
    public void update(IntermediateResult intermediateResult) {
        int count = intermediateResult.iteration();
        if (count % step == 0) {
            observer.update(intermediateResult);
        }
    }
}
