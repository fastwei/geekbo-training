package com.geekbo.training.leetcode.top150;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 */
public class WordBreak {
    public static void main(String[] args) {
        // 测试用例1
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        System.out.println(wordBreak(s1, wordDict1)); // 预期输出：true

        // 测试用例2
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        System.out.println(wordBreak(s2, wordDict2)); // 预期输出：true

        // 测试用例3
        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak(s3, wordDict3)); // 预期输出：false
    }

    /**
     * 判断字符串s是否可以被分割为一个或多个字典中的单词
     * <p>
     * 解题思路： 要判断字符串s是否能够被分割为一个或多个字典中的单词，可以使用动态规划的方法。
     * <p>
     * 定义一个布尔数组dp，其中dp[i]表示字符串s的前i个字符是否可以被分割为一个或多个字典中的单词。
     * <p>
     * 动态规划的状态转移方程如下： dp[i] = dp[j] && wordSet.contains(s.substring(j, i))
     * 其中，j的范围为[0, i)，表示字符串s的前j个字符是否可以被分割为一个或多个字典中的单词。
     * <p>
     * 遍历字符串s的每个字符，计算dp数组的值，最终返回dp[n]的结果，其中n为字符串s的长度。
     * <p>
     * 算法复杂度分析： 遍历字符串s的每个字符，时间复杂度为O(n)，其中n为字符串s的长度。
     * 使用了一个布尔数组dp，空间复杂度为O(n)。
     *
     * @param s        要判断的字符串
     * @param wordDict 字典
     * @return 如果可以被分割为一个或多个字典中的单词，则返回true，否则返回false
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
