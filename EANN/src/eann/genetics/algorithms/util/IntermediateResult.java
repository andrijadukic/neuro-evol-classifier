package eann.genetics.algorithms.util;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.population.Population;

public record IntermediateResult(int iteration, Chromosome fittest, Population current) {
}
