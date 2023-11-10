package com.geekbo.training.leetcode.daily;

/**
 * Given an integer num, repeatedly add all its digits until the result has only one digit, and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 38
 * Output: 2
 * Explanation: The process is
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * Since 2 has only one digit, return it.
 * Example 2:
 *
 * Input: num = 0
 * Output: 0
 *
 */
public class AddDigits {
    public static int addDigits(int num) {
        while (num >= 10) {
            int digitSum = 0;
            while (num > 0) {
                digitSum += num % 10;
                num /= 10;
            }
            num = digitSum;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(addDigits(38));  // Output: 2
        System.out.println(addDigits(0));   // Output: 0
    }
}