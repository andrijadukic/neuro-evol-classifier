package eann.genetics.operators.crossover;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.Children;
import eann.genetics.operators.util.DesirabilityPair;
import eann.genetics.operators.util.RouletteWheelContainer;

import java.util.List;

public class CompositeCrossoverOperator extends RouletteWheelContainer<CrossoverOperator> implements CrossoverOperator {

    public CompositeCrossoverOperator(List<DesirabilityPair<CrossoverOperator>> operatorDesirabilityPairs) {
        super(operatorDesirabilityPairs);
    }

    @Override
    public Children crossover(Chromosome first, Chromosome second) {
        return select().crossover(first, second);
    }
}
