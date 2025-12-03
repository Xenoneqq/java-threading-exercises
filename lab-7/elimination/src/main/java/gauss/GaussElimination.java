package gauss;

import model.Matrix;
import operator.MatrixCalculator;

public class GaussElimination {
    public static void Gauss(Matrix matrix){
        for(int i = 0; i < matrix.getX()-1; i++){
            for(int k = i + 1; k < matrix.getX(); k++){
                float multiplier = MatrixCalculator.GetMultiplier(i, k, matrix);
                for(int j = i; j < matrix.getY(); j++){
                    float multiplication = MatrixCalculator.GetMultiplication(i,j,multiplier,matrix);
                    float substractionResult = MatrixCalculator.GetSubstractionResult(k,j,multiplication,matrix);
                    matrix.setValue(k,j, substractionResult);
                }
            }
        }
    }
}
