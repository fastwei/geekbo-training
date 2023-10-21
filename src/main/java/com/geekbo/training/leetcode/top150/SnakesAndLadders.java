package com.geekbo.training.leetcode.top150;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon
 * style starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
 * <p>
 * You start on square 1 of the board. In each move, starting from square curr, do the following:
 * <p>
 * Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
 * This choice simulates the result of a standard 6-sided die roll: i.e.,
 * there are always at most 6 destinations, regardless of the size of the board.
 * If next has a snake or ladder, you must move to the destination of that snake or ladder.
 * Otherwise, you move to next.
 * The game ends when you reach the square n2.
 * A board square on row r and column c has a snake or ladder if board[r][c] != -1.
 * The destination of that snake or ladder is board[r][c]. Squares 1 and n2 do not have a snake or ladder.
 * <p>
 * Note that you only take a snake or ladder at most once per move.
 * If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
 * <p>
 * For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2.
 * You follow the ladder to square 3, but do not follow the subsequent ladder to 4.
 * Return the least number of moves required to reach the square n2. If it is not possible to reach the square, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
 * Output: 4
 * Explanation:
 * In the beginning, you start at square 1 (at row 5, column 0).
 * You decide to move to square 2 and must take the ladder to square 15.
 * You then decide to move to square 17 and must take the snake to square 13.
 * You then decide to move to square 14 and must take the ladder to square 35.
 * You then decide to move to square 36, ending the game.
 * This is the lowest possible number of moves to reach the last square, so return 4.
 * Example 2:
 * <p>
 * Input: board = [[-1,-1],[-1,3]]
 * Output: 1
 *
 * todo:有空再加深理解
 */
public class SnakesAndLadders {
    public static void main(String[] args) {
        // 测试用例1
        int[][] board1 = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}
        };
        System.out.println(snakesAndLadders(board1)); // 预期输出：4

        // 测试用例2
        int[][] board2 = {
                {-1, -1},
                {-1, 3}
        };
        System.out.println(snakesAndLadders(board2)); // 预期输出：1
    }

    /**
     * 计算到达方格 n2 的最少移动次数
     * 解题思路： 该问题可以使用广度优先搜索（BFS）算法来解决。
     * 从方格1开始，使用队列保存每一步可能的方格编号。
     * 遍历队列中的方格编号，对于每一个方格，从1到6遍历骰子的可能结果，并计算下一步的方格编号。
     * 如果下一步的方格是蛇或梯子，将其目的地作为下一步的方格编号。
     * 如果下一步的方格没有被访问过，则将其加入队列，并标记为已访问。
     * 重复上述步骤，直到队列为空或者到达目标方格n2。如果到达目标方格n2，则返回移动的次数，否则返回-1表示无法到达。
     * <p>
     * 算法复杂度分析： 设棋盘大小为n x n，目标方格为n2。
     * 在最坏的情况下，需要遍历所有方格才能到达目标方格。因此，时间复杂度为O(n2)。
     * 空间复杂度主要取决于队列和visited数组的空间占用。队列的空间复杂度为O(n2)，visited数组的空间复杂度为O(n2)。
     * 因此，总的空间复杂度为O(n2)。 综上所述，该算法的时间复杂度为O(n2)，空间复杂度为O(n2)。
     *
     * @param board 游戏棋盘
     * @return 到达方格 n2 的最少移动次数
     */
    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;
        boolean[] visited = new boolean[target + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); // 从方格1开始
        visited[1] = true;
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == target) {
                    return moves;
                }
                for (int j = 1; j <= 6 && curr + j <= target; j++) {
                    int next = curr + j;
                    int[] coordinates = getCoordinates(next, n);
                    int row = coordinates[0];
                    int col = coordinates[1];
                    int dest = board[row][col] == -1 ? next : board[row][col];
                    if (!visited[dest]) {
                        visited[dest] = true;
                        queue.offer(dest);
                    }
                }
            }
            moves++;
        }

        return -1; // 无法到达方格 n2
    }

    /**
     * 根据方格编号和棋盘大小计算方格的行号和列号
     *
     * @param num 方格编号
     * @param n   棋盘大小
     * @return 方格的行号和列号
     */
    private static int[] getCoordinates(int num, int n) {
        int row = (num - 1) / n;
        int col = (num - 1) % n;
        if (row % 2 == 1) {
            col = n - 1 - col;
        }
        row = n - 1 - row;
        return new int[]{row, col};
    }
}
