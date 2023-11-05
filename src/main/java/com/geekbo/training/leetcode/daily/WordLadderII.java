package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 126. Word Ladder II
 * Hard
 * A transformation sequence from word beginWord to word endWord
 * using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList,
 * return all the shortest transformation sequences from beginWord to endWord,
 * or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * todo:还有些问题，测试用例通不过
 */
public class WordLadderII {
    /**
     * 解题思路：
     * <p>
     * 使用广度优先搜索（BFS）求解最短转换序列。
     * <p>
     * 首先，将 wordList 转换为一个哈希集合，以便快速判断一个单词是否在 wordList 中。
     * 然后，使用一个队列 queue 来进行广度优先搜索。队列中存储的是一个路径（由单词组成的列表）。
     * 将 beginWord 加入队列作为初始路径。
     * 创建一个哈希集合 visited，用于记录访问过的单词，以避免重复访问。
     * 创建一个哈希映射 parent，用于记录每个单词的父节点，以便最后构造路径。
     * 使用一个标志变量 found 来记录是否找到了最短转换序列。
     * 在进行广度优先搜索时，对于队列中的每个路径，取出路径的最后一个单词，然后遍历所有与该单词相邻且在 wordList 中的单词。
     * 如果某个相邻单词与 endWord 相等，则找到了最短转换序列，将 found 置为 true，并更新 parent 哈希映射。
     * 否则，将该相邻单词加入新路径中，并将新路径加入队列。同时，将该相邻单词添加到 visited 集合中。
     * 最后，通过 parent 哈希映射构造所有的最短转换序列。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n * 26^l)，其中 n 是 wordList 的长度，l 是单词的平均长度。需要对每个单词进行遍历，并且每个单词有 26 个可能的相邻单词。
     * 空间复杂度：O(n * l)，其中 n 是 wordList 的长度，l 是单词的平均长度。需要使用哈希集合 visited、哈希映射 parent 和队列 queue。
     */

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        // 将 wordList 转换为哈希集合，以便快速判断一个单词是否在 wordList 中
        Set<String> wordSet = new HashSet<>(wordList);

        // 如果 endWord 不在 wordList 中，则无法找到最短转换序列，直接返回空列表
        if (!wordSet.contains(endWord)) {
            return result;
        }

        // 使用队列进行广度优先搜索
        Queue<List<String>> queue = new LinkedList<>();
        queue.offer(Arrays.asList(beginWord));

        // 使用哈希集合记录已访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // 使用哈希映射记录每个单词的父节点
        Map<String, List<String>> parent = new HashMap<>();
        boolean found = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> levelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                String word = path.get(path.size() - 1);

                // 遍历与 word 相邻且在 wordList 中的单词
                for (int j = 0; j < word.length(); j++) {
                    char[] wordChars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordChars[j] = c;
                        String newWord = new String(wordChars);

                        // 如果找到了最短转换序列，更新 parent 哈希映射
                        if (newWord.equals(endWord)) {
                            found = true;
                            parent.putIfAbsent(newWord, new ArrayList<>());
                            parent.get(newWord).add(word);
                        }

                        // 如果新单词在 wordList 中且未被访问过，则将其加入新路径和队列中
                        if (wordSet.contains(newWord) && !visited.contains(newWord) && !newWord.equals(word)) {
                            List<String> newPath = new ArrayList<>(path);
                            newPath.add(newWord);
                            queue.offer(newPath);
                            levelVisited.add(newWord);

                            parent.putIfAbsent(newWord, new ArrayList<>());
                            parent.get(newWord).add(word);
                        }
                    }
                }
            }

            visited.addAll(levelVisited);

            // 如果找到了最短转换序列，则停止广度优先搜索
            if (found) {
                break;
            }
        }

        // 通过 parent 哈希映射构造所有的最短转换序列
        if (found) {
            List<String> temp = new ArrayList<>();
            temp.add(endWord);
            constructPaths(beginWord, endWord, parent, temp, result);
        }

        return result;
    }

    private static void constructPaths(String beginWord, String word, Map<String, List<String>> parent, List<String> path, List<List<String>> result) {
        if (word.equals(beginWord)) {
            result.add(new ArrayList<>(path));
            Collections.reverse(result.get(result.size() - 1));
            return;
        }

        for (String parentWord : parent.get(word)) {
            path.add(parentWord);
            constructPaths(beginWord, parentWord, parent, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        // Test case 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        // Expected output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
        System.out.println(findLadders(beginWord1, endWord1, wordList1));

        // Test case 2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        // Expected output: []
        System.out.println(findLadders(beginWord2, endWord2, wordList2));
    }
}