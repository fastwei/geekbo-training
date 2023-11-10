package com.geekbo.training.leetcode.dp;

import java.util.*;

/**
 *
 * 1397. Find All Good Strings
 * Hard
 * Given the strings s1 and s2 of size n and the string evil, return the number of good strings.
 *
 * A good string has size n, it is alphabetically greater than or equal to s1,
 * it is alphabetically smaller than or equal to s2,
 * and it does not contain the string evil as a substring.
 * Since the answer can be a huge number, return this modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2, s1 = "aa", s2 = "da", evil = "b"
 * Output: 51
 * Explanation: There are 25 good strings starting with 'a': "aa","ac","ad",...,"az".
 * Then there are 25 good strings starting with 'c': "ca","cc","cd",...,"cz"
 * and finally there is one good string starting with 'd': "da".
 * Example 2:
 *
 * Input: n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
 * Output: 0
 * Explanation: All strings greater than or equal to s1 and smaller than or equal to s2 start with the prefix "leet",
 * therefore, there is not any good string.
 * Example 3:
 *
 * Input: n = 2, s1 = "gx", s2 = "gz", evil = "x"
 * Output: 2
 *
 */
public class GoodStrings {
    static Integer[][][][] dp;
    static int mod = 1000000007;
    static int[] lps;
    private static int add(int a, int b) {
        return (a % mod + b % mod) % mod;
    }
    private static int solve(char[] s1, char[] s2, int cur, boolean isStrictLower, boolean isStrictUpper, char[] evil, int evI) {
        if(evI == evil.length) return 0;
        if(cur == s2.length) return 1;
        if(dp[cur][isStrictLower ? 1 : 0][isStrictUpper ? 1 : 0][evI] != null) return dp[cur][isStrictLower ? 1 : 0][isStrictUpper ? 1 : 0][evI];
        char start = isStrictLower ? s1[cur] : 'a';
        char end = isStrictUpper ? s2[cur] : 'z';
        int res = 0;
        for(char ch = start; ch <= end; ch ++) {
            if(evil[evI] == ch)
                res = add(res, solve(s1, s2, cur + 1, isStrictLower && ch == start, isStrictUpper && ch == end, evil, evI + 1));
            else {
                int j = evI;
                while (j > 0 && evil[j] != ch) j = lps[j - 1];
                if (ch == evil[j]) j++;
                res = add(res, solve(s1, s2, cur + 1, isStrictLower && ch == start, isStrictUpper && ch == end, evil, j));
            }
        }
        return dp[cur][isStrictLower ? 1 : 0][isStrictUpper ? 1 : 0][evI] = res;
    }
    public static int findGoodStrings(int n, String s1, String s2, String evil) {
        char[] arr = s1.toCharArray();
        char[] brr = s2.toCharArray();
        char[] crr = evil.toCharArray();
        lps = new int[crr.length];
        for (int i = 1, j = 0; i < crr.length; i++) {
            while (j > 0 && crr[i] != crr[j]) j = lps[j - 1];
            if (crr[i] == crr[j]) lps[i] = ++j;
        }
        dp = new Integer[n][2][2][crr.length];
        return solve(arr, brr, 0, true, true, crr, 0);
    }

    public static void main(String[] args) {
        int n1 = 2;
        String s11 = "aa";
        String s21 = "da";
        String evil1 = "b";
        System.out.println(findGoodStrings(n1, s11, s21, evil1)); // Output: 51

        int n2 = 8;
        String s12 = "leetcode";
        String s22 = "leetgoes";
        String evil2 = "leet";
        System.out.println(findGoodStrings(n2, s12, s22, evil2)); // Output: 0

        int n3 = 2;
        String s13 = "gx";
        String s23 = "gz";
        String evil3 = "x";
        System.out.println(findGoodStrings(n3, s13, s23, evil3)); // Output: 2
    }
}