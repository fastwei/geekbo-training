package com.geekbo.training.leetcode.top75;

/**
 *
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 * Example 2:
 *
 * Input: s = "God Ding"
 * Output: "doG gniD"
 *
 *
 */
public class ReverseWordsInStringIII {
    public static String reverseWords(String s) {
        // 将字符串拆分为单词数组
        String[] words = s.split(" ");

        // 遍历每个单词，反转其中的字符
        for (int i = 0; i < words.length; i++) {
            words[i] = reverseWord(words[i]);
        }

        // 重新构建反转后的字符串
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            result.append(word).append(" ");
        }

        // 移除最后一个空格并返回结果
        return result.toString().trim();
    }

    // 反转单个单词中的字符
    private static String reverseWord(String word) {
        char[] chars = word.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        // 测试用例1
        String s1 = "Let's take LeetCode contest";
        String result1 = reverseWords(s1);
        System.out.println("Example 1: Output: " + result1); // 预期输出：s'teL ekat edoCteeL tsetnoc

        // 测试用例2
        String s2 = "God Ding";
        String result2 = reverseWords(s2);
        System.out.println("Example 2: Output: " + result2); // 预期输出：doG gniD
    }
}
