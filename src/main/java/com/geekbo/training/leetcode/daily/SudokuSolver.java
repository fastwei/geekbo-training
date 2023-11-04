package com.geekbo.training.leetcode.daily;


/**
 * 37. Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * The '.' character indicates empty cells.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * Explanation: The input board is shown above and the only valid solution is shown below:
 * <p>
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit or '.'.
 * It is guaranteed that the input board has only one solution.
 * <p>
 * todo:对于数独问题的求解，可以采用一种更高效的解法，称为Dancing Links算法，也就是精确覆盖问题的求解方法。该算法使用了一种数据结构称为Dancing Links矩阵（DLX），它能够高效地进行回溯和剪枝操作。
 * <p>
 * 使用DLX算法的具体步骤如下：
 * <p>
 * 将数独问题转化为精确覆盖问题，将数独的每个空格视为精确覆盖问题的一个候选项。
 * 构建Dancing Links矩阵，其中每个候选项对应一个节点，每个行、列、子网格对应一个列头节点。
 * 使用DLX算法进行回溯求解，选择一个列头节点，将其从矩阵中移除，并将其覆盖的行节点也从矩阵中移除。
 * 递归地继续选择下一个列头节点进行回溯求解，直到找到有效解或者遍历完所有可能性。
 * 在回溯过程中，使用剪枝操作来提前排除无效的选择，从而减少搜索空间和提高求解效率。
 * DLX算法的时间复杂度为O(k^n)，其中k为每个空格的候选项个数，n为空格的总数。相比传统的回溯算法，DLX算法在处理数独问题时能够更快地找到有效解。
 */
public class SudokuSolver {
    /**
     * 解题思路：
     * 使用回溯算法来解决数独问题。
     * 回溯算法通过逐个尝试每个空格的可能数字，直到找到有效的解或者遍历完所有可能性。
     * 具体实现步骤如下：
     * 1. 遍历数独的每个格子，如果当前格子为空，则尝试填入数字1-9中的一个数字；
     * 2. 在当前格子填入一个数字后，判断是否满足数独的规则，即当前数字在所在行、所在列以及所在的3x3子网格中没有重复；
     * 3. 如果满足数独的规则，则继续递归地填充下一个格子；
     * 4. 如果下一个格子的填充导致无法找到有效解，则回溯到当前格子，尝试填入下一个数字；
     * 5. 重复上述步骤，直到找到有效解或者遍历完所有可能性。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：回溯算法在最坏情况下需要遍历所有的空格，每个空格有9种可能的填充数字，
     * 因此时间复杂度为O(9^(n*n))，其中n为数独的大小（9x9）。
     * 空间复杂度：回溯算法使用了递归调用栈，空间复杂度为O(n*n)，其中n为数独的大小（9x9）。
     *
     * @param board
     */
    public static void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        solve(board);
    }

    private static boolean solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == '.') {
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = '.'; // 回溯到上一个状态
                            }
                        }
                    }
                    return false; // 所有数字都尝试过，无法找到有效解
                }
            }
        }
        return true; // 数独已经填充完毕，找到有效解
    }

    private static boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false; // 检查列是否有重复数字
            }
            if (board[row][i] == num) {
                return false; // 检查行是否有重复数字
            }
            if (board[3 * (row / 3) + i / 3][3 * (col
                    / 3) + i % 3] == num) {
                return false; // 检查3x3子网格是否有重复数字
            }
        }
        return true; // 当前位置的数字是有效的
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println("Input:");
        printBoard(board);

        solveSudoku(board);

        System.out.println("Output:");
        printBoard(board);
    }

    private static void printBoard(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}