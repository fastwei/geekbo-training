package com.geekbo.training.leetcode.top75;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Two strings are considered close if you can attain one from the other using the following operations:
 * <p>
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
 * You can use the operations on either string as many times as necessary.
 * <p>
 * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word1 = "abc", word2 = "bca"
 * Output: true
 * Explanation: You can attain word2 from word1 in 2 operations.
 * Apply Operation 1: "abc" -> "acb"
 * Apply Operation 1: "acb" -> "bca"
 * Example 2:
 * <p>
 * Input: word1 = "a", word2 = "aa"
 * Output: false
 * Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
 * Example 3:
 * <p>
 * Input: word1 = "cabbba", word2 = "abbccc"
 * Output: true
 * Explanation: You can attain word2 from word1 in 3 operations.
 * Apply Operation 1: "cabbba" -> "caabbb"
 * Apply Operation 2: "caabbb" -> "baaccc"
 * Apply Operation 2: "baaccc" -> "abbccc"
 * <p>
 * 解题思路：
 * <p>
 * 首先，我们检查两个字符串的长度是否相等，如果不相等，直接返回false，因为长度不同的字符串无法通过操作变得相等。
 * 然后，我们统计两个字符串中每个字符的频次，使用两个长度为26的数组分别记录字符频次。（hashmap也可以）
 * 我们检查两个字符串的字符集是否相同，即频次不为0的字符必须一样。如果字符集不同，返回false。
 * 接下来，我们对两个字符串中字符的频次进行排序，以便后续比较。
 * 最后，我们检查两个字符串的字符频次是否相同，如果相同，返回true，说明可以通过操作变得相等。
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：假设字符串长度为n，统计字符频次的过程时间复杂度为O(n)，对字符频次排序的时间复杂度为O(26*log(26))，即O(1)，因此总时间复杂度为O(n)。
 * 空间复杂度：使用了两个长度为26的数组来统计字符频次，因此空间复杂度为O(26*2)，即O(1)。
 */
class DetermineifTwoStringsAreClose {
    /**
     * 判断两个字符串是否可以通过交换字符或字符替换相互转化。
     *
     * @param word1 第一个字符串
     * @param word2 第二个字符串
     * @return 如果可以相互转化，返回true，否则返回false
     */
    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false; // 如果两个字符串长度不相等，无法通过操作相互转化
        }

        int[] count1 = new int[26]; // 用于统计字符频次的数组
        int[] count2 = new int[26];

        // 统计word1和word2中每个字符的频次
        for (char c : word1.toCharArray()) {
            count1[c - 'a']++;
        }

        for (char c : word2.toCharArray()) {
            count2[c - 'a']++;
        }

        // 检查字符频次统计数组是否一致
        for (int i = 0; i < 26; i++) {
            if ((count1[i] == 0 && count2[i] != 0) || (count1[i] != 0 && count2[i] == 0)) {
                return false; // 如果有字符频次不一致，返回false
            }
        }

        // 对字符频次统计数组进行排序，检查排序后是否一致
        Arrays.sort(count1);
        Arrays.sort(count2);

        return Arrays.equals(count1, count2);
    }

    /**
     * 判断两个字符串是否可以通过交换字符或字符替换操作变得相等。
     *
     * @param word1 字符串1
     * @param word2 字符串2
     * @return 如果可以通过操作变得相等，返回true；否则返回false。
     */
    public boolean closeStrings2(String word1, String word2) {
        // 如果两个字符串的长度不相等，直接返回false
        if (word1.length() != word2.length()) {
            return false;
        }

        // 统计两个字符串中每个字符的频次
        Map<Character, Integer> count1 = new HashMap<>();
        Map<Character, Integer> count2 = new HashMap<>();

        // 遍历字符串1，统计字符频次
        for (char c : word1.toCharArray()) {
            count1.put(c, count1.getOrDefault(c, 0) + 1);
        }

        // 遍历字符串2，统计字符频次
        for (char c : word2.toCharArray()) {
            count2.put(c, count2.getOrDefault(c, 0) + 1);
        }

        // 检查两个字符串的字符集是否相同
        if (!count1.keySet().equals(count2.keySet())) {
            return false;
        }

        // 统计字符串1中字符的频次，并存入数组
        int[] freq1 = new int[count1.size()];
        int idx = 0;
        for (char c : count1.keySet()) {
            freq1[idx++] = count1.get(c);
        }

        // 统计字符串2中字符的频次，并存入数组
        int[] freq2 = new int[count2.size()];
        idx = 0;
        for (char c : count2.keySet()) {
            freq2[idx++] = count2.get(c);
        }

        // 对两个数组进行排序
        Arrays.sort(freq1);
        Arrays.sort(freq2);

        // 检查两个数组是否相同
        return Arrays.equals(freq1, freq2);
    }

    public static void main(String[] args) {
        DetermineifTwoStringsAreClose determineifTwoStringsAreClose = new DetermineifTwoStringsAreClose();

        // 测试用例1
        String word1 = "abc";
        String word2 = "bca";
        System.out.println(determineifTwoStringsAreClose.closeStrings2(word1, word2)); // 输出 true

        // 测试用例2
        word1 = "a";
        word2 = "aa";
        System.out.println(determineifTwoStringsAreClose.closeStrings2(word1, word2)); // 输出 false

        // 测试用例3
        word1 = "cabbba";
        word2 = "abbccc";
        System.out.println(determineifTwoStringsAreClose.closeStrings2(word1, word2)); // 输出 true
    }

}
