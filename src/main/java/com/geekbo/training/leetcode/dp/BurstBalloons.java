package com.geekbo.training.leetcode.dp;

/**
 * 312. Burst Balloons
 * Hard
 * You are given n balloons, indexed from 0 to n - 1.
 * Each balloon is painted with a number on it represented by an array nums.
 * You are asked to burst all the balloons.
 * <p>
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
 * If i - 1 or i + 1 goes out of bounds of the array,
 * then treat it as if there is a balloon with a 1 painted on it.
 * <p>
 * Return the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * Example 2:
 * <p>
 * Input: nums = [1,5]
 * Output: 10
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * 0 <= nums[i] <= 100
 */
public class BurstBalloons {
    /**
     * 解题思路：
     * <p>
     * 首先，在给定的数组 nums 前后添加两个值为 1 的元素，用来表示边界。
     * 创建一个二维数组 dp，其中 dp[i][j] 表示在区间 [i, j] 内戳破气球所能获得的最大硬币数量。
     * 使用动态规划的思想，从区间长度为 3 的子问题开始，逐步扩展到整个数组的区间。
     * 对于每个子问题，使用一个循环来选择最后一个戳破的气球，计算戳破该气球所能获得的硬币数量，并更新 dp 数组。
     * 最终，dp[0][n + 1] 就是所求的最大硬币数量。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：算法中使用了三层嵌套循环，所以时间复杂度为 O(n^3)，其中 n 是数组的长度。
     * 空间复杂度：算法使用了一个二维数组 dp，所以空间复杂度为 O(n^2)，其中 n 是数组的长度。
     *
     * @param nums
     * @return
     */
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            balloons[i + 1] = nums[i];
        }

        int[][] dp = new int[n + 2][n + 2];
        for (int len = 3; len <= n + 2; len++) {
            for (int left = 0; left <= n + 2 - len; left++) {
                int right = left + len - 1;
                for (int k = left + 1; k < right; k++) {
                    dp[left][right] = Math.max(dp[left][right], dp[left][k] + dp[k][right] + balloons[left] * balloons[k] * balloons[right]);
                }
            }
        }

        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 1, 5, 8};
        System.out.println("Input: nums = [3, 1, 5, 8]");
        System.out.println("Output: " + maxCoins(nums1)); // Output: 167

        int[] nums2 = {1, 5};
        System.out.println("\nInput: nums = [1, 5]");
        System.out.println("Output: " + maxCoins(nums2)); // Output: 10
    }
}
