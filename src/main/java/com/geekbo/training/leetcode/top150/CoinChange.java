package com.geekbo.training.leetcode.top150;

import java.util.Arrays;

/**
 * 322. Coin Change
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 * <p>
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: coins = [1], amount = 0
 * Output: 0
 */
public class CoinChange {
    /**
     * 解题思路： 使用动态规划的思想来解决问题。
     * 创建一个长度为 amount+1 的数组 dp，用来保存每个金额所需的最小硬币数量。
     * 将数组中的初始值设置为 amount + 1，相当于设置为一个不可能达到的值。
     * 然后遍历金额从 1 到 amount，对于每个金额，遍历硬币数组，
     * 如果当前硬币面额小于等于当前金额，更新 dp[i] 的值为 dp[i - coin] + 1 和 dp[i] 之间的较小值。
     * 最后，如果 dp[amount] 的值仍然是 amount + 1，说明无法凑出指定金额，返回 -1，否则返回 dp[amount] 的值。
     * <p>
     * 算法复杂度分析： 设硬币数组的长度为 n，金额为 amount。
     * 时间复杂度：使用双重循环遍历硬币数组和金额，时间复杂度为 O(n * amount)。
     * 空间复杂度：创建长度为 amount+1 的数组 dp，空间复杂度为 O(amount+1)，即 O(amount)。
     * 综上所述，该算法的时间复杂度为 O(n * amount)，空间复杂度为 O(amount)。
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        // 创建一个长度为 amount+1 的数组 dp，用来保存每个金额所需的最小硬币数量
        int[] dp = new int[amount + 1];
        // 将数组中的初始值设置为 amount + 1，相当于设置为一个不可能达到的值
        Arrays.fill(dp, amount + 1);
        // 硬币数量为 0 的时候，金额为 0
        dp[0] = 0;

        // 遍历金额从 1 到 amount
        for (int i = 1; i <= amount; i++) {
            // 遍历硬币数组
            for (int coin : coins) {
                // 如果当前硬币面额小于等于当前金额 i
                if (coin <= i) {
                    // 更新 dp[i] 的值为 dp[i - coin] + 1 和 dp[i] 之间的较小值
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // 如果 dp[amount] 的值仍然是 amount + 1，说明无法凑出指定金额，返回 -1
        // 否则，返回 dp[amount] 的值
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println(coinChange(coins1, amount1)); // Output: 3

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println(coinChange(coins2, amount2)); // Output: -1

        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println(coinChange(coins3, amount3)); // Output: 0
    }
}


