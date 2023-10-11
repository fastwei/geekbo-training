package com.geekbo.training.leetcode.top150;

/**
 * 70. Climbing Stairs
 *
 * 1D DP
 *
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * Example 2:
 *
 * Input: n = 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 *解题思路：
 *
 * 这是一个经典的动态规划问题。要求爬楼梯的方式总数，可以将问题拆解为子问题。
 * 设 dp[i] 表示爬到第 i 阶楼梯的方式总数。根据题意，每次可以选择爬 1 阶或 2 阶。
 * 初始情况下，dp[1] = 1，dp[2] = 2，因为只有一阶楼梯有一种方式，两阶楼梯有两种方式。
 * 对于第 i 阶楼梯，可以通过爬一阶楼梯到达第 i-1 阶，或者爬两阶楼梯到达第 i-2 阶。所以，dp[i] = dp[i-1] + dp[i-2]。
 * 最终返回 dp[n] 即为爬到第 n 阶楼梯的方式总数。
 * 算法复杂度：
 *
 * 时间复杂度：O(n)，因为我们需要计算从 3 到 n 的每一阶楼梯的方式总数。
 * 空间复杂度：O(n)，使用了一个长度为 n+1 的数组来保存中间结果。
 * 测试用例：
 *
 * 测试用例1：n = 2，预期输出为 2。
 * 测试用例2：n = 3，预期输出为 3。
 *
 *
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        // 如果 n 小于等于 2，直接返回 n
        if (n <= 2) {
            return n;
        }

        // 创建一个长度为 n+1 的数组来保存中间结果，dp[i] 表示爬到第 i 阶楼梯的方式总数
        int[] dp = new int[n + 1];
        
        // 初始化初始情况，爬 1 阶楼梯有 1 种方式，爬 2 阶楼梯有 2 种方式
        dp[1] = 1;
        dp[2] = 2;

        // 从第 3 阶楼梯开始计算到第 n 阶楼梯的方式总数
        for (int i = 3; i <= n; i++) {
            // 根据动态规划的公式，dp[i] = dp[i-1] + dp[i-2]
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回爬到第 n 阶楼梯的方式总数
        return dp[n];
    }

    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();

        // 测试用例1
        int n1 = 2;
        int result1 = solution.climbStairs(n1);
        System.out.println("Example 1: " + result1); // 预期输出：2

        // 测试用例2
        int n2 = 3;
        int result2 = solution.climbStairs(n2);
        System.out.println("Example 2: " + result2); // 预期输出：3
    }
}
