package studentroster;

import org.junit.Test;

import static org.junit.Assert.*;


public class DateTest {

    @Test
    public void testIsValid() {

        // Test 1
        // valid date
        Date test1 = new Date("08/31/2021");
        assertTrue(test1.isValid());

        // Test 2
        //valid date with Calendar
        Date test2 = new Date();
        assertTrue(test2.isValid());

        // Test 3
        // Year less than 2021
        Date test3 = new Date("08/31/2020");
        assertFalse(test3.isValid());

        // Test 4
        // Invalid month (<= 0)
        Date test4 = new Date("0/12/2021");
        assertFalse(test4.isValid());

        // Test 5
        // Invalid month (> 12)
        Date test5 = new Date("13/31/2021");
        assertFalse(test5.isValid());

        // Test 6
        // Invalid day (<= 0)
        Date test6 = new Date("08/0/2021");
        assertFalse(test6.isValid());

        //Test 7
        // Invalid day (> 31)
        Date test7 = new Date("08/32/2021");
        assertFalse(test7.isValid());

        //Test 8
        // Invalid date (> Current Calendar Date)
        Date test8 = new Date("08/31/2025");
        assertFalse(test8.isValid());

        //Test 9
        // Invalid Feb date (>28 on non-Leap Year)
        Date test9 = new Date("02/29/2021");
        assertFalse(test9.isValid());

        //Test 10
        // Invalid date (31 days in April)
        Date test10 = new Date("04/31/2021");
        assertFalse(test10.isValid());
    }
}