package eann.genetics.operators.selection;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.population.Population;
import eann.genetics.exceptions.InsufficientPopulationSIze;
import eann.genetics.operators.util.ProbabilitySelector;
import eann.rand.SourceOfRandomness;

import java.util.*;

public class RouletteWheelSelection extends ProbabilitySelector implements SelectionOperator {

    @Override
    public Chromosome select(Population population) {
        return select(population, 1)[0];
    }

    @Override
    public Chromosome[] select(Population population, int k) {
        int size = population.size();

        if (size < k) throw new InsufficientPopulationSIze(population.size(), k);

        Random random = SourceOfRandomness.getSource();

        List<Chromosome> chromosomes = new ArrayList<>(population.getChromosomes());
        chromosomes.sort(Chromosome::compareTo);
        double[] rouletteWheel = generateRouletteWheel(chromosomes.stream().mapToDouble(Chromosome::getFitness).toArray());
        Chromosome[] selected = new Chromosome[k];
        int prev = -1;
        for (int i = 0; i < k; i++) {
            int rind = find(rouletteWheel, random.nextDouble());

            if (i % 2 == 0) {
                prev = -1;
            }

            while (prev == rind) {
                rind = find(rouletteWheel, random.nextDouble());
            }

            selected[i] = chromosomes.get(rind);
            prev = rind;
        }
        return selected;
    }

    @Override
    protected double getBias(double[] desirability) {
        return desirability[0];
    }
}
