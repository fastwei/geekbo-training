package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1727. Largest Submatrix With Rearrangements
 * Medium
 * You are given a binary matrix matrix of size m x n,
 * and you are allowed to rearrange the columns of the matrix in any order.
 * <p>
 * Return the area of the largest submatrix within matrix where every element of the
 * submatrix is 1 after reordering the columns optimally.
 * <p>
 * Example 1:
 * Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
 * Output: 4
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 4.
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,0,1,0,1]]
 * Output: 3
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 3.
 * Example 3:
 * <p>
 * Input: matrix = [[1,1,0],[1,0,1]]
 * Output: 2
 * Explanation: Notice that you must rearrange entire columns,
 * and there is no way to make a submatrix of 1s larger than an area of 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m * n <= 105
 * matrix[i][j] is either 0 or 1.
 */
public class LargestSubmatrixWithRearrangements {
    /**
     * 解题思路：
     * <p>
     * 首先，对于每一列，我们计算从该列向上的连续1的数量，将其存储在matrix数组中。
     * 然后，对于每一行，我们对该行的列进行排序，然后计算以每一列为右边界的最大矩形面积。
     * 最后，我们返回最大的矩形面积作为结果。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历矩阵计算每列的连续1的数量需要O(m * n)的时间，对每一行的列进行排序和计算面积需要O(m * n * log n)的时间，所以总时间复杂度为O(m * n * log n)。
     * 空间复杂度：使用了一个大小为m * n的二维数组来存储每列的连续1的数量，所以空间复杂度为O(m * n)。
     *
     * @param matrix
     * @return
     */
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 计算每列的连续1的数量
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        // 对每一行的列进行排序，并计算面积
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            Arrays.sort(matrix[i]);
            for (int j = n - 1; j >= 0; j--) {
                int width = n - j;
                int height = matrix[i][j];
                int area = width * height;
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestSubmatrixWithRearrangements solution = new LargestSubmatrixWithRearrangements();

        // 测试用例1
        int[][] matrix1 = {{0, 0, 1}, {1, 1, 1}, {1, 0, 1}};
        int result1 = solution.largestSubmatrix(matrix1);
        System.out.println("测试用例1:");
        System.out.println(result1); // 预期输出: 4

        // 测试用例2
        int[][] matrix2 = {{1, 0, 1, 0, 1}};
        int result2 = solution.largestSubmatrix(matrix2);
        System.out.println("测试用例2:");
        System.out.println(result2); // 预期输出: 3

        // 测试用例3
        int[][] matrix3 = {{1, 1, 0}, {1, 0, 1}};
        int result3 = solution.largestSubmatrix(matrix3);
        System.out.println("测试用例3:");
        System.out.println(result3); // 预期输出: 2
    }
}
