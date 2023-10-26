package com.geekbo.training.leetcode.top150.hard;


/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 * <p>
 * Find the maximum profit you can achieve. You may complete at most k transactions: i.e.
 * you may buy at most k times and sell at most k times.
 * <p>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * Example 2:
 * <p>
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 * Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= 100
 * 1 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 *
 * todo:加深理解
 */
public class BestTimeToBuyAndSellStockIV {
    /**
     * 计算最大利润
     * 解题思路： 本题可以使用动态规划来解决。
     * 定义一个三维数组dp，其中dp[i][j][0]表示第i天最多进行j次交易且不持有股票时的最大利润，
     * dp[i][j][1]表示第i天最多进行j次交易且持有股票时的最大利润。
     * <p>
     * 状态转移方程为：
     * <p>
     * = max(dp[i-1][j][0], dp[i-1][j][1] + prices[i])`
     * - `dp[i][j][1] = max(dp[i-1][j][1], dp[i-1][j-1][0] - prices[i])`
     * <p>
     * 其中，`prices[i]`表示第`i`天的股票价格。
     * <p>
     * 初始状态为：
     * - `dp[0][j][0] = 0`，第一天不持有股票时的最大利润为0
     * - `dp[0][j][1] = -prices[0]`，第一天持有股票时的最大利润为`-prices[0]`
     * <p>
     * 最终，答案为`dp[n-1][k][0]`，其中`n`为股票价格数组的长度。
     * <p>
     * 如果交易次数`k`大于等于数组长度的一半，相当于可以进行无限次交易，直接返回无限次交易的最大利润。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：O(n * k)，其中n为股票价格数组的长度，k为最多交易次数。
     * - 空间复杂度：O(n * k)。
     *
     * @param k      最多交易次数
     * @param prices 股票价格数组
     * @return 返回最大利润
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        // 如果交易次数超过数组长度的一半，相当于可以无限交易，直接返回最大利润即可
        if (k >= n / 2) {
            return maxProfitWithUnlimitedTransactions(prices);
        }

        // 定义状态数组
        int[][][] dp = new int[n][k + 1][2];

        // 初始化状态
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= 1; j--) {
                if (i == 0) { // 处理第一天的情况
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                } else {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }

        return dp[n - 1][k][0];
    }

    /**
     * 计算无限次交易的最大利润
     *
     * @param prices 股票价格数组
     * @return 返回最大利润
     */
    private int maxProfitWithUnlimitedTransactions(int[] prices) {
        int n = prices.length;
        int maxProfit = 0;

        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        // 测试用例1
        int k1 = 2;
        int[] prices1 = {2, 4, 1};
        int expected1 = 2;
        BestTimeToBuyAndSellStockIV solution = new BestTimeToBuyAndSellStockIV();
        int result1 = solution.maxProfit(k1, prices1);
        System.out.println(result1 == expected1); // true

        // 测试用例2
        int k2 = 2;
        int[] prices2 = {3, 2, 6, 5, 0, 3};
        int expected2 = 7;
        int result2 = solution.maxProfit(k2, prices2);
        System.out.println(result2 == expected2); // true
    }
}
