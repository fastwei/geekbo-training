package com.geekbo.training.leetcode.top150.hard;

import java.util.ArrayList;
import java.util.List;

/**
 *The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 *
 *
 * 主要思路是使用回溯算法，在每一行逐个尝试放置皇后，并检查是否符合规则。
 * 如果找到一个解法，将其添加到解法列表中。最后，打印出解法的数量。
 *
 * 初始化一个N x N的棋盘，用字符数组表示，初始值都为'.'表示空格。
 *
 * 使用回溯算法，逐行放置皇后。从第一行开始，逐列尝试放置皇后。
 *
 * 对于每一次尝试，在当前行和列位置放置皇后前，先检查是否符合规则，主要检查是否同一列、同一对角线上是否已经有皇后。
 *
 * 如果当前位置可以放置皇后，标记为'Q'，然后递归进入下一行进行放置。
 *
 * 如果成功放置N个皇后，表示找到一个解法，将该解法存储在结果列表中。
 *
 * 在递归过程中，如果无法放置皇后或者放置完成后发现无法得到解法，就会回溯，撤销之前的放置，继续尝试下一个位置。
 *
 * 最后，返回解法的数量，即N皇后问题的不同解法数量。
 *
 * 算法复杂度分析：
 *
 * 时间复杂度：在最坏情况下，需要遍历整个棋盘的每个格子，尝试放置皇后，因此时间复杂度为O(N!)，其中N是棋盘的大小。
 * 空间复杂度：空间复杂度主要由递归调用栈和存储解法的列表占用。在递归调用栈方面，最多会有N层递归（每行一个皇后），因此空间复杂度为O(N)。存储解法的列表也需要空间，因此总体空间复杂度为O(N)。
 * 这个算法是经典的N皇后问题解法，虽然时间复杂度较高，但在实际应用中N通常较小，因此可以接受。
 *
 */
public class NQueensII {

    /**
     * 求解N皇后问题的方法
     *
     * @param n 皇后数量
     * @return 不同解法的数量
     */
    public int totalNQueens(int n) {
        // 初始化棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> solutions = new ArrayList<>();
        solveNQueensHelper(board, 0, solutions);

        return solutions.size();
    }

    /**
     * 递归求解N皇后问题的辅助方法
     *
     * @param board      棋盘
     * @param row        当前处理的行
     * @param solutions  存放解法的列表
     */
    private void solveNQueensHelper(char[][] board, int row, List<List<String>> solutions) {
        // 如果已经处理完所有行，表示找到一个解法
        if (row == board.length) {
            solutions.add(generateSolution(board));
            return;
        }

        int n = board.length;
        for (int col = 0; col < n; col++) {
            // 检查当前位置是否可以放置皇后
            if (isValid(board, row, col)) {
                board[row][col] = 'Q'; // 放置皇后
                solveNQueensHelper(board, row + 1, solutions); // 递归处理下一行
                board[row][col] = '.'; // 撤销放置，回溯
            }
        }
    }

    /**
     * 检查是否可以放置皇后
     *
     * @param board 棋盘
     * @param row   行
     * @param col   列
     * @return 是否可以放置皇后
     */
    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;

        // 检查列是否有皇后
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 检查左上方是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // 检查右上方是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    /**
     * 生成解法的字符串表示
     *
     * @param board 棋盘
     * @return 解法的字符串列表
     */
    private List<String> generateSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }

    public static void main(String[] args) {
        NQueensII nQueensII = new NQueensII();
        // 示例1
        int n1 = 4;
        int count1 = nQueensII.totalNQueens(n1);
        System.out.println("n = " + n1 + ", 解法数量: " + count1);

        // 示例2
        int n2 = 1;
        int count2 = nQueensII.totalNQueens(n2);
        System.out.println("n = " + n2 + ", 解法数量: " + count2);
    }
}
