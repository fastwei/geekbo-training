package com.geekbo.training.leetcode.premium;

/**
 *
 * 256. 粉刷房子
 * 中等
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 *
 * 请计算出粉刷完所有房子最少的花费成本。
 *
 *
 *
 * 示例 1：
 *
 * 输入: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 *      最少花费: 2 + 5 + 3 = 10。
 * 示例 2：
 *
 * 输入: costs = [[7,6,2]]
 * 输出: 2
 *
 *
 * 提示:
 *
 * costs.length == n
 * costs[i].length == 3
 * 1 <= n <= 100
 * 1 <= costs[i][j] <= 20
 *
 */
public class PaintHouse {
    /**
     * 计算粉刷完所有房子的最少花费成本
     *
     * 解题思路：
     * 动态规划。定义一个 n x 3 的二维数组 dp，dp[i][j] 表示粉刷到第 i 号房子时选择颜色 j 的最小花费。
     * 初始化 dp[0][0] = costs[0][0]，dp[0][1] = costs[0][1]，dp[0][2] = costs[0][2]。
     * 从第 1 号房子开始遍历，对于每个房子 i，计算在选择颜色 j 时的最小花费：
     *   dp[i][j] = min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]) + costs[i][j]。
     * 最后，返回 dp[n-1][0]、dp[n-1][1]、dp[n-1][2] 中的最小值即可。
     *
     * 算法复杂度分析：
     * 时间复杂度是 O(n)，其中 n 是房子的数量。
     * 空间复杂度是 O(1)，不考虑输入和输出的空间。
     *
     * @param costs 房子粉刷成不同颜色的花费
     * @return 粉刷完所有房子的最少花费成本
     */
    public static int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n][3];

        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i][2];
        }

        return Math.min(Math.min(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
    }

    public static void main(String[] args) {
        int[][] costs1 = {{17, 2, 17}, {16, 16, 5}, {14, 3, 19}};
        System.out.println("输入: costs = [[17,2,17],[16,16,5],[14,3,19]]");
        System.out.println("预期输出: 10");
        System.out.println("实际输出: " + minCost(costs1));
        System.out.println();

        int[][] costs2 = {{7, 6, 2}};
        System.out.println("输入: costs = [[7,6,2]]");
        System.out.println("预期输出: 2");
        System.out.println("实际输出: " + minCost(costs2));
    }
}