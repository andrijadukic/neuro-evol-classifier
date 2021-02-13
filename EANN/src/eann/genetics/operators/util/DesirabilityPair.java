package eann.genetics.operators.util;

public record DesirabilityPair<T>(T element, double desirability) implements Comparable<DesirabilityPair<T>> {

    @Override
    public int compareTo(DesirabilityPair<T> o) {
        return Double.compare(desirability, o.desirability);
    }

    public static <T> DesirabilityPair<T> of(T element, double desirability) {
        return new DesirabilityPair<>(element, desirability);
    }
}
