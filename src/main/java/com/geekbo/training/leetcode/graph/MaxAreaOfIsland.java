package com.geekbo.training.leetcode.graph;

/**
 * 695. Max Area of Island
 * Medium
 * You are given an m x n binary matrix grid.
 * An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 * <p>
 * The area of an island is the number of cells with a value 1 in the island.
 * <p>
 * Return the maximum area of an island in grid. If there is no island, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Output: 6
 * Explanation: The answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 * <p>
 * Input: grid = [[0,0,0,0,0,0,0,0]]
 * Output: 0
 */
public class MaxAreaOfIsland {
    /**
     * 给定一个m x n的二进制矩阵grid，岛屿是指由1（表示陆地）组成的、在四个方向（水平或垂直）上相连的区域。
     * 假设网格的四个边缘都被水包围。
     * 岛屿的面积是指岛屿内部的1的个数。
     * 返回grid中岛屿的最大面积。如果没有岛屿，则返回0。
     * <p>
     * 解题思路：
     * <p>
     * 这个问题可以使用深度优先搜索（DFS）来解决。我们可以遍历二维矩阵中的每个格子，当我们遇到值为1的格子时，
     * 就开始进行DFS，统计当前岛屿的面积，并将该格子标记为已访问。
     * 在DFS的过程中，我们将递归地访问与当前格子相邻的未访问过的格子，
     * 并统计面积。最终，我们返回最大的岛屿面积。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(mn)，其中m是二维矩阵的行数，n是二维矩阵的列数。
     * 空间复杂度：O(mn)，我们使用了一个二维数组visited来记录每个格子是否已经访问过。
     */
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(grid, visited, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 0 || visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;
        int area = 1;

        area += dfs(grid, visited, row + 1, col);
        area += dfs(grid, visited, row - 1, col);
        area += dfs(grid, visited, row, col + 1);
        area += dfs(grid, visited, row, col - 1);

        return area;
    }

    public static void main(String[] args) {
        MaxAreaOfIsland solution = new MaxAreaOfIsland();

        // 测试用例1
        int[][] grid1 = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        int expected1 = 6;
        int result1 = solution.maxAreaOfIsland(grid1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        int[][] grid2 = {{0, 0, 0, 0, 0, 0, 0, 0}};
        int expected2 = 0;
        int result2 = solution.maxAreaOfIsland(grid2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);
    }
}