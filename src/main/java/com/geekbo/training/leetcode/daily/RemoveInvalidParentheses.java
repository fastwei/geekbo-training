package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 301. Remove Invalid Parentheses
 * Hard
 * Given a string s that contains parentheses and letters,
 * remove the minimum number of invalid parentheses to make the input string valid.
 * <p>
 * Return a list of unique strings that are valid with the minimum number of removals.
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()())()"
 * Output: ["(())()","()()()"]
 * Example 2:
 * <p>
 * Input: s = "(a)())()"
 * Output: ["(a())()","(a)()()"]
 * Example 3:
 * <p>
 * Input: s = ")("
 * Output: [""]
 */
public class RemoveInvalidParentheses {
    /**
     * 解题思路：
     * <p>
     * 使用深度优先搜索（DFS）来进行递归搜索。
     * 遍历给定字符串中的每个字符，如果遇到左括号或者右括号，我们可以选择删除它或者保留它。
     * 然后，我们进入下一个字符进行递归搜索。
     * 在进行DFS搜索时，我们需要注意以下几点：
     * 为了避免生成重复的字符串，我们只删除连续的右括号中的第一个。同样地，我们只删除连续的左括号中的第一个。
     * 在递归的过程中，我们需要记录已经删除的左括号和右括号的数量，以便在生成结果时使用。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(2^N)，其中 N 是给定字符串的长度。
     * 这是因为对于每个字符，我们有两个选择，即删除或保留。因此，总共有 2^N 种可能的组合。
     * 空间复杂度：O(N)，其中 N 是给定字符串的长度。空间复杂度主要取决于递归调用的深度。
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        dfs(s, result, 0, 0, '(', ')');
        return result;
    }

    private void dfs(String s, List<String> result, int start, int lastRemove, char open, char close) {
        int count = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == open) {
                count++;
            } else if (s.charAt(i) == close) {
                count--;
            }
            if (count >= 0) {
                continue;
            }
            for (int j = lastRemove; j <= i; j++) {
                if (s.charAt(j) == close && (j == lastRemove || s.charAt(j - 1) != close)) {
                    dfs(s.substring(0, j) + s.substring(j + 1), result, i, j, open, close);
                }
            }
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (open == '(') {
            dfs(reversed, result, 0, 0, close, open);
        } else {
            result.add(reversed);
        }
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        // Test case 1
        String s1 = "()())()";
        // Expected output: ["(())()", "()()()"]
        List<String> result1 = removeInvalidParentheses.removeInvalidParentheses(s1);
        System.out.println(result1);

        // Test case 2
        String s2 = "(a)())()";
        // Expected output: ["(a())()", "(a)()()"]
        List<String> result2 = removeInvalidParentheses.removeInvalidParentheses(s2);
        System.out.println(result2);

        // Test case 3
        String s3 = ")(";
        // Expected output: [""]
        List<String> result3 = removeInvalidParentheses.removeInvalidParentheses(s3);
        System.out.println(result3);
    }
}
