package com.geekbo.training.leetcode.daily;

/**
 * 231. Power of Two
 * <p>
 * Given an integer n, return true if it is a power of two. Otherwise, return false.
 * <p>
 * An integer n is a power of two, if there exists an integer x such that n == 2x.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: true
 * Explanation: 20 = 1
 * Example 2:
 * <p>
 * Input: n = 16
 * Output: true
 * Explanation: 24 = 16
 * Example 3:
 * <p>
 * Input: n = 3
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= n <= 231 - 1
 * <p>
 * <p>
 * Follow up: Could you solve it without loops/recursion?
 */
class PowerOfTwo {
    /**
     * 使用位运算的性质来判断一个整数是否是2的幂。
     * 如果一个整数是2的幂，那么它的二进制表示中只有一个位是1，其他位都是0。
     * 这意味着如果将这个数减去1，那么所有的1位都变为0，而原来的0位变为1。
     * 因此，对于一个2的幂n，它与n-1进行按位与运算的结果应该为0。
     * <p>
     * 这个解法的时间复杂度为 O(1)。
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        // 测试用例1
        int n1 = 1;
        System.out.println("测试用例1: " + isPowerOfTwo(n1)); // 预期输出: true

        // 测试用例2
        int n2 = 16;
        System.out.println("测试用例2: " + isPowerOfTwo(n2)); // 预期输出: true

        // 测试用例3
        int n3 = 3;
        System.out.println("测试用例3: " + isPowerOfTwo(n3)); // 预期输出: false
    }
}
