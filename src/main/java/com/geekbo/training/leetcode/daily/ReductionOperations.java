package com.geekbo.training.leetcode.daily;

/**
 * 1887. Reduction Operations to Make the Array Elements Equal
 * Solved
 * Medium
 * Topics
 * Companies
 * Hint
 * Given an integer array nums, your goal is to make all elements in nums equal. To complete one operation, follow these steps:
 * <p>
 * Find the largest value in nums. Let its index be i (0-indexed) and its value be largest. If there are multiple elements with the largest value, pick the smallest i.
 * Find the next largest value in nums strictly smaller than largest. Let its value be nextLargest.
 * Reduce nums[i] to nextLargest.
 * Return the number of operations to make all elements in nums equal.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,1,3]
 * Output: 3
 * Explanation: It takes 3 operations to make all elements in nums equal:
 * 1. largest = 5 at index 0. nextLargest = 3. Reduce nums[0] to 3. nums = [3,1,3].
 * 2. largest = 3 at index 0. nextLargest = 1. Reduce nums[0] to 1. nums = [1,1,3].
 * 3. largest = 3 at index 2. nextLargest = 1. Reduce nums[2] to 1. nums = [1,1,1].
 * Example 2:
 * <p>
 * Input: nums = [1,1,1]
 * Output: 0
 * Explanation: All elements in nums are already equal.
 * Example 3:
 * <p>
 * Input: nums = [1,1,2,2,3]
 * Output: 4
 * Explanation: It takes 4 operations to make all elements in nums equal:
 * 1. largest = 3 at index 4. nextLargest = 2. Reduce nums[4] to 2. nums = [1,1,2,2,2].
 * 2. largest = 2 at index 2. nextLargest = 1. Reduce nums[2] to 1. nums = [1,1,1,2,2].
 * 3. largest = 2 at index 3. nextLargest = 1. Reduce nums[3] to 1. nums = [1,1,1,1,2].
 * 4. largest = 2 at index 4. nextLargest = 1. Reduce nums[4] to 1. nums = [1,1,1,1,1].
 */
public class ReductionOperations {
    public static int reductionOperations(int[] nums) {
        int n = nums.length;
        int[] freq = new int[50001];
        for (int i = 0; i < n; i++) {
            freq[nums[i]]++;
        }
        int res = 0, operations = 0;
        for (int i = 50000; i >= 1; i--) {
            if (freq[i] > 0) {
                operations += freq[i];
                res += operations - freq[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {5, 1, 3};
        // 预期输出: 3
        System.out.println("Input: nums = [5,1,3]");
        System.out.println("Output: " + reductionOperations(nums1));

        int[] nums2 = {1, 1, 1};
        // 预期输出: 0
        System.out.println("\nInput: nums = [1,1,1]");
        System.out.println("Output: " + reductionOperations(nums2));

        int[] nums3 = {1, 1, 2, 2, 3};
        // 预期输出: 4
        System.out.println("\nInput: nums = [1,1,2,2,3]");
        System.out.println("Output: " + reductionOperations(nums3));
    }
}