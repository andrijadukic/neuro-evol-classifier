package eann.genetics.algorithms;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.population.Population;
import eann.genetics.chromosomes.Children;
import eann.genetics.operators.crossover.CrossoverOperator;
import eann.genetics.operators.mutation.MutationOperator;
import eann.genetics.operators.selection.SelectionOperator;

public class GenerationalGeneticAlgorithm extends AbstractGeneticAlgorithm {

    private final SelectionOperator selectionOperator;
    private final CrossoverOperator crossoverOperator;
    private final MutationOperator mutationOperator;
    private boolean isElitist;

    private static final boolean DEFAULT_ELITIST = true;

    public GenerationalGeneticAlgorithm(SelectionOperator selectionOperator,
                                        CrossoverOperator crossoverOperator,
                                        MutationOperator mutationOperator,
                                        boolean isElitist) {
        this.selectionOperator = selectionOperator;
        this.crossoverOperator = crossoverOperator;
        this.mutationOperator = mutationOperator;
        this.isElitist = isElitist;
    }

    public GenerationalGeneticAlgorithm(SelectionOperator selectionOperator,
                                        CrossoverOperator crossoverOperator,
                                        MutationOperator mutationOperator) {
        this(selectionOperator, crossoverOperator, mutationOperator, DEFAULT_ELITIST);
    }

    public boolean isElitist() {
        return isElitist;
    }

    public void setElitist(boolean elitist) {
        isElitist = elitist;
    }

    @Override
    public void validate(Population initial) {
    }

    @Override
    public Population next(Population current) {
        Population next = current.empty();

        if (isElitist) {
            next.addChromosome(current.getFittest());
        }

        int remaining = current.size() - next.size();
        while (remaining != 0) {
            Chromosome[] parents = selectionOperator.select(current, 2);
            Children children = crossoverOperator.crossover(parents[0], parents[1]);

            next.addChromosome(mutationOperator.mutate(children.first()));
            remaining--;

            if (remaining == 0) break;

            if (children.second().isEmpty()) continue;

            next.addChromosome(mutationOperator.mutate(children.second().get()));
            remaining--;
        }

        return next;
    }
}
