package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

class TrieNode2 {
    Map<Character, TrieNode2> children;
    boolean isWord;

    TrieNode2() {
        children = new HashMap<>();
        isWord = false;
    }
}


/**
 *
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * todo:待优化
 *
 */
public class WordDictionary {
    private TrieNode2 root;

    /**
     * 初始化数据结构
     * 解题思路：使用前缀树（Trie）来存储单词
     * 根节点不存储字符，每个节点的子节点都是一个字母，用哈希表来存储子节点
     * 每个节点还需要一个布尔值来表示是否是一个单词的结尾
     * 添加单词时，从根节点开始，逐个字符添加到前缀树中
     * 搜索单词时，从根节点开始，逐个字符查找，遇到'.'时，需要递归搜索所有可能的子节点
     * 算法复杂度分析：
     * 添加单词的时间复杂度为O(N)，其中N是单词的长度
     * 搜索单词的时间复杂度取决于单词中'.'的数量，最坏情况下为O(26^M)，其中M是单词中'.'的数量
     */
    public WordDictionary() {
        root = new TrieNode2();
    }

    /**
     * 添加单词到前缀树
     */
    public void addWord(String word) {
        TrieNode2 node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode2());
            }
            node = node.children.get(ch);
        }
        node.isWord = true;
    }

    /**
     * 搜索是否存在符合条件的单词
     */
    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }

    /**
     * 递归搜索单词
     */
    private boolean searchHelper(String word, int index, TrieNode2 node) {
        if (index == word.length()) {
            return node.isWord;
        }

        char ch = word.charAt(index);
        if (ch != '.') {
            if (!node.children.containsKey(ch)) {
                return false;
            }
            return searchHelper(word, index + 1, node.children.get(ch));
        } else {
            for (char child : node.children.keySet()) {
                if (searchHelper(word, index + 1, node.children.get(child))) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");

        System.out.println(wordDictionary.search("pad")); // false
        System.out.println(wordDictionary.search("bad")); // true
        System.out.println(wordDictionary.search(".ad")); // true
        System.out.println(wordDictionary.search("b..")); // true
    }
}