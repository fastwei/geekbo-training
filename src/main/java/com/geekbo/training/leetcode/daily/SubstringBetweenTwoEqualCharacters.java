package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 1624. Largest Substring Between Two Equal Characters
 * Easy
 * Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aa"
 * Output: 0
 * Explanation: The optimal substring here is an empty substring between the two 'a's.
 * Example 2:
 *
 * Input: s = "abca"
 * Output: 2
 * Explanation: The optimal substring here is "bc".
 * Example 3:
 *
 * Input: s = "cbzxy"
 * Output: -1
 * Explanation: There are no characters that appear twice in s.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * s contains only lowercase English letters.
 *
 */
public class SubstringBetweenTwoEqualCharacters {
    /**
     * 返回字符串中两个相同字符之间的最长子字符串的长度
     * 解题思路：
     *
     * 使用一个HashMap来记录每个字符最后一次出现的索引。
     * 遍历字符串，对于每个字符：
     * 如果当前字符在HashMap中已经存在，则计算当前字符与上一次出现的索引之间的距离，并更新最长子字符串的长度。
     * 如果当前字符在HashMap中不存在，则将其加入HashMap中。
     * 返回最长子字符串的长度。
     * 算法复杂度：
     *
     * 字符串长度为n，遍历一次字符串，时间复杂度为O(n)。
     * 使用HashMap存储字符的最后出现索引，空间复杂度为O(n)。
     * @param s 给定的字符串
     * @return 最长子字符串的长度，如果不存在则返回-1
     */
    public static int lengthOfLongestSubstring(String s) {
        // 使用map来记录每个字符最后一次出现的索引
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = -1;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果当前字符在map中已经存在，则计算当前字符与上一次出现的索引之间的距离
            if (map.containsKey(c)) {
                int distance = i - map.get(c) - 1;
                maxLength = Math.max(maxLength, distance);
            } else {
                map.put(c, i);
            }
        }
        
        return maxLength;
    }
    
    public static void main(String[] args) {
        // 测试用例
        String s1 = "aa";
        // 最长子字符串为""，长度为0
        System.out.println(lengthOfLongestSubstring(s1)); 
        
        String s2 = "abca";
        // 最长子字符串为"bc"，长度为2
        System.out.println(lengthOfLongestSubstring(s2));
        
        String s3 = "cbzxy";
        // 不存在相同的字符，返回-1
        System.out.println(lengthOfLongestSubstring(s3));
    }
}


