package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 *
 * 953. Verifying an Alien Dictionary
 * Easy
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 *
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 *
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 *
 */
public class AlienDictionary {
    /**
     * 判断给定的单词序列是否按照指定的字母顺序排序
     *
     * @param words 单词序列
     * @param order 字母顺序
     * @return 如果单词序列按照指定的字母顺序排序，则返回true；否则，返回false
     */
    public static boolean isAlienSorted(String[] words, String order) {
        // 构建字母顺序的映射关系
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }

        // 遍历单词序列，比较相邻的两个单词
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            // 比较两个单词的相同位置的字符
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);

                // 如果字符不相等，则根据字母顺序的映射关系判断它们的顺序
                if (c1 != c2) {
                    if (map.get(c1) > map.get(c2)) {
                        return false;
                    } else {
                        break;
                    }
                }

                // 如果前缀相同，但是一个单词比另一个单词更长，则返回false
                if (j == len - 1 && word1.length() > word2.length()) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // 测试用例1
        String[] words1 = {"hello", "leetcode"};
        String order1 = "hlabcdefgijkmnopqrstuvwxyz";
        boolean result1 = isAlienSorted(words1, order1);
        System.out.println(result1);  // 预期输出：true

        // 测试用例2
        String[] words2 = {"word", "world", "row"};
        String order2 = "worldabcefghijkmnpqstuvxyz";
        boolean result2 = isAlienSorted(words2, order2);
        System.out.println(result2);  // 预期输出：false

        // 测试用例3
        String[] words3 = {"apple", "app"};
        String order3 = "abcdefghijklmnopqrstuvwxyz";
        boolean result3 = isAlienSorted(words3, order3);
        System.out.println(result3);  // 预期输出：false
    }
}