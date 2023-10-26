package com.geekbo.training.leetcode.skill;

/**
 * Given two non-negative integers low and high.
 * Return the count of odd numbers between low and high (inclusive).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: low = 3, high = 7
 * Output: 3
 * Explanation: The odd numbers between 3 and 7 are [3,5,7].
 * Example 2:
 * <p>
 * Input: low = 8, high = 10
 * Output: 1
 * Explanation: The odd numbers between 8 and 10 are [9].
 */
public class CountOddNumbers {

    /**
     * 解题思路：
     * 我们可以通过计算low和high之间的整数个数来得到结果。
     * 由于范围包括low和high，所以我们只需要计算(high - low) / 2即可得到low和high之间的奇数个数。
     * 如果low或high是奇数，则需要额外加1。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：O(1)，只需执行常数次操作。
     * - 空间复杂度：O(1)，只使用了常数个变量。
     *
     * @param low
     * @param high
     * @return
     */
    public int countOdds(int low, int high) {
        int count = (high - low) / 2; // 计算low和high之间的整数个数

        if (low % 2 != 0 || high % 2 != 0) {
            count++; // 如果low或high是奇数，需要额外加1
        }

        return count;
    }

    public static void main(String[] args) {
        CountOddNumbers countOdd = new CountOddNumbers();

        // Test Case 1
        int low = 3;
        int high = 7;
        // 预期输出为3
        System.out.println(countOdd.countOdds(low, high));

        // Test Case 2
        low = 8;
        high = 10;
        // 预期输出为1
        System.out.println(countOdd.countOdds(low, high));
    }
}

