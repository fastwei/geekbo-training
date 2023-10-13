package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {

    /**
     * 解题思路：
     * <p>
     * 首先将字符串s按照空格分割成单词数组。
     * 如果pattern的长度和s中单词的个数不相等，那么它们一定不满足pattern的条件，返回false。
     * 创建两个HashMap，分别用于记录字符到单词和单词到字符的映射关系。
     * 遍历pattern和单词数组的每个位置，分别记为字符c和单词word。
     * 如果字符c已经存在映射关系，但映射的值不等于当前单词word，则不满足pattern的条件，返回false。
     * 如果单词word已经存在映射关系，但映射的值不等于当前字符c，则不满足pattern的条件，返回false。
     * 否则，建立字符到单词和单词到字符的映射
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设pattern的长度为n，单词数组的长度为m。在遍历pattern和单词数组的过程中，我们需要分别访问每个字符和单词，所以时间复杂度为O(n+m)。
     * 空间复杂度：我们使用了两个HashMap来记录映射关系，其中字符到单词的HashMap的大小不会超过n，单词到字符的HashMap的大小不会超过m。所以空间复杂度为O(n+m)。
     * 综上所述，该算法的时间复杂度为O(n+m)，空间复杂度为O(n+m)。
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        // 如果pattern的长度和s中单词的个数不相等，返回false
        if (pattern.length() != words.length) {
            return false;
        }

        // 使用两个HashMap分别记录字符到单词和单词到字符的映射关系
        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // 如果字符c已经存在映射关系，但映射的值不等于当前单词word，则不满足pattern的条件，返回false
            if (charToWord.containsKey(c) && !charToWord.get(c).equals(word)) {
                return false;
            }

            // 如果单词word已经存在映射关系，但映射的值不等于当前字符c，则不满足pattern的条件，返回false
            if (wordToChar.containsKey(word) && wordToChar.get(word) != c) {
                return false;
            }

            // 建立字符到单词和单词到字符的映射关系
            charToWord.put(c, word);
            wordToChar.put(word, c);
        }

        return true;
    }

    public static void main(String[] args) {
        WordPattern solution = new WordPattern();

        // 测试用例1
        String pattern1 = "abba";
        String s1 = "dog cat cat dog";
        boolean expected1 = true;
        boolean result1 = solution.wordPattern(pattern1, s1);
        System.out.println(result1 == expected1); // true

        // 测试用例2
        String pattern2 = "abba";
        String s2 = "dog cat cat fish";
        boolean expected2 = false;
        boolean result2 = solution.wordPattern(pattern2, s2);
        System.out.println(result2 == expected2); // true

        // 测试用例3
        String pattern3 = "aaaa";
        String s3 = "dog cat cat dog";
        boolean expected3 = false;
        boolean result3 = solution.wordPattern(pattern3, s3);
        System.out.println(result3 == expected3); // true
    }
}