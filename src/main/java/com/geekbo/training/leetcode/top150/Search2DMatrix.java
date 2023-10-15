package com.geekbo.training.leetcode.top150;


/**
 * 74. Search a 2D Matrix
 * You are given an m x n integer matrix matrix with the following two properties:
 * <p>
 * Each row is sorted in non-decreasing order.
 * The first integer of each row is greater than the last integer of the previous row.
 * Given an integer target, return true if target is in matrix or false otherwise.
 * <p>
 * You must write a solution in O(log(m * n)) time complexity.
 * <p>
 * Example 1:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 */
public class Search2DMatrix {
    /**
     * 为了以O(log(m * n))的时间复杂度高效解决这个问题，我们可以使用修改过的二分查找算法。
     * <p>
     * 该算法将矩阵视为一个展开的有序数组。它在展开的数组上执行二分查找，以找到目标元素。
     * <p>
     * 初始时，左指针设置为展开数组的第一个元素（0），右指针设置为最后一个元素（rows * cols - 1）。
     * 每次迭代中，算法计算中间索引，并从矩阵中获取对应的元素。如果中间元素等于目标元素，则算法返回true。
     * 如果中间元素小于目标元素，则将左指针移动到mid + 1。如果中间元素大于目标元素，则将右指针移动到mid - 1。
     * 算法继续执行，直到左指针大于右指针，此时目标元素未在矩阵中找到，算法返回false。
     * <p>
     * 该算法的时间复杂度为O(log(m * n))，因为它在具有m行和n列的矩阵上执行二分查找。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midElement = matrix[mid / cols][mid % cols];

            if (midElement == target) {
                return true;
            } else if (midElement < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Search2DMatrix search2DMatrix = new Search2DMatrix();

        // 测试用例 1
        int[][] matrix1 = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target1 = 3;
        System.out.println(search2DMatrix.searchMatrix(matrix1, target1));  // 输出: true

        // 测试用例 2
        int[][] matrix2 = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target2 = 13;
        System.out.println(search2DMatrix.searchMatrix(matrix2, target2));  // 输出: false
    }
}
