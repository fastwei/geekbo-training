package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 *
 * 567. Permutation in String
 * Medium
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *
 */
public class PermutationInString {
    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        System.out.println(checkInclusion(s1, s2)); // true

        s1 = "ab";
        s2 = "eidboaoo";
        System.out.println(checkInclusion(s1, s2)); // false
    }

    /**
     * 判断s2是否包含s1的排列
     *
     * 解题思路：
     * 1. 使用滑动窗口来遍历s2，窗口长度为s1的长度
     * 2. 统计s1和窗口内字符出现的频次，如果频次相同，则s2包含s1的排列
     *
     * 算法复杂度分析：
     * - 时间复杂度：O(n)，其中n为s2的长度
     * - 空间复杂度：O(1)，因为只用了常数级别的额外空间
     *
     * @param s1 字符串s1
     * @param s2 字符串s2
     * @return 如果s2包含s1的排列，则返回true；否则返回false
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        // 统计s1中每个字符的出现频次
        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
        }

        // 使用滑动窗口遍历s2
        for (int i = 0; i < s2.length(); i++) {
            // 窗口右边界的字符
            s2Count[s2.charAt(i) - 'a']++;

            // 窗口长度超过s1的长度，移动窗口左边界
            if (i >= s1.length()) {
                s2Count[s2.charAt(i - s1.length()) - 'a']--;
            }

            // 判断s1和窗口内字符的频次是否相同
            if (Arrays.equals(s1Count, s2Count)) {
                return true;
            }
        }

        return false;
    }
}

