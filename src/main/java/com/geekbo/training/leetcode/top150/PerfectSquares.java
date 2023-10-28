package com.geekbo.training.leetcode.top150;

import java.util.Arrays;

/**
 *
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other words,
 * it is the product of some integer with itself.
 * For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 */
public class PerfectSquares {
    /**
     * 解题思路：
     * 使用动态规划的思想。
     * 定义一个一维数组dp，其中dp[i]表示数字i最少可以由多少个完全平方数相加得到。
     * 初始化dp数组，将所有元素初始化为最大值Integer.MAX_VALUE。
     * 遍历从1到n的每个数字i，对于每个数字i，遍历从1到sqrt(i)的每个完全平方数j*j，
     * 更新dp[i]的值为dp[i-j*j]+1和dp[i]的较小值。
     * 最后返回dp[n]作为结果。
     *
     * 算法的时间复杂度为O(n*sqrt(n))，其中n是给定的数字。
     *
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // Test case 1
        int n1 = 12;
        // 预期输出为3
        System.out.println(numSquares(n1));

        // Test case 2
        int n2 = 13;
        // 预期输出为2
        System.out.println(numSquares(n2));
    }
}