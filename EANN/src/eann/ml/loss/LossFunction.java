package eann.ml.loss;

import eann.ml.Predictor;
import eann.ml.sampling.Sample;

import java.util.List;

@FunctionalInterface
public interface LossFunction {

    double score(Predictor model, List<Sample> samples);
}
