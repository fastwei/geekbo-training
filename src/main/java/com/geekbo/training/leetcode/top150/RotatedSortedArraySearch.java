package com.geekbo.training.leetcode.top150;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [1], target = 0
 * Output: -1
 */
public class RotatedSortedArraySearch {
    /**
     * 在旋转有序数组中查找目标值的索引
     * 解题思路：
     * <p>
     * 使用二分查找算法，在每一次查找的过程中判断目标值在哪一半有序的区间内。
     * 首先判断左半边还是右半边是有序的，通过比较左端点和中间点的值。
     * 如果目标值在有序的半边范围内，则继续在该半边进行二分查找。
     * 如果目标值不在有序的半边范围内，则在另一半边进行二分查找。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：由于是使用二分查找算法，每次查找都将数组大小减半，因此时间复杂度为O(log n)，其中n为数组的长度。
     * 空间复杂度：除了输入数组外，算法只使用了常数级别的额外空间，因此空间复杂度为O(1)。
     *
     * @param nums   旋转有序数组
     * @param target 目标值
     * @return 目标值在数组中的索引，如果不存在则返回-1
     */
    public static int search(int[] nums, int target) {
        // 数组为空，直接返回-1
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 如果中间值等于目标值，直接返回索引
            if (nums[mid] == target) {
                return mid;
            }

            // 如果左半边是有序的
            if (nums[left] <= nums[mid]) {
                // 如果目标值在左半边范围内
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 如果右半边是有序的
            else {
                // 如果目标值在右半边范围内
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        // 目标值不存在，返回-1
        return -1;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        int expected1 = 4;
        int result1 = search(nums1, target1);
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);
        System.out.println();

        // 测试用例2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        int expected2 = -1;
        int result2 = search(nums2, target2);
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);
        System.out.println();

        // 测试用例3
        int[] nums3 = {1};
        int target3 = 0;
        int expected3 = -1;
        int result3 = search(nums3, target3);
        System.out.println("Expected: " + expected3);
        System.out.println("Result: " + result3);
    }
}
