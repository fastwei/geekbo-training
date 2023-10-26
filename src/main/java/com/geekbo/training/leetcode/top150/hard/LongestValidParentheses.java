package com.geekbo.training.leetcode.top150.hard;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')',
 * return the length of the longest valid (well-formed) parentheses
 * substring
 * .
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 * <p>
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 * <p>
 * Input: s = ""
 * Output: 0
 */
public class LongestValidParentheses {

    /**
     * 解题思路： 我们可以使用栈来解决这个问题。
     * 首先，我们创建一个栈，用于存储括号的索引。
     * 然后，我们遍历字符串中的每个字符，对于每个字符，我们进行以下操作：
     * <p>
     * 如果当前字符是左括号，将其索引压入栈中。
     * 如果当前字符是右括号：
     * 如果栈为空，表示当前右括号是一个无效括号，更新最后一个无效括号的右边界索引为当前索引。
     * 如果栈不为空，弹出栈顶元素，表示匹配到一个有效括号。
     * 然后，我们更新最长有效括号的长度为当前索引减去栈顶元素的索引，
     * 或者当前索引减去最后一个无效括号的右边界索引，取两者中的较大值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是字符串的长度。我们需要遍历字符串一次。
     * 空间复杂度：O(n)，我们使用了一个栈来存储括号的索引，最坏情况下，栈的大小为n。
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        int n = s.length();

        // 创建一个栈，用于存储括号的索引
        Stack<Integer> stack = new Stack<>();

        // 初始化最长有效括号的长度为0
        int maxLen = 0;

        // 初始化最后一个无效括号的右边界索引为-1
        int lastInvalidIndex = -1;

        // 遍历字符串中的每个字符
        for (int i = 0; i < n; i++) {
            // 如果当前字符是左括号，将其索引压入栈中
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // 如果当前字符是右括号
                if (stack.isEmpty()) {
                    // 如果栈为空，表示当前右括号是一个无效括号，更新最后一个无效括号的右边界索引为当前索引
                    lastInvalidIndex = i;
                } else {
                    // 如果栈不为空，弹出栈顶元素，表示匹配到一个有效括号
                    stack.pop();
                    // 更新最长有效括号的长度为当前索引减去最后一个无效括号的右边界索引
                    maxLen = Math.max(maxLen, i - (stack.isEmpty() ? lastInvalidIndex : stack.peek()));
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // Test Case 1
        String s = "(()";
        // 预期输出为2
        System.out.println(longestValidParentheses(s));

        // Test Case 2
        s = ")()())";
        // 预期输出为4
        System.out.println(longestValidParentheses(s));

        // Test Case 3
        s = "";
        // 预期输出为0
        System.out.println(longestValidParentheses(s));
    }
}


