package com.geekbo.training.leetcode.daily;

/**
 * 424. Longest Repeating Character Replacement
 * Medium
 * <p>
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 * <p>
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 * <p>
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 */
public class LongestRepeatingCharacterReplacement {
    /**
     * 思路是使用滑动窗口来找到最长的重复字符子串，然后根据k的值来确定是否可以通过改变字符来增加重复字符的数量。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是字符串s的长度。我们使用滑动窗口来遍历字符串s，每个字符都会被访问一次。
     * 空间复杂度：O(1)，我们使用了固定大小的数组来统计字符的出现次数。
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int[] count = new int[26]; // 统计每个字符的出现次数
        int maxCount = 0; // 最大出现次数的字符数量
        int start = 0; // 滑动窗口的起始位置
        int maxLength = 0; // 最长重复字符子串的长度

        for (int end = 0; end < s.length(); end++) {
            count[s.charAt(end) - 'A']++; // 统计当前字符出现次数
            maxCount = Math.max(maxCount, count[s.charAt(end) - 'A']); // 更新最大出现次数的字符数量

            // 判断当前滑动窗口的大小是否超过了可以改变的字符数量k
            // 如果超过了，则需要缩小窗口，直到满足条件
            if (end - start + 1 - maxCount > k) {
                count[s.charAt(start) - 'A']--; // 缩小窗口，更新字符出现次数
                start++; // 移动窗口的起始位置
            }

            maxLength = Math.max(maxLength, end - start + 1); // 更新最长重复字符子串的长度
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement solution = new LongestRepeatingCharacterReplacement();

        // 测试用例1
        String s1 = "ABAB";
        int k1 = 2;
        // 用两次操作可以将字符串变为"BBBB"，得到最长重复字符子串长度4
        int expected1 = 4;
        int result1 = solution.characterReplacement(s1, k1);
        System.out.println(result1 == expected1 ? "Test case 1 passed" : "Test case 1 failed");

        // 测试用例2
        String s2 = "AABABBA";
        int k2 = 1;
        // 用一次操作可以将字符串变为"AABBBBA"，得到最长重复字符子串长度4
        int expected2 = 4;
        int result2 = solution.characterReplacement(s2, k2);
        System.out.println(result2 == expected2 ? "Test case 2 passed" : "Test case 2 failed");
    }
}
