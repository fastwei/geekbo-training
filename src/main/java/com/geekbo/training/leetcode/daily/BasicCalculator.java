package com.geekbo.training.leetcode.daily;

/**
 * 227. Basic Calculator II
 * Medium
 * Given a string s which represents an expression, evaluate this expression and return its value.
 * <p>
 * The integer division should truncate toward zero.
 * <p>
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-231, 231 - 1].
 * <p>
 * Note: You are not allowed to use any built-in function
 * which evaluates strings as mathematical expressions, such as eval().
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 * <p>
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 * <p>
 * Input: s = " 3+5 / 2 "
 * Output: 5
 */
public class BasicCalculator {
    public static int calculate(String s) {
        int result = 0;
        int num = 0;
        int prevNum = 0;
        char operation = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }

            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (operation == '+') {
                    result += prevNum;
                    prevNum = num;
                } else if (operation == '-') {
                    result += prevNum;
                    prevNum = -num;
                } else if (operation == '*') {
                    prevNum *= num;
                } else if (operation == '/') {
                    prevNum /= num;
                }

                operation = c;
                num = 0;
            }
        }

        result += prevNum;

        return result;
    }

    public static void main(String[] args) {
        String s1 = "3+2*2";
        System.out.println(calculate(s1)); // Output: 7

        String s2 = " 3/2 ";
        System.out.println(calculate(s2)); // Output: 1

        String s3 = " 3+5 / 2 ";
        System.out.println(calculate(s3)); // Output: 5
    }
}