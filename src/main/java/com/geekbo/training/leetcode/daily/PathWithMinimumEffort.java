package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 *
 * 1631. Path With Minimum Effort
 * Medium
 * You are a hiker preparing for an upcoming hike.
 * You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col).
 * You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 *
 *
 *
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 */
public class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int columns = heights[0].length;

        // 创建dp数组并初始化为正无穷大
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 优先队列，按照体力消耗值进行排序
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.offer(new int[]{0, 0, 0});

        // 方向数组，用于遍历格子的四个方向
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            int effort = cell[2];

            // 如果当前格子已经访问过，则跳过
            if (dp[row][col] != Integer.MAX_VALUE) {
                continue;
            }

            // 更新dp数组的值
            dp[row][col] = effort;

            // 遍历四个方向的相邻格子
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // 判断相邻格子是否合法
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < columns) {
                    int newEffort = Math.max(effort, Math.abs(heights[row][col] - heights[newRow][newCol]));

                    // 将新的格子加入队列
                    queue.offer(new int[]{newRow, newCol, newEffort});
                }
            }
        }

        // 返回右下角格子的最小体力消耗值
        return dp[rows - 1][columns - 1];
    }

    public static void main(String[] args) {
        PathWithMinimumEffort solution = new PathWithMinimumEffort();

        // 测试用例1
        int[][] heights1 = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int result1 = solution.minimumEffortPath(heights1);
        System.out.println("测试用例1：");
        System.out.println("预期输出：2");
        System.out.println("实际输出：" + result1);

        // 测试用例2
        int[][] heights2 = {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        int result2 = solution.minimumEffortPath(heights2);
        System.out.println("测试用例2：");
        System.out.println("预期输出：1");
        System.out.println("实际输出：" + result2);
    }
}