package com.geekbo.training.leetcode.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 * <p>
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
 * <p>
 * You may change 0's to 1's to connect the two islands to form one island.
 * <p>
 * Return the smallest number of 0's you must flip to connect the two islands.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[0,1],[1,0]]
 * Output: 1
 * Example 2:
 * <p>
 * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * Example 3:
 * <p>
 * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 */
public class ShortestBridge {

    class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    int n;
    Queue<Pair> q = new LinkedList<>();

    /**
     * Run DFS to mark all cells that belong to one land.
     * Add their neighbors to a queue.
     * Run BFS until you find a cell that is 1 (this means that we have found the other land).
     * Return the current level.
     */
    public int shortestBridge(int[][] grid) {
        // run connected componenet on one land
        n = grid.length;
        boolean f = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j, grid);
                    f = true;
                    break;
                }
            }
            if (f) break;
        }
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair cur = q.poll();
                if (cur.r < 0 || cur.r >= n || cur.c < 0 || cur.c >= n ||
                        grid[cur.r][cur.c] == -1) {
                    continue;
                }
                if (grid[cur.r][cur.c] == 1) {
                    return level;
                }
                // mark it as visited
                grid[cur.r][cur.c] = -1;
                // add its neighbors
                q.add(new Pair(cur.r + 1, cur.c));
                q.add(new Pair(cur.r - 1, cur.c));
                q.add(new Pair(cur.r, cur.c + 1));
                q.add(new Pair(cur.r, cur.c - 1));
            }
            level++;
        }
        return -1;
    }

    private void dfs(int r, int c, int[][] grid) {
        if (r < 0 || r >= n || c < 0 || c >= n) {
            return;
        }
        if (grid[r][c] == 0) {
            // add to queue
            q.add(new Pair(r, c));
            // mark as visited
            grid[r][c] = 2;
            return;
        }
        if (grid[r][c] == 1) {
            grid[r][c] = -1;
            dfs(r + 1, c, grid);
            dfs(r - 1, c, grid);
            dfs(r, c + 1, grid);
            dfs(r, c - 1, grid);
        }
    }

    public static void main(String[] args) {
        ShortestBridge solution = new ShortestBridge();

        // Test case 1
        int[][] grid1 = {{0, 1}, {1, 0}};
        int result1 = solution.shortestBridge(grid1);
        System.out.println("Test Case 1:");
        System.out.println(result1); // Expected: 1

        // Test case 2
        int[][] grid2 = {{0, 1, 0}, {0, 0, 0}, {0, 0, 1}};
        int result2 = solution.shortestBridge(grid2);
        System.out.println("Test Case 2:");
        System.out.println(result2); // Expected: 2

        // Test case 3
        int[][] grid3 = {{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}};
        int result3 = solution.shortestBridge(grid3);
        System.out.println("Test Case 3:");
        System.out.println(result3); // Expected: 1
    }
}


