package eann.genetics.chromosomes;

import java.util.Objects;

public abstract class RawGenotype<R> implements Genotype {

    protected final R raw;

    protected RawGenotype(R raw) {
        this.raw = Objects.requireNonNull(raw);
    }

    public R getRaw() {
        return raw;
    }

    public abstract RawGenotype<R> newInstance(R raw);
}
