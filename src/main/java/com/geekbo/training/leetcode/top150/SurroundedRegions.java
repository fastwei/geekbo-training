package com.geekbo.training.leetcode.top150;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Surrounded Regions
 * Medium
 * Topics
 * Companies
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Notice that an 'O' should not be flipped if:
 * - It is on the border, or
 * - It is adjacent to an 'O' that should not be flipped.
 * The bottom 'O' is on the border, so it is not flipped.
 * The other three 'O' form a surrounded region, so they are flipped.
 * Example 2:
 * <p>
 * Input: board = [["X"]]
 * Output: [["X"]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 */
public class SurroundedRegions {

    /**
     * 解题思路： 对于被'X'包围的区域，即四边上的'O'及其相邻的'O'，不需要被翻转。我们可以通过以下步骤来解决这个问题：
     * <p>
     * 遍历矩阵的四条边，对于边上的每个'O'，将其及其相邻的'O'标记为'*'，表示这些'O'不需要被翻转。
     * 遍历整个矩阵，将所有的'O'翻转为'X'，将所有的'*'恢复为'O'。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设矩阵的大小为 m × n。
     * 第一步遍历矩阵的四条边的时间复杂度为 O(m + n)，因为需要遍历整条边。
     * 第二步遍历整个矩阵的时间复杂度为 O(m × n)，因为需要遍历每个单元格。 综上，算法的总时间复杂度为 O(m × n)。
     * 空间复杂度：算法使用了常数级别的额外空间，所以空间复杂度为 O(1)。
     *
     * @param board
     */
    public static void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        // Traverse the border of the board and mark all 'O' cells and their connected 'O' cells as '*'
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                markConnectedCells(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                markConnectedCells(board, i, n - 1);
            }
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                markConnectedCells(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                markConnectedCells(board, m - 1, j);
            }
        }

        // Flip 'O' cells to 'X' and '*' cells back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void markConnectedCells(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') {
            return;
        }

        board[row][col] = '*';

        markConnectedCells(board, row - 1, col);
        markConnectedCells(board, row + 1, col);
        markConnectedCells(board, row, col - 1);
        markConnectedCells(board, row, col + 1);
    }

    /**
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设矩阵的大小为 m × n。
     * 第一步遍历矩阵的四条边的时间复杂度为 O(m + n)，因为需要遍历整条边。
     * 第二步进行 BFS 遍历的时间复杂度为 O(m × n)，因为最坏情况下需要遍历整个矩阵中的所有单元格。
     * 第三步遍历整个矩阵的时间复杂度为 O(m × n)，因为需要遍历每个单元格。 综上，算法的总时间复杂度为 O(m × n)。
     * 空间复杂度：算法使用了队列来存储访问过的单元格，最坏情况下队列的大小可以达到 m × n，所以空间复杂度为 O(m × n)。
     *
     * @param board
     */
    public static void solveBfs(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        // Define the direction vectors for BFS traversal
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Start BFS traversal from the border cells
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
            }
            if (board[i][n - 1] == 'O') {
                queue.offer(new int[]{i, n - 1});
            }
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                queue.offer(new int[]{0, j});
            }
            if (board[m - 1][j] == 'O') {
                queue.offer(new int[]{m - 1, j});
            }
        }

        // Perform BFS traversal
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];

            // Mark the current cell as '*'
            board[row][col] = '*';

            // Explore the neighboring cells
            for (int k = 0; k < 4; k++) {
                int newRow = row + dx[k];
                int newCol = col + dy[k];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && board[newRow][newCol] == 'O') {
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        // Flip 'O' cells to 'X' and '*' cells back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board1 = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        solve(board1);
        System.out.println(Arrays.deepToString(board1));
        // Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

        char[][] board2 = {
                {'X'}
        };
        solve(board2);
        System.out.println(Arrays.deepToString(board2));
        // Output: [["X"]]
    }
}


