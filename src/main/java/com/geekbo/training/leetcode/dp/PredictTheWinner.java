package com.geekbo.training.leetcode.dp;

/**
 * 486. Predict the Winner
 * Medium
 * <p>
 * You are given an integer array nums.
 * Two players are playing a game with this array: player 1 and player 2.
 * <p>
 * Player 1 and player 2 take turns, with player 1 starting first.
 * Both players start the game with a score of 0.
 * At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or nums[nums.length - 1]) which reduces the size of the array by 1.
 * The player adds the chosen number to their score. The game ends when there are no more elements in the array.
 * <p>
 * Return true if Player 1 can win the game.
 * If the scores of both players are equal, then player 1 is still the winner, and you should also return true.
 * You may assume that both players are playing optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,5,2]
 * Output: false
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return false.
 * Example 2:
 * <p>
 * Input: nums = [1,5,233,7]
 * Output: true
 * Explanation: Player 1 first chooses 1.
 * Then player 2 has to choose between 5 and 7.
 * No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12),
 * so you need to return True representing player1 can win.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 107
 */
public class PredictTheWinner {
    /**
     * 预测赢家
     * <p>
     * 解题思路：
     * <p>
     * 使用动态规划来解决问题。创建一个二维数组dp，其中dp[i][j]表示玩家1和玩家2在[i,j]范围内的得分差。
     * 初始时，对角线上的元素为数组nums中的数，表示只有一个数时，玩家1的得分差就是这个数。
     * 然后，从对角线开始，逐步计算得分差。对于每个区间[i,j]，玩家1在这个区间内的得分差等于max(选择左端点得分差，选择右端点得分差)。
     * 最后，判断玩家1的得分差是否大于等于0，如果是，则表示玩家1可以赢。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n是数组的长度。需要计算n个区间的得分差。
     *
     * @param nums 整数数组
     * @return true表示玩家1可以赢，false表示玩家2可以赢
     */
    public boolean predictTheWinner(int[] nums) {
        // 创建一个二维数组，用于记录玩家1和玩家2在[i,j]范围内的得分差
        int[][] dp = new int[nums.length][nums.length];

        // 初始化对角线，表示只有一个数时，玩家1的得分差就是这个数
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
        }

        // 从对角线开始，逐步计算得分差
        for (int len = 1; len < nums.length; len++) {
            for (int i = 0; i < nums.length - len; i++) {
                int j = i + len;
                // 玩家1在[i,j]范围内的得分差 = max(选择左端点得分差，选择右端点得分差)
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        // 玩家1的得分差大于等于0时，表示玩家1可以赢
        return dp[0][nums.length - 1] >= 0;
    }

    public static void main(String[] args) {
        PredictTheWinner solution = new PredictTheWinner();

        int[] nums1 = {1, 5, 2};
        boolean expected1 = false;
        boolean result1 = solution.predictTheWinner(nums1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        int[] nums2 = {1, 5, 233, 7};
        boolean expected2 = true;
        boolean result2 = solution.predictTheWinner(nums2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);
    }
}
