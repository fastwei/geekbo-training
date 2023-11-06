package com.geekbo.training.leetcode.dp;

/**
 * 518. Coin Change II
 * Medium
 * You are given an integer array coins representing coins of different denominations
 * and an integer amount representing a total amount of money.
 * <p>
 * Return the number of combinations that make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return 0.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * The answer is guaranteed to fit into a signed 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 * <p>
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 * <p>
 * Input: amount = 10, coins = [10]
 * Output: 1
 */
public class CoinChangeII {
    /**
     * 解题思路：
     * 使用动态规划的方法。
     * 定义一个一维数组dp，dp[i]表示金额为i时的组合数量。
     * 初始时，dp[0] = 1，即金额为0时，只有一种组合，即不选取任何硬币。
     * 对于每个硬币coin，遍历金额从coin到amount，更新dp数组的值。
     * 对于金额i，如果选取了硬币coin，则组合数量为dp[i - coin]，因为金额i - coin的部分已经使用了硬币coin。
     * 将所有硬币的组合数量累加到dp[i]中即可。
     * 最终返回dp[amount]，即金额为amount时的组合数量。
     * <p>
     * 算法复杂度分析：
     * 遍历硬币的数量为coins.length，遍历金额的数量为amount，所以总的时间复杂度为O(coins.length * amount)。
     * 使用了一个一维数组dp，空间复杂度为O(amount)。
     * 因此，总的空间复杂度为O(amount)。
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        // 创建解法对象
        CoinChangeII solution = new CoinChangeII();

        // 创建测试用例1
        // 预期输出: 4
        int amount1 = 5;
        int[] coins1 = {1, 2, 5};
        int result1 = solution.change(amount1, coins1);
        System.out.println(result1);

        // 创建测试用例2
        // 预期输出: 0
        int amount2 = 3;
        int[] coins2 = {2};
        int result2 = solution.change(amount2, coins2);
        System.out.println(result2);

        // 创建测试用例3
        // 预期输出: 1
        int amount3 = 10;
        int[] coins3 = {10};
        int result3 = solution.change(amount3, coins3);
        System.out.println(result3);
    }
}

