package com.geekbo.training.leetcode.daily;

import java.math.BigInteger;

/**
 * 306. Additive Number
 * An additive number is a string whose digits can form an additive sequence.
 * <p>
 * A valid additive sequence should contain at least three numbers.
 * Except for the first two numbers, each subsequent number in the sequence
 * must be the sum of the preceding two.
 * <p>
 * Given a string containing only digits, return true if it is an additive number or false otherwise.
 * <p>
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "112358"
 * Output: true
 * Explanation:
 * The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * Example 2:
 * <p>
 * Input: "199100199"
 * Output: true
 * Explanation:
 * The additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= num.length <= 35
 * num consists only of digits.
 * <p>
 * <p>
 * Follow up: How would you handle overflow for very large input integers?
 */
class AdditiveNumber {

    /**
     * This implementation checks all possible combinations of the first two numbers
     * and recursively checks if the remaining string can be formed by adding these numbers.
     * If a valid additive sequence is found, it returns true. Otherwise, it returns false.
     * <p>
     * The time complexity of this solution is O(n^3),
     * where n is the length of the input string.
     * This is because we are iterating through all possible combinations of the first two numbers,
     * and for each combination, we are recursively checking the remaining string.
     * The worst case occurs when the first two numbers are close to the maximum allowed length,
     * resulting in n^3 iterations. However, in practice,
     * the number of iterations will be much smaller for most inputs.
     * <p>
     * The space complexity is O(n) for the string concatenation
     * and BigInteger objects used in the calculations.
     *
     * @param num
     * @return
     */
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; i++) {
            if (num.charAt(0) == '0' && i > 1) {
                break; // Skip numbers starting with 0 (except 0 itself)
            }
            BigInteger num1 = new BigInteger(num.substring(0, i));
            for (int j = 1; Math.max(j, i) <= n - i - j; j++) {
                if (num.charAt(i) == '0' && j > 1) {
                    break; // Skip numbers starting with 0 (except 0 itself)
                }
                BigInteger num2 = new BigInteger(num.substring(i, i + j));
                if (isValidSequence(num1, num2, j + i, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidSequence(BigInteger num1, BigInteger num2, int start, String num) {
        if (start == num.length()) {
            return true; // Reached the end of the string
        }
        num2 = num2.add(num1); // Calculate the next number in the sequence
        num1 = num2.subtract(num1); // Shift the numbers
        String sum = num2.toString();
        return num.startsWith(sum, start) && isValidSequence(num1, num2, start + sum.length(), num);
    }
}