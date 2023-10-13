package com.geekbo.training.leetcode.top75;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 * <p>
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class NonOverlappingIntervals {

    /**
     * 动态规划算法（dp）实现：
     * <p>
     * 首先，对区间按照开始时间进行排序。
     * 初始化一个长度为区间个数的dp数组，dp[i]表示以第i个区间为结尾的最长不重叠区间的个数，初始化为1。
     * 遍历排序后的区间数组，从第二个区间开始：
     * 对于每个区间，遍历之前的区间，如果当前区间的开始时间大于等于之前区间的结束时间，说明不重叠，更新dp[i]为dp[j] + 1，其中j为之前区间的索引。
     * 最后，找到dp数组中的最大值maxLen，返回需要移除的区间数，即区间个数 - maxLen。
     * <p>
     * 算法复杂度
     * <p>
     * 动态规划算法（dp）实现：
     * <p>
     * 时间复杂度：O(n^2)，其中n是区间的个数。需要对区间进行排序，时间复杂度为O(nlogn)。然后需要两层嵌套循环，时间复杂度为O(n^2)。
     * 空间复杂度：O(n)。需要使用一个长度为区间个数的dp数组。
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0])); // 按照开始时间对区间进行排序

        int[] dp = new int[intervals.length]; // dp数组，dp[i]表示以第i个区间为结尾的最长不重叠区间的个数
        Arrays.fill(dp, 1);
        int maxLen = 1; // 最长不重叠区间的个数

        for (int i = 1; i < intervals.length; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[i][0] >= intervals[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return intervals.length - maxLen; // 返回需要移除的区间数
    }

    /**
     * 有时间再熟悉，greedy算法实现
     * <p>
     * 解题思路：
     * <p>
     * 首先，对区间按照结束时间进行排序。
     * 初始化一个变量end为第一个区间的结束时间，表示当前不重叠区间的结束时间。
     * 初始化一个变量count为1，表示最少需要移除的区间数，初始值为1是因为第一个区间是不重叠的。
     * 遍历排序后的区间数组，从第二个区间开始：
     * 如果当前区间的开始时间大于等于end，说明不重叠，更新end为当前区间的结束时间，同时count加1。
     * 最后，返回需要移除的区间数，即intervals.length - count。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(nlogn)，其中n是区间的个数。需要对区间进行排序，时间复杂度为O(nlogn)。然后需要遍历排序后的区间数组，时间复杂度为O(n)。
     * 空间复杂度：O(1)。除了常数级别的变量之外，不需要使用额外的空间。
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals2(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }

        // 按照结束时间对区间进行排序
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));

        int end = intervals[0][1]; // 当前不重叠区间的结束时间
        int count = 1; // 最少需要移除的区间数

        for (int i = 1; i < intervals.length; i++) {
            // 如果当前区间的开始时间大于等于上一个区间的结束时间，说明不重叠
            if (intervals[i][0] >= end) {
                end = intervals[i][1]; // 更新结束时间
                count++; // 区间数加1
            }
        }

        return intervals.length - count; // 返回需要移除的区间数
    }

    public static void main(String[] args) {
        // 测试用例
        NonOverlappingIntervals solution = new NonOverlappingIntervals();
        int[][] intervals1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(solution.eraseOverlapIntervals(intervals1)); // 1
        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        System.out.println(solution.eraseOverlapIntervals(intervals2)); // 2
        int[][] intervals3 = {{1, 2}, {2, 3}};
        System.out.println(solution.eraseOverlapIntervals(intervals3)); // 0
    }
}