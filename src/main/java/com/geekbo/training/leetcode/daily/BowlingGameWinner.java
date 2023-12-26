package com.geekbo.training.leetcode.daily;

/**
 * 2660. 保龄球游戏的获胜者
 * 简单
 * 给你两个下标从 0 开始的整数数组 player1 和 player2 ，分别表示玩家 1 和玩家 2 击中的瓶数。
 * <p>
 * 保龄球比赛由 n 轮组成，每轮的瓶数恰好为 10 。
 * <p>
 * 假设玩家在第 i 轮中击中 xi 个瓶子。玩家第 i 轮的价值为：
 * <p>
 * 如果玩家在该轮的前两轮的任何一轮中击中了 10 个瓶子，则为 2xi 。
 * 否则，为 xi 。
 * 玩家的得分是其 n 轮价值的总和。
 * <p>
 * 返回
 * <p>
 * 如果玩家 1 的得分高于玩家 2 的得分，则为 1 ；
 * 如果玩家 2 的得分高于玩家 1 的得分，则为 2 ；
 * 如果平局，则为 0 。
 */
public class BowlingGameWinner {
    public static int isWinner(int[] player1, int[] player2) {
        int s1 = score(player1);
        int s2 = score(player2);
        return s1 == s2 ? 0 : s1 > s2 ? 1 : 2;
    }

    public static int score(int[] player) {
        int res = 0;
        for (int i = 0; i < player.length; i++) {
            if ((i > 0 && player[i - 1] == 10) || (i > 1 && player[i - 2] >= 10)) {
                res += 2 * player[i];
            } else {
                res += player[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // Test Cases
        int[] player1 = {4, 10, 7, 9};
        int[] player2 = {6, 5, 2, 3};
        // player1 的得分是 4 + 10 + 2*7 + 2*9 = 46
        // player2 的得分是 6 + 5 + 2 + 3 = 16
        // player1 的得分高于 player2 的得分，所以 play1 在比赛中获胜，答案为 1
        System.out.println(isWinner(player1, player2) == 1);

        int[] player3 = {3, 5, 7, 6};
        int[] player4 = {8, 10, 10, 2};
        // player1 的得分是 3 + 5 + 7 + 6 = 21
        // player2 的得分是 8 + 10 + 2*10 + 2*2 = 42
        // player2 的得分高于 player1 的得分，所以 play2 在比赛中获胜，答案为 2
        System.out.println(isWinner(player3, player4) == 2);

        int[] player5 = {2, 3};
        int[] player6 = {4, 1};
        // player1 的得分是 2 + 3 = 5
        // player2 的得分是 4 + 1 = 5
        // player1 的得分等于 player2 的得分，所以这一场比赛平局，答案为 0
        System.out.println(isWinner(player5, player6) == 0);
    }
}