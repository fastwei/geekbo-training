package com.geekbo.training.leetcode.skill;

/**
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid. The rules of Tic-Tac-Toe are:
 * <p>
 * Players take turns placing characters into empty squares ' '.
 * The first player A always places 'X' characters,
 * while the second player B always places 'O' characters.
 * 'X' and 'O' characters are always placed into empty squares, never on filled ones.
 * The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * Given a 2D integer array moves where moves[i] = [rowi, coli] indicates that the ith move will be played on grid[rowi][coli].
 * return the winner of the game if it exists (A or B). In case the game ends in a draw return "Draw".
 * If there are still movements to play return "Pending".
 * <p>
 * You can assume that moves is valid (i.e., it follows the rules of Tic-Tac-Toe), the grid is initially empty, and A will play first.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 * Output: "A"
 * Explanation: A wins, they always play first.
 * Example 2:
 * <p>
 * <p>
 * Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
 * Output: "B"
 * Explanation: B wins.
 * Example 3:
 * <p>
 * <p>
 * Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
 * Output: "Draw"
 * Explanation: The game ends in a draw since there are no moves to make.
 */
public class TicTacToe {
    /**
     * 解题思路： 首先，我们创建一个3x3的棋盘数组来表示棋盘状态。初始化棋盘，所有位置都为空格。
     * <p>
     * 然后，我们遍历每一步下棋的位置。根据当前玩家在对应位置上放置棋子，并检查是否有玩家获胜。
     * 如果有玩家获胜，返回该玩家的标识（A或B）。
     * <p>
     * 如果没有玩家获胜，切换玩家，并继续下一步。
     * <p>
     * 最后，检查是否所有位置都已下满棋子。如果是，则返回"Draw"表示平局。否则，返回"Pending"表示游戏未结束。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历每一步下棋的位置需要O(n)的时间复杂度，其中n是moves数组的长度。检查获胜情况的时间复杂度为O(1)。
     * 空间复杂度：创建一个3x3的棋盘数组，需要O(1)的空间复杂度。
     *
     * @param moves
     * @return
     */
    public String tictactoe(int[][] moves) {
        // 创建一个3x3的棋盘数组，用于表示棋盘状态
        char[][] board = new char[3][3];
        // 初始化棋盘，所有位置都为空格
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        // 标记当前下棋的玩家，初始为玩家A
        char currentPlayer = 'A';

        // 遍历每一步下棋的位置
        for (int[] move : moves) {
            int row = move[0];
            int col = move[1];

            // 根据当前玩家在对应位置上放置棋子
            board[row][col] = currentPlayer;

            // 检查是否有玩家获胜
            if (checkWin(board, currentPlayer)) {
                return String.valueOf(currentPlayer);
            }

            // 切换玩家
            currentPlayer = (currentPlayer == 'A') ? 'B' : 'A';
        }

        // 检查是否所有位置都已下满棋子
        if (moves.length == 9) {
            return "Draw";
        }

        // 还有未下棋子，游戏未结束
        return "Pending";
    }

    // 检查当前玩家是否获胜
    private boolean checkWin(char[][] board, char player) {
        // 检查行
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // 检查列
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        // 检查对角线
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        // Test Case 1
        int[][] moves = {{0, 0}, {2, 0}, {1, 1}, {2, 1}, {2, 2}};
        // 预期输出为"A"
        System.out.println(game.tictactoe(moves));

        // Test Case 2
        moves = new int[][]{{0, 0}, {1, 1}, {0, 1}, {0, 2}, {1, 0}, {2, 0}};
        // 预期输出为"B"
        System.out.println(game.tictactoe(moves));

        // Test Case 3
        moves = new int[][]{{0, 0}, {1, 1}, {2, 0}, {1, 0}, {1, 2}, {2, 1}, {0, 1}, {0, 2}, {2, 2}};
        // 预期输出为"Draw"
        System.out.println(game
                .tictactoe(moves));
    }
}

