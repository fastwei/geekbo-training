package com.geekbo.training.leetcode.crackinterview109;

public class SetZeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // 创建两个标记数组，用于记录需要清零的行和列
        boolean[] rows = new boolean[m];
        boolean[] columns = new boolean[n];

        // 遍历矩阵，记录需要清零的行和列的索引
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        // 再次遍历矩阵，将需要清零的行和列置为0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] || columns[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        SetZeroes solution = new SetZeroes();

        int[][] matrix1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        solution.setZeroes(matrix1);
        printMatrix(matrix1);

        int[][] matrix2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        solution.setZeroes(matrix2);
        printMatrix(matrix2);
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