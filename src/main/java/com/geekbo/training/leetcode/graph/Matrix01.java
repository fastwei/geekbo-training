package com.geekbo.training.leetcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 542. 01 Matrix
 * Medium
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 * Example 2:
 * <p>
 * <p>
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 */
class Matrix01 {
    /**
     * 解题思路： 这道题可以使用广度优先搜索来解决。
     * 我们首先将所有的0加入队列，并标记为已访问。
     * 然后，从队列中依次取出元素，对每个元素的四个相邻位置进行判断，如果位置合法且未访问过，则将距离加1，并将该位置加入队列。
     * 当队列为空时，说明已经遍历完所有的元素，返回结果。
     * <p>
     * 算法复杂度分析： 假设矩阵的大小为m×n。
     * <p>
     * 遍历矩阵的时间复杂度为O(mn)。
     * <p>
     * 使用队列存储待访问的元素，空间复杂度为O(mn)。
     * 综上所述，算法的总时间复杂度为O(mn)，总空间复杂度为O(mn)。
     *
     * @param mat
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dist = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();

        // 将所有的0加入队列，并标记为已访问
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 广度优先搜索
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currRow = curr[0];
            int currCol = curr[1];

            for (int[] direction : directions) {
                int newRow = currRow + direction[0];
                int newCol = currCol + direction[1];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                    dist[newRow][newCol] = dist[currRow][currCol] + 1;
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Matrix01 matrix01 = new Matrix01();

        // 测试用例1
        int[][] mat1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] expected1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] result1 = matrix01.updateMatrix(mat1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + Arrays.deepToString(expected1));
        System.out.println("实际输出: " + Arrays.deepToString(result1));

        // 测试用例2
        int[][] mat2 = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] expected2 = {{0, 0, 0}, {0, 1, 0}, {1, 2, 1}};
        int[][] result2 = matrix01.updateMatrix(mat2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + Arrays.deepToString(expected2));
        System.out.println("实际输出: " + Arrays.deepToString(result2));
    }
}
