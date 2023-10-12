package com.geekbo.training.leetcode.top75;

import java.util.ArrayList;
import java.util.List;

/**
 * 22.Generate Parentheses
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 8
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 使用递归生成括号的组合。
 * 在递归过程中，维护当前生成的括号组合current，以及已经使用的左括号数量open和右括号数量close。
 * 当current的长度等于2n时，表示已生成一个合法的括号组合，将其添加到结果列表。
 * 递归调用分为两种情况：添加左括号"("或添加右括号")"，但需满足条件：open小于n且close小于open。
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(2^n)，最坏情况下，需要生成所有可能的2^n个括号组合。
 * 空间复杂度：O(n)，递归调用栈的深度最多为n。
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    private void generate(List<String> result, String current, int open, int close, int n) {
        if (current.length() == n * 2) {
            result.add(current);
            return;
        }

        if (open < n) {
            generate(result, current + "(", open + 1, close, n);
        }

        if (close < open) {
            generate(result, current + ")", open, close + 1, n);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses solution = new GenerateParentheses();

        // 测试用例1
        int n1 = 3;
        List<String> result1 = solution.generateParenthesis(n1);
        System.out.println(result1); // 预期输出: ["((()))","(()())","(())()","()(())","()()()"]

        // 测试用例2
        int n2 = 1;
        List<String> result2 = solution.generateParenthesis(n2);
        System.out.println(result2); // 预期输出: ["()"]
    }
}
