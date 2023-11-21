package com.geekbo.training.leetcode.daily;

/**
 * 357. Count Numbers with Unique Digits
 * Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 * excluding 11,22,33,44,55,66,77,88,99
 * Example 2:
 * <p>
 * Input: n = 0
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 8
 */
public class CountNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }

        int count = 10;
        int uniqueDigits = 9;
        int availableDigits = 9;

        for (int i = 2; i <= n && availableDigits > 0; i++) {
            uniqueDigits = uniqueDigits * availableDigits;
            count += uniqueDigits;
            availableDigits--;
        }

        return count;
    }

    public static void main(String[] args) {
        CountNumbersWithUniqueDigits solution = new CountNumbersWithUniqueDigits();

        int n1 = 2;
        System.out.println(solution.countNumbersWithUniqueDigits(n1));  // Output: 91

        int n2 = 0;
        System.out.println(solution.countNumbersWithUniqueDigits(n2));  // Output: 1
    }
}