/*
 * @file GregorianDate.java
 * @author Taylor Lapeyre
 * @date 2012/3/20
 * Description: Prints information using the Gregorian Calendar
 * Course: CS1350.s2
 * PAWS ID: tlapey1
 * Project #: 4
 * Instructor: Duncan
 */

public class GregorianDate {

    private int day;
    private int year;
    private int month;

    /**
     * Constructs a date object that represents January 1, 1970
     */
    public GregorianDate() {
        day = 1;
        month = 1;
        year = 1970;
    }

    /**
     * Constructs a parameterized date object
     * @param mm - The given month
     * @param dd - The given day
     * @param yy - The given year
     */
    public GregorianDate(int mm, int dd, int yy) throws IllegalArgumentException {
        if (mm < 1 || mm > 12)
            throw new IllegalArgumentException("Month must be between 1 and 12");
        if (dd < 1 || dd > 31)
            throw new IllegalArgumentException("Date must be between 1 and 31");
        if (yy < 1583 || yy < 0)
            throw new IllegalArgumentException("Invalid year");
        if ((mm == 9 || mm == 4 || mm == 6 || mm == 11) && dd == 31)
            throw new IllegalArgumentException("Invlaid date");
        if (mm == 2 && isLeap(yy) && dd > 29) 
            throw new IllegalArgumentException("Invalid date");
        if (mm == 2 && !isLeap(yy) && dd > 28)
            throw new IllegalArgumentException("Invalid date");

        day = dd;
        month = mm;
        year = yy;
    }

    /**
     * Determines whether the given year is a leap year
     * @param testYear - The year to be tested
     */
    public boolean isLeap(int testYear) {
        if (testYear % 400 == 0)
            return true;
        else if (testYear % 100 != 0 && testYear % 4 == 0)
            return true;
        else
            return false;
    }

    /**
     * Gives a number representing the day of the week of this date
     * @param dayNum - The given day
     */
    public String weekdayToString(int dayNum) throws IllegalArgumentException {
        switch(dayNum) {
            case 0: return "Sunday";
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";

            default: throw new IllegalArgumentException("Invalid Day");
        }
    }

    /**
     * Gives the month in words
     * @param monthNum - The given month
     */
    public String monthToString(int monthNum) throws IllegalArgumentException {
        switch(monthNum) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";    
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December"; 

            default: throw new IllegalArgumentException("Invalid Month");
        }
    }

    /**
     * Gives a number representing the day of the week of this date
     */
    public int dayOfWeek() {
        int x;

        switch(month) {
            case 1: x = isLeap(year) ? 6 : 0; break;
            case 2: x = isLeap(year) ? 2 : 3; break;
            case 3: x = 3; break;
            case 4: x = 6; break;
            case 5: x = 1; break;
            case 6: x = 4; break;
            case 7: x = 6; break;
            case 8: x = 2; break;
            case 9: x = 5; break;
            case 10: x = 0; break;
            case 11: x = 3; break;
            case 12: x = 5; break;
            default: x = 0; break;
        }

        int u = 2*(3-(year/100)%4);
        int v = year%100;
        int w = v/4;
        int y = u+v+w+x+day;
        int dayOfWeek = y%7;

        return dayOfWeek;
    }

    /**
     * Gives a string representation of this object
     */
    public String toString() {
        String ending = isLeap(year) ? "leap year" : "non-leap year"; 

        String date = 
            weekdayToString(dayOfWeek()) + ", " +
            monthToString(month) + " " +
            day + ", " +
            year + " occurred in a " +
            ending + ".";

        return date;
    }
}
