package com.geekbo.training.leetcode.daily;

/**
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * <p>
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 16
 * Output: true
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: false
 * Example 3:
 * <p>
 * Input: n = 1
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= n <= 231 - 1
 * <p>
 * <p>
 * Follow up: Could you solve it without loops/recursion?
 */
public class PowerOfFour {
    /**
     * 解题思路： 这道题可以使用位运算来解决。
     * 我们知道4的幂次方的二进制表示中，只有一个1，并且这个1出现在奇数位上（从右往左数，第1位、第3位、第5位，依此类推）。
     * <p>
     * 我们可以利用这个性质来判断一个数是否是4的幂次方。
     * 首先，我们可以将n与n-1进行按位与运算，如果结果为0，则说明n只有一个1。
     * 然后，我们可以将n与0xAAAAAAAA（10101010101010101010101010101010）进行按位与运算，
     * 如果结果为0，则说明这个1出现在奇数位上。
     * <p>
     * 所以，我们只需要判断n是否大于0，并且满足(n & (n - 1)) == 0和(n & 0xAAAAAAAA) == 0的条件，
     * 即可判断n是否是4的幂次方。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(1)。
     * 空间复杂度：O(1)。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xAAAAAAAA) == 0;
    }

    public static void main(String[] args) {
        PowerOfFour powerOfFour = new PowerOfFour();

        // Test Case 1
        int n1 = 16;
        boolean result1 = powerOfFour.isPowerOfFour(n1);
        System.out.println(result1);  // Expected output: true

        // Test Case 2
        int n2 = 5;
        boolean result2 = powerOfFour.isPowerOfFour(n2);
        System.out.println(result2);  // Expected output: false

        // Test Case 3
        int n3 = 1;
        boolean result3 = powerOfFour.isPowerOfFour(n3);
        System.out.println(result3);  // Expected output: true
    }
}
