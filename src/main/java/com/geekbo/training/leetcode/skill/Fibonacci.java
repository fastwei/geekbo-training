package com.geekbo.training.leetcode.skill;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence,
 * called the Fibonacci sequence, such that each number is the sum of the two preceding ones,
 * starting from 0 and 1. That is,
 * <p>
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * Example 3:
 * <p>
 * Input: n = 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 30
 */
public class Fibonacci {
    /**
     * 计算斐波那契数列的第n个数
     * 解题思路：
     * <p>
     * 斐波那契数列的定义是：F(0) = 0, F(1) = 1，F(n) = F(n - 1) + F(n - 2)，其中n > 1。
     * 需要计算斐波那契数列的第n个数，可以使用迭代的方式计算。
     * 使用两个变量first和second来保存前两个斐波那契数列的数。
     * 从第3个数开始，每次计算当前数等于前两个数的和，然后更新first和second的值。
     * 最终得到斐波那契数列的第n个数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，需要计算n个斐波那契数。
     * 空间复杂度：O(1)，只需要使用两个临时变量first和second。
     *
     * @param n 斐波那契数列的索引
     * @return 斐波那契数列的第n个数
     */
    public static int calculateFibonacci(int n) {
        // 斐波那契数列的前两个数为0和1
        int first = 0;
        int second = 1;

        // 特殊情况处理
        if (n == 0) {
            return first;
        } else if (n == 1) {
            return second;
        }

        // 计算斐波那契数列的第n个数
        for (int i = 2; i <= n; i++) {
            int temp = first + second;
            first = second;
            second = temp;
        }

        return second;
    }

    public static void main(String[] args) {
        // 测试用例
        int n1 = 2;
        int expected1 = 1;
        System.out.println("Input: n = " + n1);
        System.out.println("Expected output: " + expected1);
        System.out.println("Actual output: " + calculateFibonacci(n1));
        System.out.println();

        int n2 = 3;
        int expected2 = 2;
        System.out.println("Input: n = " + n2);
        System.out.println("Expected output: " + expected2);
        System.out.println("Actual output: " + calculateFibonacci(n2));
        System.out.println();

        int n3 = 4;
        int expected3 = 3;
        System.out.println("Input: n = " + n3);
        System.out.println("Expected output: " + expected3);
        System.out.println("Actual output: " + calculateFibonacci(n3));
    }
}
