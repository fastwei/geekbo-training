package com.geekbo.training.leetcode.top150;


/**
 *
 * 这个Trie类允许你初始化一个Trie对象，插入字符串，搜索字符串是否存在于Trie中，以及查找是否有以某个前缀开头的字符串。
 * Trie数据结构在自动补全和拼写检查等应用中非常有用。
 *
 */
class Trie {
    TrieNode root;

    class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            children = new TrieNode[26]; // 26 lowercase letters
            isEndOfWord = false;
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return true;
    }
}

public class TrieUsage {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 预期输出：true
        System.out.println(trie.search("app"));     // 预期输出：false
        System.out.println(trie.startsWith("app")); // 预期输出：true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 预期输出：true
    }
}
