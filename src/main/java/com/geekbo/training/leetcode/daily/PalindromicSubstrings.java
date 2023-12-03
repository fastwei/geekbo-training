package com.geekbo.training.leetcode.daily;

/**
 * 647. Palindromic Substrings
 * Medium
 * <p>
 * Given a string s, return the number of palindromic substrings in it.
 * <p>
 * A string is a palindrome when it reads the same backward as forward.
 * <p>
 * A substring is a contiguous sequence of characters within the string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2:
 * <p>
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 */
public class PalindromicSubstrings {
    /**
     * 解题思路： 这道题目是要求计算给定字符串中回文子串的个数。
     * 解法是使用中心扩展法，遍历字符串中的每个字符，以每个字符为中心向两边扩展，分别检查奇数长度和偶数长度的回文子串。
     * 具体实现时，使用两个指针left和right，分别指向当前中心字符的左右两个字符。
     * 如果左右字符相等，则将回文子串的个数加一，并将left左移，right右移，继续检查下一个字符。
     * 直到左右字符不相等或超出字符串边界为止。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历字符串需要O(n)的时间，对于每个字符，需要进行中心扩展，最坏情况下需要O(n)的时间。
     * 因此，总的时间复杂度为O(n^2)。
     * 空间复杂度：只使用了常数级别的额外空间，所以空间复杂度为O(1)。
     *
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expandFromCenter(s, i, i); // odd length palindromes
            count += expandFromCenter(s, i, i + 1); // even length palindromes
        }
        return count;
    }

    private static int expandFromCenter(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        System.out.println(countSubstrings(s1)); // Output: 3

        String s2 = "aaa";
        System.out.println(countSubstrings(s2)); // Output: 6
    }
}
