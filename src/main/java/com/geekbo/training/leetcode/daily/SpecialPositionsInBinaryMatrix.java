package com.geekbo.training.leetcode.daily;

/**
 * 1582. Special Positions in a Binary Matrix
 * Easy
 * Given an m x n binary matrix mat, return the number of special positions in mat.
 * <p>
 * A position (i, j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: mat = [[1,0,0],[0,0,1],[1,0,0]]
 * Output: 1
 * Explanation: (1, 2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
 * Example 2:
 * <p>
 * <p>
 * Input: mat = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 * Explanation: (0, 0), (1, 1) and (2, 2) are special positions.
 * <p>
 * 给定一个 m x n 的二进制矩阵 mat，返回矩阵中特殊位置的数量。
 * <p>
 * 一个位置 (i, j) 被称为特殊位置，如果 mat[i][j] 等于 1，并且该位置所在行的其他元素和该位置所在列的其他元素都等于 0。
 * <p>
 * 解题思路： 遍历矩阵中的每个位置，判断该位置是否为特殊位置。如果是特殊位置，则计数器加一。 对于每个位置 (i, j)，判断 mat[i][j] 是否等于 1，并且判断该位置所在行的其他元素和该位置所在列的其他元素是否都等于 0。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：遍历整个矩阵需要 O(m*n) 的时间。
 * 空间复杂度：只使用了常数个额外变量，所以空间复杂度为 O(1)。
 */
class SpecialPositionsInBinaryMatrix {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && isSpecial(mat, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isSpecial(int[][] mat, int row, int col) {
        int m = mat.length;
        int n = mat[0].length;

        for (int i = 0; i < m; i++) {
            if (i != row && mat[i][col] == 1) {
                return false;
            }
        }

        for (int j = 0; j < n; j++) {
            if (j != col && mat[row][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        SpecialPositionsInBinaryMatrix solution = new SpecialPositionsInBinaryMatrix();

        // 测试用例1
        int[][] mat1 = {{1, 0, 0}, {0, 0, 1}, {1, 0, 0}};
        // 预期结果：1
        System.out.println(solution.numSpecial(mat1));

        // 测试用例2
        int[][] mat2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        // 预期结果：3
        System.out.println(solution.numSpecial(mat2));
    }
}