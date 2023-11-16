package com.geekbo.training.leetcode.dp;

/**
 * 264. Ugly Number II
 * Medium
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 * <p>
 * Given an integer n, return the nth ugly number.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 1690
 */
public class UglyNumberII {

    /**
     * 给定一个整数n，返回第n个丑数。丑数是指其质因数只包含2、3和5的正整数。
     * <p>
     * 解题思路： 我们可以使用动态规划来解决这个问题。我们从第一个丑数1开始，依次计算下一个丑数，直到找到第n个丑数为止。
     * <p>
     * 我们用三个指针分别表示当前计算到的丑数乘以2、乘以3和乘以5的最小值，然后依次更新这三个指针。
     * 每次更新时，我们选择当前指针对应的丑数乘以对应的因子，并选择其中最小的值作为下一个丑数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度为O(n)，其中n是要找到的第n个丑数。
     * 空间复杂度为O(n)，我们需要使用一个数组来存储计算过的丑数。
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        int p2 = 0, p3 = 0, p5 = 0;

        for (int i = 1; i < n; i++) {
            int num2 = dp[p2] * 2;
            int num3 = dp[p3] * 3;
            int num5 = dp[p5] * 5;

            dp[i] = Math.min(Math.min(num2, num3), num5);

            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        // Test case 1
        int n1 = 10;
        // 第10个丑数为12
        int expected1 = 12;
        int result1 = nthUglyNumber(n1);
        System.out.println(result1 == expected1); // Output: true

        // Test case 2
        int n2 = 1;
        // 第1个丑数为1
        int expected2 = 1;
        int result2 = nthUglyNumber(n2);
        System.out.println(result2 == expected2); // Output: true
    }
}