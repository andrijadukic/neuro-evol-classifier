package eann.genetics.algorithms;

import eann.genetics.algorithms.conditions.StoppingCondition;
import eann.genetics.algorithms.util.AbstractGeneticAlgorithmSubject;
import eann.genetics.algorithms.util.IntermediateResult;
import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.population.Population;
import eann.genetics.exceptions.EmptyPopulationException;

import java.util.Objects;

public abstract class AbstractGeneticAlgorithm extends AbstractGeneticAlgorithmSubject implements GeneticAlgorithm {

    @Override
    public Population run(Population initial, StoppingCondition condition) {
        Objects.requireNonNull(initial);
        Objects.requireNonNull(condition);

        if (initial.size() == 0) throw new EmptyPopulationException();

        validate(initial);

        int iteration = 0;
        Population current = initial.copy();
        Chromosome fittest = current.getFittest();
        while (true) {
            IntermediateResult intermediateResult = new IntermediateResult(iteration, fittest, current);
            notifyObservers(intermediateResult);

            if (condition.isMet(intermediateResult)) break;

            current = next(current);

            Chromosome candidate = current.getFittest();
            if (candidate.compareTo(fittest) > 0) {
                fittest = candidate;
            }

            iteration++;
        }
        return current;
    }

    public abstract void validate(Population initial);

    public abstract Population next(Population current);
}
