package com.geekbo.training.leetcode.codeinterview109;

/**
 * 面试题 05.07. 配对交换
 * 简单
 * 配对交换。编写程序，交换某个整数的奇数位和偶数位，尽量使用较少的指令（也就是说，位0与位1交换，位2与位3交换，以此类推）。
 *
 * 示例1:
 *
 *  输入：num = 2（或者0b10）
 *  输出 1 (或者 0b01)
 * 示例2:
 *
 *  输入：num = 3
 *  输出：3
 * 提示:
 *
 * num的范围在[0, 2^30 - 1]之间，不会发生整数溢出。
 *
 */
public class SwapPairs {
    /**
     * 解题思路：
     * 我们可以使用位运算来实现奇偶位交换。
     * 首先，我们需要获取奇数位和偶数位的值。
     * 奇数位的值可以通过 num & 0xaaaaaaaa 进行获取，其中 0xaaaaaaaa 是一个二进制数，它的偶数位都是 1，奇数位都是 0。
     * 偶数位的值可以通过 num & 0x55555555 进行获取，其中 0x55555555 是一个二进制数，它的奇数位都是 1，偶数位都是 0。
     * 然后，我们将奇数位向右移动一位，偶数位向左移动一位，再将两者进行或运算，即可得到交换后的结果。
     *
     * 算法复杂度分析：
     * 由于位运算是在常数时间内完成的，所以该算法的时间复杂度为 O(1)。
     * 空间复杂度为 O(1)。
     */
    public static int exchangeBits(int num) {
        int odd = num & 0xaaaaaaaa;
        int even = num & 0x55555555;
        odd >>= 1;
        even <<= 1;
        return odd | even;
    }

    public static void main(String[] args) {
        // 测试用例 1
        int num1 = 2;
        // 预期输出：1
        System.out.println(exchangeBits(num1));

        // 测试用例 2
        int num2 = 3;
        // 预期输出：3
        System.out.println(exchangeBits(num2));
    }
}