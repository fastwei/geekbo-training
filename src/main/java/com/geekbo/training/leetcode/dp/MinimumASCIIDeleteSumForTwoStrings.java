package com.geekbo.training.leetcode.dp;

/**
 * Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "sea", s2 = "eat"
 * Output: 231
 * Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
 * Deleting "t" from "eat" adds 116 to the sum.
 * At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
 * Example 2:
 * <p>
 * Input: s1 = "delete", s2 = "leet"
 * Output: 403
 * Explanation: Deleting "dee" from "delete" to turn the string into "let",
 * adds 100[d] + 101[e] + 101[e] to the sum.
 * Deleting "e" from "leet" adds 101[e] to the sum.
 * At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
 * If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s1.length, s2.length <= 1000
 * s1 and s2 consist of lowercase English letters.
 */
public class MinimumASCIIDeleteSumForTwoStrings {

    /**
     * 解题思路：
     * <p>
     * 我们可以使用动态规划来解决这个问题。
     * 定义一个二维数组dp，其中dp[i][j]表示将字符串s1的前i个字符和字符串s2的前j个字符变为相等所需删除的最小ASCII值的和。
     * 初始化dp数组的第一行和第一列，分别表示将空字符串变为s1和s2所需删除的最小ASCII值的和。
     * 然后，我们遍历字符串s1和s2的所有字符。
     * 如果s1的第i个字符等于s2的第j个字符，那么dp[i][j]等于dp[i-1][j-1]，因为它们可以保留下来。
     * 否则，我们有两个选择：删除s1的第i个字符或删除s2的第j个字符。
     * 我们选择删除ASCII值较小的字符，即dp[i][j] = min(dp[i-1][j] + s1.charAt(i-1), dp[i][j-1] + s2.charAt(j-1))。
     * 最后，dp[s1.length()][s2.length()]就是所需删除的最小ASCII值的和。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：算法的时间复杂度为O(m * n)，其中m和n分别是s1和s2的长度。我们需要填充一个大小为(m+1) * (n+1)的二维数组。
     * 空间复杂度：算法的空间复杂度为O(m * n)，我们需要一个大小为(m+1) * (n+1)的二维数组来存储中间结果。
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];
        // 初始化第一行和第一列
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);
        }

        // 动态规划求解最小ASCII值的和
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "sea";
        String s2 = "eat";
        // 预期输出: 231
        System.out.println(minimumDeleteSum(s1, s2));

        String s3 = "delete";
        String s4 = "leet";
        // 预期输出: 403
        System.out.println(minimumDeleteSum(s3, s4));
    }
}
