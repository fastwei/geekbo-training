package com.geekbo.training.leetcode.daily;

/**
 * 2609. Find the Longest Balanced Substring of a Binary String
 * Easy
 * You are given a binary string s consisting only of zeroes and ones.
 * <p>
 * A substring of s is considered balanced if all zeroes are before ones and the number of zeroes
 * is equal to the number of ones inside the substring.
 * Notice that the empty substring is considered a balanced substring.
 * <p>
 * Return the length of the longest balanced substring of s.
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "01000111"
 * Output: 6
 * Explanation: The longest balanced substring is "000111", which has length 6.
 * Example 2:
 * <p>
 * Input: s = "00111"
 * Output: 4
 * Explanation: The longest balanced substring is "0011", which has length 4.
 * Example 3:
 * <p>
 * Input: s = "111"
 * Output: 0
 * Explanation: There is no balanced substring except the empty substring, so the answer is 0.
 */
public class LongestBalancedSubstring {
    public static int findTheLongestBalancedSubstring(String s) {
        int cnt0 = 0, cnt1 = 0;
        int maxLen = 0;
        for (int i = 0; i < s.length();) {
            // Set the window
            // count the number of zeros
            while (i < s.length() && s.charAt(i) == '0') {
                cnt0++; i++;
            }
            // count the number of ones
            while (i < s.length() && s.charAt(i) == '1') {
                cnt1++; i++;
            }
            // Do the pairing
            maxLen = Math.max(maxLen, 2 * Math.min(cnt0, cnt1));
            // reset the values (for new pairs)
            cnt0 = cnt1 = 0;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // 测试用例1: "01000111"
        String s1 = "01000111";
        int result1 = findTheLongestBalancedSubstring(s1);
        System.out.println("测试用例1: " + result1);

        // 测试用例2: "00111"
        String s2 = "00111";
        int result2 = findTheLongestBalancedSubstring(s2);
        System.out.println("测试用例2: " + result2);

        // 测试用例3: "111"
        String s3 = "111";
        int result3 = findTheLongestBalancedSubstring(s3);
        System.out.println("测试用例3: " + result3);
    }
}
