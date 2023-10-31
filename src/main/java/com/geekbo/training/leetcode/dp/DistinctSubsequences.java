package com.geekbo.training.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, return the number of distinct subsequences of s which equals t.
 * <p>
 * The test cases are generated so that the answer fits on a 32-bit signed integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "rabbbit", t = "rabbit"
 * Output: 3
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from s.
 * rabbbit
 * rabbbit
 * rabbbit
 * Example 2:
 * <p>
 * Input: s = "babgbag", t = "bag"
 * Output: 5
 * Explanation:
 * As shown below, there are 5 ways you can generate "bag" from s.
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 */
public class DistinctSubsequences {
    /**
     * 解题思路：
     * <p>
     * 使用动态规划来解决该问题。
     * 创建一个二维数组dp[][]来存储s[i:]与t[j:]相等的不同子序列数量，其中i和j分别表示s和t的当前位置。
     * 初始化dp的最后一列为1，因为空字符串是任意字符串的子序列。
     * 从右到左遍历字符串。
     * 如果当前位置的字符匹配，则将包含这两个字符和不包含t中的字符时的不同子序列数量相加。
     * 如果当前位置的字符不匹配，则不同子序列数量与不包含s中的字符时的不同子序列数量相同。
     * 最终答案是s中与t相等的不同子序列的数量。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n*m)，其中n和m分别是字符串s和t的长度。需要填充一个二维数组大小为(n+1)x(m+1)的dp。
     * 空间复杂度：O(n*m)，存储二维数组dp的空间。
     *
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct(String s, String t) {
        // Create a 2D array dp[][] to store the number of distinct subsequences of s[i:] which equals t[j:]
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        // Initialize the last column of dp to 1, because an empty string is a subsequence of any string
        for (int i = 0; i <= s.length(); i++) {
            dp[i][t.length()] = 1;
        }

        // Traverse the strings from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                // If the characters at the current positions match
                if (s.charAt(i) == t.charAt(j)) {
                    // Add the number of distinct subsequences when both characters are included and when the character from t is excluded
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    // If the characters do not match, the number of distinct subsequences remains the same as when the character from s is excluded
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        // The answer is the number of distinct subsequences of s which equals t
        return dp[0][0];
    }

    public static void main(String[] args) {
        String s1 = "rabbbit";
        String t1 = "rabbit";
        // Expected output: 3
        System.out.println(numDistinct(s1, t1));

        String s2 = "babgbag";
        String t2 = "bag";
        // Expected output: 5
        System.out.println(numDistinct(s2, t2));
    }
}
