package com.geekbo.training.leetcode.crackinterview109;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RobotPath {
    public static List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // 创建一个路径列表用于保存结果
        List<List<Integer>> path = new ArrayList<>();

        // 检查起点和终点是否有障碍物
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) {
            return path;
        }

        // 创建一个二维数组用于记录路径
        int[][] dp = new int[m][n];

        // 标记终点
        dp[m-1][n-1] = 1;

        // 从终点开始回溯，找到一条可行路径
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                // 如果当前点是终点或者能够从右边或下边的点到达当前点
                if ((i == m-1 && j == n-1) || (i+1 < m && dp[i+1][j] == 1) || (j+1 < n && dp[i][j+1] == 1)) {
                    dp[i][j] = 1;
                    // 将当前点添加到路径列表中
                    path.add(Arrays.asList(i, j));
                }
            }
        }

        // 反转路径列表，使其按照从起点到终点的顺序排列
        List<List<Integer>> result = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            result.add(path.get(i));
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        List<List<Integer>> result = pathWithObstacles(obstacleGrid);
        System.out.println(result);
    }
}