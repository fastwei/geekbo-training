package com.geekbo.training.leetcode.skill;


/**
 * Given a square matrix mat, return the sum of the matrix diagonals.
 * <p>
 * Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: mat = [[1,2,3],
 * [4,5,6],
 * [7,8,9]]
 * Output: 25
 * Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
 * Notice that element mat[1][1] = 5 is counted only once.
 * Example 2:
 * <p>
 * Input: mat = [[1,1,1,1],
 * [1,1,1,1],
 * [1,1,1,1],
 * [1,1,1,1]]
 * Output: 8
 * Example 3:
 * <p>
 * Input: mat = [[5]]
 * Output: 5
 */
public class MatrixDiagonalSum {

    /**
     * 解题思路：
     * <p>
     * 题目要求计算一个方阵的主对角线和副对角线的元素之和。
     * 主对角线上的元素位于行和列的相同位置，副对角线上的元素位于行和列的位置之和等于方阵的大小减一。
     * 遍历方阵的每一行，将主对角线上的元素加到总和中，同时将副对角线上的元素加到总和中。
     * 如果方阵的大小为奇数，副对角线上的中心元素会被重复计算，所以需要将其减去。
     * 返回总和作为结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 方阵的大小为n x n，需要遍历每一行，所以时间复杂度为O(n)。
     * 空间复杂度为O(1)。
     *
     * @param mat
     * @return
     */
    public static int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += mat[i][i]; // primary diagonal
            sum += mat[i][n - 1 - i]; // secondary diagonal
        }

        // Exclude the center element if the matrix has odd size
        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] mat1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(diagonalSum(mat1)); // Expected: 25

        int[][] mat2 = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        System.out.println(diagonalSum(mat2)); // Expected: 8

        int[][] mat3 = {{5}};
        System.out.println(diagonalSum(mat3)); // Expected: 5
    }
}
