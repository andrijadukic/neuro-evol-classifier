package eann.genetics.chromosomes.floatingpoint;

import eann.genetics.chromosomes.RawGenotype;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FloatingPointGenotype extends RawGenotype<double[]> {

    public FloatingPointGenotype(double[] raw) {
        super(raw);
    }

    @Override
    public FloatingPointGenotype copy() {
        double[] rawCopy = new double[raw.length];
        System.arraycopy(raw, 0, rawCopy, 0, rawCopy.length);
        return new FloatingPointGenotype(rawCopy);
    }

    @Override
    public FloatingPointGenotype newInstance(double[] raw) {
        return new FloatingPointGenotype(raw);
    }

    @Override
    public String toString() {
        return Arrays.stream(raw).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(raw);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatingPointGenotype that = (FloatingPointGenotype) o;
        return Arrays.equals(raw, that.raw);
    }
}
