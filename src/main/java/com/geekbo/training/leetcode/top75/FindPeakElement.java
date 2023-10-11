package com.geekbo.training.leetcode.top75;

/**
 *162. Find Peak Element
 * Medium
 * Topics
 * Companies
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 *
 * 解题思路：
 *
 * 使用二分查找法来查找峰值元素。
 * 在每一步中，比较中间元素与其右侧元素，如果中间元素小于右侧元素，说明右侧存在更大的元素，将搜索范围移到右侧。
 * 否则，说明中间元素大于或等于右侧元素，左侧存在更大的元素，将搜索范围移到左侧。
 * 最终返回左边界，即峰值的索引。
 * 这个算法的时间复杂度是O(log n)，因为每一步都将搜索范围减半。
 *
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                // 当中间元素小于右侧元素时，右侧存在更大的元素，将搜索范围移到右侧
                left = mid + 1;
            } else {
                // 当中间元素大于或等于右侧元素时，左侧存在更大的元素，将搜索范围移到左侧
                right = mid;
            }
        }

        return left; // 返回左边界，即峰值的索引
    }

    public static void main(String[] args) {
        FindPeakElement solution = new FindPeakElement();

        int[] nums1 = {1, 2, 3, 1};
        int result1 = solution.findPeakElement(nums1);
        System.out.println("Test Case 1: " + result1); // 预期输出：2

        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        int result2 = solution.findPeakElement(nums2);
        System.out.println("Test Case 2: " + result2); // 预期输出：2 或 5
    }
}
