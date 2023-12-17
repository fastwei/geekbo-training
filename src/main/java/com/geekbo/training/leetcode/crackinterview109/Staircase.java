package com.geekbo.training.leetcode.crackinterview109;

public class Staircase {
    public static int waysToStep(int n) {
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
            if (i >= 3) {
                dp[i] = (dp[i] + dp[i-3]) % 1000000007;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 5;
        int ways = waysToStep(n);
        System.out.println("Ways to climb " + n + " stairs: " + ways);
    }
}