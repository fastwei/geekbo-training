package com.geekbo.training.leetcode.daily;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication,
 * division, and mod operator.
 * <p>
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * Note: Assume we are dealing with an environment that could only store integers
 * within the 32-bit signed integer range: [−231, 231 − 1]. For this problem,
 * if the quotient is strictly greater than 231 - 1, then return 231 - 1,
 * and if the quotient is strictly less than -231, then return -231.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = 3.33333.. which is truncated to 3.
 * Example 2:
 * <p>
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 */
public class DivideTwoIntegers {
    /**
     * 解题思路：
     * 1. 处理特殊情况：除数为0、被除数为0、除数为1、被除数为最小值；
     * 2. 判断最终结果的符号；
     * 3. 将被除数和除数都转为负数，避免溢出；
     * 4. 使用倍增法进行除法运算，每次将除数和商都左移一位，直到被除数小于除数；
     * 5. 重复步骤4，直到被除数小于除数为止。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：O(log n)，其中 n 是被除数除以除数的商的大小。
     * - 空间复杂度：O(1)。除了存储结果以外，算法只使用了常数级的额外空间。
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        // 处理除数为0的情况
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero");
        }

        // 处理被除数为0的情况
        if (dividend == 0) {
            return 0;
        }

        // 处理除数为1的情况
        if (divisor == 1) {
            return dividend;
        }

        // 处理被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                return Integer.MAX_VALUE; // 越界处理
            } else if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
        }

        // 判断最终结果的符号
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);

        // 将被除数和除数都转为负数，避免溢出
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        int result = 0;
        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor;
            int quotient = 1;

            // 使用倍增法加速除法运算
            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                quotient <<= 1;
            }

            absDividend -= tempDivisor;
            result += quotient;
        }

        return isNegative ? -result : result;
    }

    public static void main(String[] args) {
        // 测试用例1
        int dividend1 = 10;
        int divisor1 = 3;
        // 10 / 3 = 3.33333..，截断小数部分得到3
        // 所以预期结果是3
        System.out.println("Test Case 1 - Expected: 3, Output: " + divide(dividend1, divisor1));

        // 测试用例2
        int dividend2 = 7;
        int divisor2 = -3;
        // 7 / -3 = -2.33333..，截断小数部分得到-2
        // 所以预期结果是-2
        System.out.println("Test Case 2 - Expected: -2, Output: " + divide(dividend2, divisor2));

        // 测试用例3，被除数为0
        int dividend3 = 0;
        int divisor3 = 5;
        // 0 除以任何数都等于0
        // 所以预期结果是0
        System.out.println("Test Case 3 - Expected: 0, Output: " + divide(dividend3, divisor3));

        // 测试用例4，除数为0
        int dividend4 = 10;
        int divisor4 = 0;
        // 除数不能为0，会抛出 IllegalArgumentException 异常
        // 所以预期结果是 IllegalArgumentException 异常
        try {
            divide(dividend4, divisor4);
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 4 - Expected: IllegalArgumentException, Output: IllegalArgumentException");
        }

        // 测试用例5，被除数为最小值，除数为1
        int dividend5 = Integer
                .MIN_VALUE;
        int divisor5 = 1;
// Integer.MIN_VALUE / 1 = Integer.MIN_VALUE /
// 所以预期结果是 Integer.MIN_VALUE
        System.out.println("Test Case 5 - Expected: -2147483648, Output: " + divide(dividend5, divisor5));

        // 测试用例6，被除数为最小值，除数为-1
        int dividend6 = Integer.MIN_VALUE;
        int divisor6 = -1;
        // Integer.MIN_VALUE / -1 = Integer.MAX_VALUE + 1，超出了32位有符号整数的范围
        // 所以预期结果是 Integer.MAX_VALUE
        System.out.println("Test Case 6 - Expected: 2147483647, Output: " + divide(dividend6, divisor6));
    }
}