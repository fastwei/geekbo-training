package com.geekbo.training.leetcode.top150.hard;


/**
 * 1269. Number of Ways to Stay in the Same Place After Some Steps
 * <p>
 * You have a pointer at index 0 in an array of size arrLen.
 * At each step, you can move 1 position to the left, 1 position to the right in the array,
 * or stay in the same place (The pointer should not be placed outside the array at any time).
 * <p>
 * Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps.
 * Since the answer may be too large, return it modulo 109 + 7.
 * <p>
 * Example 1:
 * <p>
 * Input: steps = 3, arrLen = 2
 * Output: 4
 * Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
 * Right, Left, Stay
 * Stay, Right, Left
 * Right, Stay, Left
 * Stay, Stay, Stay
 * <p>
 * Example 2:
 * <p>
 * Input: steps = 2, arrLen = 4
 * Output: 2
 * Explanation: There are 2 differents ways to stay at index 0 after 2 steps
 * Right, Left
 * Stay, Stay
 * Example 3:
 * <p>
 * Input: steps = 4, arrLen = 2
 * Output: 8
 */
public class NumberWaysStayInTheSomePlaceAfterStep {

    /**
     * 解题思路：
     * <p>
     * 使用动态规划的思想，定义一个二维数组dp，其中dp[i][j]表示经过i步后，指针仍然在索引j处的方式数量。
     * 初始时，dp[0][0]为1，表示经过0步后，指针仍然在索引0处的方式数量为1。
     * 对于每一步i，可以有三种移动方式：向左移动一步、向右移动一步或保持不动。
     * 根据题目要求，指针不能越界，所以最大的索引位置不能超过arrLen - 1，即maxPos = Math.min(steps / 2, arrLen - 1)。
     * 根据动态规划的状态转移方程，对于dp[i][j]，有三种情况：
     * 如果j = 0，即指针在索引0处，只能向右移动一步，即dp[i][j] = dp[i - 1][j + 1]。
     * 如果j = maxPos，即指针在最大索引位置处，只能向左移动一步，即dp[i][j] = dp[i - 1][j - 1]。
     * 如果0 < j < maxPos，即指针在中间位置，可以向左移动一步、向右移动一步或保持不动，
     * 即dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1] + dp[i - 1][j]。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：由于需要计算dp数组的所有元素，所以时间复杂度为O(steps * maxPos)。其中，steps为步数，maxPos为最大索引位置。
     * 空间复杂度：使用了一个二维数组dp，其大小为(steps+1) * (maxPos+1)，所以空间复杂度为O(steps * maxPos)。
     *
     * @param steps
     * @param arrLen
     * @return
     */
    public int numWays(int steps, int arrLen) {
        int mod = (int) 1e9 + 7;
        int maxPos = Math.min(steps / 2, arrLen - 1);
        int[][] dp = new int[steps + 1][maxPos + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxPos; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
                }
                if (j < maxPos) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % mod;
                }
            }
        }

        return dp[steps][0];
    }

    public static void main(String[] args) {
        NumberWaysStayInTheSomePlaceAfterStep numberWaysStayInTheSomePlaceAfterStep = new NumberWaysStayInTheSomePlaceAfterStep();

        // 调用numWays方法，并验证返回值为4
        System.out.println(numberWaysStayInTheSomePlaceAfterStep.numWays(3, 2));  // 输出: 4

        // 调用numWays方法，并验证返回值为2
        System.out.println(numberWaysStayInTheSomePlaceAfterStep.numWays(2, 4));  // 输出: 2

        // 调用numWays方法，并验证返回值为8
        System.out.println(numberWaysStayInTheSomePlaceAfterStep.numWays(4, 2));  // 输出: 8
    }
}