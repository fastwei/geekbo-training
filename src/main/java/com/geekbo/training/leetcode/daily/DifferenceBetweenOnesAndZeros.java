package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 2482. Difference Between Ones and Zeros in Row and Column
 * Medium
 * You are given a 0-indexed m x n binary matrix grid.
 * <p>
 * A 0-indexed m x n difference matrix diff is created with the following procedure:
 * <p>
 * Let the number of ones in the ith row be onesRowi.
 * Let the number of ones in the jth column be onesColj.
 * Let the number of zeros in the ith row be zerosRowi.
 * Let the number of zeros in the jth column be zerosColj.
 * diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj
 * Return the difference matrix diff.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,1,1],[1,0,1],[0,0,1]]
 * Output: [[0,0,4],[0,0,4],[-2,-2,2]]
 * Explanation:
 * - diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 2 + 1 - 1 - 2 = 0
 * - diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 2 + 1 - 1 - 2 = 0
 * - diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 2 + 3 - 1 - 0 = 4
 * - diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 2 + 1 - 1 - 2 = 0
 * - diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 2 + 1 - 1 - 2 = 0
 * - diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 2 + 3 - 1 - 0 = 4
 * - diff[2][0] = onesRow2 + onesCol0 - zerosRow2 - zerosCol0 = 1 + 1 - 2 - 2 = -2
 * - diff[2][1] = onesRow2 + onesCol1 - zerosRow2 - zerosCol1 = 1 + 1 - 2 - 2 = -2
 * - diff[2][2] = onesRow2 + onesCol2 - zerosRow2 - zerosCol2 = 1 + 3 - 2 - 0 = 2
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [[1,1,1],[1,1,1]]
 * Output: [[5,5,5],[5,5,5]]
 * Explanation:
 * - diff[0][0] = onesRow0 + onesCol0 - zerosRow0 - zerosCol0 = 3 + 2 - 0 - 0 = 5
 * - diff[0][1] = onesRow0 + onesCol1 - zerosRow0 - zerosCol1 = 3 + 2 - 0 - 0 = 5
 * - diff[0][2] = onesRow0 + onesCol2 - zerosRow0 - zerosCol2 = 3 + 2 - 0 - 0 = 5
 * - diff[1][0] = onesRow1 + onesCol0 - zerosRow1 - zerosCol0 = 3 + 2 - 0 - 0 = 5
 * - diff[1][1] = onesRow1 + onesCol1 - zerosRow1 - zerosCol1 = 3 + 2 - 0 - 0 = 5
 * - diff[1][2] = onesRow1 + onesCol2 - zerosRow1 - zerosCol2 = 3 + 2 - 0 - 0 = 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 105
 * 1 <= m * n <= 105
 * grid[i][j] is either 0 or 1.
 */
public class DifferenceBetweenOnesAndZeros {
    /**
     * 解题思路：
     * <p>
     * 首先，我们需要计算每一行和每一列的1和0的个数。
     * 然后，根据计算的结果，我们可以得到差值矩阵：diff[i][j]=onesRow[i]+onesCol[j]-zerosRow[i]-zerosCol[j]。
     * 最后，返回差值矩阵。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设矩阵的大小为m x n。计算每一行和每一列的1和0的个数的时间复杂度为O(m x n)，计算差值矩阵的时间复杂度也为O(m x n)，因此总的时间复杂度为O(m x n)。
     * 空间复杂度：差值矩阵的大小为m x n，因此空间复杂度为O(m x n)。
     *
     * @param grid
     * @return
     */
    public static int[][] onesMinusZeros(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] diff = new int[m][n];

        int[] onesRow = new int[m];
        int[] onesCol = new int[n];
        int[] zerosRow = new int[m];
        int[] zerosCol = new int[n];

        // 计算每一行和每一列的1和0的个数
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    onesRow[i]++;
                    onesCol[j]++;
                } else {
                    zerosRow[i]++;
                    zerosCol[j]++;
                }
            }
        }

        // 计算差值矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                diff[i][j] = onesRow[i] + onesCol[j] - zerosRow[i] - zerosCol[j];
            }
        }

        return diff;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{0, 1, 1}, {1, 0, 1}, {0, 0, 1}};
        int[][] diff1 = onesMinusZeros(grid1);
        System.out.println(Arrays.deepToString(diff1)); // [[0, 0, 4], [0, 0, 4], [-2, -2, 2]]

        int[][] grid2 = {{1, 1, 1}, {1, 1, 1}};
        int[][] diff2 = onesMinusZeros(grid2);
        System.out.println(Arrays.deepToString(diff2)); // [[5, 5, 5], [5, 5, 5]]
    }
}



