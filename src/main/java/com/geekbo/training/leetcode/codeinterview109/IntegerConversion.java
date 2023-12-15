package com.geekbo.training.leetcode.codeinterview109;

/**
 * 面试题 05.06. 整数转换
 * <p>
 * 简单
 * <p>
 * 整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
 * <p>
 * 示例1:
 * <p>
 * 输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 * 输出：2
 * 示例2:
 * <p>
 * 输入：A = 1，B = 2
 * 输出：2
 */
public class IntegerConversion {
    /**
     * 统计需要改变的位数
     * 解题思路：
     * <p>
     * 使用异或运算符（^）计算A和B的异或结果。
     * 统计异或结果中1的个数，可以使用Brian Kernighan算法。
     * 该算法每次将最右边的1置为0，直到异或结果为0，期间统计置为0的次数即为1的个数。
     * 返回统计的结果即为需要改变的位数。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(logN)，其中N为A和B的二进制表示的位数。
     * 空间复杂度：O(1)。
     *
     * @param A 整数A
     * @param B 整数B
     * @return 需要改变的位数
     */
    public static int convertInteger(int A, int B) {
        int xor = A ^ B; // 异或运算，将不同的位数置为1
        int count = 0; // 计数器，统计异或结果中1的个数

        // 统计异或结果中1的个数
        while (xor != 0) {
            count++;
            xor &= (xor - 1); // 每次将最右边的1置为0
        }

        return count;
    }

    public static void main(String[] args) {
        // 测试用例1
        int A1 = 29;
        int B1 = 15;
        // 预期输出：2
        System.out.println("需要改变的位数：" + convertInteger(A1, B1));

        // 测试用例2
        int A2 = 1;
        int B2 = 2;
        // 预期输出：2
        System.out.println("需要改变的位数：" + convertInteger(A2, B2));
    }
}
