package eann.genetics.exceptions;

public class InsufficientPopulationSIze extends RuntimeException {

    public InsufficientPopulationSIze(int populationSize, int lowerBound) {
        super("Population not of sufficient size. Population size: " + populationSize + ", need to be at least: " + lowerBound);
    }
}
