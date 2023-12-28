package com.geekbo.training.leetcode.crackinterview109;

/**
 *
 * 面试题 10.09. 排序矩阵查找
 * 中等
 * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 */
public class MatrixSearch {
    /**
     * 在给定的M×N矩阵中查找目标元素
     *
     * @param matrix 矩阵
     * @param target 目标元素
     * @return 是否找到目标元素
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int row = 0;
        int col = matrix[0].length - 1;
        
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        // 测试用例
        int[][] matrix = {
            {1,   4,  7, 11, 15},
            {2,   5,  8, 12, 19},
            {3,   6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        
        int target1 = 5;
        System.out.println(searchMatrix(matrix, target1)); // 预期输出：true
        
        int target2 = 20;
        System.out.println(searchMatrix(matrix, target2)); // 预期输出：false
    }
}