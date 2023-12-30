package com.geekbo.training.leetcode.daily;

import java.time.LocalDate;

/**
 * 1185. Day of the Week
 * Easy
 * Given a date, return the corresponding day of the week for that date.
 * <p>
 * The input is given as three integers representing the day, month and year respectively.
 * <p>
 * Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: day = 31, month = 8, year = 2019
 * Output: "Saturday"
 * Example 2:
 * <p>
 * Input: day = 18, month = 7, year = 1999
 * Output: "Sunday"
 * Example 3:
 * <p>
 * Input: day = 15, month = 8, year = 1993
 * Output: "Sunday"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The given dates are valid dates between the years 1971 and 2100.
 */
public class DayOfTheWeek {
    public static void main(String[] args) {
        // Test case 1
        int day1 = 31;
        int month1 = 8;
        int year1 = 2019;
        String result1 = dayOfTheWeek(day1, month1, year1);
        System.out.println("Test case 1: " + result1);

        // Test case 2
        int day2 = 18;
        int month2 = 7;
        int year2 = 1999;
        String result2 = dayOfTheWeek(day2, month2, year2);
        System.out.println("Test case 2: " + result2);

        // Test case 3
        int day3 = 15;
        int month3 = 8;
        int year3 = 1993;
        String result3 = dayOfTheWeek(day3, month3, year3);
        System.out.println("Test case 3: " + result3);
    }

    /**
     * Returns the corresponding day of the week for the given date.
     * 解题思路：
     * <p>
     * 首先，定义一个数组t[]，用于存储每个月的偏移量，这个偏移量与公式中的月份相关。
     * 如果给定的月份小于3，即1月或2月，将年份减1，这是因为在这个公式中，1月和2月被视为前一年的13月和14月。
     * 根据给定的公式 (year + year / 4 - year / 100 + year / 400 + t[month - 1] + day) % 7，计算出一个整数值 n。
     * 将整数值 n 作为索引，从字符串数组 arr 中获取对应的星期几。
     * 返回星期几。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(1)，因为只进行了一次计算和一次数组访问操作。
     * 空间复杂度：O(1)，因为只使用了常数级别的额外空间来存储数组和其他变量。
     * 这种算法是一种简单而高效的方法来计算给定日期的星期几。它不需要使用日期库或日期对象，只使用基本的数学运算即可得到结果。
     */
    public static String dayOfTheWeek(int day, int month, int year) {
        int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
        if (month < 3)
            year--;
        int n = (year + year / 4 - year / 100 + year / 400 + t[month - 1]+ day) % 7;
        String[]arr = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return arr[n];
    }
}

