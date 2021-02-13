package eann.ml.exceptions;

import eann.ml.MachineLearningModel;

public class ModelNotFittedException extends RuntimeException {

    public ModelNotFittedException(Class<? extends MachineLearningModel> modelClass) {
        super("This instance of " + modelClass.getSimpleName() + " has not been fitted yet");
    }
}
