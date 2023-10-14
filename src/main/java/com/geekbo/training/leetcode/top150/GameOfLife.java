package com.geekbo.training.leetcode.top150;

public class GameOfLife {
    /**
     * 解题思路：
     * <p>
     * 首先遍历每个细胞，使用countLiveNeighbors方法统计每个细胞周围的活细胞数量。
     * 根据规则更新细胞状态，将需要死亡的细胞的值设为-1，将需要复活的细胞的值设为2。
     * 最后再次遍历细胞，根据更新后的状态，将细胞的值转换为0或1。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历每个细胞的时间复杂度为O(m*n)，其中m为board的行数，n为board的列数。countLiveNeighbors方法的时间复杂度为O(1)。
     * 空间复杂度：除了原始的board数组外，算法的空间复杂度为O(1)。没有使用额外的空间。
     *
     * @param board
     */
    public static void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        // 遍历每个细胞
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveNeighbors = countLiveNeighbors(board, i, j);

                // 根据规则更新细胞状态
                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    // 任何活细胞，如果周围活细胞少于2个或多于3个，则死亡
                    board[i][j] = -1;
                } else if (board[i][j] == 0 && liveNeighbors == 3) {
                    // 任何死细胞，如果周围有恰好3个活细胞，则复活
                    board[i][j] = 2;
                }
            }
        }

        // 根据更新后的状态，转换细胞的值
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }

    // 计算细胞周围的活细胞数量
    private static int countLiveNeighbors(int[][] board, int row, int col) {
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int count = 0;

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                if (board[newRow][newCol] == 1 || board[newRow][newCol] == -1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] board1 = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        gameOfLife(board1);
        printBoard(board1);

        // 测试用例2
        int[][] board2 = {{1, 1}, {1, 0}};
        gameOfLife(board2);
        printBoard(board2);
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}