package com.geekbo.training.leetcode.crackinterview109;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.12. 八皇后
 * 困难
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，其中每个皇后都不同行、不同列，也不在对角线上。
 * 这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * <p>
 * 注意：本题相对原题做了扩展
 * <p>
 * 示例:
 * <p>
 * 输入：4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class NQueens {
    /**
     * 解决N皇后问题
     * 解题思路：使用回溯法解决N皇后问题。
     * 从第一行开始，尝试在每一列放置皇后，并判断是否合法。
     * 如果合法，则继续放置下一行的皇后；如果不合法，则回溯到上一行，尝试放置皇后在下一列。
     * 当放置了n个皇后时，将当前结果加入结果列表。
     * <p>
     * 算法复杂度分析：假设N为皇后的数量，生成的所有合法摆法的数量是N皇后问题的解的个数，记作S(N)。
     * 时间复杂度为O(S(N))，空间复杂度为O(N)。因为需要存储每一行中皇后所在的列索引的数组。
     *
     * @param n 皇后数量
     * @return 所有合法的摆法
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // 使用一个数组记录每一行皇后所在的列索引
        int[] queens = new int[n];
        // 初始化数组，所有皇后初始都放在第一行的第一列
        for (int i = 0; i < n; i++) {
            queens[i] = -1;
        }
        backtrack(result, queens, 0, n);
        return result;
    }

    /**
     * 回溯法解决N皇后问题
     *
     * @param result 结果列表
     * @param queens 皇后所在的列索引数组
     * @param row    当前行数
     * @param n      皇后数量
     */
    private void backtrack(List<List<String>> result, int[] queens, int row, int n) {
        // 如果已经放置了n个皇后，将结果加入结果列表
        if (row == n) {
            result.add(generateBoard(queens, n));
            return;
        }

        // 尝试在当前行的每一列放置皇后
        for (int col = 0; col < n; col++) {
            if (isValid(queens, row, col)) {
                queens[row] = col;
                backtrack(result, queens, row + 1, n);
                queens[row] = -1;
            }
        }
    }

    /**
     * 判断在(row, col)位置放置皇后是否合法
     *
     * @param queens 皇后所在的列索引数组
     * @param row    当前行数
     * @param col    当前列数
     * @return 是否合法
     */
    private boolean isValid(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            // 不能在同一列上
            if (queens[i] == col) {
                return false;
            }
            // 不能在对角线上
            if (Math.abs(row - i) == Math.abs(col - queens[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 生成棋盘
     *
     * @param queens 皇后所在的列索引数组
     * @param n      皇后数量
     * @return 棋盘的字符串列表
     */
    private List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            for (int j = 0; j < n; j++) {
                row[j] = '.';
            }
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        int n = 4;
        List<List<String>> result = nQueens.solveNQueens(n);
        for (List<String> board : result) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}


