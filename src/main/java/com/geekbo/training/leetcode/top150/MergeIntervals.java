package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 */
public class MergeIntervals {
    /**
     * 解题思路：
     * <p>
     * 首先，对给定的区间数组按照每个区间的起始位置进行排序。
     * 创建一个空的结果列表 merged，将排序后的第一个区间添加到 merged 中。
     * 遍历排序后的区间数组，如果当前区间的起始位置小于等于上一个区间的结束位置，则合并这两个区间，
     * 更新当前区间的结束位置为两个区间结束位置的最大值。
     * 如果当前区间的起始位置大于上一个区间的结束位置，则将当前区间添加到 merged 中。
     * 返回 merged 列表中的区间数组作为结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：排序区间的时间复杂度为 O(nlogn)，遍历区间的时间复杂度为 O(n)，因此总体时间复杂度为 O(nlogn)。
     * 空间复杂度：除了存储结果的 merged 列表外，额外使用的空间为常数级别，因此空间复杂度为 O(n)。
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        // 首先按照每个区间的起始位置进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        // 遍历每个区间，如果当前区间的起始位置小于等于上一个区间的结束位置，则合并区间，否则添加新的区间
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];
            if (currentEnd >= nextStart) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        //测试按照每个区间的起始位置进行排序
        int[][] intervals11 = {{1, 3}, {4, 6}, {1, 10}, {2, 18}};
        System.out.println(Arrays.deepToString(intervals11));
        Arrays.sort(intervals11, (a, b) -> a[0] - b[0]);
        System.out.println(Arrays.deepToString(intervals11));

        int[][] mergedIntervals1 = merge(intervals1);
        System.out.println(Arrays.deepToString(mergedIntervals1));
        // 预期输出: [[1, 6], [8, 10], [15, 18]]

        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] mergedIntervals2 = merge(intervals2);
        System.out.println(Arrays.deepToString(mergedIntervals2));
        // 预期输出: [[1, 5]]
    }
}