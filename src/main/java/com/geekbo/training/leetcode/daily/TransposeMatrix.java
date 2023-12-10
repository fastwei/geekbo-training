package com.geekbo.training.leetcode.daily;

/**
 * 867. Transpose Matrix
 * Easy
 * Given a 2D integer array matrix, return the transpose of matrix.
 * <p>
 * The transpose of a matrix is the matrix flipped over its main diagonal,
 * switching the matrix's row and column indices.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[1,4,7],[2,5,8],[3,6,9]]
 * Example 2:
 * <p>
 * Input: matrix = [[1,2,3],[4,5,6]]
 * Output: [[1,4],[2,5],[3,6]]
 */
class TransposeMatrix {
    /**
     * 解题思路： 首先，我们需要创建一个具有翻转后的行和列索引的新矩阵。
     * 通过观察，我们可以发现翻转的结果是将原矩阵的行和列进行交换。
     * 因此，我们可以创建一个新的矩阵，行数为原矩阵的列数，列数为原矩阵的行数。
     * 然后，我们遍历原矩阵中的每个元素，将其放置在新矩阵的对应位置即可得到翻转后的结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历原矩阵需要O(m * n)的时间，其中m和n分别是原矩阵的行数和列数。
     * 因此，算法的时间复杂度为O(m * n)。
     * 空间复杂度：创建一个新的矩阵需要O(n * m)的空间来存储结果，其中n和m分别是原矩阵的列数和行数。
     * 因此，算法的空间复杂度为O(n * m)。
     *
     * @param matrix
     * @return
     */
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] result = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TransposeMatrix solution = new TransposeMatrix();

        // 测试用例1
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] result1 = solution.transpose(matrix1);
        System.out.println("测试用例1：");
        printMatrix(result1);

        // 测试用例2
        int[][] matrix2 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        int[][] result2 = solution.transpose(matrix2);
        System.out.println("测试用例2：");
        printMatrix(result2);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
