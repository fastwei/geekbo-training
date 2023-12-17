package com.geekbo.training.leetcode.crackinterview109;

/**
 * 不用加号的加法
 * 面试题 17.01. 不用加号的加法
 * 简单
 * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
 * <p>
 * 示例:
 * <p>
 * 输入: a = 1, b = 1
 * 输出: 2
 * <p>
 * <p>
 * 提示：
 * <p>
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 */
public class AddWithoutPlus {

    /**
     * 两个数字相加，不使用 + 或者其他算术运算符
     * 解题思路： 我们可以使用位运算来实现加法操作。具体步骤如下：
     * <p>
     * 使用异或运算（^）计算两个数字的无进位和。
     * 使用与运算（&）计算两个数字的进位，并将结果左移一位。
     * 将步骤1中得到的无进位和和步骤2中得到的进位相加，得到最终的和。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(1)，位运算的时间复杂度为常数级别。
     * 空间复杂度：O(1)，只使用了常数个额外变量。
     *
     * @param a 数字a
     * @param b 数字b
     * @return 两个数字的和
     */
    public static int add(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        int a = 1;
        int b = 1;
        System.out.println(add(a, b));  // 预期输出：2
    }
}
