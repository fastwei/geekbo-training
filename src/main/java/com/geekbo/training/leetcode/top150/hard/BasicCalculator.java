package com.geekbo.training.leetcode.top150.hard;

import java.util.Stack;

/**
 *
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 * 解题思路：
 *
 * 使用栈来处理表达式中的数字和运算符。
 * 遍历字符串，对于每个字符，根据其类型执行相应的操作。
 * 遇到数字时，将连续的数字字符转换为整数。
 * 遇到加号或减号时，更新结果并重置当前数字和符号。
 * 遇到左括号时，将当前结果和符号入栈，重置结果和符号。
 * 遇到右括号时，出栈计算括号内的表达式，与当前结果相加。
 * 算法复杂度：
 *
 * 时间复杂度：O(N)，其中N是字符串s的长度。
 * 空间复杂度：O(N)，最坏情况下需要存储N个数字和运算符。
 *
 */
public class BasicCalculator {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int num = 0;
        int sign = 1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+') {
                result += sign * num;
                num = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * num;
                num = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * num;
                num = 0;
                result *= stack.pop(); // 出栈操作，先乘以括号前的符号
                result += stack.pop(); // 再加上括号前的结果
            }
        }

        result += sign * num; // 处理最后一个数字
        return result;
    }

    public static void main(String[] args) {
        BasicCalculator calculator = new BasicCalculator();
        String s1 = "1 + 1";
        System.out.println("Example 1: " + calculator.calculate(s1)); // 输出：2

        String s2 = " 2-1 + 2 ";
        System.out.println("Example 2: " + calculator.calculate(s2)); // 输出：3

        String s3 = "(1+(4+5+2)-3)+(6+8)";
        System.out.println("Example 3: " + calculator.calculate(s3)); // 输出：23
    }
}
