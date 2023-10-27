package com.geekbo.training.leetcode.top150;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix.
 * This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
 * target = 5
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]],
 * target = 20
 * Output: false
 */
class SearchIn2DMatrix {
    /**
     * 解题思路：
     * <p>
     * 从右上角开始搜索，因为右上角的元素是当前行的最大值，当前列的最小值。
     * 如果当前值等于目标值，则找到目标值。
     * 如果当前值小于目标值，则目标值不可能在当前行，向下搜索。
     * 如果当前值大于目标值，则目标值不可能在当前列，向左搜索。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：在最坏的情况下，需要遍历矩阵的所有元素，
     * 所以时间复杂度为O(m + n)，其中m是矩阵的行数，n是矩阵的列数。
     * 空间复杂度：只需要常数级别的额外空间，所以空间复杂度为O(1)。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 获取矩阵的行数和列数
        int rows = matrix.length;
        int cols = matrix[0].length;

        // 从右上角开始搜索
        int row = 0;
        int col = cols - 1;

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                // 找到目标值
                return true;
            } else if (matrix[row][col] < target) {
                // 当前值小于目标值，向下搜索
                row++;
            } else {
                // 当前值大于目标值，向左搜索
                col--;
            }
        }

        // 没有找到目标值
        return false;
    }

    public static void main(String[] args) {
        SearchIn2DMatrix searchIn2DMatrix = new SearchIn2DMatrix();

        // Test Case 1
        int[][] matrix1 = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int target1 = 5;
        boolean result1 = searchIn2DMatrix.searchMatrix(matrix1, target1);
        System.out.println(result1);  // Expected output: true

        // Test Case 2
        int[][] matrix2 = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int target2 = 20;
        boolean result2 = searchIn2DMatrix.searchMatrix(matrix2, target2);
        System.out.println(result2);  // Expected output: false
    }
}
