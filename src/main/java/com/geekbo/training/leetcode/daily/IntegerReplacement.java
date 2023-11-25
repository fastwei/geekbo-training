package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 397. Integer Replacement
 * Medium
 * Given a positive integer n, you can apply one of the following operations:
 * <p>
 * If n is even, replace n with n / 2.
 * If n is odd, replace n with either n + 1 or n - 1.
 * Return the minimum number of operations needed for n to become 1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 8
 * Output: 3
 * Explanation: 8 -> 4 -> 2 -> 1
 * Example 2:
 * <p>
 * Input: n = 7
 * Output: 4
 * Explanation: 7 -> 8 -> 4 -> 2 -> 1
 * or 7 -> 6 -> 3 -> 2 -> 1
 * Example 3:
 * <p>
 * Input: n = 4
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 231 - 1
 */
public class IntegerReplacement {
    private Map<Integer, Integer> dp;

    /**
     * 解题思路：
     * <p>
     * 对于给定的数字n，我们可以通过两种操作来减少它的值：
     * 如果n是偶数，我们可以将其除以2；
     * 如果n是奇数，我们可以将其加1或减1。
     * 我们需要找到最少的操作次数，使得n最终变为1。
     * 为了避免重复计算，我们使用记忆化技术，将每个数字n对应的最小操作次数存储在一个哈希表中。
     * 算法复杂度：
     * <p>
     * 时间复杂度：在递归的过程中，我们需要计算每个数字n对应的最小操作次数，而每个数字只需要计算一次。递归的深度最多为log(n)，所以时间复杂度为O(log(n))。
     * 空间复杂度：我们使用了一个哈希表来存储每个数字对应的最小操作次数，所以空间复杂度为O(n)。
     * 总结：
     * <p>
     * 我们使用递归和记忆化的方式来解决这个问题，减少了重复计算的次数，提高了效率。
     * 时间复杂度为O(log(n))，空间复杂度为O(n)。
     *
     * @param n
     * @return
     */
    public int integerReplacement(int n) {
        dp = new HashMap<>();
        dp.put(Integer.MAX_VALUE, 32);
        dp.put(1, 0);
        return helper(n);
    }

    public int helper(int n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        int result;
        if (n >= Integer.MAX_VALUE) {
            result = 32;
        } else if (n == 1) {
            result = 0;
        } else if (n < 3) {
            result = 1;
        } else if (n < 5) {
            result = 2;
        } else if (n % 2 == 0) {
            result = 1 + helper(n / 2);
        } else {
            result = Math.min(1 + helper(n + 1), 1 + helper(n - 1));
        }

        dp.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        IntegerReplacement solution = new IntegerReplacement();

        // 测试用例1
        int n1 = 8;
        int result1 = solution.integerReplacement(n1);
        System.out.println("Test Case 1:");
        System.out.println(result1); // Output: 3

        // 测试用例2
        int n2 = 7;
        int result2 = solution.integerReplacement(n2);
        System.out.println("Test Case 2:");
        System.out.println(result2); // Output: 4

        // 测试用例3
        int n3 = 4;
        int result3 = solution.integerReplacement(n3);
        System.out.println("Test Case 3:");
        System.out.println(result3); // Output: 2
    }
}