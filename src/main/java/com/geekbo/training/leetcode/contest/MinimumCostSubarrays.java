package com.geekbo.training.leetcode.contest;

//381.1
public class MinimumCostSubarrays {
    // 计算将数组分割成三个连续子数组的最小总成本
    public int minimumCost(int[] nums) {
        int n = nums.length;
        int minCost = Integer.MAX_VALUE;

        // 遍历所有可能的分割点，以形成三个子数组
        for (int i = 1; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // 计算当前分割方式的总成本
                int cost = nums[0] + nums[i] + nums[j];
                // 更新最小成本
                minCost = Math.min(minCost, cost);
            }
        }

        return minCost;
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        MinimumCostSubarrays solution = new MinimumCostSubarrays();

        // 测试用例1
        int[] nums1 = {1, 2, 3, 12};
        System.out.println("Expected: 6, Actual: " + solution.minimumCost(nums1));

        // 测试用例2
        int[] nums2 = {5, 4, 3};
        System.out.println("Expected: 12, Actual: " + solution.minimumCost(nums2));

        // 测试用例3
        int[] nums3 = {10, 3, 1, 1};
        System.out.println("Expected: 12, Actual: " + solution.minimumCost(nums3));
    }
}
