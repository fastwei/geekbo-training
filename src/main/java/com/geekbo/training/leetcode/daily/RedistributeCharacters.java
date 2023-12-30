package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 1897. Redistribute Characters to Make All Strings Equal
 * Easy
 * You are given an array of strings words (0-indexed).
 *
 * In one operation, pick two distinct indices i and j, where words[i] is a non-empty string, and move any character from words[i] to any position in words[j].
 *
 * Return true if you can make every string in words equal using any number of operations, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abc","aabc","bc"]
 * Output: true
 * Explanation: Move the first 'a' in words[1] to the front of words[2],
 * to make words[1] = "abc" and words[2] = "abc".
 * All the strings are now equal to "abc", so return true.
 * Example 2:
 *
 * Input: words = ["ab","a"]
 * Output: false
 * Explanation: It is impossible to make all the strings equal using the operation.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of lowercase English letters.
 *
 */
public class RedistributeCharacters {
    /**
     * 判断给定的字符串数组中的字符串能否通过移动字符使得所有字符串相等
     *
     * 解题思路：
     * 遍历字符串数组，统计每个字符出现的次数，如果出现次数不为字符串数组的长度，则返回false，否则返回true。
     *
     * 算法复杂度分析：
     * 时间复杂度：O(n*m)，其中 n 是字符串数组的长度，m 是字符串的平均长度。
     * 需要遍历字符串数组并统计每个字符的出现次数。
     * 空间复杂度：O(26)，因为只有小写英文字母，所以需要一个长度为 26 的数组来统计字符出现的次数。
     *
     * @param words 给定的字符串数组
     * @return 是否可以通过移动字符使得所有字符串相等
     */
    public static boolean makeEqual(String[] words) {
        int[] count = new int[26];

        for (String word : words) {
            for (char c : word.toCharArray()) {
                count[c - 'a']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] % words.length != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test case 1
        String[] words1 = {"abc", "aabc", "bc"};
        boolean result1 = makeEqual(words1);
        System.out.println("Test case 1: " + result1); // Expected output: true

        // Test case 2
        String[] words2 = {"ab", "a"};
        boolean result2 = makeEqual(words2);
        System.out.println("Test case 2: " + result2); // Expected output: false
    }
}


