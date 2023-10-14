package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    /**
     * 螺旋顺序打印矩阵的元素
     * <p>
     * 解题思路：
     * <p>
     * 题目要求以螺旋顺序打印矩阵的元素。
     * 我们可以使用四个变量来表示矩阵的边界：top、bottom、left、right。
     * 按照顺序遍历矩阵的边界，然后逐层缩小边界，直到遍历完所有元素。
     * 具体步骤：
     * 从左到右遍历上边界，将元素加入结果列表。
     * 缩小上边界。
     * 从上到下遍历右边界，将元素加入结果列表。
     * 缩小右边界。
     * 从右到左遍历下边界，将元素加入结果列表。
     * 缩小下边界。
     * 从下到上遍历左边界，将元素加入结果列表。
     * 缩小左边界。
     * 重复上述步骤直到遍历完所有元素。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(m * n)，其中m是矩阵的行数，n是矩阵的列数。我们需要遍历矩阵的所有元素。
     * 空间复杂度：O(1)。除了存储结果的列表，我们没有使用额外的空间。
     *
     * @param matrix 矩阵
     * @return 元素的螺旋顺序
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        int m = matrix.length;    // 矩阵的行数
        int n = matrix[0].length;  // 矩阵的列数
        int top = 0;              // 上边界
        int bottom = m - 1;       // 下边界
        int left = 0;             // 左边界
        int right = n - 1;        // 右边界

        while (top <= bottom && left <= right) {
            // 上边界，从左到右
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // 右边界，从上到下
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // 下边界，从右到左
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // 左边界，从下到上
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] matrix1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        // 预期输出：[1, 2, 3, 6, 9, 8, 7, 4, 5]
        System.out.println(spiralOrder(matrix1));

        // 测试用例2
        int[][] matrix2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        // 预期输出：[1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
        System.out.println(spiralOrder(matrix2));
    }
}