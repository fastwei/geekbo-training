package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1685. Sum of Absolute Differences in a Sorted Array
 * Medium
 * You are given an integer array nums sorted in non-decreasing order.
 * <p>
 * Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.
 * <p>
 * In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,5]
 * Output: [4,3,5]
 * Explanation: Assuming the arrays are 0-indexed, then
 * result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
 * result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
 * result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
 * Example 2:
 * <p>
 * Input: nums = [1,4,6,8,10]
 * Output: [24,15,13,15,21]
 */
class SumOfAbsoluteDifferencesInSortedArray {
    /**
     * 解题思路： 这道题的关键在于找到一种计算绝对差值之和的方法，使得时间复杂度尽可能低。
     * 我们可以观察到，对于数组中的每个元素nums[i]，其对应的结果result[i]可以分解为两部分：
     * <p>
     * 左侧元素的绝对差值之和：|nums[i] - nums[0]| + |nums[i] - nums[1]| + ... + |nums[i] - nums[i-1]|
     * 右侧元素的绝对差值之和：|nums[i] - nums[i+1]| + |nums[i] - nums[i+2]| + ... + |nums[i] - nums[n-1]|
     * 我们可以先计算整个数组的和sum，然后遍历数组，依次计算每个元素的左侧绝对差值之和和右侧绝对差值之和，然后将两部分相加得到result[i]。
     * 这样可以将时间复杂度优化为O(n)，其中n是数组的长度。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历数组需要O(n)的时间复杂度，计算和sum需要O(n)的时间复杂度，所以总时间复杂度为O(n)。
     * 空间复杂度：我们使用了一个额外的数组result来保存结果，空间复杂度为O(n)。
     *
     * @param nums
     * @return
     */
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int sum = 0;
        int leftSum = 0;

        // 计算数组中所有数字的和
        for (int num : nums) {
            sum += num;
        }

        // 计算第一个数字左边的绝对差值之和
        for (int i = 0; i < n; i++) {
            result[i] = (nums[i] * i) - leftSum + (sum - leftSum) - (nums[i] * (n - i));
            leftSum += nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        SumOfAbsoluteDifferencesInSortedArray solution = new SumOfAbsoluteDifferencesInSortedArray();
        // Test Case 1
        // Explanation: Assuming the arrays are 0-indexed, then
        // result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
        // result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
        // result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
        int[] nums1 = {2, 3, 5};
        int[] expected1 = {4, 3, 5};
        int[] result1 = solution.getSumAbsoluteDifferences(nums1);
        System.out.println(Arrays.equals(result1, expected1) ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2
        // Explanation: Assuming the arrays are 0-indexed, then
        // result[0] = |1-1| + |1-4| + |1-6| + |1-8| + |1-10| = 0 + 3 + 5 + 7 + 9 = 24,
        // result[1] = |4-1| + |4-4| + |4-6| + |4-8| + |4-10| = 3 + 0 + 2 + 4 + 6 = 15,
        // result[2] = |6-1| + |6-4| + |6-6| + |6-8| + |6-10| = 5 + 2 + 0 + 2 + 4 = 13,
        // result[3] = |8-1| + |8-4| + |8-6| + |8-8| + |8-10| = 7 + 4 + 2 + 0 + 2 = 15,
        // result[4] = |10-1| + |10-4| + |10-6| + |10-8| + |10-10| = 9 + 6 + 4 + 2 + 0 = 21.
        int[] nums2 = {1, 4, 6, 8, 10};
        int[] expected2 = {24, 15, 13, 15, 21};
        int[] result2 = solution.getSumAbsoluteDifferences(nums2);
        System.out.println(Arrays.equals(result2, expected2) ? "Test Case 2 Passed" : "Test Case 2 Failed");
    }
}
