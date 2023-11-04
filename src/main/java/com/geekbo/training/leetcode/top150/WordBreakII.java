package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a dictionary of strings wordDict,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences in any order.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 * Example 2:
 * <p>
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 */
public class WordBreakII {

    /**
     * 解题思路： 我们可以使用回溯法和动态规划来解决这个问题。
     * <p>
     * 首先，我们将字典中的单词存储到哈希表中，以便快速查找。接下来，我们使用回溯法来拆
     * 分字符串。我们从字符串的开头开始，依次尝试在不同位置进行拆分。
     * 如果当前拆分的前缀存在于字典中，我们递归地拆分剩余部分的字符串，并将拆分结果与前缀拼接起来。
     * 最终，我们可以得到所有可能的句子。
     * <p>
     * 为了避免重复计算，我们使用一个memo哈希表来存储已经计算过的字符串的拆分结果。
     * 在每次递归调用之前，我们先检查memo中是否已经存储了当前字符串的拆分结果，如果有，则直接返回。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：在最坏情况下，我们需要计算所有可能的拆分结果。
     * 每个拆分位置都需要进行一次递归调用，而每次递归调用的时间复杂度为O(n)，其中n为字符串的长度。
     * 因此，总共的时间复杂度为O(n^3)，其中n为字符串的长度。
     * 空间复杂度：递归调用的最大深度为字符串的长度，因此，递归调用的空间复杂度为O(n)。
     * 此外，memo哈希表的大小为O(n)，用于存储已经计算过的字符串的拆分结果。因此，总共的空间复杂度为O(n)。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static List<String> wordBreak(String s, List<String> wordDict) {
        // 将wordDict中的单词存储到哈希表中，方便快速查找
        Map<String, Boolean> dict = new HashMap<>();
        for (String word : wordDict) {
            dict.put(word, true);
        }

        return wordBreakHelper(s, dict, new HashMap<>());
    }

    private static List<String> wordBreakHelper(String s, Map<String, Boolean> dict, Map<String, List<String>> memo) {
        // 如果memo中已经存储了当前字符串的拆分结果，则直接返回
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> result = new ArrayList<>();

        // 如果当前字符串本身就在字典中，则直接将其作为一个单词添加到结果中
        if (dict.containsKey(s)) {
            result.add(s);
        }

        // 尝试在不同位置进行字符串的拆分
        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i);
            // 如果前缀在字典中，则递归地拆分剩余部分的字符串
            if (dict.containsKey(prefix)) {
                List<String> subResult = wordBreakHelper(s.substring(i), dict, memo);
                // 将拆分结果与前缀拼接，并添加到最终结果中
                for (String sub : subResult) {
                    result.add(prefix + " " + sub);
                }
            }
        }

        // 将当前字符串的拆分结果存储到memo中
        memo.put(s, result);
        return result;
    }

    public static void main(String[] args) {
        // 测试用例
        String s1 = "catsanddog";
        List<String> wordDict1 = new ArrayList<>();
        wordDict1.add("cat");
        wordDict1.add("cats");
        wordDict1.add("and");
        wordDict1.add("sand");
        wordDict1.add("dog");
        // 预期输出: ["cats and dog","cat sand dog"]
        System.out.println(wordBreak(s1, wordDict1));

        String s2 = "pineapplepenapple";
        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("apple");
        wordDict2.add("pen");
        wordDict2.add("applepen");
        wordDict2.add("pine");
        wordDict2.add("pineapple");
        // 预期输出: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
        System.out.println(wordBreak(s2, wordDict2));

        String s3 = "catsandog";
        List<String> wordDict3 = new ArrayList<>();
        wordDict3.add("cats");
        wordDict3.add("dog");
        wordDict3.add("sand");
        wordDict3.add("and");
        wordDict3.add("cat");
        // 预期输出: []
        System.out.println(wordBreak(s3, wordDict3));
    }
}
