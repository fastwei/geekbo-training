package com.geekbo.training.leetcode.contest;

/**
 *378-3
 * 10033. Find Longest Special Substring That Occurs Thrice II
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
 * 3 <= s.length <= 5 * 105
 * s consists of only lowercase English letters.
 *
 */
public class FindLongestSpecialSubstringThatOccursThriceII {
    /**
     * 算法的思路是使用一个长度为 26 的数组 maxSpecialLength 来保存每个字符对应的最长特殊子串的长度。
     * 初始时，数组的每个元素都设置为 -1。
     * <p>
     * 然后，遍历字符串 s 中的每个字符，对于每个字符，通过维护三个变量 low、mid 和 high 来记录当前字符的最长特殊子串的长度。
     * low 记录次长的特殊子串的长度，mid 记录第二长的特殊子串的长度，high 记录最长的特殊子串的长度。
     * 同时，使用变量 pos 记录当前特殊子串的起始位置。
     * <p>
     * 当遍历到一个字符时，如果该字符与当前特殊子串的字符相同，更新 low、mid 和 high 的值。
     * 如果不同，则将当前特殊子串的长度与 maxSpecialLength 数组中对应字符的值进行比较，取较大的值，并将 pos 重置为 -1，表示当前特殊子串结束。
     * <p>
     * 最后，遍历完字符串后，将每个字符对应的最长特殊子串的长度与全局变量 max 进行比较，取较大的值，作为最终的结果。
     * <p>
     * 算法的时间复杂度为 O(n)，其中 n 是字符串 s 的长度。
     */
    public int maximumLength(String s) {
        int[] maxSpecialLength = new int[26];
        int max = -1;

        for (int i = 0; i < 26; i++) {
            maxSpecialLength[i] = -1;
            int low = -1, mid = -1, high = -1;
            char ch = (char) (97 + i);
            int pos = -1;

            for (int j = 0; j < s.length(); j++) {
                if (pos == -1) {
                    pos = j;
                }

                if (ch == s.charAt(j)) {
                    if ((j - pos + 1) > high) {
                        low = mid;
                        mid = high;
                        high = (j - pos + 1);
                    } else if ((j - pos + 1) > mid) {
                        low = mid;
                        mid = (j - pos + 1);
                    } else {
                        low = (j - pos + 1);
                    }
                } else {
                    maxSpecialLength[i] = Math.max(maxSpecialLength[i], low);
                    pos = -1;
                }
            }

            maxSpecialLength[i] = Math.max(maxSpecialLength[i], low);
            max = Math.max(max, maxSpecialLength[i]);
        }

        return max;
    }


    public static void main(String[] args) {
        FindLongestSpecialSubstringThatOccursThriceII solution = new FindLongestSpecialSubstringThatOccursThriceII();
        System.out.println(solution.maximumLength("aaaa"));   // Output: 2
        System.out.println(solution.maximumLength("abcdef")); // Output: -1
        System.out.println(solution.maximumLength("abcaba")); // Output: 1
    }
}
