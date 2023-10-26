package com.geekbo.training.leetcode.top150;

/**
 * You are given an m x n integer array grid.
 * There is a robot initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 * <p>
 * An obstacle and space are marked as 1 or 0 respectively in grid.
 * A path that the robot takes cannot include any square that is an obstacle.
 * <p>
 * Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 * <p>
 * The testcases are generated so that the answer will be less than or equal to 2 * 109.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * Example 2:
 * <p>
 * <p>
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 */
public class UniquePathsII {
    /**
     * 解题思路： 我们可以使用动态规划来解决这个问题。
     * 首先，我们定义一个二维数组dp，其中dp[i][j]表示从起点到达网格的第i行第j列的位置的不同路径数。
     * 我们可以根据网格中的障碍物来更新dp数组的值。
     * 具体来说，如果网格的第i行第j列的位置有障碍物，则dp[i][j]的值为0；
     * 否则，dp[i][j]的值为dp[i-1][j]和dp[i][j-1]的值之和。
     * 因此，我们可以使用动态规划的方法来解决这个问题。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(mn)，其中m是网格的行数，n是网格的列数。我们需要计算mn个网格的路径数。
     * 空间复杂度：O(mn)，我们使用了一个大小为(m+1)(n+1)的二维数组dp来存储中间结果。
     *
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // 获取网格的行数和列数
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 如果起点或终点有障碍物，则无法到达终点，直接返回0
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }

        // 定义一个二维数组dp，其中dp[i][j]表示从起点到达网格的第i行第j列的位置的不同路径数
        int[][] dp = new int[m][n];

        // 初始化起点的路径数为1
        dp[0][0] = 1;

        // 初始化第一行和第一列的路径数
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = dp[i - 1][0];
            }
        }

        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 0) {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // 动态规划求解
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        // 返回到达终点的路径数
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        // Test Case 1
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        // 预期输出为2
        System.out.println(uniquePathsWithObstacles(obstacleGrid));

        // Test Case 2
        obstacleGrid = new int[][]{{0, 1}, {0, 0}};
        // 预期输出为1
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }
}
