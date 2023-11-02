package com.geekbo.training.leetcode.dp;

/**
 * 10. Regular Expression Matching
 * Hard
 * Topics
 * Companies
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * <p>
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 * <p>
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'.
 * Therefore, by repeating 'a' once, it becomes "aa".
 * <p>
 */
public class RegularExpressionMatching {
    /**
     * 解题思路：
     * <p>
     * 我们可以使用动态规划来解决这个问题。
     * 定义一个二维数组dp，其中dp[i][j]表示s的前i个字符和p的前j个字符是否匹配。
     * <p>
     * 首先，我们需要初始化dp数组。dp[0][0]表示s和p都为空字符串，所以dp[0][0]为true。
     * 对于dp[0][j]，如果p的第j个字符是'*'，则dp[0][j]的值与dp[0][j-2]相同，因为'*'可以匹配0个前面的字符；
     * 否则，dp[0][j]的值为false。
     * <p>
     * 然后，我们需要遍历dp数组并更新其值。对于dp[i][j]，有以下几种情况：
     * - 如果p的第j个字符是字母，那么s的第i个字符和p的第j个字符是否匹配取决于s的第i-1个字符和p的第j-1个字符是否匹配，
     * 即dp[i][j] = dp[i-1][j-1]。
     * - 如果p的第j个字符是'.'，那么s的第i个字符和p的第j个字符必定匹配，即dp[i][j] = dp[i-1][j-1]。
     * - 如果p的第j个字符是'*'，有两种情况：
     * a. '*'匹配0个前面的字符，即dp[i][j] = dp[i][j-2]。
     * b. '*'匹配1个或多个前面的字符，即s的第i个字符和p的第j-1个字符匹配，并且s的前i-1个字符和p的前j个字符匹配，
     * 即dp[i][j] = dp[i-1][j] && (s[i] == p[j-1] || p[j-1] == '.')。
     * <p>
     * 最后，我们需要返回dp[s.length()][p.length()]的值，即s的所有字符和p的所有字符是否匹配。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：算法遍历dp数组一次，时间复杂度为O(m*n)，其中m是s的长度，n是p的长度。
     * 空间复杂度：算法使用了一个二维数组dp，空间复杂度为O(m*n)。
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // 初始化dp数组
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 更新dp数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1

                ) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();

        // Test case 1
        String s1 = "aa";
        String p1 = "a";
        boolean expected1 = false;
        boolean result1 = regularExpressionMatching.isMatch(s1, p1);
        System.out.println(result1 == expected1 ? "Pass" : "Fail");

        // Test case 2
        String s2 = "aa";
        String p2 = "a*";
        boolean expected2 = true;
        boolean result2 = regularExpressionMatching.isMatch(s2, p2);
        System.out.println(result2 == expected2 ? "Pass" : "Fail");

        // Test case 3
        String s3 = "ab";
        String p3 = ".*";
        boolean expected3 = true;
        boolean result3 = regularExpressionMatching.isMatch(s3, p3);
        System.out.println(result3 == expected3 ? "Pass" : "Fail");
    }
}