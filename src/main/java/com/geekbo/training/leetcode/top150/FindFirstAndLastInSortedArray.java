package com.geekbo.training.leetcode.top150;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 * <p>
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 */
public class FindFirstAndLastInSortedArray {
    /**
     * 寻找目标值在数组中的起始和结束位置
     * 解题思路：
     * <p>
     * 使用两次二分查找，一次查找目标值的左边界，一次查找目标值的右边界。
     * 在查找目标值的左边界时，如果中间值小于目标值，则目标值在右半部分，更新左边界为中间值的右边一位；
     * 否则，目标值在左半部分，更新右边界为中间值的左边一位。
     * 在查找目标值的右边界时，如果中间值小于等于目标值，则目标值在右半部分，更新左边界为中间值的右边一位；
     * 否则，目标值在左半部分，更新右边界为中间值的左边一位。
     * 最终返回左边界和右边界作为结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：使用两次二分查找，每次查找的时间复杂度为O(log n)。因此，总体时间复杂度为O(log n)。
     * 空间复杂度：除了结果数组外，算法的空间复杂度为O(1)。
     *
     * @param nums   数组
     * @param target 目标值
     * @return 目标值在数组中的起始和结束位置
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        // 特殊情况处理
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 寻找左边界
        int left = binarySearchLeft(nums, target);
        // 寻找右边界
        int right = binarySearchRight(nums, target);

        // 如果左边界小于等于右边界，说明目标值存在于数组中
        if (left <= right) {
            result[0] = left;
            result[1] = right;
        }

        return result;
    }

    /**
     * 二分查找目标值的左边界
     *
     * @param nums   数组
     * @param target 目标值
     * @return 目标值的左边界
     */
    private static int binarySearchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    /**
     * 二分查找目标值的右边界
     *
     * @param nums   数组
     * @param target 目标值
     * @return 目标值的右边界
     */
    private static int binarySearchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] expected1 = {3, 4};
        int[] result1 = searchRange(nums1, target1);
        System.out.println("Expected: " + Arrays.toString(expected1));
        System.out.println("Result: " + Arrays.toString(result1));
        System.out.println();

        // 测试用例2
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        int[] expected2 = {-1, -1};
        int[] result2 = searchRange(nums2, target2);
        System.out.println("Expected: " + Arrays.toString(expected2));
        System.out.println("Result: " + Arrays.toString(result2));
        System.out.println();

        // 测试用例3
        int[] nums3 = {};
        int target3 = 0;
        int[] expected3 = {-1, -1};
        int[] result3 = searchRange(nums3, target3);
        System.out.println("Expected: " + Arrays
                .toString(expected3));
        System.out.println("Result: " + Arrays.toString(result3));
    }
}
