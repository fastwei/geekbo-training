package com.geekbo.training.leetcode.top150;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 *
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 */
public class EvaluateReversePolishNotation {

    /**
     * 该代码使用了一个栈来模拟计算逆波兰表达式。
     * 遍历输入的tokens数组，如果遇到操作符，则从栈中弹出两个操作数进行计算，并将计算结果压入栈中；
     * 如果遇到操作数，则将其转换为整数并压入栈中。最后，栈中剩下的唯一元素即为表达式的计算结果。
     * <p>
     * 请注意，为了判断一个字符串是否为操作符，我们定义了一个辅助函数isOperator。
     * 该函数返回true如果字符串是"+", "-", "*", 或者 "/"，否则返回false。
     * <p>
     * 该算法的时间复杂度为O(n)，其中n是tokens数组的长度。
     *
     * @param tokens
     * @return
     */
    public int evaluateRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int b = stack.pop();
                int a = stack.pop();

                if (token.equals("+")) {
                    stack.push(a + b);
                } else if (token.equals("-")) {
                    stack.push(a - b);
                } else if (token.equals("*")) {
                    stack.push(a * b);
                } else if (token.equals("/")) {
                    stack.push(a / b);
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation evaluateReversePolishNotation = new EvaluateReversePolishNotation();

        // 测试样例
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println(evaluateReversePolishNotation.evaluateRPN(tokens1));  // 输出: 9

        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println(evaluateReversePolishNotation.evaluateRPN(tokens2));  // 输出: 6

        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evaluateReversePolishNotation.evaluateRPN(tokens3));  // 输出: 22
    }
}
