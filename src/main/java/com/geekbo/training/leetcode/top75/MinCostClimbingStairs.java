package com.geekbo.training.leetcode.top75;

/**
 *
 *
 * 746 Min Cost Climbing Stairs
 *
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 *
 *
 *
 * Example 1:
 *
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 * Example 2:
 *
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 *
 *
 * 解题思路和算法复杂度分析：
 *
 * 我们使用动态规划来解决这个问题。创建一个dp数组，其中dp[i]表示到达第i个台阶的最小成本。
 * 初始条件是dp[0] = cost[0]和dp[1] = cost[1]，因为我们可以从第0或第1个台阶开始。
 * 然后，从第2个台阶开始，我们使用递推关系dp[i] = cost[i] + min(dp[i-1], dp[i-2])来计算每一步的最小成本。
 * 最终，我们返回dp[n-1]和dp[n-2]中的较小值，即最小成本。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(n)，因为我们遍历了台阶的成本数组一次。
 * 空间复杂度：O(n)，因为我们使用了一个dp数组来存储计算结果。
 *
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        
        dp[0] = cost[0];
        dp[1] = cost[1];
        
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        
        return Math.min(dp[n - 1], dp[n - 2]);
    }
    
    public static void main(String[] args) {
        MinCostClimbingStairs solution = new MinCostClimbingStairs();
        
        // 测试用例1
        int[] cost1 = {10, 15, 20};
        int result1 = solution.minCostClimbingStairs(cost1);
        System.out.println("Test Case 1: " + result1); // 预期输出：15

        // 测试用例2
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        int result2 = solution.minCostClimbingStairs(cost2);
        System.out.println("Test Case 2: " + result2); // 预期输出：6
    }
}
