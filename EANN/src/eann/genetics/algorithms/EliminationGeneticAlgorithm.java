package eann.genetics.algorithms;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.population.Population;
import eann.genetics.exceptions.InsufficientPopulationSIze;
import eann.genetics.operators.crossover.CrossoverOperator;
import eann.genetics.operators.mutation.MutationOperator;
import eann.genetics.chromosomes.util.Pair;
import eann.rand.SourceOfRandomness;

import java.util.*;

public final class EliminationGeneticAlgorithm extends AbstractGeneticAlgorithm {

    private final CrossoverOperator crossoverOperator;
    private final MutationOperator mutationOperator;
    private final int tournamentSize;

    private static final int DEFAULT_TOURNAMENT_SIZE = 3;

    public EliminationGeneticAlgorithm(CrossoverOperator crossoverOperator, MutationOperator mutationOperator, int tournamentSize) {
        this.crossoverOperator = crossoverOperator;
        this.mutationOperator = mutationOperator;
        this.tournamentSize = tournamentSize;
    }

    public EliminationGeneticAlgorithm(CrossoverOperator crossoverOperator, MutationOperator mutationOperator) {
        this(crossoverOperator, mutationOperator, DEFAULT_TOURNAMENT_SIZE);
    }

    @Override
    public void validate(Population initial) {
        int size = initial.size();
        if (size < tournamentSize) throw new InsufficientPopulationSIze(size, tournamentSize);
    }

    @Override
    public Population next(Population current) {
        int size = current.size();

        Random random = SourceOfRandomness.getSource();

        List<Pair<Chromosome, Integer>> tournament = new ArrayList<>(tournamentSize);
        Set<Integer> selected = new HashSet<>(tournamentSize, 1.f);
        for (int i = 0; i < tournamentSize; i++) {
            int rind = random.nextInt(size);
            while (selected.contains(rind)) {
                rind = random.nextInt(size);
            }
            tournament.add(new Pair<>(current.getChromosome(rind), rind));
            selected.add(rind);
        }

        tournament.sort((a, b) -> b.first().compareTo(a.first()));
        Chromosome child = mutationOperator.mutate(crossoverOperator.crossover(tournament.get(0).first(), tournament.get(1).first()).first());
        current.setChromosome(tournament.get(tournamentSize - 1).second(), child);
        return current;
    }
}
