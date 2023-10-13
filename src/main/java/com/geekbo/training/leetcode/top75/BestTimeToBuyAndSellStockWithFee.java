package com.geekbo.training.leetcode.top75;

import java.util.Arrays;

/**
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 *
 * Note:
 *
 * You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * The transaction fee is only charged once for each stock purchase and sale.
 *
 *
 * Example 1:
 *
 * Input: prices = [1,3,2,8,4,9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * - Buying at prices[0] = 1
 * - Selling at prices[3] = 8
 * - Buying at prices[4] = 4
 * - Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * Example 2:
 *
 * Input: prices = [1,3,7,5,10,3], fee = 3
 * Output: 6
 *
 */
public class BestTimeToBuyAndSellStockWithFee {

    /**
     * 解题思路：
     * <p>
     * 题目要求计算股票交易的最大利润，每次交易需要支付一定的交易费用。
     * 我们可以使用动态规划来解决此问题。
     * 创建两个变量 buy 和 sell，分别表示当前买入和卖出的状态。
     * 遍历股票价格数组，对于每一天的价格，更新买入和卖出的状态：
     * 更新买入状态：当前买入状态可以继续持有之前买入的股票，或者卖出之前的股票后再买入当前的股票。
     * 更新卖出状态：当前卖出状态可以继续持有之前卖出的股票，或者买入之前的股票后再卖出当前的股票。
     * 最后，返回卖出状态的值，即最大利润。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n)，其中n是股票价格数组的长度，需要遍历每一天的价格。
     * 空间复杂度：O(1)，只需要常数空间保存买入和卖出的状态。
     *
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;

        // 创建两个变量，用于保存买入和卖出的状态
        int buy = -prices[0]; // 初始买入状态
        int sell = 0; // 初始卖出状态

        // 动态规划计算最大利润
        for (int i = 1; i < n; i++) {
            int prevBuy = buy; // 保存之前的买入状态
            buy = Math.max(buy, sell - prices[i]); // 更新买入状态
            sell = Math.max(sell, prevBuy + prices[i] - fee); // 更新卖出状态
        }

        // 返回最大利润
        return sell;
    }

    public static void main(String[] args) {
        // 测试用例
        int[] prices1 = {1, 3, 2, 8, 4, 9};
        int fee1 = 2;
        int result1 = maxProfit(prices1, fee1);
        System.out.println("prices = " + Arrays.toString(prices1) + ", fee = " + fee1 + "，最大利润：" + result1 + "，预期结果：8");

        int[] prices2 = {1, 3, 7, 5, 10, 3};
        int fee2 = 3;
        int result2 = maxProfit(prices2, fee2);
        System.out.println("prices = " + Arrays.toString(prices2) + ", fee = " + fee2 + "，最大利润：" + result2 + "，预期结果：6");
    }
}