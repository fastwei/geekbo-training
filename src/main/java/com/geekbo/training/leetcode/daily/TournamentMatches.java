package com.geekbo.training.leetcode.daily;

/**
 *
 * 1688. Count of Matches in Tournament
 * Easy
 * You are given an integer n, the number of teams in a tournament that has strange rules:
 *
 * If the current number of teams is even, each team gets paired with another team. A total of n / 2 matches are played, and n / 2 teams advance to the next round.
 * If the current number of teams is odd, one team randomly advances in the tournament, and the rest gets paired. A total of (n - 1) / 2 matches are played, and (n - 1) / 2 + 1 teams advance to the next round.
 * Return the number of matches played in the tournament until a winner is decided.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 7
 * Output: 6
 * Explanation: Details of the tournament:
 * - 1st Round: Teams = 7, Matches = 3, and 4 teams advance.
 * - 2nd Round: Teams = 4, Matches = 2, and 2 teams advance.
 * - 3rd Round: Teams = 2, Matches = 1, and 1 team is declared the winner.
 * Total number of matches = 3 + 2 + 1 = 6.
 * Example 2:
 *
 * Input: n = 14
 * Output: 13
 * Explanation: Details of the tournament:
 * - 1st Round: Teams = 14, Matches = 7, and 7 teams advance.
 * - 2nd Round: Teams = 7, Matches = 3, and 4 teams advance.
 * - 3rd Round: Teams = 4, Matches = 2, and 2 teams advance.
 * - 4th Round: Teams = 2, Matches = 1, and 1 team is declared the winner.
 * Total number of matches = 7 + 3 + 2 + 1 = 13.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 *
 */
public class TournamentMatches {
    //

    /**
     * 计算比赛次数的方法
     *
     * 解题思路
     * 每一轮比赛会淘汰一半的队伍，最终只剩下一个获胜队伍。
     * 因此，总比赛数就是总队伍数减1，因为每场比赛都会淘汰一个队伍。
     * 算法复杂度
     * 时间复杂度：O(1)，因为解决方案只进行了一次简单的算术运算。
     * 空间复杂度：O(1)，因为没有使用额外的空间。
     *
     * @param n
     * @return
     */
    public static int numberOfMatches(int n) {
        // 因为每场比赛淘汰一个队伍，所以总比赛数就是队伍数减1
        return n - 1;
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        // 测试用例1
        int n1 = 7;
        System.out.println("Teams: " + n1 + ", Matches: " + numberOfMatches(n1)); // 预期输出6

        // 测试用例2
        int n2 = 14;
        System.out.println("Teams: " + n2 + ", Matches: " + numberOfMatches(n2)); // 预期输出13
    }
}
