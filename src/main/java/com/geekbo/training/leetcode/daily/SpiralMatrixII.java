package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 59. Spiral Matrix II
 * Medium
 * Topics
 * Companies
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [[1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 20
 */
public class SpiralMatrixII {
    public static int[][] generateMatrix(int n) {
        // 创建一个 n x n 的矩阵
        int[][] matrix = new int[n][n];

        // 定义四个边界：上边界、下边界、左边界、右边界
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        // 定义当前填充的数字
        int num = 1;

        // 循环填充矩阵
        while (top <= bottom && left <= right) {
            // 填充上边界，从左到右
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            top++;

            // 填充右边界，从上到下
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;

            // 填充下边界，从右到左
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    matrix[bottom][i] = num++;
                }
                bottom--;
            }

            // 填充左边界，从下到上
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        // 创建测试用例
        int n1 = 3;
        // 预期输出: [[1,2,3],[8,9,4],[7,6,5]]
        int[][] result1 = generateMatrix(n1);
        System.out.println(Arrays.deepToString(result1));

        int n2 = 1;
        // 预期输出: [[1]]
        int[][] result2 = generateMatrix(n2);
        System.out.println(Arrays.deepToString(result2));
    }
}