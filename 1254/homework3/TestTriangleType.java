import java.util.*;
import java.io.*;

public class TestTriangleType {
  public static Scanner userInput = new Scanner(System.in);
  public static Random rand = new Random(0);

  public static class Point {
    private int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
    public String Print() { return "(" + this.x + ", " + this.y + ")"; }
    public int getX() { return this.x; }
    public int getY() { return this.y; }
  }

  public static void GenerateRandomTriangles(int numTriangles) throws FileNotFoundException {
    try {
      PrintWriter pw = new PrintWriter("RandomTriangles.dat");
      Point p1, p2, p3;
      pw.println(numTriangles +  " // numTriangles");

      for (int i = 0; i < numTriangles; i++) {
        // make the three non-linear points
        p1 = new Point(rand.nextInt(150), rand.nextInt(150));
        p2 = new Point(rand.nextInt(150), rand.nextInt(150));
        do { p3 = new Point(rand.nextInt(150), rand.nextInt(150)); }
        while ((p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) == (p2.getY() - p1.getY()) * (p3.getX() - p1.getX()));

        double a =
          Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
        double b =
          Math.sqrt(Math.pow(p3.getX() - p2.getX(), 2) + Math.pow(p3.getY() - p2.getY(), 2));
        double c =
          Math.sqrt(Math.pow(p1.getX() - p3.getX(), 2) + Math.pow(p1.getY() - p3.getY(), 2));

        // write the triangle's info to the file
        pw.printf("Triangle #%d\nCoordinates of Vertices:\n%s %s %s\nType of Triangle:\n%s\n",
            (i + 1), p1.Print(), p2.Print(),  p3.Print(), TriangleType(p1, p2, p3));
        pw.printf("Sides: \na = %.3f \nb = %.3f \nc = %.3f \n\n", a, b, c);
      }
      pw.close();
			
			
    } catch (FileNotFoundException e) { System.out.println("File not found!"); }
  }

  public static String TriangleType(Point p1, Point p2, Point p3) {
    double a =
			Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
    double b =
			Math.sqrt(Math.pow((p3.getX() - p2.getX()), 2) + Math.pow((p3.getY() - p2.getY()), 2));
    double c =
			Math.sqrt(Math.pow((p1.getX() - p3.getX()), 2) + Math.pow((p1.getY() - p3.getY()), 2));
    // Since we are using integers, Equalateral is impossible
    return ((a == b) || (b == c) || (a == c)) ? "Isocoles" : "Scalene";
  }

  private  static void TestTriangles() throws FileNotFoundException {
    try {
      Scanner file = new Scanner(new FileReader("RandomTriangles.dat"));
      GenerateRandomTriangles(5);

      // print the contents of the file
      while (file.hasNext()) { System.out.println(file.nextLine()); }

      // rerun the program if the user enters "yes"
      System.out.print("Would you like to generate more triangles? (yes/no) : ");
      if (userInput.next().equals("yes")) TestTriangles();
    } catch (FileNotFoundException e) { System.out.println("File not found!"); }
  }

  public static void main(String[] args) throws FileNotFoundException {
      TestTriangles();
  }
}