package eann.genetics.algorithms.util;

import eann.genetics.chromosomes.Chromosome;

public class OnFittestChangeObserver implements GeneticAlgorithmObserver {

    private final GeneticAlgorithmObserver geneticAlgorithmObserver;
    private Chromosome previousFittest;

    public OnFittestChangeObserver(GeneticAlgorithmObserver geneticAlgorithmObserver) {
        this.geneticAlgorithmObserver = geneticAlgorithmObserver;
    }

    @Override
    public void update(IntermediateResult intermediateResult) {
        Chromosome intermediateFittest = intermediateResult.fittest();
        if (intermediateResult.iteration() == 0 || previousFittest.compareTo(intermediateFittest) < 0) {
            previousFittest = intermediateFittest;
            geneticAlgorithmObserver.update(intermediateResult);
        }
    }
}
