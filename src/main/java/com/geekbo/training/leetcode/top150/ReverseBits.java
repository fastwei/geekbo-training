package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 * <p>
 * Note:
 * <p>
 * Note that in some languages, such as Java, there is no unsigned integer type.
 * In this case, both input and output will be given as a signed integer type.
 * They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation.
 * Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 00000010100101000001111010011100
 * Output:    964176192 (00111001011110000010100101000000)
 * Explanation: The input binary string 00000010100101000001111010011100
 * represents the unsigned integer 43261596,
 * so return 964176192 which its binary representation is 00111001011110000010100101000000.
 * Example 2:
 * <p>
 * Input: n = 11111111111111111111111111111101
 * Output:   3221225471 (10111111111111111111111111111111)
 * Explanation: The input binary string 11111111111111111111111111111101
 * represents the unsigned integer 4294967293,
 * so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The input must be a binary string of length 32
 * <p>
 * <p>
 * Follow up: If this function is called many times, how would you optimize it?
 */
public class ReverseBits {


    private static final Map<Integer, Integer> cache = new HashMap<>();

    /**
     * 如果该函数被频繁调用，可以通过预计算和缓存的方式进行优化。
     * 可以将所有可能的输入的反转结果预先计算并存储在一个哈希表中，
     * 当需要反转某个数时，直接从哈希表中查找结果，避免重复计算。这样可以大大提高函数的执行效率。
     *
     * @param n
     * @return
     */
    // you need treat n as an unsigned value
    public int reverseBits2(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n = n >> 1;
        }
        cache.put(n, result);
        return result;
    }

    /**
     * 解题思路：
     * <p>
     * 使用位运算进行反转。
     * 遍历32次，每次将结果result左移1位，并将n的最后一位与result进行或运算，得到新的result。
     * 将n右移1位，继续下一次循环。
     * 循环结束后，result即为反转后的结果。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(1)，因为只需要遍历32次。
     * 空间复杂度：O(1)，只使用了常数级的额外空间。
     * <p>
     *
     * @param n
     * @return
     */
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n = n >> 1;
        }
        return result;
    }

    public static void main(String[] args) {
        ReverseBits solution = new ReverseBits();

        // Test Case 1
        int n1 = 43261596;
        int result1 = solution.reverseBits(n1);
        int expected1 = 964176192;
        System.out.println(result1 == expected1); // true

        // Test Case 2
        int n2 = -3;
        int result2 = solution.reverseBits(n2);
        int expected2 = -1073741825;
        System.out.println(result2 == expected2); // true
    }
}