package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 *
 * Hard
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * Example 2:
 *
 * Input: matrix = [["0"]]
 * Output: 0
 * Example 3:
 *
 * Input: matrix = [["1"]]
 * Output: 1
 *
 */
public class MaximalRectangle {
    /**
     * 解题思路：
     *
     * 维护一个一维数组 heights，长度为列数，用于记录当前行上每个位置的高度；
     * 维护一个一维数组 left，长度为列数，用于记录当前行上每个位置的左边界；
     * 维护一个一维数组 right，长度为列数，用于记录当前行上每个位置的右边界；
     * 初始化 heights，left，right 为全 0；
     * 遍历每一行，更新 heights，left，right 的值；
     * 计算当前行上最大矩形的面积，更新结果；
     * 返回结果。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(m*n)，其中 m 和 n 分别是矩阵的行数和列数。需要遍历矩阵的每个元素。
     * 空间复杂度：O(n)，其中 n 是矩阵的列数。需要维护一个长度为 n 的一维数组 heights、left、right。
     */

    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            int currLeft = 0;
            int currRight = n;

            // 更新 heights 数组和 left 数组
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                    left[j] = Math.max(left[j], currLeft);
                } else {
                    heights[j] = 0;
                    left[j] = 0;
                    currLeft = j + 1;
                }
            }

            // 更新 right 数组
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], currRight);
                } else {
                    right[j] = n;
                    currRight = j;
                }
            }

            // 计算当前行上最大矩形的面积
            for (int j = 0; j < n; j++) {
                int area = (right[j] - left[j]) * heights[j];
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // Test case 1
        char[][] matrix1 = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        // The maximal rectangle is shown in the above picture.
        // Expected output: 6
        System.out.println(maximalRectangle(matrix1));

        // Test case 2
        char[][] matrix2 = {{'0'}};
        // Expected output: 0
        System.out.println(maximalRectangle(matrix2));

        // Test case 3
        char[][] matrix3 = {{'1'}};
        // Expected output: 1
        System.out.println(maximalRectangle(matrix3));
    }
}