package com.geekbo.training.leetcode.dp;

/**
 * 1025. Divisor Game
 * Easy
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * Initially, there is a number n on the chalkboard. On each player's turn, that player makes a move consisting of:
 * <p>
 * Choosing any x with 0 < x < n and n % x == 0.
 * Replacing the number n on the chalkboard with n - x.
 * Also, if a player cannot make a move, they lose the game.
 * <p>
 * Return true if and only if Alice wins the game, assuming both players play optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 1000
 */
public class DivisorGame {
    /**
     * 解题思路：
     * <p>
     * 问题要求判断Alice是否能够赢得游戏，假设两个玩家都采取最优策略。
     * 根据题目描述，每一轮玩家可以选择一个整数x，满足0 < x < n且n能够整除x，然后将n减去x。
     * 如果一个玩家无法选择任何满足条件的x，那么他将输掉游戏。
     * 我们可以使用动态规划来解决这个问题，定义一个dp数组，dp[i]表示当前数字为i时，Alice是否能够赢得游戏。
     * 我们从小到大计算dp数组的值，对于每个数字i，我们尝试选择一个满足条件的x，如果在选择x后的状态dp[i-x]为false，则表示Alice能够赢得游戏，将dp[i]设置为true。
     * 最终，返回dp[n]的值，即表示Alice是否能够赢得游戏。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：外层循环遍历了n个数字，内层循环遍历了每个数字的一半，所以总的时间复杂度为O(n^2)。
     * 空间复杂度：使用了一个大小为n+1的dp数组，所以空间复杂度为O(n)。
     * 总结：
     * <p>
     * 使用动态规划的思路，通过计算dp数组来判断Alice是否能够赢得游戏。
     * 时间复杂度为O(n^2)，空间复杂度为O(n)。
     *
     * @param n
     * @return
     */
    public boolean divisorGame(int n) {
        boolean[] dp = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        DivisorGame solution = new DivisorGame();

        // Test Case 1
        int n1 = 2;
        boolean result1 = solution.divisorGame(n1);
        System.out.println("Test Case 1:");
        System.out.println(result1); // Output: true

        // Test Case 2
        int n2 = 3;
        boolean result2 = solution.divisorGame(n2);
        System.out.println("Test Case 2:");
        System.out.println(result2); // Output: false
    }
}