package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 2706. Buy Two Chocolates
 * Easy
 * You are given an integer array prices representing the prices of various chocolates in a store. You are also given a single integer money, which represents your initial amount of money.
 * <p>
 * You must buy exactly two chocolates in such a way that you still have some non-negative leftover money. You would like to minimize the sum of the prices of the two chocolates you buy.
 * <p>
 * Return the amount of money you will have leftover after buying the two chocolates. If there is no way for you to buy two chocolates without ending up in debt, return money. Note that the leftover must be non-negative.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: prices = [1,2,2], money = 3
 * Output: 0
 * Explanation: Purchase the chocolates priced at 1 and 2 units respectively. You will have 3 - 3 = 0 units of money afterwards. Thus, we return 0.
 * Example 2:
 * <p>
 * Input: prices = [3,2,3], money = 3
 * Output: 3
 * Explanation: You cannot buy 2 chocolates without going in debt, so we return 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= prices.length <= 50
 * 1 <= prices[i] <= 100
 * 1 <= money <= 100
 */
public class BuyTwoChocolates {


    /**
     * Greedy
     * Time complexity:
     * O(nlogn)O(nlogn)O(nlogn)
     * <p>
     * Space complexity:
     * O(n)O(n)O(n) or O(nlogn)O(nlogn)O(nlogn)
     *
     * @param prices
     * @param money
     * @return
     */
    public static int buyChoco(int[] prices, int money) {
        // Sort the Array in Increasing Order
        Arrays.sort(prices);

        // Minimum Cost
        int minCost = prices[0] + prices[1];

        // We can buy chocolates only if we have enough money
        if (minCost <= money) {
            // Return the Amount of Money Left
            return money - minCost;
        } else {
            // We cannot buy chocolates. Return the initial amount of money
            return money;
        }
    }

    /**
     * 解题思路：
     * 首先，对价格数组进行排序。
     * 然后，使用双指针方法，一个指针指向数组的起始位置，另一个指针指向数组的末尾位置。
     * 每次比较两个指针所指向的元素的和与money的大小：
     * - 如果和小于等于money，说明可以购买这两个巧克力。更新money的值，并将两个指针向中间移动一位。
     * - 如果和大于money，说明购买这两个巧克力会超出预算。将末尾指针向前移动一位。
     * 重复上述步骤，直到两个指针相遇或者找到满足条件的两个巧克力。
     * 最后，返回剩余的money值。
     * <p>
     * 算法复杂度：
     * 时间复杂度：O(nlogn)，其中n为数组的长度，排序的时间复杂度为O(nlogn)。
     * 空间复杂度：O(1)。
     * <p>
     * todo:还有些问题
     *
     * @param prices 巧克力的价格数组
     * @param money  初始金额
     * @return 剩余的金额
     */
    public static int buyChocoTwoPointers(int[] prices, int money) {
        Arrays.sort(prices);
        int left = 0;
        int right = prices.length - 1;
        while (left < right) {
            int sum = prices[left] + prices[right];
            if (sum <= money) {
                money -= sum;
                left++;
                right--;
            } else {
                right--;
            }
        }
        return money;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] prices1 = {1, 2, 2};
        int money1 = 3;
        // 期望输出：0
        System.out.println(buyChoco(prices1, money1));

        // 测试用例2
        int[] prices2 = {3, 2, 3};
        int money2 = 3;
        // 期望输出：3
        System.out.println(buyChoco(prices2, money2));
    }
}