package com.geekbo.training.leetcode.dp;

/**
 * 1312. Minimum Insertion Steps to Make a String Palindrome
 * <p>
 * Given a string s. In one step you can insert any character at any index of the string.
 * <p>
 * Return the minimum number of steps to make s palindrome.
 * <p>
 * A Palindrome String is one that reads the same backward as well as forward.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we do not need any insertions.
 * Example 2:
 * <p>
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * Example 3:
 * <p>
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 */
public class MinimumInsertionStepsMakeStringPalindrome {

    /**
     * 解题思路：
     * <p>
     * 首先，我们需要定义一个二维数组dp，其中dp[i][j]表示字符串s从索引i到索引j之间的最小插入次数。
     * 然后，我们需要使用动态规划来填充这个二维数组。
     * 我们可以从字符串s的最后一个字符开始，向前遍历，计算出每个子字符串的最小插入次数。
     * 如果s.charAt(i) == s.charAt(j)，则说明当前字符可以与对应的字符构成一个回文对，所以最小插入次数与子字符串s.substring(i + 1, j - 1)的最小插入次数相同。
     * 如果s.charAt(i) != s.charAt(j)，则说明当前字符不能与对应的字符构成一个回文对，所以我们需要在子字符串s.substring(i, j - 1)和s.substring(i + 1, j)中选择一个最小插入次数，然后再加上1次插入操作。
     * 最后，我们返回dp[0][n - 1]，即整个字符串s的最小插入次数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n为字符串s的长度。我们需要遍历每个子字符串，并计算每个子字符串的最小插入次数。
     * 空间复杂度：O(n^2)，我们需要创建一个二维数组dp来存储每个子字符串的最小插入次数。
     *
     * @param s
     * @return
     */
    public static int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0, j = len - 1; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        // Test cases
        String s1 = "zzazz";
        // The string "zzazz" is already a palindrome, so no insertions are needed
        // Expected output: 0
        System.out.println(minInsertions(s1));

        String s2 = "mbadm";
        // A possible palindrome string is "mbdadbm" with 2 insertions
        // Another possible palindrome string is "mdbabdm" with 2 insertions
        // Expected output: 2
        System.out.println(minInsertions(s2));

        String s3 = "leetcode";
        // A possible palindrome string is "leetcodocteel" with 5 insertions
        // Expected output: 5
        System.out.println(minInsertions(s3));
    }
}


