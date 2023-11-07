package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
 * Hard
 * Topics
 * Companies
 * Hint
 * Given a string num that contains only digits and an integer target,
 * return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num
 * so that the resultant expression evaluates to the target value.
 * <p>
 * Note that operands in the returned expressions should not contain leading zeros.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 * Example 2:
 * <p>
 * Input: num = "232", target = 8
 * Output: ["2*3+2","2+3*2"]
 * Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
 * Example 3:
 * <p>
 * Input: num = "3456237490", target = 9191
 * Output: []
 * Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
 */
public class ExpressionAddOperators {
    /**
     * 解题思路：
     * <p>
     * 使用回溯算法，尝试所有可能的操作符和数字的组合。
     * 在每一步中，我们有以下选择：
     * <p>
     * 将下一个数字添加到当前数字的右侧，即连续的数字组合。
     * 将当前数字和下一个数字组合成一个新的数字，即乘法操作。
     * 我们需要注意以下几点：
     * <p>
     * 避免在数字前面添加前导零。
     * 需要记录当前表达式的值，以便在达到目标值时进行比较。
     * 对于乘法操作，需要特殊处理，因为乘法优先级高于加法和减法。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N * 4^N)，其中 N 是字符串 num 的长度。
     * 对于每个数字，我们可以选择 4 种操作符：加法、减法、乘法或者不添加操作符，所以总共有 4^N 种组合。
     * 在每一步，我们都需要 O(N) 的时间来生成新的表达式。
     * 空间复杂度：O(N)，存储结果的空间。
     *
     * @param num
     * @param target
     * @return
     */
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(num, target, 0, 0, 0, "", result);
        return result;
    }

    private void backtrack(String num, int target, int index, long curr, long prev, String expr, List<String> result) {
        if (index == num.length()) {
            if (target == curr) {
                result.add(expr);
            }
            return;
        }

        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') {
                break; // Skip leading zeros
            }

            long value = Long.parseLong(num.substring(index, i + 1));

            if (index == 0) {
                backtrack(num, target, i + 1, value, value, expr + value, result);
            } else {
                // Addition
                backtrack(num, target, i + 1, curr + value, value, expr + "+" + value, result);
                // Subtraction
                backtrack(num, target, i + 1, curr - value, -value, expr + "-" + value, result);
                // Multiplication
                backtrack(num, target, i + 1, curr - prev + prev * value, prev * value, expr + "*" + value, result);
            }
        }
    }

    public static void main(String[] args) {
        ExpressionAddOperators solution = new ExpressionAddOperators();

        // 测试用例1
        String num1 = "123";
        int target1 = 6;
        List<String> expected1 = new ArrayList<>();
        expected1.add("1*2*3");
        expected1.add("1+2+3");
        List<String> result1 = solution.addOperators(num1, target1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        String num2 = "232";
        int target2 = 8;
        List<String> expected2 = new ArrayList<>();
        expected2.add("2*3+2");
        expected2.add("2+3*2");
        List<String> result2 = solution.addOperators(num2, target2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);

        // 测试用例3
        String num3 = "3456237490";
        int target3 = 9191;
        List<String> expected3 = new ArrayList<>();
        List<String> result3 = solution.addOperators(num3, target3);
        System.out.println("测试用例3:");
        System.out.println("预期输出: " + expected3);
        System.out.println("实际输出: " + result3);
    }
}
