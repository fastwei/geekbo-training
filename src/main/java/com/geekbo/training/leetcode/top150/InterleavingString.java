package com.geekbo.training.leetcode.top150;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * <p>
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m
 * substrings
 * respectively, such that:
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Explanation: One way to obtain s3 is:
 * Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
 * Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
 * Since s3 can be obtained by interleaving s1 and s2, we return true.
 * Example 2:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.
 * Example 3:
 * <p>
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 *
 * todo:加深理解
 */
public class InterleavingString {
    /**
     * 解题思路： 我们可以使用动态规划来解决这个问题。
     * 首先，我们定义一个二维数组dp，其中dp[i][j]表示s1的前i个字符和s2的前j个字符是否可以组成s3的前i+j个字符。
     * 我们可以根据s1、s2和s3的字符来更新dp数组的值。
     * 具体来说，如果s1的第i个字符等于s3的第i+j个字符，则我们可以通过将s1的前i-1个字符和s2的前j个字符组成s3的前i+j个字符。
     * 同样，如果s2的第j个字符等于s3的第i+j个字符，则我们可以通过将s1的前i个字符和s2的前j-1个字符组成s3的前i+j个字符。
     * 因此，我们可以使用动态规划的方法来解决这个问题。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(m*n)，其中m和n分别是s1和s2的长度。
     * 空间复杂度：O(mn)，我们使用了一个大小为(m+1)(n+1)的二维数组dp来存储中间结果。
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        // 获取字符串 s1、s2 和 s3 的长度
        int m = s1.length();
        int n = s2.length();

        // 如果字符串 s3 的长度不等于 s1 和 s2 的长度之和，则返回 false
        if (s3.length() != m + n) {
            return false;
        }

        // 定义状态数组 dp，dp[i][j] 表示 s1 的前 i 个字符和 s2 的前 j 个字符是否可以组成 s3 的前 i+j 个字符
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 初始化状态数组
        dp[0][0] = true;

        // 如果 s1 为空字符串，则判断 s2 的前 j 个字符是否能够组成 s3 的前 j 个字符
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // 如果 s2 为空字符串，则判断 s1 的前 i 个字符是否能够组成 s3 的前 i 个字符
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // 动态规划求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果 s1 的第 i 个字符等于 s3 的第 i+j 个字符，则判断 s1 的前 i-1 个字符和 s2 的前 j 个字符是否能够组成 s3 的前 i+j 个字符
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i - 1][j];
                }

                // 如果 s2 的第 j 个字符等于 s3 的第 i+j 个字符，则判断 s1 的前 i 个字符和 s2 的前 j-1 个字符是否能够组成 s3 的前 i+j 个字符
                if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
            }
        }

        // 返回结果
        return dp[m][n];
    }

    public static void main(String[] args) {
        // Test Case 1
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        // 预期输出为 true
        System.out.println(isInterleave(s1, s2, s3));

        // Test Case 2
        s1 = "aabcc";
        s2 = "dbbca";
        s3 = "aadbbbaccc";
        // 预期输出为 false
        System.out.println(isInterleave(s1, s2, s3));

        // Test Case 3
        s1 = "";
        s2 = "";
        s3 = "";
        // 预期输出为 true
        System.out.println(isInterleave(s1, s2, s3));
    }
}
