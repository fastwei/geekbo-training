package com.geekbo.training.leetcode.crackinterview109;

/**
 *
 * 面试题 16.01. 交换数字
 * 中等
 * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 *
 * 示例：
 *
 * 输入: numbers = [1,2]
 * 输出: [2,1]
 * 提示：
 *
 * numbers.length == 2
 * -2147483647 <= numbers[i] <= 2147483647
 *
 */
public class SwapNumbers {
    /**
     * 不用临时变量，直接交换numbers中a与b的值
     *
     * 解题思路：
     * 使用异或运算实现两个数的交换。异或运算的性质是：对于两个操作数的每一位，相同结果为0，不同结果为1。
     * 因此，将a与b依次进行异或运算，可以得到交换后的结果。
     *
     * 算法复杂度分析：
     * 时间复杂度：O(1)，只需要进行一次异或运算。
     * 空间复杂度：O(1)，不需要使用额外的空间。
     *
     * @param numbers 给定的两个数字
     * @return 交换后的结果
     */
    public static int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] ^ numbers[1];
        numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];
        return numbers;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] numbers1 = {1, 2};
        int[] result1 = swapNumbers(numbers1);
        System.out.println("Test case 1: [" + result1[0] + ", " + result1[1] + "]"); // Expected output: [2, 1]

        // Test case 2
        int[] numbers2 = {-3, 8};
        int[] result2 = swapNumbers(numbers2);
        System.out.println("Test case 2: [" + result2[0] + ", " + result2[1] + "]"); // Expected output: [8, -3]
    }
}