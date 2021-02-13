package eann.genetics.algorithms.util;

import java.util.ArrayList;
import java.util.List;

public class AbstractGeneticAlgorithmSubject implements GeneticAlgorithmSubject {

    private final List<GeneticAlgorithmObserver> observers;

    public AbstractGeneticAlgorithmSubject() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(GeneticAlgorithmObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GeneticAlgorithmObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(IntermediateResult event) {
        observers.forEach(observer -> observer.update(event));
    }
}
