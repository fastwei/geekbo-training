package com.geekbo.training.leetcode.daily;

/**
 *
 * 371. SumOfTwoIntegers
 * Medium
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 * Example 2:
 *
 * Input: a = 2, b = 3
 * Output: 5
 *
 *
 * Constraints:
 *
 * -1000 <= a, b <= 1000
 */
class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        SumOfTwoIntegers solution = new SumOfTwoIntegers();

        int a1 = 1, b1 = 2;
        // Expected output: 3
        System.out.println(solution.getSum(a1, b1));

        int a2 = 2, b2 = 3;
        // Expected output: 5
        System.out.println(solution.getSum(a2, b2));
    }
}