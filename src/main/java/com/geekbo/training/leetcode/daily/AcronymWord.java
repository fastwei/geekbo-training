package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 2828. Check if a String Is an Acronym of Words
 * Easy
 * Given an array of strings words and a string s, determine if s is an acronym of words.
 *
 * The string s is considered an acronym of words if it can be formed by concatenating the first character of each string in words in order. For example, "ab" can be formed from ["apple", "banana"], but it can't be formed from ["bear", "aardvark"].
 *
 * Return true if s is an acronym of words, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["alice","bob","charlie"], s = "abc"
 * Output: true
 * Explanation: The first character in the words "alice", "bob", and "charlie" are 'a', 'b', and 'c', respectively. Hence, s = "abc" is the acronym.
 * Example 2:
 *
 * Input: words = ["an","apple"], s = "a"
 * Output: false
 * Explanation: The first character in the words "an" and "apple" are 'a' and 'a', respectively.
 * The acronym formed by concatenating these characters is "aa".
 * Hence, s = "a" is not the acronym.
 * Example 3:
 *
 * Input: words = ["never","gonna","give","up","on","you"], s = "ngguoy"
 * Output: true
 * Explanation: By concatenating the first character of the words in the array, we get the string "ngguoy".
 * Hence, s = "ngguoy" is the acronym.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 10
 * 1 <= s.length <= 100
 * words[i] and s consist of lowercase English letters.
 *
 */
public class AcronymWord {
    public static boolean isAcronym(List<String> words, String s) {
        String[] wordList = words.toArray(new String[words.size()]);
        return  isAcronym(wordList, s);
    }


    /**
     * 判断给定的字符串s是否是由字符串数组words中每个字符串的首字母组成的缩写
     * <p>
     * 解题思路： 首先判断字符串数组words的长度是否等于字符串s的长度，如果不等，则返回false。
     * 然后遍历字符串数组words和字符串s的每个字符，判断当前字符串的首字母是否等于对应位置的字符，如果不等，则返回false。
     * 如果循环结束仍未返回false，则说明s是words中每个字符串的首字母组成的缩写，返回true。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设words的长度为n，字符串s的长度也为n，遍历数组words和字符串s的时间复杂度为O(n)。
     * 空间复杂度：除了输入参数外，算法的空间复杂度为O(1)。
     *
     * @param words 字符串数组
     * @param s     给定字符串
     * @return 如果s是words中每个字符串首字母组成的缩写，则返回true，否则返回false
     */
    public static boolean isAcronym(String[] words, String s) {
        // 首先判断数组words的长度是否等于字符串s的长度
        if (words.length != s.length()) {
            return false;
        }

        // 遍历字符串数组words和字符串s的每个字符
        for (int i = 0; i < words.length; i++) {
            // 判断当前字符串的首字母是否等于对应位置的字符
            if (words[i].charAt(0) != s.charAt(i)) {
                return false;
            }
        }

        // 如果循环结束仍未返回false，则说明s是words中每个字符串的首字母组成的缩写
        return true;
    }

    public static void main(String[] args) {
        // 测试用例1
        String[] words1 = {"alice", "bob", "charlie"};
        String s1 = "abc";
        // 期望输出：true
        System.out.println(isAcronym(words1, s1));

        // 测试用例2
        String[] words2 = {"an", "apple"};
        String s2 = "a";
        // 期望输出：false
        System.out.println(isAcronym(words2, s2));

        // 测试用例3
        String[] words3 = {"never", "gonna", "give", "up", "on", "you"};
        String s3 = "ngguoy";
        // 期望输出：true
        System.out.println(isAcronym(words3, s3));
    }

}
