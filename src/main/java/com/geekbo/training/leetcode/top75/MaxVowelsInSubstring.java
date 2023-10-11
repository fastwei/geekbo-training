package com.geekbo.training.leetcode.top75;

/**
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 *
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 * Example 2:
 *
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 * Example 3:
 *
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 *
 * 给定一个字符串 s 和整数 k，返回 s 中长度为 k 的子串中最多包含元音字母的数量。
 * 英语中的元音字母包括 'a'、'e'、'i'、'o' 和 'u'。
 *
 * 解题思路：
 *
 * 使用滑动窗口遍历字符串s，初始化窗口为前k个字符。
 * 统计窗口内元音字母的数量，并记录为maxVowels。
 * 滑动窗口遍历整个字符串，每次移动窗口时，更新窗口内元音字母的数量。
 * 在遍历过程中，不断更新maxVowels为当前窗口内元音字母数量的最大值。
 * 返回maxVowels作为结果。
 * 算法复杂度：
 *
 * 时间复杂度：O(N)，其中N是字符串s的长度。
 * 空间复杂度：O(1)，常数额外空间用于存储元音字母和窗口内元音数量。
 *
 */
public class MaxVowelsInSubstring {
    public int maxVowels(String s, int k) {
        int maxVowels = 0; // 存储最大元音数量
        int currentVowels = 0; // 当前窗口内的元音数量
        String vowels = "aeiou"; // 元音字母

        // 初始化滑动窗口
        for (int i = 0; i < k; i++) {
            if (vowels.indexOf(s.charAt(i)) != -1) {
                currentVowels++;
            }
        }
        maxVowels = currentVowels;

        // 滑动窗口遍历字符串
        for (int i = k; i < s.length(); i++) {
            // 移除窗口左侧的字符
            if (vowels.indexOf(s.charAt(i - k)) != -1) {
                currentVowels--;
            }
            // 添加窗口右侧的字符
            if (vowels.indexOf(s.charAt(i)) != -1) {
                currentVowels++;
            }
            // 更新最大元音数量
            maxVowels = Math.max(maxVowels, currentVowels);
        }

        return maxVowels;
    }

    public static void main(String[] args) {
        MaxVowelsInSubstring solution = new MaxVowelsInSubstring();
        // 测试用例
        System.out.println(solution.maxVowels("abciiidef", 3)); // 输出：3
        System.out.println(solution.maxVowels("aeiou", 2)); // 输出：2
        System.out.println(solution.maxVowels("leetcode", 3)); // 输出：2
    }
}
