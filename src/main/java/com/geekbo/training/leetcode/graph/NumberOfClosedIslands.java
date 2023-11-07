package com.geekbo.training.leetcode.graph;

/**
 * 1254. Number of Closed Islands
 * Medium
 * Given a 2D grid consists of 0s (land) and 1s (water).
 * An island is a maximal 4-directionally connected group of 0s and
 * a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 * <p>
 * Return the number of closed islands.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 * Example 3:
 * <p>
 * Input: grid = [[1,1,1,1,1,1,1],
 * [1,0,0,0,0,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,1,0,1,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,0,0,0,0,1],
 * [1,1,1,1,1,1,1]]
 * Output: 2
 */
public class NumberOfClosedIslands {
    /**
     * 给定一个二维网格grid，其中0表示陆地，1表示水。
     * 岛屿是指由0（表示陆地）组成的、在四个方向（水平或垂直）上相连的区域。
     * 一个封闭岛屿是指完全被水包围的岛屿（四周都是1）。
     * 返回封闭岛屿的数量。
     * <p>
     * 解题思路：
     * <p>
     * 这个问题可以使用深度优先搜索（DFS）来解决。我们可以遍历二维矩阵中的每个格子，当我们遇到值为0的格子时，
     * 就开始进行DFS，将与该格子相连的所有0都标记为已访问，并判断该岛屿是否为封闭岛屿。如果是封闭岛屿，则计数器加1。
     * 最终，我们返回封闭岛屿的数量。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(mn)，其中m是二维矩阵的行数，n是二维矩阵的列数。
     * 空间复杂度：O(mn)，我们使用了一个二维数组visited来记录每个格子是否已经访问过。
     */
    public int closedIsland(int[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    if (dfs(grid, visited, i, j)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean dfs(int[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return false;
        }

        if (grid[row][col] == 1 || visited[row][col]) {
            return true;
        }

        visited[row][col] = true;

        boolean top = dfs(grid, visited, row - 1, col);
        boolean bottom = dfs(grid, visited, row + 1, col);
        boolean left = dfs(grid, visited, row, col - 1);
        boolean right = dfs(grid, visited, row, col + 1);

        return top && bottom && left && right;
    }

    public static void main(String[] args) {
        NumberOfClosedIslands solution = new NumberOfClosedIslands();

        // 测试用例1
        int[][] grid1 = {
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}};
        int expected1 = 2;
        int result1 = solution.closedIsland(grid1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        int[][] grid2 = {
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0}
        };
        int expected2 = 1;
        int result2 = solution.closedIsland(grid2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);

        // 测试用例3
        int[][] grid3 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        int expected3 = 2;
        int result3 = solution.closedIsland(grid3);
        System.out.println("测试用例3:");
        System.out.println("预期输出: " + expected3);
        System.out.println("实际输出: " + result3);
    }
}