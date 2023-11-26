package com.geekbo.training.leetcode.daily;

/**
 * 400. Nth Digit
 * Medium
 * Given an integer n,
 * return the nth digit of the infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 3
 * Example 2:
 *
 * Input: n = 11
 * Output: 0
 * Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 *
 */
public class NthDigit {
    public int findNthDigit(int n) {
        int digit = 1; // 当前数字包含的位数
        long numDigitsInInterval = 9; // 当前区间中的总位数
        int base = 1; // 当前区间的起始数字

        while (n - numDigitsInInterval > 0) {
            n -= numDigitsInInterval;
            digit++;
            base *= 10;
            numDigitsInInterval = 9 * base * digit; // 计算下一个区间中的位数
        }

        // 找到包含第n位数字的具体数字
        int number = base + (n - 1) / digit;

        // 在该数字中找到第n位数字
        int digitInNumber = String.valueOf(number).charAt((n - 1) % digit) - '0';

        return digitInNumber;
    }
}