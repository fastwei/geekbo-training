package com.geekbo.training.leetcode.top150;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
 * <p>
 * You must do it in place.
 * Example 1:
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 * <p>
 * <p>
 * Follow up:
 * <p>
 * A straightforward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 */
public class SetMatrixZeroes {

    /**
     * 优化思路：
     * 使用常数空间的解决方案
     * 思路与之前相同，但是我们不再使用额外的数组来标记需要置为0的行和列，而是使用矩阵的第一行和第一列来作为标记位。
     * 先遍历矩阵，检查第一行和第一列是否有0，并用两个布尔变量firstRowHasZero和firstColHasZero来记录。
     * 然后再次遍历矩阵的除第一行和第一列之外的元素，如果某个元素为0，则将对应的第一行和第一列的元素置为0。
     *
     * @param matrix
     */
    public static void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;

        // 检查第一行是否有0
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        // 检查第一列是否有0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }

        // 遍历矩阵，如果某个元素为0，则将对应的第一行和第一列的元素置为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 根据第一行和第一列的标记，将对应行和列的元素置为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 根据第一行和第一列的标记，将第一行和第一列的元素置为0
        if (firstRowHasZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColHasZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * 将矩阵中为0的元素所在的行和列都置为0
     * <p>
     * 解题思路如下：
     * <p>
     * 遍历整个矩阵，标记第一行和第一列是否有0。
     * 用两个布尔变量firstRowHasZero和firstColHasZero来记录第一行和第一列是否存在0。
     * 再次遍历矩阵的除第一行和第一列之外的元素，如果某个元素为0，则将对应的第一行和第一列的元素置为0。
     * 这样，我们可以使用第一行和第一列来作为标记位，标记需要置为0的行和列。
     * 根据第一行和第一列的标记，将对应行和列的元素置为0。
     * 最后，根据firstRowHasZero和firstColHasZero的标记，将第一行和第一列的元素置为0。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(m * n)，其中m和n分别是矩阵的行数和列数。因为我们需要遍历整个矩阵两次。
     * 空间复杂度：O(1)。除了常数个额外变量，我们没有使用其他的额外空间。
     *
     * @param matrix 整数矩阵
     */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;

        // 遍历矩阵，标记第一行和第一列是否有0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }

        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        // 遍历矩阵的除第一行和第一列之外的元素，如果为0，则将对应的第一行和第一列的元素置为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 根据第一行和第一列的标记，将对应行和列的元素置为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 根据第一行和第一列的标记，将第一行和第一列的元素置为0
        if (firstRowHasZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColHasZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] matrix1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        // 预期输出：[[1, 0, 1], [0, 0, 0], [1, 0, 1]]
        setZeroes(matrix1);
        printMatrix(matrix1);

        // 测试用例2
        int[][] matrix2 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        // 预期输出：[[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]
        setZeroes(matrix2);
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