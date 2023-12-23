package com.geekbo.training.leetcode.crackinterview109;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.09. 括号
 * 中等
 * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class Parentheses {
    /**
     * 生成n对括号的所有合法组合
     *
     * @param n 括号对数
     * @return 所有合法组合的列表
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    /**
     * 回溯法生成括号组合
     * 解题思路： 这道题可以使用回溯法来解决。我们从空字符串开始，每次可以选择放置一个左括号或者右括号。
     * 但是放置括号时需要满足以下条件：
     * <p>
     * 左括号的数量不能超过n；
     * 右括号的数量不能超过左括号的数量。
     * 通过回溯法，我们可以生成所有合法的括号组合。
     * <p>
     * 算法复杂度分析： 假设n表示括号对数，生成的所有合法组合的数量是Catalan数，即第n个Catalan数，记作C(n)。
     * 时间复杂度为O(C(n))，空间复杂度为O(C(n))。
     *
     * @param result     结果列表
     * @param current    当前组合
     * @param openCount  已经放置的左括号数量
     * @param closeCount 已经放置的右括号数量
     * @param n          括号对数
     */
    private void backtrack(List<String> result, String current, int openCount, int closeCount, int n) {
        // 如果已经放置了n对括号，将当前组合加入结果列表
        if (openCount == n && closeCount == n) {
            result.add(current);
            return;
        }

        // 如果左括号数量小于n，可以放置一个左括号
        if (openCount < n) {
            backtrack(result, current + "(", openCount + 1, closeCount, n);
        }

        // 如果右括号数量小于左括号数量，可以放置一个右括号
        if (closeCount < openCount) {
            backtrack(result, current + ")", openCount, closeCount + 1, n);
        }
    }

    public static void main(String[] args) {
        Parentheses parentheses = new Parentheses();
        int n = 3;
        List<String> result = parentheses.generateParenthesis(n);
        System.out.println(result);
    }
}
