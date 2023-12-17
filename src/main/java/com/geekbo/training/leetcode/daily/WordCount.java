package com.geekbo.training.leetcode.daily;

/**
 *
 * 434. Number of Segments in a String
 * Easy
 * Given a string s, return the number of segments in the string.
 *
 * A segment is defined to be a contiguous sequence of non-space characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Hello, my name is John"
 * Output: 5
 * Explanation: The five segments are ["Hello,", "my", "name", "is", "John"]
 * Example 2:
 *
 * Input: s = "Hello"
 * Output: 1
 *
 */
public class WordCount {

    /**
     * 统计字符串中的单词个数
     *
     * @param str 字符串
     * @return 单词个数
     */
    public static int countSegments(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        // 初始化单词个数为0
        int count = 0;
        
        // 标识当前是否处于单词中
        boolean inWord = false;
        
        // 遍历字符串的每个字符
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                // 如果当前字符是空格，并且之前处于单词中，说明一个单词结束
                if (inWord) {
                    count++;
                    inWord = false;
                }
            } else {
                // 如果当前字符不是空格，并且之前不处于单词中，说明一个新的单词开始
                if (!inWord) {
                    inWord = true;
                }
            }
        }
        
        // 如果最后一个字符不是空格，并且之前处于单词中，则单词个数加1
        if (inWord) {
            count++;
        }
        
        return count;
    }

    public static void main(String[] args) {
        // 测试用例
        String str = "Hello, my name is John";
        int expected = 5;
        int result = countSegments(str);
        System.out.println("Input: \"" + str + "\"");
        System.out.println("Output: " + result);
        System.out.println("Expected: " + expected);
    }
}