package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 1160. Find Words That Can Be Formed by Characters
 * Easy
 * You are given an array of strings words and a string chars.
 *
 * A string is good if it can be formed by characters from chars (each character can only be used once).
 *
 * Return the sum of lengths of all good strings in words.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
 * Example 2:
 *
 * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * Output: 10
 * Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 *
 */
public class FindWordsThatCanBeFormedByCharacters {
    public int countCharacters(String[] words, String chars) {
        // 创建一个HashMap，用于记录每个字符出现的次数
        Map<Character, Integer> charCount = new HashMap<>();
        // 遍历chars字符串，统计每个字符出现的次数
        for (char c : chars.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        int totalLength = 0;
        // 遍历words数组，判断每个字符串是否可以由chars中的字符组成
        for (String word : words) {
            // 创建一个HashMap，用于记录当前字符串中每个字符出现的次数
            Map<Character, Integer> wordCount = new HashMap<>();
            // 遍历当前字符串，统计每个字符出现的次数
            for (char c : word.toCharArray()) {
                wordCount.put(c, wordCount.getOrDefault(c, 0) + 1);
            }
            
            boolean isGood = true;
            // 遍历当前字符串的每个字符，判断其出现次数是否超过chars中对应字符的出现次数
            for (char c : wordCount.keySet()) {
                if (wordCount.get(c) > charCount.getOrDefault(c, 0)) {
                    isGood = false;
                    break;
                }
            }
            
            // 如果当前字符串是好的字符串，则将其长度加到总长度中
            if (isGood) {
                totalLength += word.length();
            }
        }
        
        return totalLength;
    }
    
    public static void main(String[] args) {
        FindWordsThatCanBeFormedByCharacters solution = new FindWordsThatCanBeFormedByCharacters();
        
        // 测试用例1
        String[] words1 = {"cat", "bt", "hat", "tree"};
        String chars1 = "atach";
        // 预期输出：6
        System.out.println(solution.countCharacters(words1, chars1));
        
        // 测试用例2
        String[] words2 = {"hello", "world", "leetcode"};
        String chars2 = "welldonehoneyr";
        // 预期输出：10
        System.out.println(solution.countCharacters(words2, chars2));
    }
}