package eann.genetics.operators.crossover;

import eann.genetics.chromosomes.AbstractChromosome;
import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.Genotype;
import eann.genetics.chromosomes.Children;
import eann.genetics.chromosomes.util.Pair;

import java.util.Optional;

public abstract class AbstractChromosomeCrossover<G extends Genotype, C extends AbstractChromosome<G>> implements CrossoverOperator {

    @Override
    public final Children crossover(Chromosome first, Chromosome second) {
        Pair<C, C> parents = typeCheck(first, second);

        C p1 = parents.first();
        C p2 = parents.second();

        G pg1 = p1.getGenotype();
        G pg2 = p2.getGenotype();

        Pair<G, Optional<G>> children = mate(pg1, pg2);

        G cg1 = children.first();
        Optional<G> cg2 = children.second();

        return cg2.map(g -> new Children(p1.newInstance(cg1), p2.newInstance(g))).orElseGet(() -> new Children(p1.newInstance(cg1)));
    }

    protected abstract Pair<C, C> typeCheck(Chromosome first, Chromosome second);

    protected abstract Pair<G, Optional<G>> mate(G first, G second);
}
