package com.geekbo.training.leetcode.dp;

/**
 * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
 * <p>
 * A falling path starts at any element in the first row and chooses the element
 * in the next row that is either directly below or diagonally left/right.
 * Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a minimum sum as shown.
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is shown.
 */
public class MinimumFallingPathSum {
    /**
     * 计算矩阵的最小下降路径和
     * 解题思路：
     * <p>
     * 使用动态规划来计算矩阵的最小下降路径和。
     * 创建一个二维数组dp，其中dp[i][j]表示从第一行开始到达位置(i, j)的最小下降路径和。
     * 初始化dp数组为第一行的值。
     * 对于每一行，计算当前位置的最小路径和，可以从上一行的三个相邻位置中选择最小值，
     * 即dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]))。
     * 最后，找到最后一行的最小路径和，返回最小值即可。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n为矩阵的边长。
     * 空间复杂度：O(n^2)，需要使用一个二维数组来保存中间结果。
     *
     * @param matrix 矩阵
     * @return 最小下降路径和
     */
    public static int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];

        // 初始化dp数组为第一行的值
        for (int i = 0; i < n; i++) {
            dp[0][i] = matrix[0][i];
        }

        // 动态规划
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 计算当前位置的最小路径和
                dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], Math.min(dp[i - 1][Math.max(0, j - 1)], dp[i - 1][Math.min(n - 1, j + 1)]));
            }
        }

        // 找到最后一行的最小路径和
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minSum = Math.min(minSum, dp[n - 1][i]);
        }

        return minSum;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        System.out.println(minFallingPathSum(matrix1)); // Expected: 13

        int[][] matrix2 = {{-19, 57}, {-40, -5}};
        System.out.println(minFallingPathSum(matrix2)); // Expected: -59
    }
}
