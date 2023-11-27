package com.geekbo.training.leetcode.greedy;

import java.util.Stack;

/**
 * 402. Remove K Digits
 * Medium
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
 * <p>
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */
public class RemoveKDigits {
    /**
     * 移除字符串中的 k 个数字，使得剩下的数字组成的字符串最小
     * <p>
     * 解题思路：
     * <p>
     * 使用贪心算法来解决问题。
     * 创建一个栈来存储最终的结果。
     * 遍历字符串中的每个字符，对于每个字符：
     * 如果栈不为空且当前数字比栈顶元素小，并且还需要移除数字，则将栈顶元素出栈，直到满足条件。
     * <p>
     * -如果当前数字不是0，或者栈不为空，则将当前数字入栈。
     * -如果还需要移除数字，继续从栈顶移除元素。
     * -将栈中的元素转换为字符串，并返回最终结果。
     * <p>
     * 算法复杂度分析：
     * -遍历字符串的时间复杂度为 O(n)，其中 n 是字符串的长度。
     * -栈的大小最多为 n，因此栈操作的时间复杂度为 O(n)。
     * -最终将栈中的元素转换为字符串的时间复杂度为 O(n)。
     * -因此，总的时间复杂度为 O(n)。
     *
     * @param num 输入的非负整数字符串
     * @param k   需要移除的数字个数
     * @return 移除 k 个数字后得到的最小整数字符串
     */
    public String removeKdigits(String num, int k) {
        // 创建一个栈来存储最终的结果
        Stack<Character> stack = new Stack<>();

        // 遍历字符串中的每个字符
        for (char digit : num.toCharArray()) {
            // 如果栈不为空且当前数字比栈顶元素小，且还需要移除数字
            // 则将栈顶元素出栈
            while (!stack.isEmpty() && k > 0 && digit < stack.peek()) {
                stack.pop();
                k--;
            }
            // 如果当前数字不是0，或者栈不为空
            // 则将当前数字入栈
            if (digit != '0' || !stack.isEmpty()) {
                stack.push(digit);
            }
        }

        // 如果还需要移除数字，继续从栈顶移除元素
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        // 将栈中的元素转换为字符串
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        // 如果字符串为空，说明移除了所有数字，返回 "0"
        // 否则返回转换后的字符串
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        RemoveKDigits solution = new RemoveKDigits();

        // 测试用例
        String num1 = "1432219";
        int k1 = 3;
        // 移除数字 4, 3, 2，得到最小整数字符串 1219
        String expected1 = "1219";
        String result1 = solution.removeKdigits(num1, k1);
        System.out.println(result1.equals(expected1)); // 预期输出为 true

        String num2 = "10200";
        int k2 = 1;
        // 移除数字 1，得到最小整数字符串 200
        String expected2 = "200";
        String result2 = solution.removeKdigits(num2, k2);
        System.out.println(result2.equals(expected2)); // 预期输出为 true

        String num3 = "10";
        int k3 = 2;
        // 移除所有数字，得到最小整数字符串 0
        String expected3 = "0";
        String result3 = solution.removeKdigits(num3, k3);
        System.out.println(result3.equals(expected3)); // 预期输出为 true
    }
}
