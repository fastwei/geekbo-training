package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. Different Ways to Add Parentheses
 * Given a string expression of numbers and operators,
 * return all possible results from computing all the different possible ways to group numbers and operators.
 * You may return the answer in any order.
 * <p>
 * The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: expression = "2-1-1"
 * Output: [0,2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 * <p>
 * Input: expression = "2*3-4*5"
 * Output: [-34,-14,-10,-10,10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
class DifferentWaysToAddParentheses {
    /**
     * 该代码使用递归的方式实现了将表达式按照不同的方式分组计算的功能。
     * 对于每个运算符，将表达式分割成左右两部分，并递归计算左右两部分的结果。
     * 然后将左右两部分的结果进行组合，根据运算符进行计算，并将计算结果添加到最终的结果列表中。
     * <p>
     * 如果表达式中没有运算符，说明表达式是一个数字，直接将其转换成整数并添加到结果列表中。
     * <p>
     * 该算法的时间复杂度取决于结果的数量，最坏情况下为 O(2^n)，其中 n 是表达式中运算符的数量。
     *
     * @param expression
     * @return
     */
    public static List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                String leftExpr = expression.substring(0, i);
                String rightExpr = expression.substring(i + 1);

                List<Integer> leftResults = diffWaysToCompute(leftExpr);
                List<Integer> rightResults = diffWaysToCompute(rightExpr);

                for (int left : leftResults) {
                    for (int right : rightResults) {
                        int value = 0;

                        switch (c) {
                            case '+':
                                value = left + right;
                                break;
                            case '-':
                                value = left - right;
                                break;
                            case '*':
                                value = left * right;
                                break;
                        }

                        result.add(value);
                    }
                }
            }
        }

        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }

        return result;
    }

    public static void main(String[] args) {
        // 测试用例1
        String expression1 = "2-1-1";
        System.out.println("测试用例1: " + diffWaysToCompute(expression1)); // 预期输出: [0, 2]

        // 测试用例2
        String expression2 = "2*3-4*5";
        System.out.println("测试用例2: " + diffWaysToCompute(expression2)); // 预期输出: [-34, -14, -10, -10, 10]
    }
}
