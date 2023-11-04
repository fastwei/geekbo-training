package com.geekbo.training.leetcode.dp;

/**
 * 87. Scramble String
 * Hard
 * We can scramble a string s to get a string t using the following algorithm:
 * <p>
 * If the length of the string is 1, stop.
 * If the length of the string is > 1, do the following:
 * Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
 * Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
 * Apply step 1 recursively on each of the two substrings x and y.
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Explanation: One possible scenario applied on s1 is:
 * "great" --> "gr/eat" // divide at random index.
 * "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
 * "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
 * "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
 * "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
 * The algorithm stops now, and the result string is "rgeat" which is s2.
 * As one possible scenario led s1 to be scrambled to s2, we return true.
 * Example 2:
 * <p>
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 * Example 3:
 * <p>
 * Input: s1 = "a", s2 = "a"
 * Output: true
 */
public class ScrambleString {

    /**
     * 我们可以使用动态规划来解决这个问题。
     * 我们定义一个三维布尔数组dp，其中dp[i][j][len]表示长度为len的s1起始位置为i，
     * 和长度为len的s2起始位置为j的两个子串是否互为扰乱字符串。
     * <p>
     * 我们可以使用自底向上的方式进行动态规划，从小长度的子串开始逐步计算长度较长的子串。
     * 具体步骤如下：
     * <p>
     * 初始化数组：对于长度为1的子串，我们将dp[i][j][1]设置为true，当且仅当s1[i]和s2[j]相等。
     * 枚举子串长度：从长度为2开始，逐步增加子串的长度。
     * 枚举起始位置：对于每个长度为len的子串，我们需要枚举起始位置i和j。
     * 枚举划分位置：对于每个起始位置i和j，我们需要枚举划分位置k（1 <= k <= len-1）。
     * 判断是否互为扰乱字符串：对于划分位置k，我们需要判断两种情况：
     * 不交换两个子串的情况：判断dp[i][j][k]和dp[i+k][j+k][len-k]是否都为true。
     * 交换两个子串的情况：判断dp[i][j+len-k][k]和dp[i+k][j][len-k]是否都为true。
     * 更新dp数组：如果在上述两种情况中至少有一种情况满足，则将dp[i][j][len]设置为true。
     * 返回结果：最终的结果存储在dp[0][0][n]中，其中n为字符串的长度。
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];

        // 初始化长度为1的子串
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        // 枚举子串长度
        for (int len = 2; len <= n; len++) {
            // 枚举起始位置
            for (int i = 0; i <= n - len; i++) {
                for (int j = 0; j <= n - len; j++) {
                    // 枚举划分位置
                    for (int k = 1; k < len; k++) {
                        // 不交换两个子串的情况
                        if (dp[i][j][k] && dp[i + k][j + k][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                        // 交换两个子串的情况

                        if (dp[i][j + len - k][k] && dp[i + k][j][len - k]) {
                            dp[i][j][len] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[0][0][n];
    }

    /**
     * 通过使用记忆化搜索，我们避免了重复计算，从而提高了算法的效率。
     * <p>
     * 算法复杂度分析：
     * -时间复杂度：对于每个长度为n的子串，我们需要枚举划分位置，因此时间复杂度为O(n^3)。
     * -空间复杂度：我们使用了一个二维布尔数组memo来存储中间结果，其大小为n^2。因此，空间复杂度为O(n^2)。
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isScramble2(String s1, String s2) {
        int n = s1.length();
        boolean[][][] memo = new boolean[n][n][n + 1];
        return isScrambleHelper(s1, s2, 0, 0, n, memo);
    }

    private static boolean isScrambleHelper(String s1, String s2, int i, int j, int len, boolean[][][] memo) {
        if (memo[i][j][len]) {
            return memo[i][j][len];
        }

        if (s1.substring(i, i + len).equals(s2.substring(j, j + len))) {
            memo[i][j][len] = true;
            return true;
        }

        for (int k = 1; k < len; k++) {
            if (isScrambleHelper(s1, s2, i, j, k, memo) && isScrambleHelper(s1, s2, i + k, j + k, len - k, memo)) {
                memo[i][j][len] = true;
                return true;
            }
            if (isScrambleHelper(s1, s2, i, j + len - k, k, memo) && isScrambleHelper(s1, s2, i + k, j, len - k, memo)) {
                memo[i][j][len] = true;
                return true;
            }
        }

        memo[i][j][len] = false;
        return false;
    }

    public static void main(String[] args) {
        // 测试用例
        String s1 = "great";
        String s2 = "rgeat";
        // 预期输出: true
        System.out.println(isScramble(s1, s2));

        String s3 = "abcde";
        String s4 = "caebd";
        // 预期输出: false
        System.out.println(isScramble(s3, s4));

        String s5 = "a";
        String s6 = "a";
        // 预期输出: true
        System.out.println(isScramble(s5, s6));
    }
}

