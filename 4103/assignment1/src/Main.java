/***
 * Lapeyre, Taylor
 * PA-1a(Multithreading)
 * Main.java
 * cs4103-sp15
 * cs410310
 * */
public class Main {
    public static void main(String[] args) {

        float[][] aElements = new float[][]{{1,4},{2,5},{3,6}};
        float[][] bElements = new float[][]{{8,7,6},{5,4,3}};
        float[][] resultArray = new float[aElements.length][bElements[0].length];

        Matrix a = new Matrix(aElements);
        Matrix b = new Matrix(bElements);
        Matrix result = new Matrix(resultArray);

        for (int i = 0; i < aElements.length; i++) {
            for (int j = 0; j < bElements[0].length; j++) {
                Thread t = new Thread(new MatrixMultiplication(i, j, a, b, result));
                t.start();
            }
        }

        System.out.println(result);

    }
}
