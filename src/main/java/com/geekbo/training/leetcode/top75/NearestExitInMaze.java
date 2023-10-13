package com.geekbo.training.leetcode.top75;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 1926. Nearest Exit from Entrance in Maze
 *
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
 * You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol]
 * denotes the row and column of the cell you are initially standing at.
 * <p>
 * In one step, you can move one cell up, down, left, or right.
 * You cannot step into a cell with a wall, and you cannot step outside the maze.
 * Your goal is to find the nearest exit from the entrance.
 * An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
 * <p>
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 * Example 1:
 * <p>
 * <p>
 * Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
 * Output: 1
 * Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
 * Initially, you are at the entrance cell [1,2].
 * - You can reach [1,0] by moving 2 steps left.
 * - You can reach [0,2] by moving 1 step up.
 * It is impossible to reach [2,3] from the entrance.
 * Thus, the nearest exit is [0,2], which is 1 step away.
 * Example 2:
 * <p>
 * <p>
 * Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
 * Output: 2
 * Explanation: There is 1 exit in this maze at [1,2].
 * [1,0] does not count as an exit since it is the entrance cell.
 * Initially, you are at the entrance cell [1,0].
 * - You can reach [1,2] by moving 2 steps right.
 * Thus, the nearest exit is [1,2], which is 2 steps away.
 * Example 3:
 * <p>
 * <p>
 * Input: maze = [[".","+"]], entrance = [0,0]
 * Output: -1
 * Explanation: There are no exits in this maze.
 */
public class NearestExitInMaze {

    /**
     * 解题思路：
     * <p>
     * 使用BFS搜索最近的出口。
     * 从入口开始，逐层搜索周围的空单元格。
     * 当找到一个出口时，返回步数。
     * 如果没有找到出口，返回-1。
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(m * n)，其中 m 和 n 是迷宫的行数和列数。
     * 空间复杂度：O(m * n)，用于存储访问标记。
     *
     * @param maze
     * @param entrance
     * @return
     */
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                if (isExit(current, m, n)) {
                    if (!isEntrance(current, entrance)) {
                        return steps;
                    }
                }

                for (int[] dir : directions) {
                    int newRow = current[0] + dir[0];
                    int newCol = current[1] + dir[1];

                    if (isValid(newRow, newCol, m, n) && maze[newRow][newCol] == '.' && !visited[newRow][newCol]) {
                        queue.offer(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    private boolean isValid(int row, int col, int m, int n) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }

    private boolean isExit(int[] cell, int m, int n) {
        return cell[0] == 0 || cell[0] == m - 1 || cell[1] == 0 || cell[1] == n - 1;
    }

    private boolean isEntrance(int[] cell, int[] entrance) {
        return cell[0] == entrance[0] && cell[1] == entrance[1];
    }
}
