package com.geekbo.training.leetcode.daily;

/**
 *
 * 419. Battleships in a Board
 * Medium
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.
 *
 * Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 * Output: 2
 * Example 2:
 *
 * Input: board = [["."]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is either '.' or 'X'.
 *
 *
 * Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
 *
 */
public class BattleshipsInABoard {
    /**
     * 解题思路：
     * 遍历整个board，如果当前位置是'X'且左边和上边都不是'X'，则说明遇到了一个新的战舰
     * 统计遇到的新战舰的数量即可
     *
     * 算法复杂度：
     * 时间复杂度：O(m*n)，其中 m 是矩阵的行数，n 是矩阵的列数，需要遍历整个矩阵
     * 空间复杂度：O(1)
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        int count = 0;
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'X' && (i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X')) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        BattleshipsInABoard solution = new BattleshipsInABoard();

        // 测试用例 1
        char[][] board1 = {
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        };
        // 预期输出：2
        System.out.println(solution.countBattleships(board1));

        // 测试用例 2
        char[][] board2 = {
                {'.'}
        };
        // 预期输出：0
        System.out.println(solution.countBattleships(board2));
    }
}