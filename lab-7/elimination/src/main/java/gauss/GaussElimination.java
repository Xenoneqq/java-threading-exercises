package gauss;

import model.Matrix;
import operator.MatrixCalculator;

public class GaussElimination {
    public static void Gauss(Matrix matrix){
        Thread[] rowThreads = new Thread[matrix.getX()];
        for(int i = 0; i < matrix.getX()-1; i++){
            for(int k = i + 1; k < matrix.getX(); k++){
                RawCalculation calc = new RawCalculation(matrix, i , k);
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

}
