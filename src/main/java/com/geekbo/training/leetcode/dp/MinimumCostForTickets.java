package com.geekbo.training.leetcode.dp;

/**
 * 983. Minimum Cost For Tickets
 * Medium

 * You have planned some train traveling one year in advance.
 * The days of the year in which you will travel are given as an integer array days.
 * Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in three different ways:
 *
 * a 1-day pass is sold for costs[0] dollars,
 * a 7-day pass is sold for costs[1] dollars, and
 * a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 *
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 *
 *
 *
 * Example 1:
 *
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 * On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 * On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 * In total, you spent $11 and covered all the days of your travel.
 * Example 2:
 *
 * Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * Output: 17
 * Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
 * On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 * On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 * In total, you spent $17 and covered all the days of your travel.
 *
 */
public class MinimumCostForTickets {
    /**
     * 解题思路：
     * 使用动态规划的方法。
     * 定义一个一维数组dp，dp[i]表示在第i天旅行所需的最小费用。
     * 初始时，dp[0] = 0。
     * 对于每一天i，考虑三种购买方式的情况：
     * 1. 如果选择1天通行证，则前一天的费用加上当天购买的费用，即dp[i] = dp[i - 1] + costs[0]。
     * 2. 如果选择7天通行证，则找到前7天内的最小费用，加上7天通行证的费用，即dp[i] = min(dp[i - 7], dp[i - 6], ..., dp[i - 1]) + costs[1]。
     * 3. 如果选择30天通行证，则找到前30天内的最小费用，加上30天通行证的费用，即dp[i] = min(dp[i - 30], dp[i - 29], ..., dp[i - 1]) + costs[2]。
     * 最终返回dp[n]，其中n是旅行天数。
     *
     * 算法复杂度分析：
     * 遍历旅行天数的数量为n，遍历三种购买方式的数量为3，所以总的时间复杂度为O(n)。
     * 使用了一个一维数组dp，空间复杂度为O(n)。
     * 因此，总的空间复杂度为O(n)。
     */
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n + 1];
        int[] durations = {1, 7, 30};

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            int j = i;
            for (int k = 0; k < 3; k++) {
                while (j > 0 && days[i - 1] - days[j - 1] < durations[k]) {
                    j--;
                }
                dp[i] = Math.min(dp[i], dp[j] + costs[k]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // 创建解法对象
        MinimumCostForTickets solution = new MinimumCostForTickets();

        // 创建测试用例1
        // 预期输出: 11
        int[] days1 = {1, 4, 6, 7, 8, 20};
        int[] costs1 = {2, 7, 15};
        int result1 = solution.mincostTickets(days1, costs1);
        System.out.println(result1);

        // 创建测试用例2
        // 预期输出: 17
        int[] days2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] costs2 = {2, 7, 15};
        int result2 = solution.mincostTickets(days2, costs2);
        System.out.println(result2);
    }
}

