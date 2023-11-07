package com.geekbo.training.leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1091. Shortest Path in Binary Matrix
 * Medium
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
 * If there is no clear path, return -1.
 * <p>
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0))
 * to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * <p>
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected
 * (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * Example 3:
 * <p>
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 */
public class ShortestPathInBinaryMatrix {
    /**
     * 给定一个n x n的二进制矩阵grid，返回从左上角到右下角的最短路径长度。如果不存在清除路径，则返回-1。
     * <p>
     * 解题思路：
     * <p>
     * 这个问题可以使用广度优先搜索（BFS）来解决。
     * 我们可以将二维矩阵看作一个图，每个节点表示一个格子，每个节点和相邻的节点之间有一条边。
     * 我们从起始节点开始，使用BFS遍历图，直到我们到达目标节点或者遍历完所有可以访问的节点为止。
     * 在遍历的过程中，我们需要记录每个节点的层数，
     * 即从起始节点到该节点的最短路径长度。最后，我们返回目标节点的层数作为最短路径的长度。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n是二维矩阵的边长。最坏情况下，我们需要遍历所有节点。
     * 空间复杂度：O(n^2)，我们使用了一个队列来进行BFS，并且需要一个二维数组来记录每个节点的层数。
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            if (row == n - 1 && col == n - 1) {
                return dist[row][col];
            }

            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 0 && dist[newRow][newCol] == Integer.MAX_VALUE) {
                    queue.offer(new int[]{newRow, newCol});
                    dist[newRow][newCol] = dist[row][col] + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ShortestPathInBinaryMatrix solution = new ShortestPathInBinaryMatrix();

        // 测试用例1
        int[][] grid1 = {{0, 1}, {1, 0}};
        int expected1 = 2;
        int result1 = solution.shortestPathBinaryMatrix(grid1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        int[][] grid2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        int expected2 = 4;
        int result2 = solution.shortestPathBinaryMatrix(grid2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);

        // 测试用例3
        int[][] grid3 = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        int expected3 = -1;
        int result3 = solution.shortestPathBinaryMatrix(grid3);
        System.out.println("测试用例3:");
        System.out.println("预期输出: " + expected3);
        System.out.println("实际输出: " + result3);
    }
}