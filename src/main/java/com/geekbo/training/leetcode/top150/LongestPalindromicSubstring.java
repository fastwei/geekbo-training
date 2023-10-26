package com.geekbo.training.leetcode.top150;

/**
 * Given a string s, return the longest
 * palindromic
 * <p>
 * substring
 * in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * todo:加深理解
 *
 */
public class LongestPalindromicSubstring {
    /**
     * 解题思路： 我们可以使用动态规划来解决这个问题。
     * 首先，我们定义一个二维数组dp，其中dp[i][j]表示s的第i个字符到第j个字符是否为回文子串。
     * 我们可以根据字符串s的字符来更新dp数组的值。
     * 具体来说，如果s的第i个字符等于s的第j个字符，并且s的第i+1个字符到第j-1个字符是回文子串，
     * 则s的第i个字符到第j个字符也是回文子串。
     * 因此，我们可以使用动态规划的方法来解决这个问题。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n是字符串s的长度。
     * 我们需要计算n个长度为1的回文子串，n-1个长度为2的回文子串，n-2个长度为3的回文子串，...，
     * 2个长度为n-1的回文子串，1个长度为n的回文子串。
     * 因此，总共需要进行n^2次判断。
     * 空间复杂度：O(n^2)，我们使用了一个大小为n*n的二维数组dp来存储中间结果。
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        // 特殊情况处理
        if (s == null || s.length() < 2) {
            return s;
        }

        int start = 0; // 最长回文子串的起始位置
        int maxLen = 1; // 最长回文子串的长度

        // 动态规划求解
        int n = s.length();
        boolean[][] dp = new boolean[n][n]; // dp[i][j]表示s的第i个字符到第j个字符是否为回文子串

        // 初始化长度为1的回文子串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 遍历字符串s，从长度为2的子串开始判断
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (len > maxLen) {
                            maxLen = len;
                            start = i;
                        }
                    }
                }
            }
        }

        // 返回最长回文子串
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        // Test Case 1
        String s = "babad";
        // 预期输出为 "bab" 或 "aba"
        System.out.println(longestPalindrome(s));

        // Test Case 2
        s = "cbbd";
        // 预期输出为 "bb"
        System.out.println(longestPalindrome(s));
    }
}
