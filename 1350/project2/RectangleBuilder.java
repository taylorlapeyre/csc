import java.awt.Rectangle;
import java.math.*;

/*
 * @file RectangleBuilder.java
 * @author Taylor Lapeyre
 * @date 2012/2/6
 * Description: Prints information about rectangles
 * Course: CS1350.s2
 * PAWS ID: 898658660
 * Project #: 2
 * Instructor: Duncan
 */

public class RectangleBuilder {
  public static void printInfo(Rectangle bigBox, Rectangle smallBox) {

    /* Declarations */
    int bigRightX = bigBox.x + bigBox.width;
    int bigBottomY = bigBox.y + bigBox.height;
    int smallRightX = smallBox.x + smallBox.width;
    int smallBottomY = smallBox.y + smallBox.height;
      // Area = Length x Width
    int bigArea = bigBox.height * bigBox.width;
    int smallArea = smallBox.height * smallBox.width;
    int areaOfPath = bigArea - smallArea;
    /* End Declarations */

    System.out.println();
    System.out.print("The coordinates of the bigger rectangle are ");
    System.out.print("{(" + bigBox.x + "," + bigBox.y + "),(" + bigRightX + "," + bigBox.y + "),(");
    System.out.print(bigRightX + "," + bigBottomY + "),(" + bigBox.x + "," + bigBottomY + ")}.");
    System.out.println();

    System.out.print("The coordinates of the smaller rectangle are ");
    System.out.print("{(" + smallBox.x + "," + smallBox.y + "),(" + smallRightX + "," + smallBox.y + "),(");
    System.out.print(smallRightX + "," + smallBottomY + "),(" + smallBox.x + "," + smallBottomY + ")}.");
    System.out.println();

    System.out.print("The area of the path is " + areaOfPath + ".");
    System.out.println();
    System.out.println();
  }


  public static void printDiagonal(Rectangle bigBox, Rectangle smallBox) {

    /* Declarations */
    double bigDiagonal = Math.sqrt(Math.pow(bigBox.width, 2)+Math.pow(bigBox.height, 2));
    double smallDiagonal = Math.sqrt(Math.pow(smallBox.width, 2)+Math.pow(smallBox.height, 2));
    /* End Declarations */

    System.out.println("The length of the diagonal of the bigger triangle is " + bigDiagonal + ".");
    System.out.println("The length of the diagonal of the smaller triangle is " + smallDiagonal + ".");
  }


  public static void main(String[] args) {

    /* Declarations */
    Rectangle bigBox = new Rectangle(60, 40, 120, 80);
    Rectangle smallBox = new Rectangle(70, 50, 100, 60);
    /* End Declarations */

    printInfo(bigBox, smallBox);

    bigBox.translate(10, -10);
    smallBox.translate(10, -10);
    System.out.println("After the rectangles are translated: ");
    printInfo(bigBox, smallBox);

    bigBox.setSize(bigBox.width + 10, bigBox.height + 20);
    smallBox.setSize(smallBox.width + 10, smallBox.height + 20);
    System.out.println("After the rectangles are resized: ");
    printInfo(bigBox, smallBox);

    printDiagonal(bigBox, smallBox);
  }
}
