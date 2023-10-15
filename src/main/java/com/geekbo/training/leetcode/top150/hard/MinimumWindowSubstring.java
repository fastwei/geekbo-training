package com.geekbo.training.leetcode.top150.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Minimum Window Substring
 * Hard
 * Topics
 * Companies
 * Hint
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring
 * of s such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 * <p>
 * The testcases will be generated such that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 * <p>
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 * <p>
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 * <p>
 * <p>
 * <p>
 * 给定两个字符串s和t，长度分别为m和n，返回s的最小窗口子串，使得窗口中包含t中的每个字符（包括重复字符）。
 * 如果不存在这样的子串，则返回空字符串""。
 * <p>
 * 示例1： 输入：s = "ADOBECODEBANC", t = "ABC" 输出："BANC"
 * 解释：最小窗口子串"BANC"包含了字符串t中的'A'、'B'和'C'。
 * <p>
 * 示例2： 输入：s = "a", t = "a" 输出："a" 解释：整个字符串s就是最小窗口。
 * <p>
 * 示例3： 输入：s = "a", t = "aa" 输出：""
 * 解释：t中的两个'a'必须都包含在窗口中。由于s中最大的窗口只有一个'a'，返回空字符串。
 * <p>
 * Sliding Window
 */


public class MinimumWindowSubstring {

    /**
     * 该算法使用了滑动窗口的思想来解决问题。
     * <p>
     * 首先，我们统计字符串t中每个字符的出现次数，存储在targetMap中。
     * 然后，我们使用两个指针left和right来构建滑动窗口。
     * right指针先向右移动，并将遇到的字符加入到windowMap中，同时更新窗口中字符出现的次数。
     * 当窗口中某个字符的出现次数与目标字符的出现次数一致时，我们将count加1，表示窗口中已经包含了一个目标字符。
     * <p>
     * 接下来，我们进入内层循环，不断移动left指针，缩小窗口大小，直到窗口无法再缩小。
     * 在每次移动left指针时，我们更新窗口中字符出现的次数，同时判断窗口中是否包含了所有的目标字符。
     * 如果窗口中的字符出现次数小于目标字符的出现次数，则将count减1，表示窗口中缺少一个目标字符。
     * <p>
     * 在循环结束后，我们找到了最小窗口子串的起始位置和终止位置，通过substring方法截取该子串并返回。
     * <p>
     * 算法的时间复杂度是O(m+n)，其中m是字符串s的长度，n是字符串t的长度。
     * 在最坏情况下，我们需要遍历字符串s和字符串t的每个字符，因此时间复杂度是O(m+n)。
     * 同时，算法使用了两个HashMap来存储字符出现的次数，因此空间复杂度是O(m+n)。
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || t.length() > s.length()) {
            return "";
        }

        // 统计字符串t中每个字符的出现次数
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int count = 0;  // 记录窗口中已经包含了t中字符的个数
        int minLength = Integer.MAX_VALUE;
        int minLeft = 0;
        int minRight = 0;
        Map<Character, Integer> windowMap = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            if (targetMap.containsKey(c) && windowMap.get(c).intValue() == targetMap.get(c).intValue()) {
                count++;
            }

            while (count == targetMap.size()) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minLeft = left;
                    minRight = right;
                }

                char leftChar = s.charAt(left);
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                if (targetMap.containsKey(leftChar) && windowMap.get(leftChar).intValue() < targetMap.get(leftChar).intValue()) {
                    count--;
                }

                left++;
            }

            right++;
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(minLeft, minRight + 1);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();

        // 测试样例
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println(minimumWindowSubstring.minWindow(s1, t1));  // 输出: "BANC"

        String s2 = "a";
        String t2 = "a";
        System.out.println(minimumWindowSubstring.minWindow(s2, t2));  // 输出: "a"

        String s3 = "a";
        String t3 = "aa";
        System.out.println(minimumWindowSubstring.minWindow(s3, t3));  // 输出: ""
    }
}