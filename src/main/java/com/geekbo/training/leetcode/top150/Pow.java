package com.geekbo.training.leetcode.top150;

/**
 *
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 *
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 *
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 */
public class Pow {
    /**
     * todo:有空再加深理解
     *
     * 解题思路：
     * <p>
     * 如果 n 为 0 或 x 为 1，则直接返回 1。
     * 在处理 n 为负数的情况时，我们首先将 x 变为 1/x，并将 n 变为其绝对值。
     * 如果 n 的值为 Integer.MIN_VALUE，我们将 n 除以 2，并将 x 的值平方，以防止整数溢出。
     * 然后，我们按照原来的逻辑计算 x 的 n 次方。
     * 使用循环计算 x 的 n 次方，每次循环将当前结果 result 乘以当前 x 的值。
     * 为了降低时间复杂度，每次循环将当前 x 的值平方，并将 n 除以 2。
     * 当 n 为奇数时，将当前结果 result 乘以当前 x 的值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：通过将 n 除以 2 来降低循环次数，因此时间复杂度为 O(logn)。
     * 空间复杂度：除了存储结果的 result 和 currentProduct 外，额外使用的空间为常数级别，因此空间复杂度为 O(1)。
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        // 处理特殊情况，n 为 0 或 x 为 1 的情况
        if (n == 0 || x == 1)
            return 1;

        // 处理 n 为负数的情况
        if (n < 0) {
            x = 1 / x;
            // 处理 n 为最小负整数的情况
            if (n == Integer.MIN_VALUE) {
                n /= 2;
                x *= x;
            }
            n = -n;
        }

        double result = 1;
        double currentProduct = x;

        // 计算 x 的 n 次方
        while (n > 0) {
            if (n % 2 == 1) {
                result *= currentProduct;
            }
            currentProduct *= currentProduct;
            n /= 2;
        }

        return result;
    }

    public static void main(String[] args) {
        double x1 = 2.00000;
        int n1 = 10;
        double result1 = myPow(x1, n1);
        System.out.println(result1);
        // 预期输出: 1024.00000

        double x2 = 2.10000;
        int n2 = 3;
        double result2 = myPow(x2, n2);
        System.out.println(result2);
        // 预期输出: 9.26100

        double x3 = 2.00000;
        int n3 = -2;
        double result3 = myPow(x3, n3);
        System.out.println(result3);
        // 预期输出: 0.25000
    }
}
