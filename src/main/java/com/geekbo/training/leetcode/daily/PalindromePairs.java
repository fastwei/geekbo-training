package com.geekbo.training.leetcode.daily;


import java.util.*;

/**
 *
 * 336. Palindrome Pairs
 * Hard
 * You are given a 0-indexed array of unique strings words.
 *
 * A palindrome pair is a pair of integers (i, j) such that:
 *
 * 0 <= i, j < words.length,
 * i != j, and
 * words[i] + words[j] (the concatenation of the two strings) is a
 * palindrome
 * .
 * Return an array of all the palindrome pairs of words.
 *
 * You must write an algorithm with O(sum of words[i].length) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["abcddcba","dcbaabcd","slls","llssssll"]
 * Example 2:
 *
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * Example 3:
 *
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["a","a"]
 */
public class PalindromePairs {

    /**
     * 解题思路：
     * 我们首先创建一个哈希表，用于存储每个单词的反转形式以及其在数组中的索引。
     * 然后，对于数组中的每个单词，我们可以通过迭代其字符来检查它是否具有回文前缀或后缀。
     * 如果我们找到一个回文前缀或后缀，我们可以检查剩余部分的单词（不包括前缀或后缀）是否存在于哈希表中。
     * 如果存在，那么我们找到了一个回文对。
     * <p>
     * 算法复杂度的分析：
     * <p>
     * 首先，我们需要遍历整个数组来创建哈希表，这需要 O (n) 的时间复杂度，其中 n 是数组中单词的总数。
     * 然后，对于每个单词，我们需要检查它的后缀和前缀是否是回文，并且在哈希表中查找是否存在对应的单词。
     * 这个过程的时间复杂度是 O (m ^ 2)，其中 m 是单词的平均长度。
     * 最后，我们需要将找到的回文对添加到结果列表中，这需要 O (1) 的时间复杂度。
     * 因此，总的时间复杂度是 O (n * m ^ 2)。
     * 在空间复杂度方面，我们需要使用一个哈希表来存储单词的反转形式和索引，这需要 O (n * m) 的空间复杂度，其中 n 是数组中单词的总数，m 是单词的平均长度。
     * <p>
     * 综上所述，该算法的时间复杂度是 O (n * m ^ 2)，空间复杂度是 O (n * m)。
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new ArrayList<>();

        // 创建一个哈希表，用于存储每个单词的反转形式以及其在数组中的索引
        Map<String, Integer> reverseMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            reverseMap.put(new StringBuilder(words[i]).reverse().toString(), i);
        }

        // 遍历数组中的每个单词
        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // 检查单词的反转形式是否存在于哈希表中
            if (reverseMap.containsKey(word) && reverseMap.get(word) != i) {
                pairs.add(Arrays.asList(i, reverseMap.get(word)));
            }

            // 检查后缀回文
            for (int j = 1; j <= word.length(); j++) {
                String suffix = word.substring(word.length() - j);
                if (isPalindrome(suffix) && reverseMap.containsKey(word.substring(0, word.length() - j))) {
                    pairs.add(Arrays.asList(i, reverseMap.get(word.substring(0, word.length() - j))));
                }
            }

            // 检查前缀回文
            for (int j = 1; j <= word.length(); j++) {
                String prefix = word.substring(0, j);
                if (isPalindrome(prefix) && reverseMap.containsKey(word.substring(j))) {
                    pairs.add(Arrays.asList(reverseMap.get(word.substring(j)), i));
                }
            }
        }

        return pairs;
    }

    // 辅助方法，用于检查字符串是否为回文
    private boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        // 测试用例1
        String[] words1 = {"abcd", "dcba", "lls", "s", "sssll"};
        PalindromePairs solution = new PalindromePairs();
        List<List<Integer>> result1 = solution.palindromePairs(words1);
        System.out.println(result1); // 输出: [[0,1],[1,0],[3,2],[2,4]]

        // 测试用例2
        String[] words2 = {"bat", "tab", "cat"};
        List<List<Integer>> result2 = solution.palindromePairs(words2);
        System.out.println(result2); // 输出: [[0,1],[

    }
}


