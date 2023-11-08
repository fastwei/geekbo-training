package com.geekbo.training.leetcode.dp;

/**
 * 174. Dungeon Game
 * Hard
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially
 * positioned in the top-left room and must fight his way through dungeon to rescue the princess.
 * <p>
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 * <p>
 * Some of the rooms are guarded by demons (represented by negative integers),
 * so the knight loses health upon entering these rooms; other rooms
 * are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).
 * <p>
 * To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 * <p>
 * Return the knight's minimum initial health so that he can rescue the princess.
 * <p>
 * Note that any room can contain threats or power-ups, even the first room the knight enters
 * and the bottom-right room where the princess is imprisoned.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * Output: 7
 * Explanation: The initial health of the knight must be at least 7 if he follows the optimal path:
 * RIGHT-> RIGHT -> DOWN -> DOWN.
 * Example 2:
 * <p>
 * Input: dungeon = [[0]]
 * Output: 1
 */
public class DungeonGame {
    /**
     * 解题思路：
     * <p>
     * 我们使用动态规划来解决这个问题。我们创建一个二维数组dp来存储到达每个房间所需的最小初始健康值。
     * 从右下角的房间开始，向上和向左遍历每个房间。
     * 对于每个房间，我们计算到达该房间所需的最小健康值，考虑到它下方和右方的房间的健康值需求。
     * 到达公主所需的最小健康值是1和到达右边房间或下方房间所需的最小健康值中的较小值，减去当前房间的健康值。
     * 如果所需的最小健康值小于等于0，我们将其设置为1，以确保骑士生存。
     * 最后返回到达左上角房间（起点）所需的最小健康值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历每个房间需要O(m * n)的时间，其中m是dungeon的行数，n是dungeon的列数。
     * 因此，总的时间复杂度为O(m * n)。
     * 空间复杂度：我们使用了一个二维数组dp来存储到达每个房间所需的最小初始健康值，其大小与dungeon的大小相同。
     * 因此，空间复杂度为O(m * n)。
     *
     * @param dungeon
     * @return
     */
    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // Create a dp array to store the minimum health required to reach each room
        int[][] dp = new int[m][n];

        // Calculate the minimum health required to reach the princess starting from the bottom-right room
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    // Base case: bottom-right room
                    dp[i][j] = Math.max(1, 1 - dungeon[i][j]);
                } else if (i == m - 1) {
                    // Base case: last row
                    dp[i][j] = Math.max(1, dp[i][j + 1] - dungeon[i][j]);
                } else if (j == n - 1) {
                    // Base case: last column
                    dp[i][j] = Math.max(1, dp[i + 1][j] - dungeon[i][j]);
                } else {
                    // General case: calculate the minimum health required by choosing the path with the lower health requirement
                    int minHealth = Math.min(dp[i][j + 1], dp[i + 1][j]);
                    dp[i][j] = Math.max(1, minHealth - dungeon[i][j]);
                }
            }
        }

        // Return the minimum health required to reach the top-left room (starting point)
        return dp[0][0];
    }

    public static void main(String[] args) {
        // Test cases
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        // The optimal path: RIGHT -> RIGHT -> DOWN -> DOWN
        // The initial health of the knight must be at least 7
        System.out.println(calculateMinimumHP(dungeon));  // Expected output: 7

        dungeon = new int[][]{{0}};
        // The knight can reach the princess with an initial health of 1 (no threats in the dungeon)
        System.out.println(calculateMinimumHP(dungeon));  // Expected output: 1
    }
}
