package com.geekbo.training.leetcode.daily;


import java.util.HashMap;
import java.util.Map;

/**
 * 1814. Count Nice Pairs in an Array
 * Medium
 * You are given an array nums that consists of non-negative integers.
 * Let us define rev(x) as the reverse of the non-negative integer x. For example, rev(123) = 321,
 * and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all of the following conditions:
 * <p>
 * 0 <= i < j < nums.length
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
 * Return the number of nice pairs of indices. Since that number can be too large,
 * return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [42,11,1,97]
 * Output: 2
 * Explanation: The two pairs are:
 * - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
 * - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
 * Example 2:
 * <p>
 * Input: nums = [13,10,35,24,76]
 * Output: 4
 */
public class CountNicePairs {
    public int countNicePairs(int[] nums) {
        int count = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        int mod = (int) 1e9 + 7;

        for (int num : nums) {
            int revNum = reverseNum(num);
            int diff = num - revNum;
            count = (count + freqMap.getOrDefault(diff, 0)) % mod;
            freqMap.put(diff, freqMap.getOrDefault(diff, 0) + 1);
        }

        return count;
    }

    private int reverseNum(int num) {
        int revNum = 0;
        while (num > 0) {
            revNum = revNum * 10 + num % 10;
            num /= 10;
        }
        return revNum;
    }

    public static void main(String[] args) {
        CountNicePairs solution = new CountNicePairs();

        int[] nums1 = {42, 11, 1, 97};
        System.out.println(solution.countNicePairs(nums1));  // Output: 2

        int[] nums2 = {13, 10, 35, 24, 76};
        System.out.println(solution.countNicePairs(nums2));  // Output: 4
    }
}