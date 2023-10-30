package com.geekbo.training.leetcode.dp;

/**
 * Longest Palindromic Subsequence
 * Medium
 * Topics
 * Companies
 * Given a string s, find the longest palindromic subsequence's length in s.
 * <p>
 * A subsequence is a sequence that can be derived from another sequence
 * by deleting some or no elements without changing the order of the remaining elements.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 */
public class LongestPalindromicSubsequence {
    /**
     * 返回字符串中最长回文子序列的长度
     * 解题思路：
     * <p>
     * 使用动态规划来解决这个问题。
     * 定义一个二维数组dp，其中dp[i][j]表示字符串s从索引i到索引j之间的最长回文子序列的长度。
     * 初始化对角线上的元素为1，表示单个字符是回文子序列。
     * 从下到上，从左到右遍历数组，计算每个dp[i][j]的值。
     * 如果s.charAt(i)等于s.charAt(j)，则说明可以将两个字符加入回文子序列，长度加2，
     * 即dp[i][j] = dp[i + 1][j - 1] + 2。
     * 如果s.charAt(i)不等于s.charAt(j)，则说明需要排除一个字符，取剩余部分的最长回文子序列长度，
     * 即dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1])。
     * 最后，返回整个字符串的最长回文子序列长度，即dp[0][n - 1]，其中n是字符串s的长度。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n是字符串的长度。
     * 需要填充二维数组dp，长度为n的二维数组需要进行O(n^2)的计算。
     * <p>
     * 空间复杂度：O(n^2)，需要使用一个二维数组dp来存储中间结果，其大小为n×n。除此之外，不需要使用额外的空间。
     * <p>
     * 补充说明：
     * <p>
     * 这个问题可以使用递归+记忆化搜索的方式进行求解，也可以使用动态规划的方式进行求解。
     * 上述代码使用了动态规划的方法，通过填充二维数组dp来保存子问题的结果，从而求解整个问题。
     * 动态规划的思路是自底向上的，先求解子问题，再逐步推导出更大规模的问题的解。
     * 在本问题中，子问题是求解字符串s的子串的最长回文子序列的长度，然后通过子问题的解来求解更大规模的问题，
     * 最终得到整个字符串s的最长回文子序列的长度。
     * 动态规划是一种常用的解决问题的方法，适用于具有重叠子问题和最优子结构性质的问题。
     * 在本问题中，子问题之间存在重叠，因为求解较大规模的问题时会重复求解较小规模的子问题。
     * 通过使用动态规划，可以避免重复计算，提高算法的效率。
     *
     * @param s 给定的字符串
     * @return 最长回文子序列的长度
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        // dp[i][j]表示字符串s从索引i到索引j之间的最长回文子序列的长度
        int[][] dp = new int[n][n];

        // 初始化对角线上的元素为1，表示单个字符是回文子序列
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // 从下到上，从左到右遍历数组
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 字符相等，可以将两个字符加入回文子序列，长度加2
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 字符不相等，将s.charAt(i)或s.charAt(j)其中一个字符排除，取最长的回文子序列长度
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回整个字符串的最长回文子序列长度
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        String s1 = "bbbab";
        System.out.println(longestPalindromeSubseq(s1)); // Expected: 4

        String s2 = "cbbd";
        System.out.println(longestPalindromeSubseq(s2)); // Expected: 2
    }
}
