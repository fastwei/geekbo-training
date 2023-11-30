package com.geekbo.training.leetcode.design;

import java.util.HashSet;
import java.util.Set;

/**
 * 676. Implement Magic Dictionary
 * Medium
 * Design a data structure that is initialized with a list of different words.
 * Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.
 * <p>
 * Implement the MagicDictionary class:
 * <p>
 * MagicDictionary() Initializes the object.
 * void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
 * bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure,
 * otherwise returns false.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * Output
 * [null, null, false, true, false, false]
 * <p>
 * Explanation
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // return False
 * magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
 * magicDictionary.search("hell"); // return False
 * magicDictionary.search("leetcoded"); // return False
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] consists of only lower-case English letters.
 * All the strings in dictionary are distinct.
 * 1 <= searchWord.length <= 100
 * searchWord consists of only lower-case English letters.
 * buildDict will be called only once before search.
 * At most 100 calls will be made to search.
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 使用一个 HashSet 存储字典中的单词。
 * 在搜索时，遍历字典中的每个单词，判断是否与搜索词只有一个字符不同，如果是，则返回 true，否则继续搜索。
 * 如果遍历完字典都没有找到符合条件的单词，则返回 false。
 * 算法复杂度分析：
 * <p>
 * buildDict 方法的时间复杂度为 O(m)，其中 m 为字典中的单词数量，因为需要将所有单词添加到 HashSet 中。
 * search 方法的时间复杂度为 O(m * n)，其中 m 为字典中的单词数量，n 为单词的长度，
 * 因为需要遍历字典中的每个单词，并对每个单词进行一次字符比较。
 */
public class MagicDictionary {
    private Set<String> wordSet;

    /**
     * 初始化 MagicDictionary 对象
     */
    public MagicDictionary() {
        wordSet = new HashSet<>();
    }

    /**
     * 构建字典
     *
     * @param dictionary 字符串数组作为字典
     */
    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            wordSet.add(word);
        }
    }

    /**
     * 搜索是否存在可以通过改变一个字符与字典中的字符串匹配的字符串
     *
     * @param searchWord 搜索字符串
     * @return 如果存在匹配的字符串则返回 true，否则返回 false
     */
    public boolean search(String searchWord) {
        for (String word : wordSet) {
            if (isOneCharDifferent(word, searchWord)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两个字符串是否只有一个字符不同
     *
     * @param word1 字符串1
     * @param word2 字符串2
     * @return 如果只有一个字符不同，则返回 true，否则返回 false
     */
    private boolean isOneCharDifferent(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});

        // 测试用例
        String searchWord1 = "hello";
        // 预期输出：false
        System.out.println(magicDictionary.search(searchWord1));

        String searchWord2 = "hhllo";
        // 预期输出：true
        System.out.println(magicDictionary.search(searchWord2));

        String searchWord3 = "hell";
        // 预期输出：false
        System.out.println(magicDictionary.search(searchWord3));

        String searchWord4 = "leetcoded";
        // 预期输出：false
        System.out.println(magicDictionary.search(searchWord4));
    }
}
