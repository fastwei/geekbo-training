package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
 * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {

    /**
     * 解题思路：
     * <p>
     * 首先，创建一个空的结果列表 merged。
     * 遍历原始区间数组 intervals，将不重叠且在 newInterval 之前的区间添加到 merged 中。
     * 合并与 newInterval 重叠的区间，更新 newInterval 的起始位置为重叠区间的最小起始位置，
     * 更新 newInterval 的结束位置为重叠区间的最大结束位置。
     * 将合并后的 newInterval 添加到 merged 中。
     * 添加剩余的不重叠区间到 merged 中。
     * 将 merged 列表转换为数组并返回作为结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历原始区间数组的时间复杂度为 O(n)，其中 n 是区间的数量。因此总体时间复杂度为 O(n)。
     * 空间复杂度：除了存储结果的 merged 列表外，额外使用的空间为常数级别，因此空间复杂度为 O(n)。
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> merged = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 添加在 newInterval 之前的不重叠区间
        while (i < n && intervals[i][1] < newInterval[0]) {
            merged.add(intervals[i]);
            i++;
        }

        // 合并与 newInterval 重叠的区间
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        merged.add(newInterval);

        // 添加剩余的不重叠区间
        while (i < n) {
            merged.add(intervals[i]);
            i++;
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        int[][] mergedIntervals1 = insert(intervals1, newInterval1);
        System.out.println(Arrays.deepToString(mergedIntervals1));
        // 预期输出: [[1, 5], [6, 9]]

        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        int[][] mergedIntervals2 = insert(intervals2, newInterval2);
        System.out.println(Arrays.deepToString(mergedIntervals2));
        // 预期输出: [[1, 2], [3, 10], [12, 16]]
    }
}