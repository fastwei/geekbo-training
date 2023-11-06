package com.geekbo.training.leetcode.dp;

/**
 * 474. Ones and Zeroes
 * Medium
 * You are given an array of binary strings strs and two integers m and n.
 * <p>
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * <p>
 * A set x is a subset of a set y if all elements of x are also elements of y.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"},
 * so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 * Example 2:
 * <p>
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 */
public class OnesAndZeroes {
    /**
     * 解题思路： 这是一个动态规划问题。
     * 我们定义一个二维数组dp，其中dp[i][j]表示使用i个0和j个1能够构成的最大子集的大小。
     * 我们遍历每个字符串，统计其中的0和1的个数，然后更新dp数组。
     * 具体地，对于每个字符串，我们使用两个循环遍历dp数组，从m和n开始到zeros和ones结束，
     * 更新dp[i][j]的值为dp[i][j]和dp[i-zeros][j-ones]+1的较大值，表示使用i个0和j个1能够构成的最大子集的大小。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：每个字符串的长度为L，一共有N个字符串，所以总时间复杂度为O(NL)
     * 空间复杂度：使用了一个二维数组dp，大小为(m+1) * (n+1)，所以空间复杂度为O(mn)
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int zeros = 0, ones = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String[] strs1 = {"10", "0001", "111001", "1", "0"};
        int m1 = 5, n1 = 3;
        System.out.println("Test Case 1 - Expected: 4, Actual: " + findMaxForm(strs1, m1, n1));

        String[] strs2 = {"10", "0", "1"};
        int m2 = 1, n2 = 1;
        System.out.println("Test Case 2 - Expected: 2, Actual: " + findMaxForm(strs2, m2, n2));
    }
}

