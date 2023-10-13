package com.geekbo.training.leetcode.top150.hard;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * Example 2:
 *
 * Input: n = 1
 * Output: [["Q"]]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 9
 *
 * 关于优化：
 *
 * 在当前的算法中，回溯法已经能够找到所有合法的解。从时间复杂度的角度来看，回溯法的时间复杂度已经是最优的，因为需要尝试所有可能的解。但是可以通过一些优化来减少不必要的递归和检查。
 *
 * 一种优化方法是使用位运算来检查皇后的放置情况。可以使用三个整数来表示每一列、左对角线和右对角线上是否已经放置了皇后。这样可以通过位运算来快速进行检查，而不需要遍历整个棋盘。
 *
 * 另一种优化方法是使用剪枝操作来减少不必要的递归。例如，在放置皇后时，可以根据之前已经放置的皇后的位置，判断哪些列是不可能放置皇后的，从而减少尝试的次数。
 *
 * 这些优化方法可以在一定程度上提高算法的效率，但是无法改变算法的最坏情况时间复杂度。回溯法的最坏情况时间复杂度仍然是 O(N!)。所以，从算法复杂度的角度来看，目前的算法已经是最优的。
 *
 * 然而，在具体的实现中，这些优化方法可能会提高算法的效率，减少不必要的计算和递归。可以根据具体情况选择是否应用这些优化方法。
 *
 */
public class NQueens {
    /**
     * 解题思路：
     * <p>
     * 题目要求在 N x N 的棋盘上放置 N 个皇后，使得它们互相不攻击。每个解法是一个合法的棋盘配置，其中 'Q' 表示皇后，'.' 表示空位。
     * 使用回溯法来解决此问题。回溯法是一种通过尝试所有可能的解来找到解的方法。
     * 创建一个二维字符数组 board，表示棋盘。初始时，所有位置都是空位。
     * 从第一行开始，依次尝试在每一列放置一个皇后，并递归处理下一行。
     * 在放置皇后之前，需要检查当前位置是否合法，即在同一列、同一对角线上是否已经存在皇后。
     * 如果找到一个解法，将其存储到结果列表中。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(N!)，其中 N 是棋盘的大小。需要尝试每一行的每一列，时间复杂度为 O(N^N)。但是由于每一行只能放置一个皇后，所以实际的时间复杂度较小。
     * 空间复杂度：O(N^2)，需要使用一个二维字符数组来表示棋盘。此外，还需要使用递归调用栈，最多有 N 层递归，所以空间复杂度为 O(N)。
     *
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(board, 0, result);
        return result;
    }

    private static void backtrack(char[][] board, int row, List<List<String>> result) {
        if (row == board.length) {
            result.add(constructSolution(board));
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(board, row + 1, result);
                board[row][col] = '.';
            }
        }
    }

    private static boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private static List<String> constructSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            solution.add(new String(board[i]));
        }
        return solution;
    }

    public static void main(String[] args) {
        // 测试用例
        int n1 = 4;
        List<List<String>> result1 = solveNQueens(n1);
        System.out.println("n = " + n1 + "，解法数量：" + result1.size() + "，解法：" + result1);

        int n2 = 1;
        List<List<String>> result2 = solveNQueens(n2);
        System.out.println("n = " + n2 + "，解法数量：" + result2.size() + "，解法：" + result2);
    }
}