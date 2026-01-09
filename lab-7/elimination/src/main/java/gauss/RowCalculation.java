package gauss;

import model.Matrix;
import operator.MatrixCalculator;

public class RowCalculation extends Thread{

    private Matrix matrix;
    private int i, k;

    public RowCalculation(Matrix matrix, int i, int k) {
        this.matrix = matrix;
        this.i = i;
        this.k = k;
    }

    @Override
    public void run(){
        float multiplier = MatrixCalculator.GetMultiplier(i, k, matrix);
        for(int j = i; j < matrix.getY(); j++){
            float multiplication = MatrixCalculator.GetMultiplication(i,j,multiplier,matrix);
            float substractionResult = MatrixCalculator.GetSubstractionResult(k,j,multiplication,matrix);
            matrix.setValue(k,j, substractionResult);
        }
    }
}
