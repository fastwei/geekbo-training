package com.geekbo.training.leetcode.top150;

/**
 *
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 * Example 2:
 *
 * Input: grid = [[1,2,3],[4,5,6]]
 * Output: 12
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 200
 *
 */
public class MinimumPathSum {
    /**
     * 计算最小路径和
     * 解题思路： 本题可以使用动态规划来解决。
     * 定义一个二维数组dp，其中dp[i][j]表示从网格的左上角到达位置(i, j)的最小路径和。
     * <p>
     * 状态转移方程为：
     * <p>
     * dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
     * 其中grid[i][j]表示网格中位置(i, j)的值。
     * <p>
     * 初始状态为：
     * <p>
     * dp[0][0] = grid[0][0]
     * 对于第一行和第一列，路径和为前一个位置的路径和加上当前位置的值：
     * dp[i][0] = dp[i-1][0] + grid[i][0]，dp[0][j] = dp[0][j-1] + grid[0][j]
     * 最终，答案为dp[m-1][n-1]，其中m为网格的行数，n为网格的列数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(m * n)，其中m为网格的行数，n为网格的列数。
     * 空间复杂度：O(m * n)。
     *
     * @param grid 网格数组
     * @return 返回最小路径和
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length; // 网格的行数
        int n = grid[0].length; // 网格的列数

        // 定义状态数组
        int[][] dp = new int[m][n];

        // 初始化状态
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 动态规划求解最小路径和
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int expected1 = 7;
        MinimumPathSum solution = new MinimumPathSum();
        int result1 = solution.minPathSum(grid1);
        System.out.println(result1 == expected1); // true

        // 测试用例2
        int[][] grid2 = {{1, 2, 3}, {4, 5, 6}};
        int expected2 = 12;
        int result2 = solution.minPathSum(grid2);
        System.out.println(result2 == expected2); // true
    }
}
