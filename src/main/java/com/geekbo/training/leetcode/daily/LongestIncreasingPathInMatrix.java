package com.geekbo.training.leetcode.daily;

/**
 *
 * 329. Longest Increasing Path in a Matrix
 * Hard
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions:
 * left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 */
class LongestIncreasingPathInMatrix {
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 解题思路：
     * <p>
     * 这道题可以使用深度优先搜索（DFS）和记忆化搜索的方法来解决。
     * 我们从矩阵中的每个单元格开始，通过DFS遍历矩阵中的每个单元格，查找单调递增路径的最大长度。
     * 对于每个单元格，我们可以向四个方向（上、下、左、右）移动，但不能对角移动或越过边界。
     * 在DFS中，我们使用记忆化搜索，即在每个单元格中存储已计算的最长递增路径长度，以避免重复计算。
     * 我们使用一个memo数组来存储已计算的最长递增路径长度。
     * 我们遍历矩阵中的每个单元格，对于每个单元格，
     * 我们调用DFS函数来获取以当前单元格为起点的最长递增路径长度，并将其与最大长度进行比较，更新最大长度。
     *
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n]; // memoization array to store the longest increasing path length for each cell

        int maxLength = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int length = dfs(matrix, i, j, memo);
                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j]; // return memoized value if it exists
        }

        int maxLength = 1;
        for (int[] direction : directions) {
            int newRow = i + direction[0];
            int newCol = j + direction[1];

            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length &&
                    matrix[newRow][newCol] > matrix[i][j]) {
                int length = 1 + dfs(matrix, newRow, newCol, memo); // recursive call to explore the next cell in the increasing path
                maxLength = Math.max(maxLength, length);
            }
        }

        memo[i][j] = maxLength; // store the longest increasing path length for the current cell
        return maxLength;
    }

    public static void main(String[] args) {
        LongestIncreasingPathInMatrix solution = new LongestIncreasingPathInMatrix();

        int[][] matrix1 = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println(solution.longestIncreasingPath(matrix1)); // Expected output: 4

        int[][] matrix2 = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        System.out.println(solution.longestIncreasingPath(matrix2)); // Expected output: 4

        int[][] matrix3 = {{1}};
        System.out.println(solution.longestIncreasingPath(matrix3)); // Expected output: 1
    }
}
