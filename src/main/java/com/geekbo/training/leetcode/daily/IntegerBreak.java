package com.geekbo.training.leetcode.daily;

/**
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 * <p>
 * Return the maximum product you can get.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 * <p>
 * Input: n = 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 58
 */
public class IntegerBreak {
    /**
     * 解题思路：
     * 首先分析题目中的例子，可以观察到以下规律：
     * - 当n为2时，最大乘积为1。
     * - 当n为3时，最大乘积为2。
     * - 当n为4时，最大乘积为4。
     * - 当n为5时，最大乘积为6。
     * - 当n为6时，最大乘积为9。
     * - 当n为7时，最大乘积为12。
     * - 当n为8时，最大乘积为18。
     * - 当n为9时，最大乘积为27。
     * - 当n为10时，最大乘积为36。
     * 可以发现，当n大于等于5时，将n拆分为尽可能多的3，可以得到最大的乘积。
     * 当n为4时，拆分为2和2可以得到最大乘积。
     * 当n为2和3时，不需要拆分，直接返回n的值即可。
     * <p>
     * 算法的时间复杂度为O(1)。
     * 算法的空间复杂度为O(1)。
     *
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        if (n == 2 || n == 3) {
            return n - 1;
        }

        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        product *= n;

        return product;
    }

    public static void main(String[] args) {
        int n1 = 2;
        // 预期输出为1
        System.out.println(integerBreak(n1));

        int n2 = 10;
        // 预期输出为36
        System.out.println(integerBreak(n2));
    }
}