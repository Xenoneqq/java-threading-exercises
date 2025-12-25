package model;

public class Matrix {

    private int x;
    private int y;
    private float[][] matrix;

    double epsilon = 1e-6;


    public Matrix(int x, int y) {
        this.x = x;
        this.y = y;
        this.matrix = new float[x][y];
    }

    public void setValue(int x, int y, float value) {
        if (Math.abs(value) < epsilon) {
            this.matrix[x][y] = 0.0f;
        } else {
            this.matrix[x][y] = value;
        }
    }

    public float getValue(int x, int y) {
        return this.matrix[x][y];
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                result.append(matrix[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

}
