package operator;

import model.Matrix;

public class MatrixCalculator {
    // OPERATION A
    public static float GetMultiplier(int i, int k, Matrix matrix){
        return matrix.getValue(k,i) / matrix.getValue(i,i);
    }

    // OPERATION B
    public static float GetMultiplication(int i, int j, float mutliplier, Matrix matrix){
        return matrix.getValue(i,j) * mutliplier;
    }

    // OPERATION C
    public static float GetSubstractionResult(int k, int j, float multiplication, Matrix matrix){
        return matrix.getValue(k,j) - multiplication;
    }
}
