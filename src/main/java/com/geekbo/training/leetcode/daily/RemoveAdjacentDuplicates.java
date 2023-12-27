package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.Stack;

/**
 * 1578. 使⽤最⼩时间删除字符串中所有的相邻重复项 III
 * 中等
 * <p>
 * Description:
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 * <p>
 * We repeatedly make k duplicate removals on s until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * Example 2:
 * <p>
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * Example 3:
 * <p>
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * 2 <= k <= 104
 * s only contains lower case English letters.
 */
public class RemoveAdjacentDuplicates {

    public static int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return calculateMinCost(n - 1, colors, neededTime, dp, 'A', neededTime[n - 1]);
    }

    private static int calculateMinCost(int i, String colorSequence, int[] timeRequired, int[] memo, char previousColor, int previousTime) {
        if (i < 0) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        if (colorSequence.charAt(i) == previousColor) {
            return memo[i] = calculateMinCost(i - 1, colorSequence, timeRequired, memo, colorSequence.charAt(i), Math.max(timeRequired[i], previousTime)) + Math.min(timeRequired[i], previousTime);
        } else {
            return memo[i] = calculateMinCost(i - 1, colorSequence, timeRequired, memo, colorSequence.charAt(i), timeRequired[i]);
        }
    }
}