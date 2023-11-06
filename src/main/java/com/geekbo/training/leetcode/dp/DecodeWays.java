package com.geekbo.training.leetcode.dp;

/**
 * 91. Decode Ways
 * Medium

 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters
 * using the reverse of the mapping above (there may be multiple ways).
 * For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 *
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 */
public class DecodeWays {
    /**
     * 解题思路：
     * 使用动态规划的方法。
     * 定义一个一维数组dp，dp[i]表示字符串s的前i个字符的解码方式数量。
     * 初始时，dp[0] = 1，即空字符串的解码方式数量为1。
     * 对于字符串s的每个位置i，考虑两种情况：
     * 1. 如果当前位置的字符可以单独解码（即非零字符），则解码方式数量为dp[i-1]；
     * 2. 如果当前位置的字符和前一个字符可以组合解码（即构成10到26之间的数字），则解码方式数量为dp[i-2]。
     * 最终返回dp[n]，其中n是字符串s的长度。
     *
     * 算法复杂度分析：
     * 遍历字符串s的每个字符，时间复杂度为O(n)，其中n是字符串s的长度。
     * 使用了一个一维数组dp，空间复杂度为O(n)。
     * 因此，总的时间复杂度为O(n)，空间复杂度为O(n)。
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));

            if (oneDigit >= 1 && oneDigit <= 9) {
                dp[i] += dp[i - 1];
            }

            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // 创建解法对象
        DecodeWays solution = new DecodeWays();

        // 创建测试用例1
        // 预期输出: 2
        String s1 = "12";
        int result1 = solution.numDecodings(s1);
        System.out.println(result1);

        // 创建测试用例2
        // 预期输出: 3
        String s2 = "226";
        int result2 = solution.numDecodings(s2);
        System.out.println(result2);

        // 创建测试用例3
        // 预期输出: 0
        String s3 = "06";
        int result3 = solution.numDecodings(s3);
        System.out.println(result3);
    }
}

