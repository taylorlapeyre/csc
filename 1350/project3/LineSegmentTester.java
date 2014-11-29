import java.util.Scanner;
import java.math.*;

/**
* @file LineSegmentTester.java
* @author Taylor Lapeyre
* @date 02/27/2012
* Description: Asks for the coordinates of three line segments and prints information about them
* Course: CSC1350 Section 11
* pawsID: tlapey1
* Project #: 3
* Instructor: Duncan
*/


public class LineSegmentTester {

    /**
    * Prints the information of the triangle's sides
    */
    public static void printInfo(LineSegment lsOne, LineSegment lsTwo, LineSegment lsThree) {

        /* Declarations */

        // Perimeter is the sum of the three sides
        double perimeter = lsOne.length() + lsTwo.length() + lsThree.length();
        // Heron's Formula
        double s = perimeter / 2;
        double area = Math.sqrt(s*(s-lsOne.length())*(s-lsTwo.length())*(s-lsThree.length()));

        /* End Declarations */

        System.out.print("line 1:[(" + lsOne.getX1() + "," + lsOne.getY1());
        System.out.print(")->(" + lsOne.getX2() + "," + lsOne.getY2() + ")]");
        System.out.println();

        System.out.print("line 1:[(" + lsTwo.getX1() + "," + lsTwo.getY1());
        System.out.print(")->(" + lsTwo.getX2() + "," + lsTwo.getY2() + ")]");
        System.out.println();

        System.out.print("line 1:[(" + lsThree.getX1() + "," + lsThree.getY1());
        System.out.print(")->(" + lsThree.getX2() + "," + lsThree.getY2() + ")]");
        System.out.println();
        System.out.println();

        System.out.println("Perimeter = " + perimeter);
        System.out.println("Area = " + area);
    }

    public static void main(String[] args) {

        /* Declarations */

        Scanner keyb = new Scanner(System.in);

        System.out.print("Enter the X and Y coordinates for the first point: ");
        double firstX = keyb.nextDouble();
        double firstY = keyb.nextDouble();

        System.out.print("Enter the X and Y coordinates for the second point: ");
        double secondX = keyb.nextDouble();
        double secondY = keyb.nextDouble();

        System.out.print("Enter the X and Y coordinates for the third point: ");
        double thirdX = keyb.nextDouble();
        double thirdY = keyb.nextDouble();

        LineSegment lsOne = new LineSegment(firstX, firstY, secondX, secondY);
        LineSegment lsTwo = new LineSegment(secondX, secondY, thirdX, thirdY);
        LineSegment lsThree = new LineSegment(thirdX, thirdY, firstX, firstY);

        /* End Declarations */

        System.out.println();
        System.out.println("BEFORE TRANSFORMING: ");
        System.out.println();

        printInfo(lsOne, lsTwo, lsThree);

        lsOne.setLine(lsOne.getX1(), lsOne.getY1(), lsOne.getX2() + 5, lsOne.getY2() + 10);
        lsTwo.setLine(lsTwo.getX1() + 5, lsTwo.getY1() + 10, lsTwo.getX2() - 5, lsTwo.getY2() + 10);
        lsThree.setLine(lsThree.getX1() - 5, lsThree.getY1() + 10, lsThree.getX2(), lsThree.getY2());

        System.out.println();
        System.out.println("AFTER TRANSFORMING: ");
        System.out.println();

        printInfo(lsOne, lsTwo, lsThree);
    }
}