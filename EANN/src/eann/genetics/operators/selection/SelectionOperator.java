package eann.genetics.operators.selection;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.population.Population;

@FunctionalInterface
public interface SelectionOperator {

    Chromosome select(Population population);

    default Chromosome[] select(Population population, int k) {
        Chromosome[] chromosomes = new Chromosome[k];
        for (int i = 0; i < k; i++) {
            chromosomes[i] = select(population);
        }
        return chromosomes;
    }
}
