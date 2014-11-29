import java.math.*;

/**
* @file LineSegment.java
* @author Taylor Lapeyre
* @date 02/27/2012
* Description: Contains methods and constructors for the LineSegment object
* Course: CSC1350 Section 11
* pawsID: tlapey1
* Project #: 3
* Instructor: Duncan
*/


public class LineSegment {

    private double x1;
    private double x2;
    private double y1;
    private double y2;

    /**
    * Makes a new LineSegment object with null coordinates
    */
    public LineSegment() {
        double x1 = 0;
        double x2 = 0;
        double y1 = 0;
        double y2 = 0;
    }

    /**
    * Makes a new LineSegment object with the given coordinates
    */
    public LineSegment(double x1Val, double y1Val, double x2Val, double y2Val) {
        x1 = x1Val;
        x2 = x2Val;
        y1 = y1Val;
        y2 = y2Val;
    }

    /**
    * Returns the length of the given LineSegemnt
    */
    public double length() {
        double length = Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));
        return length;
    }

    /**
    * Sets a currrent LineSegment's coordinates to the given values
    */
    public void setLine(double x1Val, double y1Val, double x2Val, double y2Val) {
        x1 = x1Val;
        x2 = x2Val;
        y1 = y1Val;
        y2 = y2Val;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }
}