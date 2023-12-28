package com.geekbo.training.leetcode.premium100;

import java.util.*;

/**
 *
 * 340. 至多包含 K 个不同字符的最长子串
 * 中等
 * 给你一个字符串 s 和一个整数 k ，请你找出 至多 包含 k 个 不同 字符的最长子串，并返回该子串的长度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "eceba", k = 2
 * 输出：3
 * 解释：满足题目要求的子串是 "ece" ，长度为 3 。
 * 示例 2：
 *
 * 输入：s = "aa", k = 1
 * 输出：2
 * 解释：满足题目要求的子串是 "aa" ，长度为 2 。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 5 * 104
 * 0 <= k <= 50
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    /**
     *
     * 解题思路：
     *
     * 用一个哈希表来记录每个字符出现的次数
     * 双指针维护一个滑动窗口，右指针不断向右移动，直到窗口中的不同字符数量超过了k个
     * 当窗口中的不同字符数量超过了k个时，需要移动左指针缩小窗口，直到窗口中的不同字符数量等于k
     * 每次移动左指针时，更新最大长度
     * 返回最大长度
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(n)，其中n为字符串s的长度
     * 空间复杂度：O(k)，其中k为字符集合的大小
     *
     * @param s
     * @param k
     * @return
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }

        // 用一个哈希表来记录每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);

            // 如果哈希表中的不同字符数量超过了k个，则需要移动左指针缩小窗口
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }

            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // 测试用例1
        String s1 = "eceba";
        int k1 = 2;
        // 最长子串是"ece"，长度为3
        System.out.println(lengthOfLongestSubstringKDistinct(s1, k1)); // 输出: 3

        // 测试用例2
        String s2 = "aa";
        int k2 = 1;
        // 最长子串是"aa"，长度为2
        System.out.println(lengthOfLongestSubstringKDistinct(s2, k2)); // 输出: 2
    }
}

