package com.geekbo.training.leetcode.top150.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 * todo:加深理解，使用backtracking
 */
public class WordSearchII {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 在给定的字符矩阵board中查找所有由给定单词数组words构成的单词。
     * 每个单词必须由相邻的单元格的字母构成，其中相邻单元格是水平或垂直相邻的。
     * 同一个字母单元格不能重复使用。
     * <p>
     * <p>
     * <p>
     * 解题思路：
     * 1. 首先构建一个前缀树（Trie），将单词数组中的单词插入到前缀树中。
     * 2. 遍历字符矩阵中的每个位置，以每个位置为起点，通过深度优先搜索（DFS）寻找满足条件的单词。
     * 3. 在DFS过程中，判断当前位置是否越界或已被访问，若是则返回；否则，继续判断当前字符是否在前缀树中。
     * 4. 如果当前字符存在于前缀树中，继续DFS搜索相邻位置。
     * 5. 若DFS搜索到的节点表示一个单词，将其添加到结果集中。
     * 6. 最后，将结果集转换为列表形式并返回。
     * <p>
     * 算法复杂度分析：
     * - 假设字符矩阵的大小为m x n，单词数组的长度为L，单词的平均长度为W。
     * - 构建前缀树的时间复杂度为O(LW)，每个单词插入前缀树的时间复杂度为O(W)。
     * - DFS搜索的时间复杂度为O(mn * 4^W)，其中mn为字符矩阵的大小，每个位置都可能有4个相邻位置要搜索。
     * - 因此，总的时间复杂度为O(LW + mn * 4^W)。
     * - 空间复杂度为O(LW)，用于存储前缀树的节点和单词结果集。
     *
     * @param board 字符矩阵
     * @param words 单词数组
     * @return 所有找到的单词列表
     */
    public static List<String> findWords(char[][] board, String[] words) {
        // 创建一个集合来存储找到的单词
        Set<String> result = new HashSet<>();

        // 创建一个前缀树来存储单词
        TrieNode root = buildTrie(words);

        // 遍历字符矩阵并搜索单词
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(board, i, j, visited, root, result);
            }
        }

        // 将集合转换为列表并返回
        return new ArrayList<>(result);
    }

    private static void dfs(char[][] board, int i, int j, boolean[][] visited, TrieNode node, Set<String> result) {
        // 检查当前位置是否越界或已被访问
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return;
        }

        // 获取当前位置的字符
        char c = board[i][j];

        // 检查当前字符是否在前缀树中
        if (!node.containsKey(c)) {
            return;
        }

        // 标记当前位置为已访问
        visited[i][j] = true;

        // 获取前缀树的下一个节点
        TrieNode nextNode = node.get(c);

        // 检查是否有单词以当前位置结尾
        if (nextNode.isWord()) {
            result.add(nextNode.getWord());
        }

        // 遍历相邻的位置
        for (int[] direction : DIRECTIONS) {
            int ni = i + direction[0];
            int nj = j + direction[1];
            dfs(board, ni, nj, visited, nextNode, result);
        }

        // 标记当前位置为未访问
        visited[i][j] = false;
    }

    private static TrieNode buildTrie(String[] words) {
        // 创建前缀树的根节点
        TrieNode root = new TrieNode();

        // 将每个单词插入前缀树
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
            }
            node.setWord(word);
        }

        return root;
    }

    public static void main(String[] args) {
        char[][] board1 = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words1 = {"oath", "pea", "eat", "rain"};
        // 预期输出: ["eat", "oath"]
        System.out.println(findWords(board1, words1));

        char[][] board2 = {
                {'a', 'b'},
                {'c', 'd'}
        };
        String[] words2 = {"abcb"};
        // 预期输出: []
        System.out.println(findWords(board2, words2));
    }

    static class TrieNode {
        private TrieNode[] children;
        private String word;

        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }

        public boolean containsKey(char c) {
            return children[c - 'a'] != null;
        }

        public TrieNode get(char c) {
            return children[c - 'a'];
        }

        public void put(char c, TrieNode node) {
            children[c - 'a'] = node;
        }

        public boolean isWord() {
            return word != null;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public String getWord() {
            return word;
        }
    }
}
