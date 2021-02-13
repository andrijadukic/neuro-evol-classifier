package eann.genetics.operators.mutation;

import eann.genetics.chromosomes.AbstractChromosome;
import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.Genotype;

public abstract class AbstractChromosomeMutation<G extends Genotype, C extends AbstractChromosome<G>> implements MutationOperator {

    @Override
    public final Chromosome mutate(Chromosome chromosome) {
        C original = typeCheck(chromosome);
        G originalGenotype = original.getGenotype();
        return original.newInstance(mutate(originalGenotype));
    }

    protected abstract C typeCheck(Chromosome chromosome);

    protected abstract G mutate(G genotype);
}
