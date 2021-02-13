package eann.genetics.operators.crossover;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.Children;

@FunctionalInterface
public interface CrossoverOperator {

    Children crossover(Chromosome first, Chromosome second);
}
