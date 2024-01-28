package com.geekbo.training.leetcode.contest;

import java.util.Arrays;

public class Solution2 {
    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;
        long[][] dp = new long[n + 1][k + 1];

        // 初始化dp数组
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        dp[0][0] = 0;

        // 动态规划填表
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int l = i - 1; l >= Math.max(0, i - dist - 1); l--) {
                    dp[i][j] = Math.min(dp[i][j], dp[l][j - 1] + nums[l]);
                }
            }
        }

        return dp[n][k];
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // 测试用例1
        int[] nums1 = {1,3,2,6,4,2};
        int k1 = 3, dist1 = 3;
        System.out.println("Expected: 5, Actual: " + solution.minimumCost(nums1, k1, dist1));

        // 测试用例2
        int[] nums2 = {10,1,2,2,2,1};
        int k2 = 4, dist2 = 3;
        System.out.println("Expected: 15, Actual: " + solution.minimumCost(nums2, k2, dist2));

        // 测试用例3
        int[] nums3 = {10,8,18,9};
        int k3 = 3, dist3 = 1;
        System.out.println("Expected: 36, Actual: " + solution.minimumCost(nums3, k3, dist3));
    }
}
