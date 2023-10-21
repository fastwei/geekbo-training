package com.geekbo.training.leetcode.top150;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water),
 * return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * <p>
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        // 测试用例1
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println(numIslands(grid1)); // 预期输出：1

        // 测试用例2
        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslands(grid2)); // 预期输出：3
    }

    /**
     * 计算二维网格中岛屿的数量
     *
     * @param grid 二维网格
     * @return 岛屿的数量
     */
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    /**
     * 深度优先搜索，将相邻的陆地标记为水
     * 解题思路： 要计算二维网格中岛屿的数量，可以使用深度优先搜索（DFS）的方法。
     * <p>
     * 遍历二维网格的每个位置，如果当前位置是陆地（'1'），则将其标记为水（'0'），
     * 并对其相邻的位置进行深度优先搜索，将相邻的陆地都标记为水。
     * <p>
     * 算法的时间复杂度是 O(m * n)，其中 m 是二维网格的行数，n 是二维网格的列数。
     * 因为我们需要遍历整个二维网格，对于每个位置进行 DFS 操作。
     * <p>
     * 算法的空间复杂度是 O(m * n)，主要是递归调用 DFS 时的栈空间。
     * 在最坏的情况下，如果二维网格中的所有位置都是陆地（'1'），那么递归的最大深度将是二维网格的大小，即 O(m * n)。
     * <p>
     * 因此，整体的算法复杂度是 O(m * n)。
     *
     * @param grid 二维网格
     * @param i    当前位置的行坐标
     * @param j    当前位置的列坐标
     */
    public static void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '0';

        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    /**
     * 计算二维网格中岛屿的数量
     * 解题思路： 使用广度优先搜索（BFS）算法实现。
     * 遍历二维网格的每个位置，如果当前位置是陆地（'1'），则将其标记为水（'0'），并将当前位置加入队列。
     * 然后从队列中依次取出位置，将其相邻的陆地标记为水，并将相邻陆地加入队列。
     * 不断重复上述过程，直到队列为空。每次从队列中取出一个位置，都表示找到了一个岛屿。
     * <p>
     * 算法复杂度分析： 遍历二维网格的每个位置，时间复杂度为 O(m * n)，其中 m 和 n 分别是二维网格的行数和列数。
     *
     * @param grid 二维网格
     * @return 岛屿的数量
     */
    public static int numIslandsBfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    queue.offer(new int[]{i, j});
                    grid[i][j] = '0';

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        int row = curr[0];
                        int col = curr[1];

                        for (int[] direction : directions) {
                            int newRow = row + direction[0];
                            int newCol = col + direction[1];

                            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == '1') {
                                queue.offer(new int[]{newRow, newCol});
                                grid[newRow][newCol] = '0';
                            }
                        }
                    }
                }
            }
        }

        return count;
    }


}
