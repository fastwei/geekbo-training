package com.geekbo.training.leetcode.graph;

/**
 * 1020. Number of Enclaves
 * Medium
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 * <p>
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 * <p>
 * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation: All 1s are either on the boundary or can reach the boundary.
 */
public class NumberOfEnclaves {
    /**
     * 计算不能走出边界的陆地单元格数量
     * 解题思路：
     * <p>
     * 首先，遍历网格的边界，将边界上的陆地标记为-1。
     * 然后，使用深度优先搜索（DFS）遍历网格，将与边界联通的陆地单元格标记为-1。
     * 最后，统计剩余的陆地单元格数量，即值为1的单元格数量，作为结果返回。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历网格的边界，时间复杂度为O(m + n)，其中m为网格的行数，n为网格的列数。
     * 使用深度优先搜索遍历网格，时间复杂度为O(m * n)，其中m为网格的行数，n为网格的列数。
     * 统计剩余的陆地单元格数量，时间复杂度为O(m * n)。
     * 因此，总的时间复杂度为O(m * n)。
     * 空间复杂度分析：
     * <p>
     * 使用了一个二维数组来表示网格，空间复杂度为O(m * n)，其中m为网格的行数，n为网格的列数。
     * 在上述的代码中，我已经给出了两个测试用例并打印了预期的输出结果。
     * <p>
     * 请注意，题目要求计算不能走出边界的陆地单元格数量，因此将边界上的陆地标记为-1，而不是直接将其置为0。
     * 在统计剩余的陆地单元格数量时，只需计算值为1的单元格数量即可。
     *
     * @param grid 二维网格
     * @return 不能走出边界的陆地单元格数量
     */
    public static int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 将边界上的陆地标记为-1
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                dfs(grid, i, 0);
            }
            if (grid[i][n - 1] == 1) {
                dfs(grid, i, n - 1);
            }
        }
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) {
                dfs(grid, 0, j);
            }
            if (grid[m - 1][j] == 1) {
                dfs(grid, m - 1, j);
            }
        }
        // 统计剩余的陆地数量
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 深度优先搜索，将与边界联通的陆地标记为-1
     *
     * @param grid 二维网格
     * @param i    当前单元格的行索引
     * @param j    当前单元格的列索引
     */
    private static void dfs(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = -1;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] grid1 = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        // 预期输出：3
        System.out.println(numEnclaves(grid1));

        // 测试用例2
        int[][] grid2 = {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        // 预期输出：0
        System.out.println(numEnclaves(grid2));
    }
}
