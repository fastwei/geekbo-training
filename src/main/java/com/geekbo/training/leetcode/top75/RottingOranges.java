package com.geekbo.training.leetcode.top75;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * <p>
 * You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * If this is impossible, return -1.
 * Example 1:
 * <p>
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * <p>
 * Example 2:
 * <p>
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
 * because rotting only happens 4-directionally.
 * Example 3:
 * <p>
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 */
class RottingOranges {

    /**
     * 解题思路：
     * <p>
     * 使用广度优先搜索（BFS）来模拟橙子腐烂的过程。
     * 遍历网格，将腐烂的橙子入队，并统计新鲜橙子的数量。
     * 使用四个方向遍历腐烂的橙子，如果可以腐烂新鲜橙子，则标记为腐烂并入队。
     * 每轮BFS代表一分钟，直到队列为空或没有新鲜橙子。
     * 最后检查新鲜橙子的数量，如果为0，则返回所需的分钟数；否则返回-1。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(m * n)，其中m和n分别是网格的行数和列数。
     * 空间复杂度：O(m * n)，用于存储队列。
     *
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int minutes = 0;

        Queue<int[]> queue = new LinkedList<>();
        int freshOranges = 0;

        // 将所有腐烂的橙子入队，并统计新鲜橙子的数量
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty() && freshOranges > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] orange = queue.poll();
                int x = orange[0];
                int y = orange[1];

                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2;
                        queue.offer(new int[]{newX, newY});
                        freshOranges--;
                    }
                }
            }
            minutes++;
        }

        return freshOranges == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        RottingOranges solution = new RottingOranges();

        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println(solution.orangesRotting(grid1)); // 输出：4

        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        System.out.println(solution.orangesRotting(grid2)); // 输出：-1

        int[][] grid3 = {
                {0, 2}
        };
        System.out.println(solution.orangesRotting(grid3)); // 输出：0
    }

}
