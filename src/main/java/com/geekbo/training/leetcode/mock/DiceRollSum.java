package com.geekbo.training.leetcode.mock;


/**
 *
 * You have n dice, and each die has k faces numbered from 1 to k.
 *
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1, k = 6, target = 3
 * Output: 1
 * Explanation: You throw one die with 6 faces.
 * There is only one way to get a sum of 3.
 * Example 2:
 *
 * Input: n = 2, k = 6, target = 7
 * Output: 6
 * Explanation: You throw two dice, each with 6 faces.
 * There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 * Example 3:
 *
 * Input: n = 30, k = 30, target = 500
 * Output: 222616187
 * Explanation: The answer must be returned modulo 109 + 7.
 *
 * 使用动态规划来解决问题。首先，初始化一个二维数组 dp 来存储子问题的解。然后，根据骰子数量和目标值，使用三层循环来填充 dp 数组。
 * 最终，返回 dp[n][target] 即可得到结果。需要注意的是，答案需要取模 10^9 + 7 以防止溢出。
 */
public class DiceRollSum {
    public int numRollsToTarget(int n, int k, int target) {
        int MOD = 1000000007;
        int[][] dp = new int[n + 1][target + 1];

        // 初始化基础情况
        for (int i = 1; i <= Math.min(k, target); i++) {
            dp[1][i] = 1;
        }

        // 填充dp数组
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                for (int face = 1; face <= k; face++) {
                    if (j - face >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - face]) % MOD;
                    }
                }
            }
        }

        return dp[n][target];
    }

    public static void main(String[] args) {
        DiceRollSum solution = new DiceRollSum();

        // 测试用例1
        int result1 = solution.numRollsToTarget(1, 6, 3);
        System.out.println(result1); // 1

        // 测试用例2
        int result2 = solution.numRollsToTarget(2, 6, 7);
        System.out.println(result2); // 6

        // 测试用例3
        int result3 = solution.numRollsToTarget(30, 30, 500);
        System.out.println(result3); // 222616187
    }
}
