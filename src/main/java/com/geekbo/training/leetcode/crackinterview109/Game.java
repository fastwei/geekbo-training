package com.geekbo.training.leetcode.crackinterview109;

/**
 *
 * 面试题 16.04. 井字游戏
 * 已解答
 * 中等
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 *
 * 示例 1：
 *
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 * 示例 2：
 *
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 * 示例 3：
 *
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 * 提示：
 *
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 *
 */
public class Game {
    /**
     * 井字游戏判断获胜者的方法
     *
     * @param board 井字游戏的棋盘
     * @return 获胜者的字符（"X"或"O"），平局返回"Draw"，游戏未结束返回"Pending"
     */
    public static String tictactoe(String[] board) {
        int n = board.length;
        // 检查行
        for (int i = 0; i < n; i++) {
            if (board[i].charAt(0) != ' ' && allSame(board[i])) {
                return String.valueOf(board[i].charAt(0));
            }
        }
        // 检查列
        for (int j = 0; j < n; j++) {
            StringBuilder column = new StringBuilder();
            for (int i = 0; i < n; i++) {
                column.append(board[i].charAt(j));
            }
            if (column.charAt(0) != ' ' && allSame(column.toString())) {
                return String.valueOf(column.charAt(0));
            }
        }
        // 检查对角线
        StringBuilder diagonal1 = new StringBuilder();
        StringBuilder diagonal2 = new StringBuilder();
        for (int i = 0; i < n; i++) {
            diagonal1.append(board[i].charAt(i));
            diagonal2.append(board[i].charAt(n - i - 1));
        }
        if (diagonal1.charAt(0) != ' ' && allSame(diagonal1.toString())) {
            return String.valueOf(diagonal1.charAt(0));
        }
        if (diagonal2.charAt(0) != ' ' && allSame(diagonal2.toString())) {
            return String.valueOf(diagonal2.charAt(0));
        }
        // 检查是否有空位
        for (String row : board) {
            if (row.indexOf(' ') != -1) {
                return "Pending";
            }
        }
        // 平局
        return "Draw";
    }

    /**
     * 判断字符串中的字符是否全部相同（非空字符）
     *
     * @param s 输入字符串
     * @return 是否全部相同
     */
    private static boolean allSame(String s) {
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] board1 = {"O X", " XO", "X O"};
        System.out.println(tictactoe(board1));  // Output: "X"

        String[] board2 = {"OOX", "XXO", "OXO"};
        System.out.println(tictactoe(board2));  // Output: "Draw"

        String[] board3 = {"OOX", "XXO", "OX "};
        System.out.println(tictactoe(board3));  // Output: "Pending"
    }
}
