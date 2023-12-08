package com.geekbo.training.leetcode.dp;

import java.util.Arrays;
import java.util.TreeMap;

/**
 *
 * 2008. Maximum Earnings From Taxi
 * Medium
 * There are n points on a road you are driving your taxi on. The n points on the road are labeled from 1 to n in the direction you are going, and you want to drive from point 1 to point n to make money by picking up passengers. You cannot change the direction of the taxi.
 *
 * The passengers are represented by a 0-indexed 2D integer array rides, where rides[i] = [starti, endi, tipi] denotes the ith passenger requesting a ride from point starti to point endi who is willing to give a tipi dollar tip.
 *
 * For each passenger i you pick up, you earn endi - starti + tipi dollars. You may only drive at most one passenger at a time.
 *
 * Given n and rides, return the maximum number of dollars you can earn by picking up the passengers optimally.
 *
 * Note: You may drop off a passenger and pick up a different passenger at the same point.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, rides = [[2,5,4],[1,5,1]]
 * Output: 7
 * Explanation: We can pick up passenger 0 to earn 5 - 2 + 4 = 7 dollars.
 * Example 2:
 *
 * Input: n = 20, rides = [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
 * Output: 20
 * Explanation: We will pick up the following passengers:
 * - Drive passenger 1 from point 3 to point 10 for a profit of 10 - 3 + 2 = 9 dollars.
 * - Drive passenger 2 from point 10 to point 12 for a profit of 12 - 10 + 3 = 5 dollars.
 * - Drive passenger 5 from point 13 to point 18 for a profit of 18 - 13 + 1 = 6 dollars.
 * We earn 9 + 5 + 6 = 20 dollars in total.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * 1 <= rides.length <= 3 * 104
 * rides[i].length == 3
 * 1 <= starti < endi <= n
 * 1 <= tipi <= 105
 *
 */
public class TaxiProfit {
    /**
     * 解题思路：
     * <p>
     * 首先按照乘客的终点位置对乘客进行排序，这样可以保证我们在遍历乘客信息时，乘客的终点位置是递增的。
     * 使用dp数组来记录到达每个位置时的最大盈利，dp[i]表示到达位置i时的最大盈利。
     * 初始化dp数组，将所有位置的最大盈利都设为0。
     * 遍历位置1到n，对于每个位置i，我们遍历乘客信息，更新最大盈利：
     * 如果乘客的终点位置等于当前位置i，我们计算盈利并更新最大盈利。
     * 最后返回dp数组中的最大值，即为最大盈利。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 对乘客进行排序的时间复杂度为O(mlogm)，其中m为乘客的数量。
     * 遍历位置1到n以及乘客信息的时间复杂度为O(nm)，其中n为位置的数量。
     * 因此，总体时间复杂度为O(mlogm + nm)。
     *
     * todo:会有超时问题
     * @param n
     * @param rides
     * @return
     */
    public static int maxTaxiProfit(int n, int[][] rides) {
        // 首先按照乘客的终点位置对乘客进行排序
        Arrays.sort(rides, (a, b) -> a[1] - b[1]);

        int[] dp = new int[n + 1]; // dp数组表示到达每个位置时的最大盈利
        int maxProfit = 0;

        for (int i = 1; i <= n; i++) {
            // 当前位置的最大盈利等于前一个位置的最大盈利
            dp[i] = dp[i - 1];

            // 遍历乘客信息，更新最大盈利
            for (int[] ride : rides) {
                int start = ride[0];
                int end = ride[1];
                int tip = ride[2];

                // 如果乘客的终点位置等于当前位置
                if (end == i) {
                    // 更新最大盈利
                    dp[i] = Math.max(dp[i], dp[start] + end - start + tip);
                }
            }

            // 更新最大盈利
            maxProfit = Math.max(maxProfit, dp[i]);
        }

        return maxProfit;
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> (a[1] - b[1]));
        TreeMap<Integer, Long> dp = new TreeMap<>();
        dp.put(0, 0L);
        for (int[] ride : rides) {
            long currEarning = ride[1] - ride[0] + ride[2] + dp.floorEntry(ride[0]).getValue();
            if (currEarning > dp.lastEntry().getValue()) {
                dp.put(ride[1], currEarning);
            }
        }
        return dp.lastEntry().getValue();
    }

    public static void main(String[] args) {
        // 测试用例
        int n1 = 5;
        int[][] rides1 = {{2, 5, 4}, {1, 5, 1}};
        // 预期输出: 7
        System.out.println(maxTaxiProfit(n1, rides1));

        int n2 = 20;
        int[][] rides2 = {{1, 6, 1}, {3, 10, 2}, {10, 12, 3}, {11, 12, 2}, {12, 15, 2}, {13, 18, 1}};
        // 预期输出: 20
        System.out.println(maxTaxiProfit(n2, rides2));
    }
}