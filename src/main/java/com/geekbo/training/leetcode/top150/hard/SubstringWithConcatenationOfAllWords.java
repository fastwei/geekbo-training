package com.geekbo.training.leetcode.top150.hard;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. Substring with Concatenation of All Words
 * Hard
 * Topics
 * Companies
 * You are given a string s and an array of strings words.
 * All the strings of words are of the same length.
 * <p>
 * A concatenated substring in s is a substring that contains all the strings of any permutation of words concatenated.
 * <p>
 * For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab"
 * are all concatenated strings.
 * "acdbef" is not a concatenated substring because it is not the concatenation of any permutation of words.
 * Return the starting indices of all the concatenated substrings in s. You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Since words.length == 2 and words[i].length == 3, the concatenated substring has to be of length 6.
 * The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
 * The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
 * The output order does not matter. Returning [9,0] is fine too.
 * Example 2:
 * <p>
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 * Explanation: Since words.length == 4 and words[i].length == 4, the concatenated substring has to be of length 16.
 * There is no substring of length 16 in s that is equal to the concatenation of any permutation of words.
 * We return an empty array.
 * Example 3:
 * <p>
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 * Explanation: Since words.length == 3 and words[i].length == 3, the concatenated substring has to be of length 9.
 * The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"] which is a permutation of words.
 * The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"] which is a permutation of words.
 * The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"] which is a permutation of words.
 */
public class SubstringWithConcatenationOfAllWords {
    /**
     * 可以使用滑动窗口的方法来解决这个问题。
     * <p>
     * 首先，我们统计了 words 数组中每个单词的出现次数，存储在 wordCount 哈希表中。
     * 然后，我们使用一个滑动窗口来遍历字符串 s。窗口的长度为 wordLength * words.length，
     * 其中 wordLength 是 words 数组中单词的长度。
     * 在每次窗口滑动时，我们在窗口内部使用一个哈希表 window 来统计窗口中每个单词的出现次数。
     * <p>
     * 在窗口内部的循环中，我们遍历窗口中的每个单词，
     * 如果该单词不在 wordCount 中或者在 window 中的出现次数超过了 wordCount 中的出现次数，就退出循环。
     * 如果窗口中的每个单词都满足条件，我们将窗口的起始索引添加到结果列表中。
     * <p>
     * 算法的时间复杂度是 O(s * m * n)，其中 s 是字符串 s 的长度，m 是 words 数组中单词的平均长度，n 是 words 数组的长度。
     * 在最坏情况下，我们需要遍历字符串 s 的每个字符，并在窗口内部遍历 words 数组中的每个单词，因此时间复杂度是 O(s * m * n)。
     * 同时，算法使用了两个哈希表 wordCount 和 window 来存储单词的出现次数，因此空间复杂度是 O(m * n)。
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int totalCount = wordLength * words.length;
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i <= s.length() - totalCount; i++) {
            Map<String, Integer> window = new HashMap<>();
            int j;
            for (j = 0; j < words.length; j++) {
                String word = s.substring(i + j * wordLength, i + (j + 1) * wordLength);
                if (!wordCount.containsKey(word)) {
                    break;
                }
                window.put(word, window.getOrDefault(word, 0) + 1);
                if (window.get(word) > wordCount.get(word)) {
                    break;
                }
            }
            if (j == words.length) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords solution = new SubstringWithConcatenationOfAllWords();

        // Test Case 1
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo", "bar"};
        System.out.println(solution.findSubstring(s1, words1));  // Output: [0, 9]

        // Test Case 2
        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word", "good", "best", "word"};
        System.out.println(solution.findSubstring(s2, words2));  // Output: []

        // Additional Test Case
        String s3 = "wordgoodgoodgoodbestword";
        String[] words3 = {"word", "good", "best", "good"};
        System.out.println(solution.findSubstring(s3, words3));  // Output: [8]
    }
}