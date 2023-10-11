package com.geekbo.training.leetcode.top150;

/**
 * Array / String
 *
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 *
 * On each day, you may decide to buy and/or sell the stock.
 * You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit,
 * so we never buy the stock to achieve the maximum profit of 0.
 *
 *解题思路：
 *
 * 我们遍历价格数组，如果当前价格高于前一天的价格，说明可以在前一天买入并在当天卖出，从而获取利润。
 * 累加这些利润即可得到最大利润。
 * 算法复杂度：
 *
 * 时间复杂度：O(n)，其中 n 是价格数组的长度，因为我们只需要一次遍历数组。
 * 空间复杂度：O(1)，因为我们只需要常数个额外变量来保存状态。
 *
 *
 *
 *
 *
 *
 *
 */
public class BestTimetoBuyAndSellStockII {
    public int maxProfit(int[] prices) {
        int maxProfit = 0; // 初始化最大利润为0

        // 遍历价格数组
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                // 如果当前价格高于前一天的价格，计算并累加利润
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        return maxProfit; // 返回最大利润
    }

    public static void main(String[] args) {
        BestTimetoBuyAndSellStockII solution = new BestTimetoBuyAndSellStockII();

        // 测试用例1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int result1 = solution.maxProfit(prices1);
        System.out.println("Example 1: " + result1); // 预期输出：7

        // 测试用例2
        int[] prices2 = {1, 2, 3, 4, 5};
        int result2 = solution.maxProfit(prices2);
        System.out.println("Example 2: " + result2); // 预期输出：4

        // 测试用例3
        int[] prices3 = {7, 6, 4, 3, 1};
        int result3 = solution.maxProfit(prices3);
        System.out.println("Example 3: " + result3); // 预期输出：0
    }
}
