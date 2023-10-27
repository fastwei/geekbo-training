package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 * <p>
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInString {

    /**
     * 解题思路： 本题可以使用滑动窗口的方法来解决。
     * 首先，我们可以统计字符串p中每个字符出现的次数，可以使用一个长度为26的数组来表示。
     * 接下来，我们使用两个指针left和right来构建滑动窗口，初始时都指向字符串s的第一个字符。
     * <p>
     * 我们将right指针向右移动，每次将right指向的字符在sCount数组中的计数+1。
     * 当窗口大小等于p字符串的长度时，我们需要判断窗口内的字符是否是p字符串的anagram。
     * 为了判断两个字符串是否是anagram，我们可以比较两个字符串的计数数组是否相等。
     * 如果相等，则说明窗口内的字符是p字符串的anagram，将left指向的索引加入到结果列表中。
     * <p>
     * 然后，我们将left指向的字符在sCount数组中的计数-1，并将left指针向右移动一位。
     * 重复上述步骤，直到right指针遍历完字符串s。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是s字符串的长度。我们需要遍历字符串s一次来构建滑动窗口，所以时间复杂度是O(n)。
     * 空间复杂度：O(1)，使用了常数个额外空间。
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] pCount = new int[26];

        // 统计p字符串中每个字符出现的次数
        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int[] sCount = new int[26];

        // 滑动窗口遍历s字符串，判断窗口内的字符是否是p字符串的anagram
        while (right < s.length()) {
            sCount[s.charAt(right) - 'a']++;

            // 当窗口大小等于p字符串的长度时，判断窗口内的字符是否是p字符串的anagram
            if (right - left + 1 == p.length()) {
                if (Arrays.equals(sCount, pCount)) {
                    result.add(left);
                }
                sCount[s.charAt(left) - 'a']--;
                left++;
            }

            right++;
        }

        return result;
    }

    public static void main(String[] args) {
        FindAllAnagramsInString findAllAnagramsInString = new FindAllAnagramsInString();

        // Test Case 1
        String s1 = "cbaebabacd";
        String p1 = "abc";
        List<Integer> result1 = findAllAnagramsInString.findAnagrams(s1, p1);
        System.out.println(result1);  // Expected output: [0, 6]

        // Test Case 2
        String s2 = "abab";
        String p2 = "ab";
        List<Integer> result2 = findAllAnagramsInString.findAnagrams(s2, p2);
        System.out.println(result2);  // Expected output: [0, 1, 2]
    }
}
