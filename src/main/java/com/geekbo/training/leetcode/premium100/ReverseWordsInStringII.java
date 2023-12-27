package com.geekbo.training.leetcode.premium100;

import java.util.Arrays;

/**
 * 186. 反转字符串中的单词 II
 * 中等
 * 给你一个字符数组 s ，反转其中 单词 的顺序。
 * <p>
 * 单词 的定义为：单词是一个由非空格字符组成的序列。s 中的单词将会由单个空格分隔。
 * <p>
 * 必须设计并实现 原地 解法来解决此问题，即不分配额外的空间。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * 输出：["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * 示例 2：
 * <p>
 * 输入：s = ["a"]
 * 输出：["a"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 105
 * s[i] 可以是一个英文字母（大写或小写）、数字、或是空格 ' ' 。
 * s 中至少存在一个单词
 * s 不含前导或尾随空格
 * 题目数据保证：s 中的每个单词都由单个空格分隔
 */
public class ReverseWordsInStringII {
    /**
     * 解题思路：
     * <p>
     * 首先，将整个字符串进行反转，即将整个字符数组进行反转；
     * 然后，依次找到每个单词的起始位置和结束位置，并将其反转；
     * 最后得到的字符数组即为反转后的结果。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中 n 为字符数组的长度。
     * 空间复杂度：O(1)。
     *
     * @param s
     */
    public static void reverseWords(char[] s) {
        // 先整体反转字符串
        reverse(s, 0, s.length - 1);
        int start = 0;
        int end = 0;
        while (end < s.length) {
            // 找到单词的结束位置
            while (end < s.length && s[end] != ' ') {
                end++;
            }
            // 反转单词
            reverse(s, start, end - 1);
            // 更新单词的起始位置
            start = end + 1;
            end = start;
        }
    }

    // 反转字符串的指定区间[start, end]
    private static void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        // 测试用例
        char[] s1 = {'t', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e'};
        reverseWords(s1);
        System.out.println(Arrays.toString(s1));

        char[] s2 = {'a'};
        reverseWords(s2);
        System.out.println(Arrays.toString(s2));
    }
}
