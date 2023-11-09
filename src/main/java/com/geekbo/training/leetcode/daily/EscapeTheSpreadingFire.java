package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 2258. Escape the Spreading Fire
 * Hard
 * You are given a 0-indexed 2D integer array grid of size m x n which represents a field.
 * Each cell has one of three values:
 * <p>
 * 0 represents grass,
 * 1 represents fire,
 * 2 represents a wall that you and fire cannot pass through.
 * You are situated in the top-left cell, (0, 0),
 * and you want to travel to the safehouse at the bottom-right cell,
 * (m - 1, n - 1). Every minute, you may move to an adjacent grass cell.
 * After your move, every fire cell will spread to all adjacent cells that are not walls.
 * <p>
 * Return the maximum number of minutes that you can stay in your initial position
 * before moving while still safely reaching the safehouse. If this is impossible, return -1.
 * If you can always reach the safehouse regardless of the minutes stayed, return 109.
 * <p>
 * Note that even if the fire spreads to the safehouse immediately after you have reached it,
 * it will be counted as safely reaching the safehouse.
 * <p>
 * A cell is adjacent to another cell if the former is directly north, east, south,
 * or west of the latter (i.e., their sides are touching).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
 * Output: 3
 * Explanation: The figure above shows the scenario where you stay in the initial position for 3 minutes.
 * You will still be able to safely reach the safehouse.
 * Staying for more than 3 minutes will not allow you to safely reach the safehouse.
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
 * Output: -1
 * Explanation: The figure above shows the scenario where you immediately move towards the safehouse.
 * Fire will spread to any cell you move towards and it is impossible to safely reach the safehouse.
 * Thus, -1 is returned.
 * Example 3:
 * <p>
 * <p>
 * Input: grid = [[0,0,0],[2,2,0],[1,2,0]]
 * Output: 1000000000
 * Explanation: The figure above shows the initial grid.
 * Notice that the fire is contained by walls and you will always be able to safely reach the safehouse.
 * Thus, 109 is returned.
 */
public class EscapeTheSpreadingFire {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 计算可以在初始位置停留的最大分钟数
     * 下面是代码的解题思路和算法复杂度分析：
     * <p>
     * 首先，定义了一个directions数组，表示四个方向上的移动（上、下、左、右）。
     * 主函数maximumMinutes中，使用二分查找来确定最大停留时间。初始的上界为场地的大小（m * n），下界为-1。
     * 在每一次二分查找中，调用reachable函数来判断是否能够到达安全地点。
     * reachable函数会传入当前移动次数和火灾位置的列表。
     * 在reachable函数中，首先进行深拷贝，将原始的场地复制一份，然后使用两个队列分别存储火灾位置和人的位置。
     * 在每一次移动中，先判断火灾是否扩散到安全地点，如果是则返回false。
     * 然后判断人是否能够移动到安全地点，如果是则返回true。
     * 如果火灾扩散和人的移动都没有发生，则返回false。
     * 最后，在主函数中返回最大停留时间，如果最大停留时间等于场地大小（m * n），
     * 则返回1e9（表示无论停留多久都可以到达安全地点），否则返回最大停留时间。
     * 算法的时间复杂度为O((m * n) * log(m * n))，其中m和n分别是场地的行数和列数，
     * 二分查找的次数为log(m * n)，而在每一次二分查找中，需要进行一次火灾扩散和一次人的移动，时间复杂度为O(m * n)。
     *
     * @param grid 二维数组表示的场地
     * @return 最大分钟数，如果不可能到达安全地点则返回1e9，如果无论停留多久都可以到达安全地点则返回最大停留时间
     */
    public int maximumMinutes(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<int[]> fires = new ArrayList<>();

        // 遍历场地，找到火灾的位置
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    fires.add(new int[]{i, j});
                }
            }
        }

        int l = -1, r = m * n;
        while (l < r) {
            // 使用二分查找确定最大停留时间
            int mid = l + (r - l) / 2 + 1;
            if (reachable(grid, mid, fires)) l = mid;
            else r = mid - 1;
        }
        return l == m * n ? (int) 1e9 : l;
    }

    /**
     * 判断是否可以在给定的移动次数内到达安全地点
     *
     * @param grid  场地数组
     * @param move  移动次数
     * @param fires 火灾位置的列表
     * @return 是否可以到达安全地点
     */
    boolean reachable(int[][] grid, int move, List<int[]> fires) {
        int m = grid.length, n = grid[0].length;
        int[][] copy = clone(grid);

        Queue<int[]> fire = new LinkedList<>();
        fire.addAll(fires);
        while (!fire.isEmpty() && move-- > 0) {
            // 火灾扩散
            if (spread(fire, copy)) return false;
        }

        Queue<int[]> person = new LinkedList<>();
        person.add(new int[]{0, 0});
        while (!person.isEmpty()) {
            boolean onFire = spread(fire, copy);
            // 人的移动
            if (spread(person, copy)) return true;
            if (onFire) return false;
        }
        return false;
    }

    /**
     * 判断队列中的火灾是否可以扩散到安全地点
     *
     * @param queue 队列
     * @param grid  场地数组
     * @return 是否可以扩散到安全地点
     */
    boolean spread(Queue<int[]> queue, int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int size = queue.size();

        while (size-- > 0) {
            int[] cell = queue.remove();
            for (int[] d : DIRECTIONS) {
                int x = cell[0] + d[0], y = cell[1] + d[1];
                if (x == m - 1 && y == n - 1) return true;
                if (x >= 0 &&
                        x < m && y >= 0 && y < n && grid[x][y] == 0) {
                    grid[x][y] = -1;
                    queue.add(new int[]{x, y});
                }
            }
        }
        return false;
    }

    /**
     * 对二维数组进行深拷贝
     *
     * @param grid 原始的二维数组
     * @return 新的二维数组
     */
    int[][] clone(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = grid[i][j];
            }
        }
        return copy;
    }
}