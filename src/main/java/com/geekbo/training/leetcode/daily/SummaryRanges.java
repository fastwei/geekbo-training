package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 352. Data Stream as Disjoint Intervals
 * Hard
 * Given a data stream input of non-negative integers a1, a2, ..., an,
 * summarize the numbers seen so far as a list of disjoint intervals.
 * <p>
 * Implement the SummaryRanges class:
 * <p>
 * SummaryRanges() Initializes the object with an empty stream.
 * void addNum(int value) Adds the integer value to the stream.
 * int[][] getIntervals() Returns a summary of the integers
 * in the stream currently as a list of disjoint intervals [starti, endi].
 * The answer should be sorted by starti.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * Output
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 * <p>
 * Explanation
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // return [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= value <= 104
 * At most 3 * 104 calls will be made to addNum and getIntervals.
 * At most 102 calls will be made to getIntervals.
 * <p>
 * <p>
 * Follow up: What if there are lots of merges and the number of disjoint intervals is small compared to the size of the data stream?
 */
class SummaryRanges {
    private List<int[]> intervals;

    public SummaryRanges() {
        intervals = new ArrayList<>();
    }

    /**
     * 解题思路：
     * <p>
     * 使用一个列表intervals来保存区间的起始和结束值。
     * 在addNum方法中，使用二分查找找到插入新值的位置。
     * 插入新值时，需要考虑是否需要合并区间。
     * 如果新值可以和左边的区间合并，则更新左边的区间，如果左边的区间可以和右边的区间合并，则更新左边的区间。
     * 同时，根据合并的情况进行区间的删除和插入操作。
     * <p>
     * 在getIntervals方法中，将intervals列表转换为二维数组形式，并返回结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * addNum方法中，二分查找的时间复杂度为O(logN)，合并区间的时间复杂度为O(1)，因此总体时间复杂度为O(logN)。
     * getIntervals方法中，将intervals列表转换为二维数组的时间复杂度为O(N)，因此总体时间复杂度为O(N)。
     *
     * @param value
     */
    public void addNum(int value) {
        int n = intervals.size();
        int left = 0;
        int right = n - 1;

        // Binary search to find the insertion position for the new value
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int[] interval = intervals.get(mid);

            if (value < interval[0]) {
                right = mid - 1;
            } else if (value > interval[1]) {
                left = mid + 1;
            } else {
                return; // Value already exists in an interval
            }
        }

        // Insert the new value as a new interval or merge with existing intervals
        int[] newInterval = new int[]{value, value};
        intervals.add(left, newInterval);

        // Merge intervals if necessary
        if (left > 0 && intervals.get(left - 1)[1] + 1 >= value) {
            intervals.set(left - 1, mergeIntervals(intervals.get(left - 1), newInterval));
            intervals.remove(left);
            left--;
        }
        if (left < n && intervals.get(left)[0] - 1 <= value) {
            intervals.set(left, mergeIntervals(newInterval, intervals.get(left)));
            if (left < n - 1 && intervals.get(left)[1] + 1 >= intervals.get(left + 1)[0]) {
                intervals.set(left, mergeIntervals(intervals.get(left), intervals.get(left + 1)));
                intervals.remove(left + 1);
            }
        }
    }

    public int[][] getIntervals() {
        mergeIntervals();
        int[][] result = new int[intervals.size()][2];
        for (int i = 0; i < intervals.size(); i++) {
            result[i] = intervals.get(i);
        }
        return result;
    }

    private void mergeIntervals() {
        List<int[]> mergedIntervals = new ArrayList<>();
        for (int[] interval : intervals) {
            if (mergedIntervals.isEmpty() || mergedIntervals.get(mergedIntervals.size() - 1)[1] + 1 < interval[0]) {
                mergedIntervals.add(interval);
            } else {
                mergedIntervals.get(mergedIntervals.size() - 1)[1] = Math.max(mergedIntervals.get(mergedIntervals.size() - 1)[1], interval[1]);
            }
        }
        intervals = mergedIntervals;
    }

    private int[] mergeIntervals(int[] interval1, int[] interval2) {
        return new int[]{Math.min(interval1[0], interval2[0]), Math.max(interval1[1], interval2[1])};
    }

    public static void main(String[] args) {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);
        // Expected output: [[1, 1]]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));

        summaryRanges.addNum(3);
        // Expected output: [[1, 1], [3, 3]]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));

        summaryRanges.addNum(7);
        // Expected output: [[1, 1], [3, 3], [7, 7]]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));

        summaryRanges.addNum(2);
        // Expected output: [[1, 3], [7, 7]]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));

        summaryRanges.addNum(6);
        // Expected output: [[1, 3], [6, 7]]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
    }
}
