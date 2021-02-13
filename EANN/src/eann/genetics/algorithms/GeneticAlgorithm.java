package eann.genetics.algorithms;

import eann.genetics.algorithms.conditions.StoppingCondition;
import eann.genetics.algorithms.util.GeneticAlgorithmSubject;
import eann.genetics.chromosomes.population.Population;

public interface GeneticAlgorithm extends GeneticAlgorithmSubject {

    Population run(Population initial, StoppingCondition condition);
}
