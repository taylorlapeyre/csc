import java.util.*;

/*
 * @file GregorianDateTester.java
 * @author Taylor Lapeyre
 * @date 2012/3/20
 * Description: Prints information using the Gregorian Calendar
 * Course: CS1350.s2
 * PAWS ID: tlapey1
 * Project #: 4
 * Instructor: Duncan
 */

public class GregorianDateTester {
    public static void main(String args[]) {
        System.out.print("Enter values for month, day and year of a date > ");

        Scanner keyb = new Scanner(System.in);
        int monthVal = keyb.nextInt();
        int dayVal = keyb.nextInt();
        int yearVal = keyb.nextInt();

        GregorianDate newDate = new GregorianDate(monthVal, dayVal, yearVal);

        System.out.println(newDate.toString());

    }
}