package gauss;

import model.Matrix;

public class GaussElimination {
    public static void Gauss(Matrix matrix){
        Thread[] rowThreads = new Thread[matrix.getX()];
        for(int i = 0; i < matrix.getX()-1; i++){
            for(int k = i + 1; k < matrix.getX(); k++){
                RowCalculation calc = new RowCalculation(matrix, i , k);
                calc.start();
                rowThreads[k] = calc;
            }

            for (int k = i + 1; k < matrix.getX(); k++){
                try {
                    rowThreads[k].join();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    public static void BackwardElimination(Matrix matrix) {
        int n = matrix.getX();
        for (int i = n - 1; i >= 0; i--) {
            float pivot = matrix.getValue(i, i);
            if (Math.abs(pivot) < 1e-10) continue;
            for (int k = i - 1; k >= 0; k--) {
                float multiplier = matrix.getValue(k, i) / pivot;
                for (int j = i; j < matrix.getY(); j++) {
                    float value = matrix.getValue(k, j) - multiplier * matrix.getValue(i, j);
                    matrix.setValue(k, j, value);
                }
            }
        }
    }

    public static void Normalize(Matrix matrix) {
        int n = matrix.getX();
        for (int i = 0; i < n; i++) {
            float diag = matrix.getValue(i, i);
            if (Math.abs(diag) > 1e-10) {
                for (int j = i; j < matrix.getY(); j++) {
                    matrix.setValue(i, j, matrix.getValue(i, j) / diag);
                }
            }
        }
    }
}