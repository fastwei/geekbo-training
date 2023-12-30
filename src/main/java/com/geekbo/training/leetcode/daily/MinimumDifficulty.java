package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 *
 * 1335. Minimum Difficulty of a Job Schedule
 * Solved
 * Hard
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 *
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 * Example 2:
 *
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 * Example 3:
 *
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 *
 * Constraints:
 *
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 *
 */
public class MinimumDifficulty {
    /**
     * 计算工作调度的最小困难度
     *
     * @param jobDifficulty 工作的困难度数组
     * @param d             需要完成工作的天数
     * @return 最小的工作调度困难度
     */
    public static int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            // 工作数量小于需要完成的天数，无法完成调度
            return -1;
        }

        // dp[i][j] 表示前 i 个工作在 j 天内完成的最小困难度
        int[][] dp = new int[n + 1][d + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            // j 从 1 开始，因为至少需要完成一个工作
            for (int j = 1; j <= d; j++) {
                int maxDifficulty = 0;
                // 从后往前遍历，计算前 i 个工作在 j 天内完成的最小困难度
                for (int k = i - 1; k >= j - 1; k--) {
                    maxDifficulty = Math.max(maxDifficulty, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + maxDifficulty);
                }
            }
        }

        return dp[n][d];
    }

    public static void main(String[] args) {
        int[] jobDifficulty1 = {6, 5, 4, 3, 2, 1};
        int d1 = 2;
        // 预期输出：7
        System.out.println(minDifficulty(jobDifficulty1, d1));

        int[] jobDifficulty2 = {9, 9, 9};
        int d2 = 4;
        // 预期输出：-1
        System.out.println(minDifficulty(jobDifficulty2, d2));

        int[] jobDifficulty3 = {1, 1, 1};
        int d3 = 3;
        // 预期输出：3
        System.out.println(minDifficulty(jobDifficulty3, d3));
    }
}
