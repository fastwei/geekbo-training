package com.geekbo.training.leetcode.classic119;

import java.util.Set;

/**
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
class MagicDictionary {

    private Set<String> wordSet;

    /**
     * 初始化 MagicDictionary 对象
     */
    public MagicDictionary() {
        //wordSet = new HashSet<>();
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
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */