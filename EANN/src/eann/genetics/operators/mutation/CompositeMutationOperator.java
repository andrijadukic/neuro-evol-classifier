package eann.genetics.operators.mutation;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.operators.util.RouletteWheelContainer;
import eann.genetics.operators.util.DesirabilityPair;

import java.util.List;

public class CompositeMutationOperator extends RouletteWheelContainer<MutationOperator> implements MutationOperator {

    public CompositeMutationOperator(List<DesirabilityPair<MutationOperator>> operatorDesirabilityPairs) {
        super(operatorDesirabilityPairs);
    }

    @Override
    public Chromosome mutate(Chromosome chromosome) {
        return select().mutate(chromosome);
    }
}
