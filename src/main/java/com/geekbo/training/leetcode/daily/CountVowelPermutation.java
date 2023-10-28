package com.geekbo.training.leetcode.daily;

public class CountVowelPermutation {
    /**
     * 解题思路：
     * <p>
     * 使用动态规划来解决问题。我们创建一个二维数组dp，其中dp[i][j]记录了长度为i的字符串以字母j结尾的合法字符串的个数。
     * 其中，i表示字符串的长度，j表示字母的索引，0代表'a'，1代表'e'，2代表'i'，3代表'o'，4代表'u'。
     * <p>
     * 初始化：对于长度为1的字符串，每个字母都是合法的，所以dp[0][j] = 1，其中0 <= j < 5。
     * <p>
     * 状态转移方程：根据题目规则，我们可以推导出每个字母后面可以跟随的合法字母。
     * 根据这个规则，我们更新dp[i][j]的值：
     * <p>
     * 如果j = 0，即当前字母是'a'，那么它可以跟随'a'、'e'和'u'。
     * 所以dp[i][0] = dp[i-1][1] + dp[i-1][2] + dp[i-1][4]。
     * 如果j = 1，即当前字母是'e'，那么它可以跟随'a'和'i'。所以dp[i][1] = dp[i-1][0] + dp[i-1][2]。
     * 如果j = 2，即当前字母是'i'，那么它可以跟随'e'和'o'。所以dp[i][2] = dp[i-1][1] + dp[i-1][3]。
     * 如果j = 3，即当前字母是'o'，那么它可以跟随'i'。所以dp[i][3] = dp[i-1][2]。
     * 如果j = 4，即当前字母是'u'，那么它可以跟随'a'和'o'。所以dp[i][4] = dp[i-1][2] + dp[i-1][3]。
     * 最终答案：对于长度为n的字符串，我们需要计算所有以'a'、'e'、'i'、'o'和'u'结尾的合法字符串的个数之和，
     * 即dp[n-1][0] + dp[n-1][1] + dp[n-1][2] + dp[n-1][3] + dp[n-1][4]。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n)，其中n是字符串的长度。
     * 空间复杂度：O(n)，需要使用一个二维数组dp来记录状态。
     *
     * @param n
     * @return
     */
    public int countVowelPermutation(int n) {
        int mod = (int) 1e9 + 7;
        long[][] dp = new long[n][5];

        // 初始化长度为1的字符串的情况
        for (int i = 0; i < 5; i++) {
            dp[0][i] = 1;
        }

        // 遍历字符串的长度
        for (int i = 1; i < n; i++) {
            dp[i][0] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]) % mod; // 后面可以跟随'a'的个数
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod; // 后面可以跟随'e'的个数
            dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % mod; // 后面可以跟随'i'的个数
            dp[i][3] = (dp[i - 1][2]) % mod; // 后面可以跟随'o'的个数
            dp[i][4] = (dp[i - 1][2] + dp[i - 1][3]) % mod; // 后面可以跟随'u'的个数
        }

        long count = 0;
        // 计算所有长度为n的字符串可以组成的个数
        for (int i = 0; i < 5; i++) {
            count = (count + dp[n - 1][i]) % mod;
        }

        return (int) count;
    }

    public static void main(String[] args) {
        CountVowelPermutation countVowelPermutation = new CountVowelPermutation();

        // Test Case 1
        int n1 = 1;
        int result1 = countVowelPermutation.countVowelPermutation(n1);
        System.out.println("Test Case 1:");
        System.out.println("Input: " + n1);
        System.out.println("Output: " + result1);

        // Test Case 2
        int n2 = 2;
        int result2 = countVowelPermutation.countVowelPermutation(n2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: " + n2);
        System.out.println("Output: " + result2);

        // Test Case 3
        int n3 = 5;
        int result3 = countVowelPermutation.countVowelPermutation(n3);
        System.out.println("\nTest Case 3:");
        System.out.println("Input: " + n3);
        System.out.println("Output: " + result3);

        // Test Case 4
        int n4 = 144;
        int result4 = countVowelPermutation.countVowelPermutation(n4);
        System.out.println("\nTest Case 4:");
        System.out.println("Input: " + n4);
        System.out.println("Output: " + result4);
    }
}