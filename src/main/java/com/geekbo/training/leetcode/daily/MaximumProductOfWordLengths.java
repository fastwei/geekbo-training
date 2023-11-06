package com.geekbo.training.leetcode.daily;

/**
 * 318. Maximum Product of Word Lengths
 * Medium
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j])
 * where the two words do not share common letters. If no such two words exist, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 * Example 2:
 * <p>
 * Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4
 * Explanation: The two words can be "ab", "cd".
 * Example 3:
 * <p>
 * Input: words = ["a","aa","aaa","aaaa"]
 * Output: 0
 * Explanation: No such pair of words.
 */
public class MaximumProductOfWordLengths {
    /**
     * 计算两个不共享字母的单词的最大长度乘积
     * 解题思路：
     * <p>
     * 首先，我们需要将每个单词转换成一个只包含小写字母的字母掩码，用于判断两个单词是否共享字母。
     * 我们可以使用一个32位整数来表示字母掩码，其中每一位表示一个小写字母的存在与否。
     * 然后，我们遍历所有的单词对，计算它们的长度乘积。
     * 如果两个单词的字母掩码没有公共的1，则它们不共享字母，我们可以计算它们的长度乘积并更新最大值。
     * 最后，返回最大长度乘积。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历所有单词并计算字母掩码的时间复杂度为 O(n*k)，其中 n 是单词的数量，k 是每个单词的平均长度。
     * 遍历所有单词对并计算长度乘积的时间复杂度为 O(n^2*k)，其中 n 是单词的数量，k 是每个单词的平均长度。
     * 总的时间复杂度为 O(n^2*k)。
     * <p>
     * 字母掩码的空间复杂度为 O(n)，用于存储每个单词的字母掩码。
     * 最大长度乘积的空间复杂度为 O(1)，只需存储一个变量。
     *
     * @param words 单词数组
     * @return 最大长度乘积
     */
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n]; // 存储每个单词的字母掩码

        // 计算每个单词的字母掩码
        for (int i = 0; i < n; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                masks[i] |= 1 << (c - 'a');
            }
        }

        int maxProduct = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 如果两个单词的字母掩码没有公共的1，则它们不共享字母
                if ((masks[i] & masks[j]) == 0) {
                    int product = words[i].length() * words[j].length();
                    maxProduct = Math.max(maxProduct, product);
                }
            }
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        // 创建解法对象
        MaximumProductOfWordLengths maximumProductOfWordLengths = new MaximumProductOfWordLengths();

        // 测试用例1
        // 预期输出: 16
        String[] words1 = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        int result1 = maximumProductOfWordLengths.maxProduct(words1);
        System.out.println(result1);

        // 测试用例2
        // 预期输出: 4
        String[] words2 = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        int result2 = maximumProductOfWordLengths.maxProduct(words2);
        System.out.println(result2);

        // 测试用例3
        // 预期输出: 0
        String[] words3 = {"a", "aa", "aaa", "aaaa"};
        int result3 = maximumProductOfWordLengths.maxProduct(words3);
        System.out.println(result3);
    }
}
