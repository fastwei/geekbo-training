package com.geekbo.training.leetcode.top150.hard;

import java.util.*;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList
 * is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList,
 * return the number of words in the shortest transformation sequence from beginWord to endWord,
 * or 0 if no such sequence exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog",
 * which is 5 words long.
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * todo:加深理解
 */
public class WordTransformation {
    /**
     * 解题思路：
     * <p>
     * 可以将问题转化为求解图中最短路径的问题，其中图的节点表示单词，边表示单词之间的变换关系。
     * 使用广度优先搜索算法来求解最短路径。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：假设单词的长度为L，单词列表的长度为N。
     * 构建变换关系图的时间复杂度为O(N*L^2)。
     * 使用广度优先搜索算法的时间复杂度为O(N*L^2)。
     * 综上，算法的总时间复杂度为O(N*L^2)。
     * <p>
     * 空间复杂度：算法使用了队列和集合来存储数据，空间复杂度为O(N)。
     *
     * @param beginWord 起始单词
     * @param endWord   目标单词
     * @param wordList  单词列表
     * @return 最短变换序列的长度，如果不存在则返回0
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 将单词列表转化为集合，方便查找
        Set<String> wordSet = new HashSet<>(wordList);

        // 如果目标单词不在单词列表中，则无法进行变换，返回0
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // 创建队列，用于进行广度优先搜索
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        // 创建集合，用于记录已经访问过的单词
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // 创建变换序列长度变量
        int ladderLength = 1;

        // 进行广度优先搜索
        while (!queue.isEmpty()) {
            // 遍历当前层的所有单词
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                // 如果当前单词等于目标单词，返回变换序列长度
                if (currentWord.equals(endWord)) {
                    return ladderLength;
                }

                // 遍历当前单词的每个位置
                char[] wordArray = currentWord.toCharArray();
                for (int j = 0; j < wordArray.length; j++) {
                    char originalChar = wordArray[j];

                    // 尝试将当前位置的字符变换为其他字符
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordArray[j] = c;
                        String newWord = new String(wordArray);

                        // 如果变换后的单词存在于单词列表中，并且没有被访问过，则加入队列进行下一轮搜索
                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }

                    // 恢复当前位置的字符
                    wordArray[j] = originalChar;
                }
            }

            // 增加变换序列长度
            ladderLength++;
        }

        // 如果无法变换到目标单词，返回0
        return 0;
    }

    public static void main(String[] args) {
        // 测试用例1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int expected1 = 5;
        int result1 = ladderLength(beginWord1, endWord1, wordList1);
        System.out.println(result1 == expected1 ? "Test case 1 passed" : "Test case 1 failed");

        // 测试用例2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        int expected2 = 0;
        int result2 = ladderLength(beginWord2, endWord2, wordList2);
        System.out.println(result2 == expected2 ? "Test case 2 passed" : "Test case 2 failed");
    }
}