package com.geekbo.training.leetcode.top150;


/**
 *
 *
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3.
 * Example 2:
 *
 * Input: nums = [5,-3,5]
 * Output: 10
 * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
 * Example 3:
 *
 * Input: nums = [-3,-2,-3]
 * Output: -2
 * Explanation: Subarray [-2] has maximum sum -2.
 *
 * Kadane's Algorithm
 *
 * Divide and Conquer,DP,queue
 *
 */
public class MaximumCircularSubarray {
    /**
     * 找到循环数组中具有最大和的子数组，并返回其和。
     * <p>
     * 解题思路：
     * <p>
     * 对于循环数组，可以分成两种情况来考虑最大子数组的和：
     * 最大子数组不跨越数组的首尾元素。
     * 最大子数组跨越数组的首尾元素。
     * 对于第一种情况，可以使用之前提到的最大子数组和的解法。
     * 对于第二种情况，可以转化为求循环数组的最小子数组和，然后用总和减去最小子数组和。
     * 因此，可以先计算普通子数组的最大和和最小和，然后比较两者的大小，得出最大子数组的和。
     * 如果所有元素都是负数，则最大和即为普通子数组的最大和。
     * 最后返回普通子数组的最大和和循环数组的最大和中的较大值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历整个数组一次，时间复杂度为 O(n)。
     * 只使用了常数个额外变量，空间复杂度为 O(1)。
     *
     * @param nums 循环整数数组
     * @return 最大和的子数组的和
     */
    public static int maxSubarraySumCircular(int[] nums) {
        int maxSum = nums[0];  // 普通子数组的最大和
        int currMax = nums[0];  // 当前的最大子数组和
        int minSum = nums[0];  // 普通子数组的最小和
        int currMin = nums[0];  // 当前的最小子数组和
        int totalSum = nums[0];  // 数组的总和

        for (int i = 1; i < nums.length; i++) {
            totalSum += nums[i]; // 计算数组的总和

            // 计算普通子数组的最大和
            currMax = Math.max(currMax + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currMax);

            // 计算普通子数组的最小和
            currMin = Math.min(currMin + nums[i], nums[i]);
            minSum = Math.min(minSum, currMin);
        }

        // 如果数组中的所有元素都是负数，则最大和即为普通子数组的最大和
        if (maxSum < 0) {
            return maxSum;
        }

        // 循环数组的最大和等于总和减去普通子数组的最小和
        return Math.max(maxSum, totalSum - minSum);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, -2, 3, -2};
        // 预期输出: 3
        System.out.println(maxSubarraySumCircular(nums1));

        int[] nums2 = {5, -3, 5};
        // 预期输出: 10
        System.out.println(maxSubarraySumCircular(nums2));

        int[] nums3 = {-3, -2, -3};
        // 预期输出: -2
        System.out.println(maxSubarraySumCircular(nums3));
    }
}




