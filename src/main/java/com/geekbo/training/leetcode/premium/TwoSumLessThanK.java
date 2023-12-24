package com.geekbo.training.leetcode.premium;

import java.util.Arrays;

/**
 *
 *
 * 1099. 小于 K 的两数之和
 * 简单
 * 给你一个整数数组 nums 和整数 k ，返回最大和 sum ，满足存在 i < j 使得 nums[i] + nums[j] = sum 且 sum < k 。如果没有满足此等式的 i,j 存在，则返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [34,23,1,24,75,33,54,8], k = 60
 * 输出：58
 * 解释：
 * 34 和 24 相加得到 58，58 小于 60，满足题意。
 * 示例 2：
 *
 * 输入：nums = [10,20,30], k = 15
 * 输出：-1
 * 解释：
 * 我们无法找到和小于 15 的两个元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 1000
 * 1 <= k <= 2000
 */
public class TwoSumLessThanK {
    /**
     * 解题思路：
     * <p>
     * 首先，对数组进行排序，然后使用双指针的方法，从数组的两端向中间遍历。
     * 初始化左指针 left 为 0，右指针 right 为数组长度减一。
     * 如果 nums[left] + nums[right] 小于 k，则将当前的和更新为 maxSum，并将左指针向右移动一位。
     * 否则，将右指针向左移动一位。
     * 最后返回 maxSum。
     *
     * 算法复杂度：
     * <p>
     * 时间复杂度：排序的时间复杂度为 O(nlogn)，双指针遍历的时间复杂度为 O(n)，所以总的时间复杂度为 O(nlogn)。
     * 空间复杂度：排序的空间复杂度为 O(logn)，所以总的空间复杂度为 O(logn)。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int maxSum = -1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < k) {
                maxSum = Math.max(maxSum, sum);
                left++;
            } else {
                right--;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums1 = {34, 23, 1, 24, 75, 33, 54, 8};
        int k1 = 60;
        System.out.println(twoSumLessThanK(nums1, k1)); // Output: 58

        int[] nums2 = {10, 20, 30};
        int k2 = 15;
        System.out.println(twoSumLessThanK(nums2, k2)); // Output: -1
    }
}