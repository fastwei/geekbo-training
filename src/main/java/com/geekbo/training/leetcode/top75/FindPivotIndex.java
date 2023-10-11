package com.geekbo.training.leetcode.top75;

/**
 *Prefix Sum
 *
 * Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * Example 3:
 *
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 *
 * 题目描述：
 * 给定一个整数数组nums，计算数组的“中心索引”。
 * 中心索引是指该索引左边所有数字的和等于该索引右边所有数字的和。
 * 如果数组没有中心索引，则返回-1。
 * 
 * 解题思路：
 * 首先计算整个数组的总和totalSum。
 * 然后遍历数组，维护一个左边元素的和leftSum，初始值为0。
 * 对于每个索引i，比较leftSum和totalSum - leftSum - nums[i]是否相等，
 * 如果相等，则说明该索引是中心索引，返回i。
 * 如果没有找到中心索引，返回-1。
 * 
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中n为数组的长度。
 * - 空间复杂度：O(1)，只需要常数额外的空间。
 */

public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int totalSum = 0; // 计算整个数组的总和
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0; // 左边元素的和
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i; // 找到中心索引
            }
            leftSum += nums[i];
        }

        return -1; // 没有中心索引
    }

    public static void main(String[] args) {
        FindPivotIndex solution = new FindPivotIndex();

        // 测试用例1
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        int result1 = solution.pivotIndex(nums1);
        System.out.println("Example 1: Output: " + result1); // 预期输出：3

        // 测试用例2
        int[] nums2 = {1, 2, 3};
        int result2 = solution.pivotIndex(nums2);
        System.out.println("Example 2: Output: " + result2); // 预期输出：-1

        // 测试用例3
        int[] nums3 = {2, 1, -1};
        int result3 = solution.pivotIndex(nums3);
        System.out.println("Example 3: Output: " + result3); // 预期输出：0
    }
}
