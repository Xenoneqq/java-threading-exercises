package reader;

import model.Matrix;
import java.nio.file.*;

public class MatrixReader {
    public static Matrix readMatrix(String input) {
        String[] lines = input.trim().split("\\s*\\n\\s*");
        int size = Integer.parseInt(lines[0].trim());
        Matrix matrix = new Matrix(size, size + 1);
        for (int i = 0; i < size; i++) {
            String[] valuesText = lines[i + 1].trim().split("\\s+");
            for (int j = 0; j < size; j++) {
                matrix.setValue(i, j, Float.parseFloat(valuesText[j]));
            }
        }
        String[] resultsText = lines[size + 1].trim().split("\\s+");
        for (int i = 0; i < size; i++) {
            matrix.setValue(i, size, Float.parseFloat(resultsText[i]));
        }

        return matrix;
    }

    public static Matrix readMatrixFromFile(String filePath) {
        try {
            var lines = Files.readAllLines(Path.of(filePath));
            StringBuilder inputBuilder = new StringBuilder();
            for (String line : lines) {
                inputBuilder.append(line).append("\n");
            }
            return readMatrix(inputBuilder.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
