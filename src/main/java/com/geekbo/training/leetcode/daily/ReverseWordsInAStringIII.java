package com.geekbo.training.leetcode.daily;

/**
 * Given a string s, reverse the order of characters in each word within a sentence
 * while still preserving whitespace and initial word order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Example 2:
 * <p>
 * Input: s = "God Ding"
 * Output: "doG gniD"
 */
public class ReverseWordsInAStringIII {
    /**
     * 解题思路：
     * 遍历字符串s，对于每个单词，使用双指针技巧将其进行翻转。具体步骤如下：
     * 1. 定义一个StringBuilder，用于存储最终结果。
     * 2. 定义两个指针start和end，初始值都为0。
     * 3. 遍历字符串s，如果当前字符不是空格，则将end指针向后移动，直到遇到空格或到达字符串的末尾。
     * 4. 在遍历过程中，如果遇到空格，则将[start, end]之间的字符进行翻转，并追加到StringBuilder中，同时追加一个空格。
     * 5. 如果end指针到达字符串的末尾，则将[start, end]之间的字符进行翻转，并追加到StringBuilder中，不追加空格。
     * 6. 最后，将StringBuilder转换为字符串并返回结果。
     * <p>
     * 算法的时间复杂度为O(n)，其中n为字符串s的长度。
     * 算法的空间复杂度为O(n)，使用了一个StringBuilder来存储结果。
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = 0;

        while (end < s.length()) {
            if (s.charAt(end) == ' ') {
                // 遇到空格，将[start, end)之间的字符进行翻转，并追加到StringBuilder中
                for (int i = end - 1; i >= start; i--) {
                    sb.append(s.charAt(i));
                }
                sb.append(' ');
                start = end + 1;
            }
            end++;
        }

        // 处理最后一个单词
        for (int i = end - 1; i >= start; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "Let's take LeetCode contest";
        // 预期输出为 "s'teL ekat edoCteeL tsetnoc"
        System.out.println(reverseWords(s1));

        String s2 = "God Ding";
        // 预期输出为 "doG gniD"
        System.out.println(reverseWords(s2));
    }
}