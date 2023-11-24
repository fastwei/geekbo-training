package com.geekbo.training.leetcode.daily;

/**
 * 372. Super Pow
 * Medium
 * Your task is to calculate ab mod 1337 where a is a positive integer
 * and b is an extremely large positive integer given in the form of an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = 2, b = [3]
 * Output: 8
 * Example 2:
 * <p>
 * Input: a = 2, b = [1,0]
 * Output: 1024
 * Example 3:
 * <p>
 * Input: a = 1, b = [4,3,3,8,5,2]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= a <= 231 - 1
 * 1 <= b.length <= 2000
 * 0 <= b[i] <= 9
 * b does not contain leading zeros.
 */
class SuperPow {
    public int superPow(int a, int[] b) {
        int mod = 1337;
        a %= mod; // Reduce a to the range [0, 1336] to optimize
        int num = 0;
        for (int i = 0; i < b.length; i++) {
            num = (num * 10 + b[i]) % 1140;
        }
        if (num == 0) {
            num += 1140;
        }
        int result = 1;
        for (int i = 0; i < num; i++) {
            result = (result * a) % mod;
        }
        return result;
    }

    public static void main(String[] args) {
        SuperPow solution = new SuperPow();

        // 测试用例1
        int a1 = 2;
        int[] b1 = {3};
        // 预期输出: 8
        System.out.println(solution.superPow(a1, b1));

        // 测试用例2
        int a2 = 2;
        int[] b2 = {1, 0};
        // 预期输出: 1024
        System.out.println(solution.superPow(a2, b2));

        // 测试用例3
        int a3 = 1;
        int[] b3 = {4, 3, 3, 8, 5, 2};
        // 预期输出: 1
        System.out.println(solution.superPow(a3, b3));
    }
}
