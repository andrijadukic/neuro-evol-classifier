package eann.genetics.operators.selection;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.population.Population;
import eann.genetics.exceptions.InsufficientPopulationSIze;
import eann.rand.SourceOfRandomness;

import java.security.InvalidParameterException;
import java.util.Random;
import java.util.stream.Stream;

public class TournamentSelection implements SelectionOperator {

    private final int tournamentSize;

    public TournamentSelection(int tournamentSize) {
        if (tournamentSize <= 0) throw new InvalidParameterException("Tournament size must be greater than zero");
        this.tournamentSize = tournamentSize;
    }

    @Override
    public Chromosome select(Population population) {
        int size = population.size();

        if (size < tournamentSize) throw new InsufficientPopulationSIze(size, tournamentSize);

        if (size == tournamentSize) return population.getFittest();

        Random random = SourceOfRandomness.getSource();
        return Stream.generate(() -> population.getChromosome(random.nextInt(size)))
                .limit(tournamentSize)
                .max(Chromosome::compareTo)
                .orElseThrow(IllegalStateException::new);
    }
}
