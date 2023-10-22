package com.geekbo.training.leetcode.top150;

import java.util.Arrays;

/**
 *
 * There is a function signFunc(x) that returns:
 *
 * 1 if x is positive.
 * -1 if x is negative.
 * 0 if x is equal to 0.
 * You are given an integer array nums. Let product be the product of all values in the array nums.
 *
 * Return signFunc(product).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,-2,-3,-4,3,2,1]
 * Output: 1
 * Explanation: The product of all values in the array is 144, and signFunc(144) = 1
 * Example 2:
 *
 * Input: nums = [1,5,0,2,-3]
 * Output: 0
 * Explanation: The product of all values in the array is 0, and signFunc(0) = 0
 * Example 3:
 *
 * Input: nums = [-1,1,-1,1,-1]
 * Output: -1
 * Explanation: The product of all values in the array is -1, and signFunc(-1) = -1
 *
 *
 */
public class SignFunction {

    public static int signFunc(int x) {
        if (x > 0) {
            return 1;
        } else if (x < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public static int productSign(int[] nums) {
        int sign = 1; // 初始值为1，用于存储符号
        for (int num : nums) {
            sign *= signFunc(num);
        }
        return sign;
    }

    public static void main(String[] args) {
        int[] nums = {41, 65, 14, 80, 20, 10, 55, 58, 24, 56, 28, 86, 96, 10, 3, 84, 4, 41, 13, 32, 42, 43, 83, 78, 82, 70, 15, -41};
        int expected = -1;
        int result = productSign(nums);
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.println("Expected Output: " + expected);
        System.out.println("Actual Output: " + result);
    }
}
