package com.geekbo.training.leetcode.daily;

import java.time.LocalDate;

/**
 *
 * 1154. Day of the Year
 * Easy
 * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD,
 * return the day number of the year.
 *
 *
 *
 * Example 1:
 *
 * Input: date = "2019-01-09"
 * Output: 9
 * Explanation: Given date is the 9th day of the year in 2019.
 * Example 2:
 *
 * Input: date = "2019-02-10"
 * Output: 41
 *
 *
 * Constraints:
 *
 * date.length == 10
 * date[4] == date[7] == '-', and all other date[i]'s are digits
 * date represents a calendar date between Jan 1st, 1900 and Dec 31th, 2019.
 *
 */
public class DayOfYear {
    /**
     * Calculates the day number of the year for a given date.
     *
     * @param date The date in the format "YYYY-MM-DD".
     * @return The day number of the year.
     */
    public static int dayOfYear(String date) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        int dayOfYear = day;
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += daysInMonth[i];
        }

        if (month > 2 && isLeapYear(year)) {
            dayOfYear += 1;
        }

        return dayOfYear;
    }

    /**
     * Checks if a year is a leap year.
     *
     * @param year The year to check.
     * @return True if the year is a leap year, false otherwise.
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(dayOfYear("2019-01-09")); // Output: 9
        System.out.println(dayOfYear("2019-02-10")); // Output: 41
    }
}