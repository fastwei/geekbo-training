package com.geekbo.training.leetcode.skill;

/**
 * Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise,
 * return the number of negative numbers in grid.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 * Example 2:
 * <p>
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 * <p>
 * Follow up: Could you find an O(n + m) solution?
 */
public class CountNegativeNumbers {
    /**
     * 解题思路：
     * <p>
     * 由于矩阵grid是按非递增顺序排序的，可以从矩阵的右上角开始遍历。
     * 初始化行row为0，列col为n-1，其中n是矩阵的列数。
     * 如果当前元素为负数，则说明当前列及其后面的列都是负数，因此将当前列及其后面的列的数量加到计数器中，并向上移动到前一行。
     * 如果当前元素为非负数，则向左移动到前一列。
     * 重复上述步骤直到遍历完矩阵。
     * 返回计数器的值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(m+n)，其中m和n分别是矩阵grid的行数和列数。最坏情况下，需要遍历整个矩阵。
     * 空间复杂度：O(1)，不使用额外的空间。
     *
     * @param grid
     * @return
     */
    public static int countNegatives(int[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        int row = rows - 1;
        int col = 0;

        while (row >= 0 && col < cols) {
            if (grid[row][col] < 0) {
                // If the current number is negative, increment the count and move to the previous row
                count += cols - col;
                row--;
            } else {
                // If the current number is non-negative, move to the next column
                col++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
        // Expected output: 8
        System.out.println(countNegatives(grid1));

        int[][] grid2 = {{3, 2}, {1, 0}};
        // Expected output: 0
        System.out.println(countNegatives(grid2));
    }
}
