package eann.genetics.operators.util;

public class ProbabilitySelector {

    protected boolean isValid(double[] desirability) {
        for (double v : desirability) {
            if (v < 0) return false;
        }
        return true;
    }

    protected double[] generateRouletteWheel(double[] desirability) {
        cumulativeSum(desirability, getBias(desirability));
        scale(desirability, desirability[desirability.length - 1]);
        return desirability;
    }

    protected double getBias(double[] desirability) {
        return 0.;
    }

    protected void cumulativeSum(double[] array, double bias) {
        double sum = 0.;
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] = (sum += array[i] + bias);
        }
    }

    protected void scale(double[] array, double factor) {
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] /= factor;
        }
    }

    protected int find(double[] rouletteWheel, double selection) {
        int l = 0;
        int r = rouletteWheel.length;
        while (r > l) {
            int mid = (l + r) / 2;
            if (mid == 0 || (rouletteWheel[mid] >= selection && rouletteWheel[mid - 1] < selection)) {
                return mid;
            } else if (rouletteWheel[mid] <= selection) {
                l = mid + 1;
            } else if (rouletteWheel[mid] > selection) {
                r = mid;
            }
        }

        throw new IllegalStateException();
    }
}
