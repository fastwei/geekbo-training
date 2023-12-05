package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 2085. Count Common Words With One Occurrence
 * Easy
 * <p>
 * Given two string arrays words1 and words2, return the number of strings that appear exactly once in each of the two arrays.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
 * Output: 2
 * Explanation:
 * - "leetcode" appears exactly once in each of the two arrays. We count this string.
 * - "amazing" appears exactly once in each of the two arrays. We count this string.
 * - "is" appears in each of the two arrays, but there are 2 occurrences of it in words1. We do not count this string.
 * - "as" appears once in words1, but does not appear in words2. We do not count this string.
 * Thus, there are 2 strings that appear exactly once in each of the two arrays.
 * Example 2:
 * <p>
 * Input: words1 = ["b","bb","bbb"], words2 = ["a","aa","aaa"]
 * Output: 0
 * Explanation: There are no strings that appear in each of the two arrays.
 * Example 3:
 * <p>
 * Input: words1 = ["a","ab"], words2 = ["a","a","a","ab"]
 * Output: 1
 * Explanation: The only string that appears exactly once in each of the two arrays is "ab".
 */
class CountCommonWordsWithOneOccurrence {
    public static int countWords(String[] words1, String[] words2) {
        HashMap<String,Integer> hm1 = new HashMap<>();
        HashMap<String,Integer> hm2 = new HashMap<>();
        int numOfWords = 0;

        for(int i=0;i<words1.length;i++) {
            if(hm1.containsKey(words1[i])) {
                hm1.put(words1[i],hm1.get(words1[i])+1);
            } else {
                hm1.put(words1[i],1);
            }
        }

        for(int i=0;i<words2.length;i++) {
            if(hm2.containsKey(words2[i])) {
                hm2.put(words2[i],hm2.get(words2[i])+1);
            } else {
                hm2.put(words2[i],1);
            }
        }

        for(String s : hm1.keySet()) {
            if(hm2.containsKey(s) && hm2.get(s) == 1 && hm1.get(s) == 1) {
                numOfWords++;
            }
        }

        return numOfWords;
    }

    public static void main(String[] args) {
        // 测试用例1
        String[] words1 = {"leetcode", "is", "amazing", "as", "is"};
        String[] words2 = {"amazing", "leetcode", "is"};
        // 预期输出：2
        System.out.println(countWords(words1, words2));

        // 测试用例2
        String[] words3 = {"b", "bb", "bbb"};
        String[] words4 = {"a", "aa", "aaa"};
        // 预期输出：0
        System.out.println(countWords(words3, words4));

        // 测试用例3
        String[] words5 = {"a", "ab"};
        String[] words6 = {"a", "a", "a", "ab"};
        // 预期输出：1
        System.out.println(countWords(words5, words6));
    }
}

