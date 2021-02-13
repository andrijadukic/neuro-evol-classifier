package eann.genetics.operators.mutation.floatingpoint;

import eann.genetics.chromosomes.Chromosome;
import eann.genetics.chromosomes.floatingpoint.FloatingPointChromosome;
import eann.genetics.chromosomes.floatingpoint.FloatingPointGenotype;
import eann.genetics.exceptions.InvalidChromosomeTypeException;
import eann.genetics.operators.mutation.AbstractChromosomeMutation;

abstract class AbstractFloatingPointMutation extends AbstractChromosomeMutation<FloatingPointGenotype, FloatingPointChromosome> {

    @Override
    protected final FloatingPointChromosome typeCheck(Chromosome chromosome) {
        if (!(chromosome instanceof FloatingPointChromosome floatingPointChromosome))
            throw new InvalidChromosomeTypeException(FloatingPointChromosome.class, chromosome.getClass());

        return floatingPointChromosome;
    }
}
