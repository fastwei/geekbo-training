package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    TrieNode[] children;
    boolean isEnd;

    Map<Character, TrieNode> childrenMap;
    boolean isWord;

    TrieNode() {
        children = new TrieNode[26];
        childrenMap = new HashMap<>();
        isWord = false;
        isEnd = false;
    }



}
public class LongestCommonPrefix {

    /**
     * 首先构建了一个Trie树，然后从根节点开始，
     *
     * 逐字符比较每个字符串，直到找到最长公共前缀或遇到不同字符。
     *
     * 算法复杂度为O(N)，其中N是所有字符串的字符总数。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        TrieNode root = new TrieNode();

        // 构建Trie树
        for (String word : strs) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        // 寻找最长公共前缀
        StringBuilder prefix = new StringBuilder();
        TrieNode node = root;
        while (true) {
            int count = 0;
            int index = 0;
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    count++;
                    index = i;
                }
            }
            if (count == 1 && !node.isEnd) {
                prefix.append((char) ('a' + index));
                node = node.children[index];
            } else {
                break;
            }
        }

        return prefix.toString();
    }

    /**
     * 首先假设第一个字符串是最长公共前缀，然后逐个比较每个字符串与当前公共前缀的相同部分。
     * 如果发现没有公共前缀，就返回空字符串。
     * 否则，更新公共前缀为相同部分，继续比较下一个字符串。
     * <p>
     * 时间复杂度为O(S)，其中S是所有字符串中字符的总数。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefixNormal(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0]; // 假设第一个字符串是最长公共前缀

        for (int i = 1; i < strs.length; i++) {
            String current = strs[i];
            int j = 0;

            while (j < prefix.length() && j < current.length() && prefix.charAt(j) == current.charAt(j)) {
                j++;
            }

            if (j == 0) {
                return ""; // 没有公共前缀，直接返回空字符串
            }

            prefix = prefix.substring(0, j); // 更新最长公共前缀
        }

        return prefix;
    }

    // 测试用例
    public static void main(String[] args) {
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();

        String[] strs1 = {"flower", "flow", "flight"};
        String result1 = longestCommonPrefix.longestCommonPrefix(strs1);
        System.out.println("Example 1: " + result1); // 输出 "fl"

        String[] strs2 = {"dog", "racecar", "car"};
        String result2 = longestCommonPrefix.longestCommonPrefix(strs2);
        System.out.println("Example 2: " + result2); // 输出 ""

        String[] strs3 = {"abc", "ab", "abcd"};
        String result3 = longestCommonPrefix.longestCommonPrefix(strs3);
        System.out.println("Example 3: " + result3); // 输出 "ab"
    }
}
