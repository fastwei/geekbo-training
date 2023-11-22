package com.geekbo.training.leetcode.daily;

/**
 * 326. Power of Three
 * Easy
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 *
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 27
 * Output: true
 * Explanation: 27 = 33
 * Example 2:
 *
 * Input: n = 0
 * Output: false
 * Explanation: There is no x where 3x = 0.
 * Example 3:
 *
 * Input: n = -1
 * Output: false
 * Explanation: There is no x where 3x = (-1).
 *
 *
 * Constraints:
 *
 * -231 <= n <= 231 - 1
 *
 *
 * Follow up: Could you solve it without loops/recursion?
 *
 */
class PowerOfThree {
    /**
     * 解题思路：
     * <p>
     * 首先，负数和0不是3的幂，所以返回false。
     * 然后，我们对n进行除以3的操作，直到n不再能被3整除为止。
     * 最后，如果n等于1，说明n是3的幂，返回true；否则，返回false。
     * 算法复杂度分析：
     * <p>
     * 如果n是3的幂，那么在每次迭代中，我们将n除以3，直到n等于1。所以时间复杂度为O(logn)。
     * 空间复杂度为O(1)。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false; // Negative numbers and 0 are not powers of three
        }
        while (n % 3 == 0) {
            n /= 3; // Divide n by 3 until it is no longer divisible by 3
        }
        return n == 1; // If n is a power of three, it should be equal to 1 after the division
    }

    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        PowerOfThree solution = new PowerOfThree();
        System.out.println(solution.isPowerOfThree(27)); // Expected output: true
        System.out.println(solution.isPowerOfThree(0)); // Expected output: false
        System.out.println(solution.isPowerOfThree(-1)); // Expected output: false
    }
}


