package com.geekbo.training.leetcode.dp;

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * <p>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * <p>
 * Find the maximum profit you can achieve.
 * You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * <p>
 * After you sell your stock, you cannot buy stock on the next day
 * (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously
 * (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 * <p>
 * Input: prices = [1]
 * Output: 0
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    /**
     * 解题思路：
     * <p>
     * 首先，我们需要定义三个数组：buy、sell和cooldown，
     * 分别表示在当前第i天结束时的买入状态、卖出状态和冷却状态下的最大利润。
     * 然后，我们需要使用动态规划来填充这三个数组。
     * 对于数组buy，我们可以选择在第i天买入股票或者不买入股票。
     * 如果我们选择在第i天买入股票，那么最大利润为前一天的冷却状态减去当天的股票价格。
     * 如果我们选择不买入股票，那么最大利润与前一天的买入状态相同。
     * 所以我们可以得到状态转移方程：buy[i]=max(buy[i-1],cooldown[i-1]-prices[i])。
     * 对于数组sell，我们可以选择在第i天卖出股票或者不卖出股票。
     * 如果我们选择在第i天卖出股票，那么最大利润为前一天的买入状态加上当天的股票价格。
     * 如果我们选择不卖出股票，那么最大利润与前一天的卖出状态相同。
     * 所以我们可以得到状态转移方程：sell[i]=max(sell[i-1],buy[i-1]+prices[i])。
     * 对于数组cooldown，我们可以选择在第i天保持冷却状态或者在第i天冷却。
     * 如果我们选择在第i天保持冷却，那么最大利润与前一天的冷却状态相同。
     * 所以我们可以得到状态转移方程：cooldown[i]=max(cooldown[i-1],sell[i-1])。
     * <p>
     * 最后，我们返回sell[n-1]和cooldown[n-1]中的较大值，即整个数组prices结束时的最大利润。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n为数组prices的长度。我们需要遍历整个数组来计算每一天的最大利润。
     * 空间复杂度：O(n)，我们需要创建三个数组buy、sell和cooldown，来存储每一天的最大利润。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;

        if (n <= 1) {
            return 0;
        }

        int[] buy = new int[n];
        int[] sell = new int[n];
        int[] cooldown = new int[n];

        buy[0] = -prices[0];
        sell[0] = 0;
        cooldown[0] = 0;

        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i - 1], cooldown[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            cooldown[i] = Math.max(cooldown[i - 1], sell[i - 1]);
        }

        return Math.max(sell[n - 1], cooldown[n - 1]);
    }

    public static void main(String[] args) {
        // Test cases
        int[] prices1 = {1, 2, 3, 0, 2};
        // The maximum profit can be achieved by buying at day 1 (price = 1) and selling at day 2 (price = 3) for a profit of 2,
        // then buying at day 4 (price = 0) and selling at day 5 (price = 2) for a profit of 1.
        // Total profit = 2 + 1 = 3
        // Expected output: 3
        System.out.println(maxProfit(prices1));

        int[] prices2 = {1};
        // Since there is only one price, we cannot make any transactions, so the maximum profit is 0.
        // Expected output: 0
        System.out.println(maxProfit(prices2));
    }
}
