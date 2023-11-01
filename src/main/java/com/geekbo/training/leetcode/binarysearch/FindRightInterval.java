package com.geekbo.training.leetcode.binarysearch;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 * <p>
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized. Note that i may equal j.
 * <p>
 * Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,2]]
 * Output: [-1]
 * Explanation: There is only one interval in the collection, so it outputs -1.
 * Example 2:
 * <p>
 * Input: intervals = [[3,4],[2,3],[1,2]]
 * Output: [-1,0,1]
 * Explanation: There is no right interval for [3,4].
 * The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
 * The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
 * Example 3:
 * <p>
 * Input: intervals = [[1,4],[2,3],[3,4]]
 * Output: [-1,2,-1]
 * Explanation: There is no right interval for [1,4] and [3,4].
 * The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
 *
 * todo:待加深理解
 */
public class FindRightInterval {
    /**
     * 解题思路：
     * <p>
     * 首先，我们将每个区间的起始位置和索引保存到一个新的二维数组中。
     * 然后，我们根据起始位置对新的二维数组进行排序。
     * 对于每个区间，我们使用二分搜索找到第一个起始位置大于等于当前区间结束位置的区间，并记录其索引作为右侧区间的索引。
     * 最后，将每个区间的右侧区间索引保存到结果数组中。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：排序的时间复杂度为O(nlogn)，每个区间的查找时间复杂度为O(logn)，因此总时间复杂度为O(nlogn)。
     * 空间复杂度：除了输出数组外，我们使用了额外的二维数组来保存每个区间的起始位置和索引，
     * 因此，空间复杂度为O(n)，其中n是区间的数量。
     * <p>
     * 请注意，这里的二分搜索使用的是迭代版本，而不是递归版本。
     * 迭代版本的二分搜索通常比递归版本更高效，因为它不会在每次递归调用时创建新的函数栈帧。
     * <p>
     * 总结一下，该问题的解决方案是根据起始位置对区间进行排序，然后使用二分搜索找到每个区间的右侧区间索引。
     * 算法的时间复杂度为O(nlogn)，空间复杂度为O(n)。
     *
     * @param intervals
     * @return
     */
    public static int[] findRightInterval(int[][] intervals) {
        // 创建一个二维数组，保存每个区间的起始位置和索引
        int[][] intervalsWithIndex = new int[intervals.length][3];
        for (int i = 0; i < intervals.length; i++) {
            intervalsWithIndex[i][0] = intervals[i][0];
            intervalsWithIndex[i][1] = intervals[i][1];
            intervalsWithIndex[i][2] = i;
        }

        // 根据起始位置对二维数组进行排序
        Arrays.sort(intervalsWithIndex, Comparator.comparingInt(a -> a[0]));

        // 创建一个结果数组，用于保存每个区间的右侧区间索引
        int[] result = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            int target = intervals[i][1]; // 当前区间的结束位置
            int left = 0;
            int right = intervalsWithIndex.length - 1;
            int index = -1; // 右侧区间的索引

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (intervalsWithIndex[mid][0] >= target) {
                    index = intervalsWithIndex[mid][2]; // 更新右侧区间的索引
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            result[i] = index; // 将右侧区间的索引保存到结果数组中
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 2}};
        // 预期输出: [-1]
        System.out.println(Arrays.toString(findRightInterval(intervals1)));

        int[][] intervals2 = {{3, 4}, {2, 3}, {1, 2}};
        // 预期输出: [-1, 0, 1]
        System.out.println(Arrays.toString(findRightInterval(intervals2)));

        int[][] intervals3 = {{1, 4}, {2, 3}, {3, 4}};
        // 预期输出: [-1, 2, -1]
        System.out.println(Arrays.toString(findRightInterval(intervals3)));
    }
}
