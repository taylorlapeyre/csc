import java.util.*;
import java.math.*;

public class RandomTriangle {

  private Random rand = new Random();

  private int x1, y1,     // Coordinates
              x2, y2,
              x3, y3;
  private double a, b, c; // Sides

  public RandomTriangle(int limit) {
    do { // make sure that no points are on the same line
      x1 = rand.nextInt(limit);
      y1 = rand.nextInt(limit);

      x2 = rand.nextInt(limit);
      y2 = rand.nextInt(limit);

      x3 = rand.nextInt(limit);
      y3 = rand.nextInt(limit);
    } while ((x2 - x1) * (y3 - y1) == (y2 - y1) * (x3 - x1));
    
    a = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    b = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
    c = Math.sqrt(Math.pow((x1 - x3), 2) + Math.pow((y1 - y3), 2));
  }

  public String determineType() {
    return ((a == b) || (b == c) || (a == c)) ? "Isocoles" : "Scalene";
  }

  public int[] getXCoordinates() {
    return new int[] { x1, x2, x3 };
  }

  public int[] getYCoordinates() {
    return new int[] { y1, y2, y3 };
  }

  public String toString() {
    String result = "";
    result += "(" + x1 + ", ";
    result += y1 + "),  ";

    result += "(" + x2 + ", ";
    result += y2 + "),  ";
    
    result += "(" + x3 + ", ";
    result += y3 + ")   \n";
    
    result += Math.round(a * 100000) / 100000 + "\n"; // round the values of the sides
    result += Math.round(b * 100000) / 100000 + "\n";
    result += Math.round(c * 100000) / 100000 + "\n";

    result += this.determineType() + "\n";
    return result;
  }
}