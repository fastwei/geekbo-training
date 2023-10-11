package com.geekbo.training.leetcode.top75;

import java.util.Stack;

/**
 *
 * You are given a string s, which contains stars *.
 *
 * In one operation, you can:
 *
 * Choose a star in s.
 * Remove the closest non-star character to its left, as well as remove the star itself.
 * Return the string after all stars have been removed.
 *
 * Note:
 *
 * The input will be generated such that the operation is always possible.
 * It can be shown that the resulting string will always be unique.
 *
 *
 * Example 1:
 *
 * Input: s = "leet**cod*e"
 * Output: "lecoe"
 * Explanation: Performing the removals from left to right:
 * - The closest character to the 1st star is 't' in "leet**cod*e". s becomes "lee*cod*e".
 * - The closest character to the 2nd star is 'e' in "lee*cod*e". s becomes "lecod*e".
 * - The closest character to the 3rd star is 'd' in "lecod*e". s becomes "lecoe".
 * There are no more stars, so we return "lecoe".
 * Example 2:
 *
 * Input: s = "erase*****"
 * Output: ""
 * Explanation: The entire string is removed, so we return an empty string.
 *
 *
 *使用栈来实现这个问题可以简化操作。
 *
 * 解题思路：
 *
 * 创建一个字符栈 stack 用于存储字符。
 * 遍历输入字符串 s 中的每个字符。
 * 如果当前字符是星号 *，并且栈不为空，弹出栈顶元素（即最近的非星号字符）。
 * 如果当前字符不是星号，将其推入栈中。
 * 返回栈中的元素组成的字符串。
 * 算法复杂度：
 *
 * 时间复杂度：O(n)，其中 n 是输入字符串的长度。我们只需一次遍历输入字符串。
 * 空间复杂度：O(n)，因为我们使用了字符栈，其最大长度可能等于输入字符串的长度。
 *
 */
public class RemovingStarsFromString {
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '*' && !stack.isEmpty()) {
                stack.pop(); // 弹出栈顶元素，即最近的非星号字符
            } else if (c != '*') {
                stack.push(c); // 推入栈
            }
        }

        // 构造结果字符串
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop()); // 从栈中弹出字符，注意要逆序插入结果字符串
        }

        return result.toString();
    }

    public static void main(String[] args) {
        RemovingStarsFromString removingStarsFromString = new RemovingStarsFromString();

        // 测试用例1
        String input1 = "leet**cod*e";
        String output1 = removingStarsFromString.removeStars(input1);
        System.out.println(output1); // 应该输出 "lecoe"

        // 测试用例2
        String input2 = "erase*****";
        String output2 = removingStarsFromString.removeStars(input2);
        System.out.println(output2); // 应该输出 ""

        // 更多测试用例
        // 测试用例3：包含多个星号和非星号字符
        String input3 = "abc*def**ghi";
        String output3 = removingStarsFromString.removeStars(input3);
        System.out.println(output3); // 应该输出 "abcdefghi"

        // 测试用例4：星号在开头和结尾
        String input4 = "***start*end***";
        String output4 = removingStarsFromString.removeStars(input4);
        System.out.println(output4); // 应该输出 "start*end"
    }
}
