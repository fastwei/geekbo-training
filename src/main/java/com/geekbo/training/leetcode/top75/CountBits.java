package com.geekbo.training.leetcode.top75;

import java.util.Arrays;

/**
 *Given an integer n, return an array ans of length n + 1
 * such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * Example 2:
 *
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 * 解题思路：
 *
 * 对于每个数字 i，我们使用位运算 (i & (i - 1)) 来得到比 i 小的数的1的个数，再加上 i 本身的最低位是否为1。这样可以快速计算出每个数字的比特位数。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(n)，我们需要遍历从0到n的所有数字。
 * 空间复杂度：O(n)，需要存储结果数组。
 * 这个算法是一个非常高效的解决方案，能够在O(n)时间内解决问题。
 *
 *
 */
public class CountBits {
    public int[] countBits(int n) {
        int[] result = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            // 使用位运算统计1的个数，通过i & (i - 1)可以得到比i小的数的1的个数，再加上i本身的最低位是否为1
            result[i] = result[i >> 1] + (i & 1);
        }

        return result;
    }

    public static void main(String[] args) {
        CountBits solution = new CountBits();

        // 测试用例1
        int n1 = 2;
        int[] result1 = solution.countBits(n1);
        System.out.println(Arrays.toString(result1)); // 预期输出: [0, 1, 1]

        // 测试用例2
        int n2 = 5;
        int[] result2 = solution.countBits(n2);
        System.out.println(Arrays.toString(result2)); // 预期输出: [0, 1, 1, 2, 1, 2]
    }
}
