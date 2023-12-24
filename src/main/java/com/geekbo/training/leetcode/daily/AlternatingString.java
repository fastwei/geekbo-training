package com.geekbo.training.leetcode.daily;

/**
 *
 * 1758. Minimum Changes To Make Alternating Binary String
 * Solved
 * Easy
 * You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0' to '1' or vice versa.
 *
 * The string is called alternating if no two adjacent characters are equal. For example, the string "010" is alternating, while the string "0100" is not.
 *
 * Return the minimum number of operations needed to make s alternating.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "0100"
 * Output: 1
 * Explanation: If you change the last character to '1', s will be "0101", which is alternating.
 * Example 2:
 *
 * Input: s = "10"
 * Output: 0
 * Explanation: s is already alternating.
 * Example 3:
 *
 * Input: s = "1111"
 * Output: 2
 * Explanation: You need two operations to reach "0101" or "1010".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s[i] is either '0' or '1'.
 *
 * 给定一个由字符'0'和'1'组成的字符串s，每次操作可以将任意'0'变为'1'或将任意'1'变为'0'。
 * 如果字符串中相邻的字符都不相等，则称该字符串为交替字符串。例如，字符串"010"是交替字符串，而字符串"0100"不是。
 * 返回使字符串s成为交替字符串所需的最小操作次数。
 *
 * 示例 1：
 * 输入：s = "0100"
 * 输出：1
 * 解释：将最后一个字符变为'1'，则s变为"0101"，是交替字符串。
 *
 * 示例 2：
 * 输入：s = "10"
 * 输出：0
 * 解释：s已经是交替字符串。
 *
 * 示例 3：
 * 输入：s = "1111"
 * 输出：2
 * 解释：需要两次操作才能使s变为"0101"或"1010"。
 *
 * 提示：
 * 1 <= s.length <= 104
 * s[i]为'0'或'1'
 */

public class AlternatingString {
    /**
     * 计算使字符串s成为交替字符串所需的最小操作次数。
     * 遍历字符串，统计相邻字符相等的次数，即为需要改变的次数。
     * 时间复杂度：O(n)，其中n为字符串的长度。
     * 空间复杂度：O(1)。
     */
    public static int minOperations(String s) {
        int n = s.length();
        int operationsCount = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) - '0' != i % 2) {
                operationsCount++;
            }
        }

        return Math.min(operationsCount, n - operationsCount);
    }

    public static void main(String[] args) {
        // Test case 1
        String s1 = "0100";
        // 改变最后一个字符为'1'，则s1变为"0101"，是交替字符串
        int expected1 = 1;
        int result1 = minOperations(s1);
        System.out.println(result1 == expected1 ? "Test case 1 passed" : "Test case 1 failed");

        // Test case 2
        String s2 = "10";
        // s2已经是交替字符串
        int expected2 = 0;
        int result2 = minOperations(s2);
        System.out.println(result2 == expected2 ? "Test case 2 passed" : "Test case 2 failed");

        // Test case 3
        String s3 = "1111";
        // 需要两次操作才能使s3变为"0101"或"1010"
        int expected3 = 2;
        int result3 = minOperations(s3);
        System.out.println(result3 == expected3 ? "Test case 3 passed" : "Test case 3 failed");
    }
}