package com.geekbo.training.leetcode.top75;

/**
 *
 *Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 *
 * Constraints:
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 *
 */
public class EditDistance {

    /**
     * 解题思路：
     * <p>
     * 题目要求计算将一个字符串转换为另一个字符串所需的最小操作次数。
     * 我们可以使用动态规划来解决此问题。
     * 创建一个二维数组dp，用于保存编辑距离。
     * 初始化边界条件，即当一个字符串为空时，将另一个字符串变为空字符串所需的操作次数。
     * 动态规划计算编辑距离，通过状态转移方程：
     * 如果两个字符相等，则编辑距离等于它们前面的子串的编辑距离。
     * 如果两个字符不相等，则编辑距离等于它们前面子串中的较小者加1。
     * 最后，返回编辑距离。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(m*n)，其中m和n分别是两个字符串的长度，需要计算每个位置的编辑距离。
     * 空间复杂度：O(m*n)，需要创建一个二维数组来保存编辑距离。
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 创建一个二维数组dp，用于保存编辑距离
        int[][] dp = new int[m + 1][n + 1];

        // 初始化边界条件
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // 动态规划计算编辑距离
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果两个字符相等，则编辑距离等于它们前面的子串的编辑距离
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 如果两个字符不相等，则编辑距离等于它们前面子串中的较小者加1
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        // 返回编辑距离
        return dp[m][n];
    }

    public static void main(String[] args) {
        // 测试用例
        String word1 = "horse";
        String word2 = "ros";
        int result1 = minDistance(word1, word2);
        System.out.println("word1 = " + word1 + ", word2 = " + word2 + "，编辑距离：" + result1 + "，预期结果：3");

        String word3 = "intention";
        String word4 = "execution";
        int result2 = minDistance(word3, word4);
        System.out.println("word1 = " + word3 + ", word2 = " + word4 + "，编辑距离：" + result2 + "，预期结果：5");
    }
}