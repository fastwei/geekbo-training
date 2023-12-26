package com.geekbo.training.leetcode.crackinterview109;


/**
 * 面试题 08.14. 布尔运算
 * 中等
 * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "1^0|0|1", result = 0
 * <p>
 * 输出: 2
 * 解释: 两种可能的括号方法是
 * 1^(0|(0|1))
 * 1^((0|0)|1)
 * 示例 2:
 * <p>
 * 输入: s = "0&0&0&1^1|0", result = 1
 * <p>
 * 输出: 10
 * 提示：
 * <p>
 * 运算符的数量不超过 19 个
 */
public class BooleanExpression {
    /**
     * 解题思路： 我们可以使用动态规划来解决这个问题。
     * 定义两个二维数组dpTrue和dpFalse，其中dpTrue[i][j]表示从索引i到j的子表达式为true的括号方法数，dpFalse[i][j]表示从索引i到j的子表达式为false的括号方法数。
     * <p>
     * 然后，我们可以使用递归的方式计算出dpTrue和dpFalse数组的值。
     * 具体的递归函数为helper(s, start, end, isTrue)，其中s为布尔表达式字符串，start和end表示当前计算的子表达式的起始和结束索引，isTrue表示期望的布尔结果。
     * <p>
     * 算法复杂度分析： 时间复杂度：O(N^3)，其中N为布尔表达式的长度。
     * 因为我们需要计算三层循环来填充dpTrue和dpFalse数组。
     * 空间复杂度：O(N^2)，我们需要使用两个二维数组来存储中间结果。
     *
     * @param s
     * @param result
     * @return
     */
    public static int countEval(String s, int result) {
        int n = s.length();
        int[][] dpTrue = new int[n][n];
        int[][] dpFalse = new int[n][n];

        for (int len = 1; len <= n; len += 2) {
            for (int i = 0; i + len - 1 < n; i += 2) {
                int j = i + len - 1;
                if (len == 1) {
                    dpTrue[i][j] = (s.charAt(i) == '1' ? 1 : 0);
                    dpFalse[i][j] = (s.charAt(i) == '0' ? 1 : 0);
                } else {
                    for (int k = i + 1; k <= j; k += 2) {
                        char operator = s.charAt(k);
                        if (operator == '&') {
                            dpTrue[i][j] += dpTrue[i][k - 1] * dpTrue[k + 1][j];
                            dpFalse[i][j] += dpFalse[i][k - 1] * dpFalse[k + 1][j]
                                    + dpTrue[i][k - 1] * dpFalse[k + 1][j]
                                    + dpFalse[i][k - 1] * dpTrue[k + 1][j];
                        } else if (operator == '|') {
                            dpTrue[i][j] += dpTrue[i][k - 1] * dpFalse[k + 1][j]
                                    + dpFalse[i][k - 1] * dpTrue[k + 1][j]
                                    + dpTrue[i][k - 1] * dpTrue[k + 1][j];
                            dpFalse[i][j] += dpFalse[i][k - 1] * dpFalse[k + 1][j];
                        } else if (operator == '^') {
                            dpTrue[i][j] += dpTrue[i][k - 1] * dpFalse[k + 1][j]
                                    + dpFalse[i][k - 1] * dpTrue[k + 1][j];
                            dpFalse[i][j
                                    ] += dpTrue[i][k - 1] * dpTrue[k + 1][j] + dpFalse[i][k - 1] * dpFalse[k + 1][j];
                        }
                    }
                }
            }
        }

        return result == 1 ? dpTrue[0][n - 1] : dpFalse[0][n - 1];
    }

    public static void main(String[] args) {
        String s1 = "1^0|0|1";
        int result1 = 0;
        System.out.println("括号方法数：" + countEval(s1, result1)); // 输出：2

        String s2 = "0&0&0&1^1|0";
        int result2 = 1;
        System.out.println("括号方法数：" + countEval(s2, result2)); // 输出：10
    }
}