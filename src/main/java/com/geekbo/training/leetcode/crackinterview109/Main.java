package com.geekbo.training.leetcode.crackinterview109;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 16.02. 单词频率
 * 中等
 * 设计一个方法，找出任意指定单词在一本书中的出现频率。
 * <p>
 * 你的实现应该支持如下操作：
 * <p>
 * WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
 * get(word)查询指定单词在书中出现的频率
 * 示例：
 * <p>
 * WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
 * wordsFrequency.get("you"); //返回0，"you"没有出现过
 * wordsFrequency.get("have"); //返回2，"have"出现2次
 * wordsFrequency.get("an"); //返回1
 * wordsFrequency.get("apple"); //返回1
 * wordsFrequency.get("pen"); //返回1
 * 提示：
 * <p>
 * book[i]中只包含小写字母
 * 1 <= book.length <= 100000
 * 1 <= book[i].length <= 10
 * get函数的调用次数不会超过100000
 *
 * 解题思路：通过构造函数将书中的单词及其出现频率存储在一个HashMap中。
 *         查询单词的频率时，直接从HashMap中获取即可。
 *
 * 算法复杂度分析：
 *
 * 构造函数的时间复杂度为O(n)，其中n是书中单词的总数。
 * get方法的时间复杂度为O(1)，因为HashMap的查找操作的时间复杂度为O(1)。
 * 因此，该解法的时间复杂度为O(n)，空间复杂度为O(n)。
 */
class WordsFrequency {
    private Map<String, Integer> frequencyMap;

    public WordsFrequency(String[] book) {
        frequencyMap = new HashMap<>();
        for (String word : book) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }
    }

    public int get(String word) {
        return frequencyMap.getOrDefault(word, 0);
    }

    public static void main(String[] args) {
        String[] book = {"i", "have", "an", "apple", "he", "have", "a", "pen"};
        WordsFrequency wordsFrequency = new WordsFrequency(book);
        System.out.println(wordsFrequency.get("you"));   // 0
        System.out.println(wordsFrequency.get("have"));  // 2
        System.out.println(wordsFrequency.get("an"));    // 1
        System.out.println(wordsFrequency.get("apple")); // 1
        System.out.println(wordsFrequency.get("pen"));   // 1
    }
}
