package com.geekbo.training.leetcode.top150;

/**
 *
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * Example 2:
 *
 *
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * Example 3:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 *
 */
public class MaximalSquare {

    /**
     * 解题思路：
     * <p>
     * 使用动态规划的思想来解决这个问题。
     * 我们定义一个状态数组dp，其中dp[i][j]表示以(i, j)为右下角的最大正方形的边长。
     * 初始化状态数组的第一行和第一列，即将矩阵中第一行和第一列的元素转换为整型存入状态数组。
     * 然后，我们从矩阵的第二行和第二列开始，遍历矩阵中的每个元素。
     * 对于每个元素(i, j)，如果该元素为1，则说明可以构成正方形。
     * 此时，我们取左上角、左边和上边三个位置的最小值加1，更新dp[i][j]为以(i, j)为右下角的最大正方形的边长。
     * 同时，我们维护一个变量maxSquareLen来记录最大正方形的边长。
     * 最后，返回最大正方形的面积，即maxSquareLen * maxSquareLen。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(m*n)，其中m和n分别是矩阵的行数和列数。需要遍历整个矩阵。
     * 空间复杂度：O(m*n)，需要使用一个二维数组来存储中间结果。
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        // 获取矩阵的行数和列数
        int rows = matrix.length;
        int cols = matrix[0].length;

        // 定义状态数组，dp[i][j]表示以(i, j)为右下角的最大正方形的边长
        int[][] dp = new int[rows + 1][cols + 1];

        // 定义最大正方形的边长
        int maxSquareLen = 0;

        // 动态规划求解最大正方形的边长
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                // 如果当前位置为1，则取左上、左边和上边三个位置的最小值加1
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxSquareLen = Math.max(maxSquareLen, dp[i][j]);
                }
            }
        }

        // 返回最大正方形的面积
        return maxSquareLen * maxSquareLen;
    }


    public static void main(String[] args) {
        MaximalSquare solution = new MaximalSquare();

        // Test Case 1
        char[][] matrix1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        int expected1 = 4;
        int result1 = solution.maximalSquare(matrix1);
        System.out.println(result1 == expected1); // true

        // Test Case 2
        char[][] matrix2 = {
                {'0', '1'},
                {'1', '0'}
        };
        int expected2 = 1;
        int result2 = solution.maximalSquare(matrix2);
        System.out.println(result2 == expected2); // true

        // Test Case 3
        char[][] matrix3 = {
                {'0'}
        };
        int expected3 = 0;
        int result3 = solution.maximalSquare(matrix3);
        System.out.println(result3 == expected3); // true
    }
}