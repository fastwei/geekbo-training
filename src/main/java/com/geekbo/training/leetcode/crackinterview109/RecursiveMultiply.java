package com.geekbo.training.leetcode.crackinterview109;

/**
 * 面试题 08.05. 递归乘法
 * 中等
 * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
 * <p>
 * 示例1:
 * <p>
 * 输入：A = 1, B = 10
 * 输出：10
 * 示例2:
 * <p>
 * 输入：A = 3, B = 4
 * 输出：12
 * 提示:
 * <p>
 * 保证乘法范围不会溢出
 */
public class RecursiveMultiply {

    /**
     * 解题思路：
     * 使用循环的方式实现两个正整数的相乘。
     * 首先找出较小的数和较大的数，然后遍历较小的数的每一位。
     * 如果当前位是1，将较大的数左移对应的位数，并累加到结果中。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(logn)，其中 n 是较小的数。
     * 空间复杂度：O(1)。
     *
     * @param A 正整数 A
     * @param B 正整数 B
     * @return 两个正整数的乘积
     */
    public static int multiply(int A, int B) {
        int min = Math.min(A, B);
        int max = Math.max(A, B);
        int ans = 0;

        for (int i = 0; min != 0; i++) {
            if ((min & 1) == 1) {
                ans += max << i;
            }
            min >>= 1;
        }

        return ans;
    }

    /**
     *
     * 时间复杂度：O(logn)
     * 空间复杂度：O(1)。
     */
    public int recursiveMultiply(int A, int B) {
        int min = Math.min(A, B);
        int max = Math.max(A, B);
        int ans = 0;

        for (int i = 0; min != 0; i++) {
            if ((min & 1) == 1) {
                ans += max << i;
            }
            min >>= 1;
        }

        return ans;
    }

    public static void main(String[] args) {
        // 测试用例1
        int A1 = 1;
        int B1 = 10;
        // 预期输出：10
        System.out.println(multiply(A1, B1));

        // 测试用例2
        int A2 = 3;
        int B2 = 4;
        // 预期输出：12
        System.out.println(multiply(A2, B2));
    }
}
