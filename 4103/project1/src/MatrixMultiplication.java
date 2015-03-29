/***
 * Lapeyre, Taylor
 * PA-1a(Multithreading)
 * Matrix.java
 * cs4103-sp15
 * cs410310
 * */
public class MatrixMultiplication implements Runnable {
    int row;
    int col;
    Matrix a;
    Matrix b;
    Matrix result;

    public MatrixMultiplication(int row, int col, Matrix a, Matrix b, Matrix result) {
        this.row    = row;
        this.col    = col;
        this.a      = a;
        this.b      = b;
        this.result = result;
    }

    public void run() {
        float sum = 0;
        for (int i = 0; i < a.elements[0].length; i++) {
            sum += a.elements[row][i] * b.elements[i][col];
        }
        result.elements[row][col] = sum;
    }
}
