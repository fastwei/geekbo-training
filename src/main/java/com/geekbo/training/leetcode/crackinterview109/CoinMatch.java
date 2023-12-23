package com.geekbo.training.leetcode.crackinterview109;

/**
 * 硬币组合问题
 * 面试题 08.11. 硬币
 * 中等
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。
 * (结果可能会很大，你需要将结果模上1000000007)
 * <p>
 * 示例1:
 * <p>
 * 输入: n = 5
 * 输出：2
 * 解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 * 示例2:
 * <p>
 * 输入: n = 10
 * 输出：4
 * 解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * 说明：
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= n (总金额) <= 1000000
 */
public class CoinMatch {
    /**
     * 解题思路： 该问题可以看作是一个完全背包问题，我们使用动态规划来解决。
     * <p>
     * 定义一个一维数组dp，其中dp[i]表示总金额为i时的硬币组合数。初始化时，dp[0] = 1，表示总金额为0时，有一种硬币组合方式。
     * <p>
     * 遍历硬币的面值，对于每个硬币面值coin，从coin开始遍历到总金额n，更新dp[i]的值为dp[i] + dp[i - coin]。
     * 这里的含义是，当总金额为i时，如果选择了面值为coin的硬币，那么剩余的金额为i - coin，我们需要计算剩余金额为i - coin时的硬币组合数，加到dp[i]上。
     * <p>
     * 最后返回dp[n]，即总金额为n时的硬币组合数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n为总金额。
     * 空间复杂度：O(n)，需要一个长度为n+1的数组dp来保存中间结果。
     *
     * @param n
     * @return
     */
    public int waysToChange(int n) {
        int[] coins = {1, 5, 10, 25};
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int mod = 1000000007;
        for (int coin : coins) {
            for (int i = coin; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - coin]) % mod;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        CoinMatch coinMatch = new CoinMatch();
        // 测试用例
        int n = 5;
        int expected = 2;
        int result = coinMatch.waysToChange(n);
        System.out.println("输入: n = " + n);
        System.out.println("预期输出: " + expected);
        System.out.println("实际输出: " + result);
        System.out.println();

        n = 10;
        expected = 4;
        result = coinMatch.waysToChange(n);
        System.out.println("输入: n = " + n);
        System.out.println("预期输出: " + expected);
        System.out.println("实际输出: " + result);
        System.out.println();
    }
}
