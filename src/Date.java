import java.util.Calendar;

/**
 * This class defines the properties of a Date object.
 * The class includes two date constructors, various private helper methods along with the isValid() method to
 * check whether the date is valid, getters to access instance variables, and a testbed main to test
 * the isValid() method thoroughly.
 * @author Isha Vora, Kathleen Eife
 */

public class Date {

    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;

    public static final int START_YEAR = 1900;
    public static final int FIRST_MONTH = 1;
    public static final int LAST_MONTH = 12;
    public static final int FIRST_DAY = 1;
    public static final int LAST_DAY_FEB_NON_LEAP_YEAR = 28;
    public static final int LAST_DAY_FEB_LEAP_YEAR = 29;
    public static final int LAST_DAY_SHORT_MONTH = 30;
    public static final int LAST_DAY_LONG_MONTH = 31;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    /**
     * This constructor takes in a date string and creates a date object.
     * @param date a string in the form of mm/dd/yyyy
     */
    public Date(String date) {   //taking mm/dd/yyyy and create a Date object
        String [] dateParts = date.split("/");
        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * This constructor creates a date object with today's date.
     */
    public Date() {   //Creates an object with today's date
        int currMonth = (Calendar.getInstance().get(Calendar.MONTH)) + 1; //one added to get the numeric value of the month
        int currDay = Calendar.getInstance().get(Calendar.DATE);
        int currYear = Calendar.getInstance().get(Calendar.YEAR);

        this.month = currMonth;
        this.day = currDay;
        this.year = currYear;
    }

    /**
     * This method checks if the year is a leap year.
     * @return true if year is a leap year, false otherwise
     */
    private boolean isLeapYear() {
        if (year%QUADRENNIAL == 0) {
            if (year%CENTENNIAL == 0) {
                if (year%QUARTERCENTENNIAL == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * This method checks if the date entered by the user past the current date.
     * @return true if date entered by the user past the current date, false otherwise
     */
    private boolean isPastCurrDate() {
        Calendar calToday = Calendar.getInstance();
        Calendar calInput = Calendar.getInstance();
        calToday.set(calToday.get(Calendar.YEAR), calToday.get(Calendar.MONTH), calToday.get(Calendar.DATE), 0, 0, 0);
        calInput.set(year, month - 1, day, 0, 0, 0);

        int result = calInput.compareTo(calToday);

        if (result == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * This method checks if the date entered by the user is valid.
     * @return true if date is valid, false otherwise
     */
    public boolean isValid() {
        if (year < START_YEAR) {
            return false;
        }

        if (isPastCurrDate()) {
            return false;
        }

        if (month < FIRST_MONTH || month > LAST_MONTH) {
            return false;
        }

        if (day < FIRST_DAY || day > LAST_DAY_LONG_MONTH) {
            return false;
        }

        if (month == JANUARY || month == MARCH || month == MAY || month == JULY || month == AUGUST
                || month == OCTOBER || month == DECEMBER) {
            if (day < FIRST_DAY || day > LAST_DAY_LONG_MONTH) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER) {
            if (day < FIRST_DAY || day > LAST_DAY_SHORT_MONTH) {
                return false;
            }
            else {
                return true;
            }
        }
        else if (month == FEBRUARY) {
            if (isLeapYear()) {
                if (day < FIRST_DAY || day > LAST_DAY_FEB_LEAP_YEAR) {
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                if (day < FIRST_DAY || day > LAST_DAY_FEB_NON_LEAP_YEAR) {
                    return false;
                }
                else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This getter method returns the string form of the date.
     * @return dateString the string form of the date
     */
    public String getDate() {
        String dateString = (month + "/" + day + "/" + year);
        return dateString;
    }

    /**
     * This getter method returns the month that the book was published.
     * @return month the month that the book was published
     */
    public int getMonth() {
        return month;
    }

    /**
     * This getter method returns the year that the book was published.
     * @return year the year that the book was published
     */
    public int getYear() {
        return year;
    }

    /**
     * This getter method returns the day that the book was published.
     * @return day the day that the book was published
     */
    public int getDay() {
        return day;
    }

    /**
     * This is a testbed main to exercise the methods in the Date class.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        //testing the isValid() method
        //test case #1, entering an invalid month component
        System.out.println("running test case #1");
        Date test1 = new Date("31/2/2000");
        boolean isDateValid1 = test1.isValid();
        if (!isDateValid1) {
            System.out.println("test case #1, entering an invalid month component: 31/2/2000. PASSED.");
        }
        else {
            System.out.println("test case #1, entering an invalid month component: 31/2/2000. FAILED.");
        }

        //testing the isValid() method
        //test case #2, entering an invalid month component
        System.out.println("running test case #2");
        Date test2 = new Date("13/2/2020");
        boolean isDateValid2 = test2.isValid();
        if (!isDateValid2) {
            System.out.println("test case #2, entering an invalid month component: 13/2/2020. PASSED.");
        }
        else {
            System.out.println("test case #2, entering an invalid month component: 13/2/2020. FAILED.");
        }

        //testing the isValid() method
        //test case #3, entering an invalid day component
        System.out.println("running test case #3");
        Date test3 = new Date("2/29/2021");
        boolean isDateValid3 = test3.isValid();
        if (!isDateValid3) {
            System.out.println("test case #3, entering an invalid day component: 2/29/2021. PASSED.");
        }
        else {
            System.out.println("test case #3, entering an invalid day component: 2/29/2021. FAILED.");
        }

        //testing the isValid() method
        //test case #4, entering an invalid day component
        System.out.println("running test case #4");
        Date test4 = new Date("2/29/2009");
        boolean isDateValid4 = test4.isValid();
        if (!isDateValid4) {
            System.out.println("test case #4, entering an invalid day component: 2/29/2009. PASSED.");
        }
        else {
            System.out.println("test case #4, entering an invalid day component: 2/29/2009. FAILED.");
        }

        //testing the isValid() method
        //test case #5, entering an invalid day component
        System.out.println("running test case #5");
        Date test5 = new Date("4/31/2009");
        boolean isDateValid5 = test5.isValid();
        if (!isDateValid5) {
            System.out.println("test case #5, entering an invalid day component: 4/31/2009. PASSED.");
        }
        else {
            System.out.println("test case #5, entering an invalid day component: 4/31/2009. FAILED.");
        }

        //testing the isValid() method
        //test case #6, entering an invalid day component
        System.out.println("running test case #6");
        Date test6 = new Date("3/32/2009");
        boolean isDateValid6 = test6.isValid();
        if (!isDateValid6) {
            System.out.println("test case #6, entering an invalid day component: 3/32/2009. PASSED.");
        }
        else {
            System.out.println("test case #6, entering an invalid day component: 3/32/2009. FAILED.");
        }

        //testing the isValid() method
        //test case #7, entering an invalid year component
        System.out.println("running test case #7");
        Date test7 = new Date("3/31/1800");
        boolean isDateValid7 = test7.isValid();
        if (!isDateValid7) {
            System.out.println("test case #7, entering an invalid year component: 3/31/1800. PASSED.");
        }
        else {
            System.out.println("test case #7, entering an invalid year component: 3/31/1800. FAILED.");
        }

        //testing the isValid() method
        //test case #8, entering an invalid date, past the current date
        System.out.println("running test case #8");
        Date test8 = new Date("10/30/2022");
        boolean isDateValid8 = test8.isValid();
        if (!isDateValid8) {
            System.out.println("test case #8, entering an invalid date, past the current date: 10/30/2022. PASSED.");
        }
        else {
            System.out.println("test case #8, entering an invalid date, past the current date: 10/30/2022. FAILED.");
        }

        //testing the isValid() method
        //test case #9, entering an invalid date, past the current date
        System.out.println("running test case #9");
        Date test9 = new Date("3/30/2021");
        boolean isDateValid9 = test9.isValid();
        if (!isDateValid9) {
            System.out.println("test case #9, entering an invalid date, past the current date: 3/30/2021. PASSED.");
        }
        else {
            System.out.println("test case #9, entering an invalid date, past the current date: 3/30/2021. FAILED.");
        }

        //testing the isValid() method
        //test case #10, entering an invalid month component
        System.out.println("running test case #10");
        Date test10 = new Date("0/12/2019");
        boolean isDateValid10 = test10.isValid();
        if (!isDateValid10) {
            System.out.println("test case #10, entering an invalid month component: 0/12/2019. PASSED.");
        }
        else {
            System.out.println("test case #10, entering an invalid month component: 0/12/2019. FAILED.");
        }

        //testing the isValid() method
        //test case #11, entering an invalid month component
        System.out.println("running test case #11");
        Date test11 = new Date("-5/20/2000");
        boolean isDateValid11 = test11.isValid();
        if (!isDateValid11) {
            System.out.println("test case #11, entering an invalid month component: -5/20/2000. PASSED.");
        }
        else {
            System.out.println("test case #11, entering an invalid month component: -5/20/2000. FAILED.");
        }

        //testing the isValid() method
        //test case #12, entering an invalid day component
        System.out.println("running test case #12");
        Date test12 = new Date("2/30/2020");
        boolean isDateValid12 = test12.isValid();
        if (!isDateValid12) {
            System.out.println("test case #12, entering an invalid day component: 2/30/2020. PASSED.");
        }
        else {
            System.out.println("test case #12, entering an invalid day component: 2/30/2020. FAILED.");
        }

        //testing the isValid() method
        //test case #13, entering an invalid day component
        System.out.println("running test case #13");
        Date test13 = new Date("4/0/2012");
        boolean isDateValid13 = test13.isValid();
        if (!isDateValid13) {
            System.out.println("test case #13, entering an invalid day component: 4/0/2012. PASSED.");
        }
        else {
            System.out.println("test case #13, entering an invalid day component: 4/0/2012. FAILED.");
        }

        //testing the isValid() method
        //test case #14, entering an invalid day component
        System.out.println("running test case #14");
        Date test14 = new Date("3/-20/2014");
        boolean isDateValid14 = test14.isValid();
        if (!isDateValid14) {
            System.out.println("test case #14, entering an invalid day component: 3/-20/2014. PASSED.");
        }
        else {
            System.out.println("test case #14, entering an invalid day component: 3/-20/2014. FAILED.");
        }

        //testing the isValid() method
        //test case #15, entering an invalid year component
        System.out.println("running test case #15");
        Date test15 = new Date("12/31/1899");
        boolean isDateValid15 = test15.isValid();
        if (!isDateValid15) {
            System.out.println("test case #15, entering an invalid year component: 12/31/1899. PASSED.");
        }
        else {
            System.out.println("test case #15, entering an invalid year component: 12/31/1899. FAILED.");
        }

        //testing the isValid() method
        //test case #16, entering aa valid year component
        System.out.println("running test case #16");
        Date test16 = new Date("1/1/1900");
        boolean isDateValid16 = test16.isValid();
        if (isDateValid16) {
            System.out.println("test case #16, entering a valid year component: 1/1/1900. PASSED.");
        }
        else {
            System.out.println("test case #16, entering a valid year component: 1/1/1900. FAILED.");
        }
    }
}
