package com.geekbo.training.leetcode.top150;

import java.util.Stack;

public class ValidParentheses {
    /**
     *
     * 解题思路：
     *
     * 使用栈来维护括号的匹配关系。
     * 遍历输入字符串，当遇到开括号（'(', '{', '['）时，将其压入栈中。
     * 当遇到闭括号（')', '}', ']'）时，从栈中弹出栈顶元素，检查是否匹配。
     * 如果栈为空或者栈顶元素与当前闭括号不匹配，返回false。
     * 如果循环结束后，栈为空，说明所有括号都匹配成功，返回true。
     * 算法复杂度分析：
     *
     * 时间复杂度：O(n)，其中 n 是输入字符串的长度。
     * 空间复杂度：O(n)，最坏情况下，所有的字符都是开括号，需要存储在栈中。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false; // 如果栈为空，说明没有对应的开括号，直接返回false
                }

                char top = stack.pop(); // 弹出栈顶元素
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false; // 如果栈顶元素与当前字符不匹配，返回false
                }
            }
        }

        return stack.isEmpty(); // 如果栈为空，说明所有括号都匹配成功
    }

    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();
        String s1 = "()";
        System.out.println(solution.isValid(s1)); // 预期输出：true

        String s2 = "()[]{}";
        System.out.println(solution.isValid(s2)); // 预期输出：true

        String s3 = "(]";
        System.out.println(solution.isValid(s3)); // 预期输出：false
    }
}
