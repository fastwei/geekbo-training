package com.geekbo.training.leetcode.top150;

import org.springframework.util.Assert;

/**
 *Array / String
 *
 *You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 * Example 2:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 *
 *解题思路：
 *
 * 我们通过遍历价格数组，同时维护最小价格和最大利润两个变量。
 * 如果当前价格小于最小价格，我们更新最小价格。
 * 如果当前价格与最小价格之差大于最大利润，我们更新最大利润。
 * 最后返回最大利润即可。
 * 算法复杂度：
 *
 * 时间复杂度：O(n)，其中 n 是价格数组的长度，因为我们只需要一次遍历数组。
 * 空间复杂度：O(1)，因为我们只需要常数个额外变量来保存状态。
 *
 * Array / String
 */
public class BestTimetoBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int maxProfit = 0; // 初始化最大利润为0
        int minPrice = Integer.MAX_VALUE; // 初始化最小价格为整数最大值

        // 遍历价格数组
        for (int price : prices) {
            if (price < minPrice) {
                // 如果当前价格小于最小价格，更新最小价格
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                // 如果当前价格与最小价格之差大于最大利润，更新最大利润
                maxProfit = price - minPrice;
            }
        }

        return maxProfit; // 返回最大利润
    }

    public static void main(String[] args) {
        BestTimetoBuyAndSellStock solution = new BestTimetoBuyAndSellStock();

        // 测试用例1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int result1 = solution.maxProfit(prices1);
        Assert.isTrue(result1 == 5, "Test Case 1 failed");
        System.out.println("Example 1: " + result1); // 预期输出：5

        // 测试用例2
        int[] prices2 = {7, 6, 4, 3, 1};
        int result2 = solution.maxProfit(prices2);
        Assert.isTrue(result2 == 0, "Test Case 2 failed");
        System.out.println("Example 2: " + result2); // 预期输出：0
    }
}
