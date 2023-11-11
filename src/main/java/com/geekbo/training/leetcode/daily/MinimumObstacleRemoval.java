package com.geekbo.training.leetcode.daily;

import java.util.PriorityQueue;

/**
 * 2290. Minimum Obstacle Removal to Reach Corner
 * Hard
 * Topics
 * Companies
 * Hint
 * You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:
 * <p>
 * 0 represents an empty cell,
 * 1 represents an obstacle that may be removed.
 * You can move up, down, left, or right from and to an empty cell.
 * <p>
 * Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
 * Output: 2
 * Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a path from (0, 0) to (2, 2).
 * It can be shown that we need to remove at least 2 obstacles, so we return 2.
 * Note that there may be other ways to remove 2 obstacles to create a path.
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
 * Output: 0
 * Explanation: We can move from (0, 0) to (2, 4) without removing any obstacles, so we return 0.
 * <p>
 * todo:还有问题
 */
public class MinimumObstacleRemoval {
    public class pair {
        int r;
        int c;
        int val;

        pair(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }

    int min = Integer.MAX_VALUE;

    public int minimumObstacles(int[][] grid) {
        //The shortest number of removal we can go for it
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        pq.add(new pair(0, 0, 0));
        while (!pq.isEmpty()) {
            pair rs = pq.poll();
            int i = rs.r;
            int j = rs.c;
            int step = rs.val;
            if (i == grid.length - 1 && j == grid[0].length - 1) {
                min = Math.min(min, step);
            }
            if (isValid(i + 1, j, grid, visited)) {
                visited[i + 1][j] = true;
                if (grid[i + 1][j] == 1) {
                    pq.add(new pair(i + 1, j, step + 1));
                } else {
                    pq.add(new pair(i + 1, j, step));
                }
            }
            if (isValid(i - 1, j, grid, visited)) {
                visited[i - 1][j] = true;
                if (grid[i - 1][j] == 1) {
                    pq.add(new pair(i - 1, j, step + 1));
                } else {
                    pq.add(new pair(i - 1, j, step));
                }
            }
            if (isValid(i, j + 1, grid, visited)) {
                visited[i][j + 1] = true;
                if (grid[i][j + 1] == 1) {
                    pq.add(new pair(i, j + 1, step + 1));
                } else {
                    pq.add(new pair(i, j + 1, step));
                }
            }
            if (isValid(i, j - 1, grid, visited)) {
                visited[i][j - 1] = true;
                if (grid[i][j - 1] == 1) {
                    pq.add(new pair(i, j - 1, step + 1));
                } else {
                    pq.add(new pair(i, j - 1, step));
                }
            }
        }
        return min;


    }

    public boolean isValid(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] == true) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{0, 1, 1}, {1, 1, 0}, {1, 1, 0}};
        MinimumObstacleRemoval solution=new MinimumObstacleRemoval();
        int result1 = solution.minimumObstacles(grid1);
        System.out.println(result1);  // 输出: 2

        int[][] grid2 = {{0, 1, 0, 0, 0}, {0, 1, 0, 1, 0}, {0, 0, 0, 1, 0}};
        int result2 = solution.minimumObstacles(grid2);
        System.out.println(result2);  // 输出: 0
    }
}