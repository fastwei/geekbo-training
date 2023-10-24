package com.geekbo.training.leetcode.skill;

import java.util.Stack;

/**
 *
 * You are keeping the scores for a baseball game with strange rules.
 * At the beginning of the game, you start with an empty record.
 *
 * You are given a list of strings operations,
 * where operations[i] is the ith operation you must apply to the record and is one of the following:
 *
 * An integer x.
 * Record a new score of x.
 * '+'.
 * Record a new score that is the sum of the previous two scores.
 * 'D'.
 * Record a new score that is the double of the previous score.
 * 'C'.
 * Invalidate the previous score, removing it from the record.
 * Return the sum of all the scores on the record after applying all the operations.
 *
 * The test cases are generated such that the answer
 * and all intermediate calculations fit in a 32-bit integer and that all operations are valid.
 *
 *
 *
 * Example 1:
 *
 * Input: ops = ["5","2","C","D","+"]
 * Output: 30
 * Explanation:
 * "5" - Add 5 to the record, record is now [5].
 * "2" - Add 2 to the record, record is now [5, 2].
 * "C" - Invalidate and remove the previous score, record is now [5].
 * "D" - Add 2 * 5 = 10 to the record, record is now [5, 10].
 * "+" - Add 5 + 10 = 15 to the record, record is now [5, 10, 15].
 * The total sum is 5 + 10 + 15 = 30.
 *
 */
public class BaseballGame {
    /**
     * 解题思路：
     * <p>
     * 使用栈来记录每个操作的得分，并计算总得分。
     * 遍历操作列表，对于每个操作：
     * - 如果操作是一个整数，将其转化为整数并将其加入栈中。
     * - 如果操作是"+"，将栈顶的两个元素相加得到新的得分，并将新的得分加入栈中。
     * - 如果操作是"D"，将栈顶的元素乘以2得到新的得分，并将新的得分加入栈中。
     * - 如果操作是"C"，将栈顶的元素移除。
     * 最后，将栈中的所有元素相加得到总得分。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历操作列表的时间复杂度为O(N)，其中N是操作的数量。
     * 空间复杂度：使用了栈来存储得分，栈的大小最大为操作的数量，因此空间复杂度为O(N)。
     *
     * @param ops 操作列表
     * @return 总得分
     */
    public static int calPoints(String[] ops) {
        // 创建栈来记录得分
        Stack<Integer> stack = new Stack<>();

        // 遍历操作列表
        for (String op : ops) {
            if (op.equals("+")) {
                // "+"操作，将栈顶的两个元素相加得到新的得分，并将新的得分加入栈中
                int top = stack.pop();
                int newScore = top + stack.peek();
                stack.push(top);
                stack.push(newScore);
            } else if (op.equals("D")) {
                // "D"操作，将栈顶的元素乘以2得到新的得分，并将新的得分加入栈中
                stack.push(stack.peek() * 2);
            } else if (op.equals("C")) {
                // "C"操作，将栈顶的元素移除
                stack.pop();
            } else {
                // 整数操作，将其转化为整数并将其加入栈中
                stack.push(Integer.parseInt(op));
            }
        }

        // 计算总得分
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;
    }

    public static void main(String[] args) {
        // 测试用例1
        String[] ops1 = {"5", "2", "C", "D", "+"};
        int expected1 = 30;
        int result1 = calPoints(ops1);
        System.out.println(result1 == expected1 ? "Test case 1 passed" : "Test case 1 failed");

        // 测试用例2
        String[] ops2 = {"5", "-2", "4", "C", "D", "9", "+", "+"};
        int expected2 = 27;
        int result2 = calPoints(ops2);
        System.out.println(result2 == expected2 ? "Test case 2 passed" : "Test case 2 failed");

        // 测试用例3
        String[] ops3 = {"1", "C"};
        int expected3 = 0;
        int result3 = calPoints(ops3);
        System.out.println(result3 == expected3 ? "Test case 3 passed" : "Test case 3 failed");
    }
}