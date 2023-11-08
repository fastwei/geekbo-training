package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 164. Maximum Gap
 * Medium
 * Given an integer array nums, return the maximum difference
 * between two successive elements in its sorted form. If the array contains less than two elements, return 0.
 * <p>
 * You must write an algorithm that runs in linear time and uses linear extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 * <p>
 * Input: nums = [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 */
public class MaximumGap {
    /**
     * 计算数组中相邻元素的最大差值
     * 解题思路：
     * <p>
     * 首先判断数组长度是否小于2，如果是则直接返回0。
     * 对数组进行排序。
     * 遍历排序后的数组，计算相邻元素的差值，并更新最大差值。
     * 返回最大差值。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：排序的时间复杂度为O(nlogn)，遍历数组的时间复杂度为O(n)，所以总的时间复杂度为O(nlogn)。
     * 空间复杂度：排序所需要的额外空间为O(n)，所以总的空间复杂度为O(n)。
     *
     * @param nums 整数数组
     * @return 最大差值
     */
    public static int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }

        // 将数组排序
        Arrays.sort(nums);

        int maxGap = 0;
        for (int i = 1; i < n; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }

        return maxGap;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {3, 6, 9, 1};
        int expected1 = 3;
        int result1 = maximumGap(nums1);
        System.out.println("Test Case 1 - Expected: " + expected1 + ", Result: " + result1);

        // 测试用例2
        int[] nums2 = {10};
        int expected2 = 0;
        int result2 = maximumGap(nums2);
        System.out.println("Test Case 2 - Expected: " + expected2 + ", Result: " + result2);
    }
}
