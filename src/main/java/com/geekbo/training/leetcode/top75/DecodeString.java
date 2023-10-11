package com.geekbo.training.leetcode.top75;

import java.util.Stack;

/**
 * Given an encoded string, return its decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 *
 * The test cases are generated so that the length of the output will never exceed 105.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 *
 * 解题思路：
 *
 * 使用两个栈，一个用于存储数字，另一个用于存储字符串。
 * 遍历输入字符串，根据字符的不同情况进行处理：
 * 如果是数字，将数字累积到num中。
 * 如果是左括号[，将当前数字入数字栈，当前字符串入字符串栈，并重置num和currentStr。
 * 如果是右括号]，从数字栈中弹出重复次数，然后将当前字符串重复相应次数，并与弹出的字符串相加。
 * 如果是字母，直接追加到currentStr中。
 * 最后，currentStr中存储的就是解码后的字符串。
 * 算法复杂度分析：
 *
 * 时间复杂度：遍历输入字符串一次，时间复杂度为O(n)，其中n是字符串长度。
 * 空间复杂度：使用两个栈，空间复杂度为O(n)。
 *
 */
public class DecodeString {

    /**
     * 解码给定的字符串。
     *
     * @param s 给定的编码字符串
     * @return 解码后的字符串
     */
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();   // 用于存储数字的栈
        Stack<StringBuilder> strStack = new Stack<>();  // 用于存储字符串的栈
        StringBuilder currentStr = new StringBuilder();  // 当前正在构建的字符串
        int num = 0;  // 用于存储累积的数字

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');  // 累积数字
            } else if (c == '[') {
                numStack.push(num);  // 将数字入数字栈
                strStack.push(currentStr);  // 将当前字符串入字符串栈
                num = 0;  // 重置数字
                currentStr = new StringBuilder();  // 重置当前字符串
            } else if (c == ']') {
                int repeatTimes = numStack.pop();  // 弹出重复次数
                StringBuilder repeatedStr = new StringBuilder();
                for (int i = 0; i < repeatTimes; i++) {
                    repeatedStr.append(currentStr);  // 将当前字符串重复相应次数
                }
                currentStr = strStack.pop().append(repeatedStr);  // 与弹出的字符串相加
            } else {
                currentStr.append(c);  // 如果是字母，直接追加到当前字符串
            }
        }

        return currentStr.toString();  // 返回解码后的字符串
    }

    public static void main(String[] args) {
        DecodeString decoder = new DecodeString();
        // 测试用例
        System.out.println(decoder.decodeString("3[a]2[bc]")); // 输出 "aaabcbc"
        System.out.println(decoder.decodeString("3[a2[c]]")); // 输出 "accaccacc"
        System.out.println(decoder.decodeString("2[abc]3[cd]ef")); // 输出 "abcabccdcdcdef"
    }
}
