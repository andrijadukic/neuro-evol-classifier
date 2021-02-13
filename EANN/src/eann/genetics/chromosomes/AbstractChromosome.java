package eann.genetics.chromosomes;

import java.util.Objects;

public abstract class AbstractChromosome<G extends Genotype> implements Chromosome {

    private static final double DEFAULT_FITNESS = Double.NEGATIVE_INFINITY;

    protected final G genotype;
    protected double fitness;

    protected AbstractChromosome(G genotype) {
        this.genotype = Objects.requireNonNull(genotype);
        fitness = DEFAULT_FITNESS;
    }

    public G getGenotype() {
        return genotype;
    }

    public abstract AbstractChromosome<G> newInstance(G genotype);

    @Override
    public double getFitness() {
        if (fitness == DEFAULT_FITNESS) {
            fitness = evaluate();
        }
        return fitness;
    }

    protected abstract double evaluate();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{genotype={" + genotype.toString() + "}}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractChromosome<?> that = (AbstractChromosome<?>) o;
        return genotype.equals(that.genotype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genotype);
    }
}
