package com.geekbo.training.leetcode.top150;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,2,4,5,6,7] might become:
 * <p>
 * [4,5,6,7,0,1,2] if it was rotated 4 times.
 * [0,1,2,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * <p>
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 * Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 * Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 * Example 3:
 * <p>
 * Input: nums = [11,13,15,17]
 * Output: 11
 * Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 */
public class RotatedSortedArraySearch {
    /**
     * 解题思路：
     * <p>
     * 由于数组是旋转有序的，我们可以使用二分查找来找到最小元素。
     * 初始化左边界left为数组的开始位置，右边界right为数组的结束位置。
     * 如果数组没有发生旋转，直接返回数组的第一个元素。
     * 在每一次循环中，计算中间位置mid，然后根据中间元素与右边界元素的大小进行判断：
     * 如果中间元素大于右边界元素，则最小元素在[mid+1, right]之间。
     * 如果中间元素小于右边界元素，则最小元素在[left, mid]之间。
     * 最终，当left和right相等时，找到了最小元素。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 由于每次循环将搜索范围减少一半，所以时间复杂度为O(log n)。
     * 空间复杂度为O(1)。
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // 如果数组没有发生旋转，直接返回数组的第一个元素
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 如果中间元素大于右边界元素，则最小元素在[mid+1, right]之间
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // 如果中间元素小于右边界元素，则最小元素在[left, mid]之间
            else if (nums[mid] < nums[right]) {
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println(findMin(nums1)); // Expected: 1

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(findMin(nums2)); // Expected: 0

        int[] nums3 = {11, 13, 15, 17};
        System.out.println(findMin(nums3)); // Expected: 11
    }
}
