package com.geekbo.training.leetcode.dp;

/**
 * 935. Knight Dialer
 * Medium
 * <p>
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L).
 * The possible movements of chess knight are shown in this diagaram:
 * <p>
 * A chess knight can move as indicated in the chess diagram below:
 * <p>
 * <p>
 * We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
 * <p>
 * <p>
 * Given an integer n, return how many distinct phone numbers of length n we can dial.
 * <p>
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.
 * <p>
 * As the answer may be very large, return the answer modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: 10
 * Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: 20
 * Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
 * Example 3:
 * <p>
 * Input: n = 3131
 * Output: 136006598
 * Explanation: Please take care of the mod.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 5000
 */
public class KnightDialer {
    int mod = 1000000007;
    int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
    int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    int[][][] dp = new int[5][4][5002];

    /**
     * 解题思路:
     * <p>
     * 该问题可以使用动态规划来解决。
     * 首先创建一个4x3的数组nums表示电话键盘，其中-1表示无法拨打的数字，0表示数字0。
     * 创建一个3维数组dp，dp[i][j][k]表示在位置(i, j)上拨打长度为k的电话号码的数量。
     * 初始化dp数组为-1。
     * 使用递归函数solve来计算从位置(i, j)开始拨打长度为n的电话号码的数量，利用动态规划避免重复计算。
     * 最后，在knightDialer函数中，遍历电话键盘的每个位置，如果该位置不是无法拨打的数字，则累加从该位置开始拨打长度为n-1的电话号码的数量，并将结果取模。
     * 返回最终的累加结果。
     * <p>
     * 代码分析：
     * <p>
     * 时间复杂度：该算法使用动态规划，需要遍历每个位置和长度，所以时间复杂度为O(4*3*n)，其中n是电话号码的长度。
     * 空间复杂度：使用了一个3维数组dp来存储中间结果，所以空间复杂度为O(4*3*n)。
     *
     * @param n
     * @return
     */
    public int knightDialer(int n) {
        int[][] nums = new int[4][3];
        nums[3][1] = 0;
        nums[3][0] = -1;
        nums[3][2] = -1;
        int x = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                nums[i][j] = x;
                x++;
            }
        }

        // 初始化dp数组
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 5002; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (nums[i][j] != -1) {
                    ans = (ans + solve(nums, i, j, n - 1)) % mod;
                }
            }
        }

        return ans;
    }

    public int solve(int[][] nums, int i, int j, int n) {
        if (i < 0 || j < 0 || i >= 4 || j >= 3 || nums[i][j] == -1) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (dp[i][j][n] != -1) {
            return dp[i][j][n];
        }

        int t = 0;
        for (int k = 0; k < 8; k++) {
            int x = i + dx[k];
            int y = j + dy[k];

            t = (t + solve(nums, x, y, n - 1)) % mod;
        }

        return dp[i][j][n] = (t % mod);
    }

    public static void main(String[] args) {
        KnightDialer solution=new KnightDialer();
        // 测试用例1
        int n1 = 1;
        int expected1 = 10;
        int result1 = solution.knightDialer(n1);
        System.out.println(result1 == expected1); // 预期输出为true

        // 测试用例2
        int n2 = 2;
        int expected2 = 20;
        int result2 = solution.knightDialer(n2);
        System.out.println(result2 == expected2); // 预期输出为true

        // 测试用例3
        int n3 = 3131;
        int expected3 = 136006598;
        int result3 = solution.knightDialer(n3);
        System.out.println(result3 == expected3); // 预期输出为true
    }
}