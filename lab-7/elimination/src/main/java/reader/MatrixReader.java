package reader;

import model.Matrix;
import java.nio.file.*;

public class MatrixReader {
    public static Matrix readMatrix(String input) {
        String[] lines = input.trim().split("\n");
        String[] dimensions = lines[0].trim().split(" ");
        String sizeXText = dimensions[0];
        int size = Integer.parseInt(sizeXText);

        Matrix matrix = new Matrix(size, size+1);

        for (int i = 0; i < matrix.getY(); i++) {
            String[] valuesText = lines[i + 1].trim().split(" ");
            for (int j = 0; j < matrix.getX(); j++) {
                String valueText = valuesText[j];
                float value = Float.parseFloat(valueText);
                matrix.setValue(j, i, value);
            }
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
