package com.geekbo.training.leetcode.top150.hard;

/**
 * 123. Best Time to Buy and Sell Stock III
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 * DP实现：
 *
 * 使用一个二维数组 dp 来表示状态，其中 dp[k][i] 表示在第 i 天完成了第 k 次交易后的最大利润。外层循环 k 表示交易次数，内层循环 i 表示天数，依次更新状态数组。
 *
 * 这个算法的时间复杂度为 O(N)，其中 N 是价格数组的长度。它可以找到最多完成两次交易后的最大利润。
 *
 */
public class BestTimetoBuyAndSellStockIII {
    /**
     *
     * 解题思路：
     *
     * 创建四个变量来保存不同状态下的最大利润：
     * buy1：第一次买入股票后的最大利润
     * sell1：第一次卖出股票后的最大利润
     * buy2：第二次买入股票后的最大利润
     * sell2：第二次卖出股票后的最大利润
     * 初始化这些变量，初始值为负无穷大，表示还没有进行任何交易。
     * 遍历价格数组，更新上述变量的值：
     * 更新buy1和sell1的值，考虑当前价格卖出或买入第一次股票的情况。
     * 更新buy2和sell2的值，考虑当前价格卖出或买入第二次股票的情况。
     * 最终的答案是sell2，表示最多完成两次交易后的最大利润。
     * 算法复杂度：
     *
     * 时间复杂度：O(N)，其中N是价格数组的长度，因为我们只需要一次遍历。
     * 空间复杂度：O(1)，因为只使用了常数个额外变量。
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;

        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }

        return sell2;
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }

        // 创建状态数组
        int[][] dp = new int[3][n];

        for (int k = 1; k <= 2; k++) {
            int maxDiff = -prices[0];
            for (int i = 1; i < n; i++) {
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[k - 1][i] - prices[i]);
            }
        }

        return dp[2][n - 1];
    }

    public static void main(String[] args) {
        BestTimetoBuyAndSellStockIII solution = new BestTimetoBuyAndSellStockIII();
        
        // 测试用例
        int[] prices1 = {3,3,5,0,0,3,1,4};
        int result1 = solution.maxProfit(prices1);
        System.out.println("Example 1: " + result1); // 输出: 6

        int[] prices2 = {1,2,3,4,5};
        int result2 = solution.maxProfit(prices2);
        System.out.println("Example 2: " + result2); // 输出: 4

        int[] prices3 = {7,6,4,3,1};
        int result3 = solution.maxProfit(prices3);
        System.out.println("Example 3: " + result3); // 输出: 0
    }
}
