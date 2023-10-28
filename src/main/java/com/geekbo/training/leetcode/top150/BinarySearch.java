package com.geekbo.training.leetcode.top150;

import java.util.Arrays;

/**
 * Given an array of integers nums which is sorted in ascending order,
 * and an integer target, write a function to search target in nums.
 * If target exists, then return its index. Otherwise, return -1.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * Example 2:
 * <p>
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 */
public class BinarySearch {

    /**
     * 在升序数组中搜索目标值的索引
     * 解题思路:
     * <p>
     * 因为数组是升序排列的，所以可以使用二分查找算法来快速定位目标值。
     * 初始化左指针 left 为数组开头的索引，右指针 right 为数组末尾的索引。
     * 在每次循环中，计算中间元素的索引 mid，并与目标值进行比较：
     * 如果 nums[mid] 等于目标值，则返回 mid。
     * 如果 nums[mid] 小于目标值，则说明目标值位于 mid 的右侧，更新 left = mid + 1。
     * 如果 nums[mid] 大于目标值，则说明目标值位于 mid 的左侧，更新 right = mid - 1。
     * 当 left > right 时，说明目标值不存在于数组中，返回 -1。
     * <p>
     * 算法复杂度分析:
     * <p>
     * 时间复杂度: 二分查找的时间复杂度为 O(log n)，其中 n 为数组的长度。
     * 空间复杂度: 二分查找只使用了常数级别的额外空间，所以空间复杂度为 O(1)。
     * 请注意，以上代码假设输入的数组 nums 是升序排列的。
     *
     * @param nums   升序数组
     * @param target 目标值
     * @return 目标值的索引，如果不存在则返回 -1
     */
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        int expected1 = 4;
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("Expected output: " + expected1);
        System.out.println("Actual output: " + search(nums1, target1));
        System.out.println();

        // 测试用例2
        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
        int expected2 = -1;
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("Expected output: " + expected2);
        System.out.println("Actual output: " + search(nums2, target2));
        System.out.println();
    }
}


