package com.geekbo.training.leetcode.top75;

/**
 *
 * There is a robot on an m x n grid.
 * The robot is initially located at the top-left corner (i.e., grid[0][0]).
 * The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
 * The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n,
 * return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 * Example 1:
 *
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 */
public class UniquePaths {

    /**
     * 解题思路：
     * <p>
     * 我们可以使用动态规划来解决此问题。
     * 创建一个二维数组dp，用于保存到达每个位置的唯一路径数。
     * 初始化第一行和第一列的值为1，因为只能向右或向下移动。
     * 使用动态规划计算到达每个位置的唯一路径数，通过状态转移方程dp[i][j] = dp[i-1][j] + dp[i][j-1]，
     * 其中dp[i-1][j]表示上方位置的路径数，dp[i][j-1]表示左方位置的路径数。
     * 最后，返回到达右下角位置的唯一路径数。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(m*n)，需要计算每个位置的路径数。
     * 空间复杂度：O(m*n)，需要创建一个二维数组来保存路径数。
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {

        //Constraints:1 <= m, n <= 100
        if(m <1 || n > 100){
            return -1;
        }

        // 创建一个二维数组dp，用于保存到达每个位置的唯一路径数
        int[][] dp = new int[m][n];

        // 初始化第一行和第一列的值为1，因为只能向右或向下移动
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 动态规划计算到达每个位置的唯一路径数
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 到达当前位置的唯一路径数等于上方位置和左方位置的路径数之和
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // 返回到达右下角位置的唯一路径数
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        // 测试用例
        int m1 = 3, n1 = 7;
        int result1 = uniquePaths(m1, n1);
        System.out.println("m = " + m1 + ", n = " + n1 + "，唯一路径数：" + result1);

        int m2 = 3, n2 = 2;
        int result2 = uniquePaths(m2, n2);
        System.out.println("m = " + m2 + ", n = " + n2 + "，唯一路径数：" + result2);
    }
}