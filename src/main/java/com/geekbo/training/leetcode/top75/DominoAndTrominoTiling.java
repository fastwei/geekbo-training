package com.geekbo.training.leetcode.top75;


/**
 * 790. Domino and Tromino Tiling
 * <p>
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
 * Given an integer n, return the number of ways to tile an 2 x n board.
 * Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * In a tiling, every square must be covered by a tile.
 * Two tilings are different if and only if there are two 4-directionally adjacent cells on the board
 * such that exactly one of the tilings has both squares occupied by a tile.
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: 5
 * Explanation: The five different ways are show above.
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 1
 */
public class DominoAndTrominoTiling {

    /**
     * 这段代码使用动态规划来解决问题。它创建一个长度为n+1的dp数组，用于保存每个位置的铺砖方案数。
     * 初始时，将dp数组的前两个位置分别设置为1、1，第三个位置设置为2，因为对于长度小于等于2的板子，只有一种铺砖方案。
     * <p>
     * 然后，从第三个位置开始进行动态规划。根据题目要求，将2 x i的板子拆分为两种情况的组合：
     * <p>
     * 使用两个2 x 1的砖块铺满，此时dp[i-2]的方案数为dp[i]的一部分。
     * 使用一个特殊的L形砖块铺满，此时dp[i-1]的方案数为dp[i]的一部分。
     * 综合这两种情况，得到状态转移方程：dp[i] = dp[i-1] + dp[i-2] + 2 * (dp[i-3] + ... + dp[0])。
     * <p>
     * 为了计算dp[i]的值，使用内层循环从i-3开始遍历到0，将2 * dp[j]累加到dp[i]中。
     * <p>
     * 最后，返回dp数组的最后一个位置的值，即为铺砖方案数。
     * <p>
     * 该算法的时间复杂度为O(n^2)，空间复杂度为O(n)。内层循环的时间复杂度为O(n)，外层循环的时间复杂度为O(n)，
     * 因此总体的时间复杂度为O(n^2)。空间复杂度为O(n)，因为创建了长度为n+1的dp数组来保存每个位置的铺砖方案数。
     *
     * @param n
     * @return
     */
    public int numTilings(int n) {
        int MOD = 1000000007;

        // 创建一个长度为n+1的dp数组，用于保存每个位置的铺砖方案数
        long[] dp = new long[Math.max(n + 1, 3)];

        // 初始化dp数组的前两个位置
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        // 从第三个位置开始动态规划
        for (int i = 3; i <= n; i++) {
            // 根据题目要求，可以将2 x i的板子拆分为两种情况的组合：
            // 1. 使用两个2 x 1的砖块铺满，此时dp[i-2]的方案数为dp[i]的一部分
            // 2. 使用一个特殊的L形砖块铺满，此时dp[i-1]的方案数为dp[i]的一部分
            // 综合两种情况，dp[i] = dp[i-1] + dp[i-2] + 2 * (dp[i-3] + ... + dp[0])
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
            for (int j = i - 3; j >= 0; j--) {
                dp[i] = (dp[i] + 2 * dp[j]) % MOD;
            }
        }

        // 返回dp数组的最后一个位置的值，即为铺砖方案数
        return (int) dp[n];
    }

    public static void main(String[] args) {
        DominoAndTrominoTiling solution = new DominoAndTrominoTiling();

        // 测试用例1
        int n1 = 3;
        int result1 = solution.numTilings(n1);
        System.out.println(result1); // 输出: 5

        // 测试用例2
        int n2 = 1;
        int result2 = solution.numTilings(n2);
        System.out.println(result2); // 输出: 1

        // 测试用例3
        int n3 = 40;
        int result3 = solution.numTilings(n3);
        System.out.println(result3); // 输出: 833773577
    }
}