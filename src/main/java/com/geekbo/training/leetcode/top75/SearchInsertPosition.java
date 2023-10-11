package com.geekbo.training.leetcode.top75;

/**
 *
 *Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 * Example 3:
 *
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 *
 * 解题思路：
 *
 * 使用二分查找算法，初始化左边界（left）为0，右边界（right）为数组长度减1。
 * 在每次循环中，计算中间位置（mid），检查中间元素是否等于目标值。
 * 如果中间元素等于目标值，返回中间位置。
 * 如果中间元素小于目标值，说明目标值在右半部分，更新left为mid + 1。
 * 如果中间元素大于目标值，说明目标值在左半部分，更新right为mid - 1。
 * 最终，left即为插入位置，返回left。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(log n)。二分查找的时间复杂度为对数级别。
 * 空间复杂度：O(1)。只使用了常数额外空间。
 *
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
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

        return left; // 返回插入位置
    }

    public static void main(String[] args) {
        SearchInsertPosition solution = new SearchInsertPosition();

        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        int result1 = solution.searchInsert(nums1, target1);
        System.out.println("Test Case 1: " + result1); // 预期输出：2

        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;
        int result2 = solution.searchInsert(nums2, target2);
        System.out.println("Test Case 2: " + result2); // 预期输出：1

        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;
        int result3 = solution.searchInsert(nums3, target3);
        System.out.println("Test Case 3: " + result3); // 预期输出：4
    }
}
