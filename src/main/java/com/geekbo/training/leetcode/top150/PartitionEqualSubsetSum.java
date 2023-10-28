package com.geekbo.training.leetcode.top150;

/**
 * Given an integer array nums, return true if you can partition the array into two subsets
 * such that the sum of the elements in both subsets is equal or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class PartitionEqualSubsetSum {
    /**
     * 解题思路：
     * 首先计算数组的总和sum，如果sum是奇数，则无法分割成两个和相等的子集，直接返回false。
     * 如果sum是偶数，可以将问题转化为是否能在数组中找到一组元素，使得它们的和等于sum的一半。
     * 使用动态规划的思想，定义一个二维数组dp，其中dp[i][j]表示在前i个元素中是否存在一组元素，使得它们的和等于j。
     * 初始化dp数组，dp[0][0]为true，其余元素都为false。
     * 对于每个元素nums[i]，遍历可能的和j，如果dp[i-1][j]为true，则dp[i][j]也为true；
     * 如果dp[i-1][j-nums[i]]为true，则dp[i][j]也为true。
     * 最后返回dp[nums.length][sum/2]作为结果。
     * <p>
     * 算法的时间复杂度为O(n*sum/2)，其中n是数组的长度，sum是数组的总和。
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][target + 1];
        dp[0][0] = true;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][target];
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 5, 11, 5};
        // 预期输出为true
        System.out.println(canPartition(nums1));

        // Test case 2
        int[] nums2 = {1, 2, 3, 5};
        // 预期输出为false
        System.out.println(canPartition(nums2));
    }
}