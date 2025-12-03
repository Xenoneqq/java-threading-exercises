import gauss.GaussElimination;
import model.Matrix;
import reader.MatrixReader;

public class Runner {


    public static void main(String[] args) {
        String path = "data/data_in.txt";
        Matrix matrix = MatrixReader.readMatrixFromFile(path);
        if(matrix == null) {return;}
        System.out.println("Initial matrix:");
        System.out.println(matrix.toString());
        System.out.println(" - === - === - === -\n");
        GaussElimination.Gauss(matrix);
        System.out.println("Matrix after Gauss elimination:");
        System.out.println(matrix.toString());
    }


}
