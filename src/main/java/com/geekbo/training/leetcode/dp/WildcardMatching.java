package com.geekbo.training.leetcode.dp;

/**
 * 44. Wildcard Matching
 * Given an input string (s) and a pattern (p),
 * implement wildcard pattern matching with support for '?' and '*' where:
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
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
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 * <p>
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length, p.length <= 2000
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '?' or '*'.
 */
public class WildcardMatching {
    /**
     * 解题思路： 使用动态规划来解决通配符匹配问题，其中dp[i][j]表示s的前i个字符和p的前j个字符是否匹配。
     * 根据题目中的匹配规则，我们可以得到以下状态转移方程：
     * <p>
     * 如果p的当前字符是?或者与s的当前字符相同，则dp[i][j]取决于dp[i-1][j-1]。
     * 如果p的当前字符是*，则dp[i][j]取决于dp[i][j-1]（*匹配空字符）或者dp[i-1][j]（*匹配多个字符）。
     * 算法复杂度分析：
     * 时间复杂度：动态规划的过程需要遍历s的每个字符和p的每个字符，所以时间复杂度为O(m * n)，
     * 其中m是字符串s的长度，n是字符串p的长度。
     * 空间复杂度：需要创建一个二维数组dp来存储中间结果，所以空间复杂度为O(m * n)。
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // 创建一个布尔数组dp，dp[i][j]表示s的前i个字符和p的前j个字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 空字符串和空模式匹配
        dp[0][0] = true;

        // 处理空字符串和以*开头的模式
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // 动态规划求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果p的当前字符是?或者与s的当前字符相同，则dp[i][j]取决于dp[i-1][j-1]
                if (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 如果p的当前字符是*，则dp[i][j]取决于dp[i][j-1]（*匹配空字符）或者dp[i-1][j]（*匹配多个字符）
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        // 返回结果，即s的所有字符和p的所有字符是否完全匹配
        return dp[m][n];
    }

    /**
     * 除了动态规划，还可以使用递归和回溯的方法来实现通配符匹配。
     * 这种递归的实现方法相对简单，但在某些情况下会导致重复计算，效率较低。
     * 对于较大的输入，可能会导致栈溢出。
     * 在实际应用中，建议使用动态规划的方法来解决通配符匹配问题。
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatchRecursive(String s, String p) {
        // 处理边界情况
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        // 当前字符匹配或者p的当前字符为?，则继续匹配下一个字符
        if (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?')) {
            return isMatch(s.substring(1), p.substring(1));
        }

        // 当前字符为*，则有两种情况，*匹配空字符或者*匹配多个字符
        if (p.charAt(0) == '*') {
            return isMatch(s, p.substring(1)) || (!s.isEmpty() && isMatch(s.substring(1), p));
        }

        // 其他情况，不匹配
        return false;
    }

    public static void main(String[] args) {
        // 测试用例
        String s1 = "aa";
        String p1 = "a";
        // 预期输出: false
        System.out.println(isMatch(s1, p1));

        String s2 = "aa";
        String p2 = "*";
        // 预期输出: true
        System.out.println(isMatch(s2, p2));

        String s3 = "cb";
        String p3 = "?a";
        // 预期输出: false
        System.out.println(isMatch(s3, p3));
    }
}
