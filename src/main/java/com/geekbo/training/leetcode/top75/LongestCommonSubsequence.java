package com.geekbo.training.leetcode.top75;

/**
 *
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English characters.
 *
 */
public class LongestCommonSubsequence {

    /**
     * 解题思路：
     * <p>
     * 我们可以使用动态规划来解决此问题。
     * 创建一个二维数组dp，用于保存最长公共子序列的长度。
     * 动态规划计算最长公共子序列的长度，通过状态转移方程：
     * 状态转移方程：
     * 当 text1[i-1] == text2[j-1] 时，dp[i][j] = dp[i-1][j-1] + 1
     * 当 text1[i-1] != text2[j-1] 时，dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * 如果两个字符相等，则最长公共子序列的长度等于它们前面的子序列的长度加1。
     * 如果两个字符不相等，则最长公共子序列的长度等于它们前面子序列中的较长者。
     * 最后，返回最长公共子序列的长度。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(m*n)，其中m和n分别是两个字符串的长度，需要计算每个位置的最长公共子序列的长度。
     * 空间复杂度：O(m*n)，需要创建一个二维数组来保存最长公共子序列的长度。
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 创建一个二维数组dp，用于保存最长公共子序列的长度
        int[][] dp = new int[m + 1][n + 1];

        // 动态规划计算最长公共子序列的长度
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果两个字符相等，则最长公共子序列的长度等于它们前面的子序列的长度加1
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 如果两个字符不相等，则最长公共子序列的长度等于它们前面子序列中的较长者
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回最长公共子序列的长度
        return dp[m][n];
    }

    public static void main(String[] args) {
        // 测试用例
        String text1 = "abcde";
        String text2 = "ace";
        int result1 = longestCommonSubsequence(text1, text2);
        System.out.println("text1 = " + text1 + ", text2 = " + text2 + "，最长公共子序列的长度：" + result1);

        String text3 = "abc";
        String text4 = "abc";
        int result2 = longestCommonSubsequence(text3, text4);
        System.out.println("text1 = " + text3 + ", text2 = " + text4 + "，最长公共子序列的长度：" + result2);

        String text5 = "abc";
        String text6 = "def";
        int result3 = longestCommonSubsequence(text5, text6);
        System.out.println("text1 = " + text5 + ", text2 = " + text6 + "，最长公共子序列的长度：" + result3);
    }
}