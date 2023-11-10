package com.geekbo.training.leetcode.daily;

/**
 * 268. Missing Number
 * Easy
 * Given an array nums containing n distinct numbers in the range [0, n],
 * return the only number in the range that is missing from the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 * <p>
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 */
public class MissingNumber {
    /**
     * We first calculate the expected sum of the numbers in the range [0, n]
     * using the formula n * (n + 1) / 2, where n is the length of the array.
     * This is the sum of all numbers that should be present in the array if no number is missing.
     * <p>
     * Then, we calculate the actual sum of the numbers in the array by iterating over
     * each number in nums and adding it to actualSum.
     * <p>
     * Finally, we return the difference between the expected sum and the actual sum,
     * which gives us the missing number.
     * <p>
     * <p>
     * This solution has a time complexity of O(n) and uses O(1) extra space.
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 0, 1};
        System.out.println(missingNumber(nums1));  // Output: 2

        int[] nums2 = {0, 1};
        System.out.println(missingNumber(nums2));  // Output: 2

        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(missingNumber(nums3));  // Output: 8
    }
}