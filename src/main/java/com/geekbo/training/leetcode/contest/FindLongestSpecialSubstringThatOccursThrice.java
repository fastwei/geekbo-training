package com.geekbo.training.leetcode.contest;

/**
 *
 *378-2
 * 10032. Find Longest Special Substring That Occurs Thrice I
 * Solved
 * Medium
 * You are given a string s that consists of lowercase English letters.
 *
 * A string is called special if it is made up of only a single character. For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.
 *
 * Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaaa"
 * Output: 2
 * Explanation: The longest special substring which occurs thrice is "aa": substrings "aaaa", "aaaa", and "aaaa".
 * It can be shown that the maximum length achievable is 2.
 * Example 2:
 *
 * Input: s = "abcdef"
 * Output: -1
 * Explanation: There exists no special substring which occurs at least thrice. Hence return -1.
 * Example 3:
 *
 * Input: s = "abcaba"
 * Output: 1
 * Explanation: The longest special substring which occurs thrice is "a": substrings "abcaba", "abcaba", and "abcaba".
 * It can be shown that the maximum length achievable is 1.
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 50
 * s consists of only lowercase English letters.
 *
 */
public class FindLongestSpecialSubstringThatOccursThrice {

    public int maximumLength(String s) {
        int maxLen = -1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // Check if this substring occurs at least thrice
                    int len = j - i + 1;
                    if (occursAtLeastThrice(s, i, len)) {
                        maxLen = Math.max(maxLen, len);
                    }
                } else {
                    break; // Break the loop if characters are not the same
                }
            }
        }
        return maxLen;
    }

    private boolean occursAtLeastThrice(String s, int start, int len) {
        int count = 0;
        for (int i = 0; i <= s.length() - len; i++) {
            boolean match = true;
            for (int j = 0; j < len; j++) {
                if (s.charAt(i + j) != s.charAt(start + j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                count++;
                if (count == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FindLongestSpecialSubstringThatOccursThrice solution2 = new FindLongestSpecialSubstringThatOccursThrice();
        System.out.println(solution2.maximumLength("aaaa"));   // Output: 2
        System.out.println(solution2.maximumLength("abcdef")); // Output: -1
        System.out.println(solution2.maximumLength("abcaba")); // Output: 1
    }
}
