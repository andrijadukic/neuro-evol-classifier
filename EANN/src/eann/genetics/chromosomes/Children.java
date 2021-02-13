package eann.genetics.chromosomes;

import eann.genetics.chromosomes.util.Pair;

import java.util.Optional;

public final class Children extends Pair<Chromosome, Optional<Chromosome>> {

    public Children(Chromosome child) {
        super(child, Optional.empty());
    }

    public Children(Chromosome first, Chromosome second) {
        super(first, Optional.of(second));
    }

}
