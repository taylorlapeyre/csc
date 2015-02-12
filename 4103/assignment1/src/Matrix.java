import java.util.Arrays;

/***
 * Lapeyre, Taylor
 * PA-1a(Multithreading)
 * MatrixMultiplication.java
 * cs4103-sp15
 * cs410310
 * */
public class Matrix {
    float[][] elements;

    public Matrix(float[][] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
