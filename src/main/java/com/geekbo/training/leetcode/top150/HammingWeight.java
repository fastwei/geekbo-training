package com.geekbo.training.leetcode.top150;

/**
 * 191. Number of 1 Bits
 * <p>
 * Write a function that takes the binary representation of an unsigned integer and
 * returns the number of '1' bits it has (also known as the Hamming weight).
 * <p>
 * Note:
 * <p>
 * Note that in some languages, such as Java, there is no unsigned integer type.
 * In this case, the input will be given as a signed integer type.
 * It should not affect your implementation, as the integer's internal binary representation is the same,
 * whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation.
 * Therefore, in Example 3, the input represents the signed integer. -3.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 00000000000000000000000000001011
 * Output: 3
 * Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 * Example 2:
 * <p>
 * Input: n = 00000000000000000000000010000000
 * Output: 1
 * Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
 * Example 3:
 * <p>
 * Input: n = 11111111111111111111111111111101
 * Output: 31
 * Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1'
 *
 * todo:有空加深理解
 */
public class HammingWeight {
    /**
     * 计算二进制表示中 '1' 的个数
     * <p>
     * 解题思路：
     * <p>
     * 题目要求计算一个无符号整数的二进制表示中 '1' 的个数。
     * 我们可以使用位运算来检查每个位是否为1。
     * 遍历整数的二进制表示，将其与1进行位与运算，如果结果为1，则说明该位上是1，将计数器加1。
     * 然后将整数右移一位，继续检查下一位。
     * 重复上述步骤直到整数变为0。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(log n)，其中n是输入整数的位数。我们需要遍历整数的二进制表示中的每一位。
     * 空间复杂度：O(1)。除了存储结果的变量，我们没有使用额外的空间。
     *
     * @param n 无符号整数
     * @return '1' 的个数
     */
    public static int hammingWeight(int n) {
        int count = 0;
        // 通过位运算检查每个位上是否为1
        while (n != 0) {
            count += n & 1;  // 取最低位的值并累加
            n >>>= 1;  // 无符号右移一位
        }
        return count;
    }

    public static void main(String[] args) {
        // 测试用例1
        int n1 = 0b00000000000000000000000000001011;
        // 预期输出：3
        System.out.println(hammingWeight(n1));

        // 测试用例2
        int n2 = 0b00000000000000000000000010000000;
        // 预期输出：1
        System.out.println(hammingWeight(n2));

        // 测试用例3
        int n3 = 0b11111111111111111111111111111101;
        // 预期输出：31
        System.out.println(hammingWeight(n3));
    }
}