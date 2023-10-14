package com.geekbo.training.leetcode.top150;

/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 * <p>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * <p>
 * Example 2:
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
public class RotateImage {
    /**
     * 将二维矩阵顺时针旋转90度
     * <p>
     * 解题思路：
     * <p>
     * 题目要求将二维矩阵顺时针旋转90度。
     * 我们可以先进行矩阵的转置操作，然后将每一行逆序。
     * 具体步骤：
     * 先遍历矩阵，将第i行第j列的元素与第j行第i列的元素进行交换，完成矩阵的转置。
     * 再遍历矩阵的每一行，将第i行的元素与第n-1-i行的元素进行交换，完成每一行的逆序操作。
     * 通过上述步骤，我们就可以将二维矩阵顺时针旋转90度。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n是矩阵的边长。我们需要遍历矩阵的所有元素进行转置和逆序操作。
     * 空间复杂度：O(1)。我们只使用了常数个额外变量来进行元素交换。
     *
     * @param matrix 二维矩阵
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // 先进行矩阵的转置操作
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 再将每一行逆序
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // 预期输出：[[7, 4, 1], [8, 5, 2], [9, 6, 3]]
        rotate(matrix1);
        printMatrix(matrix1);

        // 测试用例2
        int[][] matrix2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        // 预期输出：[[15, 13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7, 10, 11]]
        rotate(matrix2);
        printMatrix(matrix2);
    }

    /**
     * 打印二维矩阵
     *
     * @param matrix 二维矩阵
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}