package eann.genetics.operators.util;

import eann.rand.SourceOfRandomness;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RouletteWheelContainer<E> extends ProbabilitySelector {

    private final List<E> elements;
    private final double[] rouletteWheel;

    public RouletteWheelContainer(List<DesirabilityPair<E>> operatorDesirabilityPairs) {
        var sortedPairs = operatorDesirabilityPairs.stream().sorted().collect(Collectors.toUnmodifiableList());
        elements = sortedPairs.stream().map(DesirabilityPair::element).collect(Collectors.toUnmodifiableList());
        double[] desirability = sortedPairs.stream().mapToDouble(DesirabilityPair::desirability).toArray();

        if (!isValid(desirability)) throw new InvalidParameterException();

        rouletteWheel = generateRouletteWheel(desirability);
    }

    protected E select() {
        return select(SourceOfRandomness.getSource());
    }

    protected E select(Random random) {
        return elements.get(find(rouletteWheel, random.nextDouble()));
    }
}
