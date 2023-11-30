package com.geekbo.training.leetcode.daily;

/**
 * 1611. Minimum One Bit Operations to Make Integers Zero
 * Hard
 * Given an integer n, you must transform it into 0 using the following operations any number of times:
 * <p>
 * Change the rightmost (0th) bit in the binary representation of n.
 * Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1 and the (i-2)th through 0th bits are set to 0.
 * Return the minimum number of operations to transform n into 0.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: 2
 * Explanation: The binary representation of 3 is "11".
 * "11" -> "01" with the 2nd operation since the 0th bit is 1.
 * "01" -> "00" with the 1st operation.
 * Example 2:
 * <p>
 * Input: n = 6
 * Output: 4
 * Explanation: The binary representation of 6 is "110".
 * "110" -> "010" with the 2nd operation since the 1st bit is 1 and 0th through 0th bits are 0.
 * "010" -> "011" with the 1st operation.
 * "011" -> "001" with the 2nd operation since the 0th bit is 1.
 * "001" -> "000" with the 1st operation.
 */
public class MinimumOneBitOperations {

    int min = Integer.MAX_VALUE;

    public static int minimumOneBitOperations(int n) {
        if (n <= 1) return n;
        int count = 0;
        while ((1 << count) <= n) count++;
        return ((1 << count) - 1) - minimumOneBitOperations(n - (1 << (count - 1)));
    }

    public static void main(String[] args) {
        // 测试用例
        int n1 = 3;
        // 预期输出：2
        System.out.println(minimumOneBitOperations(n1));

        int n2 = 6;
        // 预期输出：4
        System.out.println(minimumOneBitOperations(n2));
    }
}
