package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1425. Constrained Subsequence Sum
 * Given an integer array nums and an integer k,
 * return the maximum sum of a non-empty subsequence of that array
 * such that for every two consecutive integers in the subsequence,
 * nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 * <p>
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array,
 * leaving the remaining elements in their original order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subsequence is [10, 2, 5, 20].
 * Example 2:
 * <p>
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subsequence must be non-empty, so we choose the largest number.
 * Example 3:
 * <p>
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 */
public class MaximumSubsequenceSum {

    /**
     * 返回满足条件的子序列的最大和
     * 解题思路：
     * <p>
     * 使用动态规划的思想，定义dp数组，dp[i]表示以第i个元素结尾的满足条件的子序列的最大和。
     * 初始化dp数组，将第一个元素的值赋给dp[0]。
     * 从第二个元素开始，遍历数组，计算dp[i]的值：
     * 限制条件j - i <= k，因此只需要考虑前面的k个元素。
     * 遍历前面的k个元素，计算以第j个元素结尾的满足条件的子序列的最大和，更新dp[i]。
     * 当前元素nums[i]也可以作为一个满足条件的 子序列，所以需要将dp[i]与nums[i]比较，取较大值。
     * <p>
     * 在遍历过程中，同时更新最大和maxSum，即为所求的结果。
     * 返回maxSum作为满足条件的子序列的最大和。
     * <p>
     * 算法复杂度分析:
     * <p>
     * 时间复杂度: 遍历数组需要 O(n) 的时间，每个元素需要计算前面的 k 个元素的最大和，所以总的时间复杂度为 O(n*k)。
     * 空间复杂度: 需要使用一个长度为 n 的 dp 数组来保存中间结果，所以空间复杂度为 O(n)。
     * 请注意，以上代码假设输入的数组 nums 不为空，并且 k 的取值范围在 [1, n] 之间。
     *
     * @param nums 整数数组
     * @param k    条件限制
     * @return 子序列的最大和
     */
    public static int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int maxSum = dp[0];

        for (int i = 1; i < n; i++) {
            int jStart = Math.max(0, i - k);
            int maxSubsequenceSum = Integer.MIN_VALUE;
            for (int j = jStart; j < i; j++) {
                maxSubsequenceSum = Math.max(maxSubsequenceSum, dp[j]);
            }
            dp[i] = maxSubsequenceSum + nums[i];
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {10, 2, -10, 5, 20};
        int k1 = 2;
        int expected1 = 37;
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Expected output: " + expected1);
        System.out.println("Actual output: " + constrainedSubsetSum(nums1, k1));
        System.out.println();

        // 测试用例2
        int[] nums2 = {-1, -2, -3};
        int k2 = 1;
        int expected2 = -1;
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Expected output: " + expected2);
        System.out.println("Actual output: " + constrainedSubsetSum(nums2, k2));
        System.out.println();

        // 测试用例3
        int[] nums3 = {10, -2, -10, -5, 20};
        int k3 = 2;
        int expected3 = 23;
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Expected output: " + expected3);
        System.out.println("Actual output: " + constrainedSubsetSum(nums3, k3));
        System.out.println();
    }
}
