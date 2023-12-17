package com.geekbo.training.leetcode.crackinterview109;

/**
 * 题目：面试题 05.01. 插入
 * 
 * 描述：给定两个整型数字 N 与 M，以及表示比特位置的 i 与 j（i <= j，且从 0 位开始计算）。
 * 编写一种方法，使 M 对应的二进制数字插入 N 对应的二进制数字的第 i ~ j 位区域，不足之处用 0 补齐。
 * 
 * 解题思路：
 * 1. 首先将 N 的第 i 位到第 j 位清零，可以通过将 N 与一个掩码进行按位与操作实现，掩码的定义如下：
 *    掩码 mask = (-1 << (j + 1)) | ((1 << i) - 1)，即将从第 i 位到第 j 位的所有位取反，其他位保持不变。
 * 2. 将 M 左移 i 位，然后与掩码进行按位与操作，得到 M 在第 i 位到第 j 位的值。
 * 3. 将得到的 M 值右移 i 位，然后与 N 进行按位或操作，即可得到结果。
 * 
 * 算法复杂度分析：
 * - 时间复杂度：O(1)，只需要进行几次位运算操作。
 * - 空间复杂度：O(1)，只需要常数空间存储变量。
 */

public class InsertBits {


    public static int insertBits(int N, int M, int i, int j) {
        int mask = ~(((1 << (j - i + 1)) - 1) << i); // Create a mask to clear the bits from i to j in N
        N &= mask; // Clear the bits from i to j in N
        N |= (M << i); // Shift M to the left by i bits and set the bits from i to j in N
        return N;
    }

    public static void main(String[] args) {
        // 测试用例
        int N1 = 1024;
        int M1 = 19;
        int i1 = 2;
        int j1 = 6;
        System.out.println("输入：N = " + N1 + ", M = " + M1 + ", i = " + i1 + ", j = " + j1);
        int result1 = insertBits(N1, M1, i1, j1);
        System.out.println("输出：N = " + result1);

        int N2 = 0;
        int M2 = 31;
        int i2 = 0;
        int j2 = 4;
        System.out.println("输入：N = " + N2 + ", M = " + M2 + ", i = " + i2 + ", j = " + j2);
        int result2 = insertBits(N2, M2, i2, j2);
        System.out.println("输出：N = " + result2);
    }
}