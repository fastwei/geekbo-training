package com.geekbo.training.leetcode.dp;

/**
 * 132. Palindrome Partitioning II
 * Hard
 * Given a string s, partition s such that every
 * substring
 * of the partition is a
 * palindrome
 * .
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: 0
 * Example 3:
 * <p>
 * Input: s = "ab"
 * Output: 1
 */
public class PalindromePartitioningII {
    /**
     * 解题思路：
     *
     * 使用动态规划求解。
     *
     * 维护一个一维数组 dp，长度为字符串的长度。
     * dp[i] 表示从字符串的第 i 个字符到最后一个字符，所需的最小切割次数。
     * 初始化 dp 为最大值，并将 dp[n-1] 置为 0（n 为字符串的长度）。
     * 从字符串的倒数第二个字符开始向前遍历，计算 dp[i] 的值。
     *   - 如果字符串从第 i 个字符到第 j 个字符是回文串，则 dp[i] 的值为 dp[j+1] + 1。
     *   - 如果字符串从第 i 个字符到第 j 个字符不是回文串，则 dp[i] 的值为 dp[i] 和 dp[j+1] + 1 之间的较小值。
     * 返回 dp[0] 的值。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(n^2)，其中 n 是字符串的长度。需要计算和更新 dp 数组，共需两层循环。
     * 空间复杂度：O(n)，其中 n 是字符串的长度。需要维护一个长度为 n 的一维数组 dp。
     */

    public static int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n];
        boolean[][] isPalindrome = new boolean[n][n];

        // 初始化 dp 数组
        for (int i = 0; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[n - 1] = 0;

        // 计算 dp 数组
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                    if (j == n - 1) {
                        dp[i] = 0;
                    } else {
                        dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                    }
                }
            }
        }

        return dp[0];
    }


    public static void main(String[] args) {
        // Test case 1
        String s1 = "aab";
        // The palindrome partitioning ["aa","b"] could be produced using 1 cut.
        // Expected output: 1
        System.out.println(minCut(s1));

        // Test case 2
        String s2 = "a";
        // The string "a" is already a palindrome, so no cuts are needed.
        // Expected output: 0
        System.out.println(minCut(s2));

        // Test case 3
        String s3 = "ab";
        // The palindrome partitioning ["a","b"] could be produced using 1 cut.
        // Expected output: 1
        System.out.println(minCut(s3));
    }
}