package com.geekbo.training.leetcode.premium100;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * 159. 至多包含两个不同字符的最长子串
 * 中等
 * 给你一个字符串 s ，请你找出 至多 包含 两个不同字符 的最长子串，并返回该子串的长度。
 *
 *
 * 示例 1：
 *
 * 输入：s = "eceba"
 * 输出：3
 * 解释：满足题目要求的子串是 "ece" ，长度为 3 。
 * 示例 2：
 *
 * 输入：s = "ccaabbb"
 * 输出：5
 * 解释：满足题目要求的子串是 "aabbb" ，长度为 5 。
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 105
 * s 由英文字母组成
 */
public class LongestSubstring {
    /**
     *
     * 解题思路：
     *
     * 使用滑动窗口来解决问题。
     * 定义一个窗口，窗口内最多只包含两个不同的字符。
     * 使用哈希表来存储窗口内每个字符的出现次数。
     * 右指针向右移动，将当前字符加入窗口，并更新字符的出现次数。
     * 当窗口内不同字符的个数超过2个时，左指针向右移动，直到窗口内不同字符的个数不超过2个。
     * 每次移动右指针和左指针时，更新最长子串的长度。
     * 最后返回最长子串的长度。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(n)，其中 n 是字符串的长度。最坏情况下，左指针和右指针各遍历字符串一次。
     * 空间复杂度：O(1)，哈希表的大小最多为 3。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // 用于存储字符及其出现的次数
        Map<Character, Integer> map = new HashMap<>();
        int left = 0; // 窗口左指针
        int right = 0; // 窗口右指针
        int maxLen = 0; // 最长子串的长度
        
        while (right < s.length()) {
            // 将当前字符添加到窗口中
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            right++;
            
            // 判断窗口中的字符个数是否超过2个
            if (map.size() > 2) {
                // 如果超过2个，则需要移动窗口左指针，直到窗口中的字符个数不超过2个为止
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;
            }
            
            // 更新最长子串的长度
            maxLen = Math.max(maxLen, right - left);
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        // 测试用例
        String s1 = "eceba";
        // 最长子串是 "ece"，长度为 3
        System.out.println(lengthOfLongestSubstringTwoDistinct(s1)); 
        
        String s2 = "ccaabbb";
        // 最长子串是 "aabbb"，长度为 5
        System.out.println(lengthOfLongestSubstringTwoDistinct(s2)); 
    }
}
