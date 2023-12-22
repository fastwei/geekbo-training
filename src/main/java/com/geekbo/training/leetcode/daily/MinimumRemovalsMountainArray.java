package com.geekbo.training.leetcode.daily;

/**
 * 1671. Minimum Number of Removals to Make Mountain Array
 * Hard
 *
 * Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.
 *
 * Example:
 * Input: nums = [2,1,1,5,6,2,3,1]
 * Output: 3
 * Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
 */
public class MinimumRemovalsMountainArray {

    public static int minimumMountainRemovals(int[] nums) {
        int n = nums.length;

        // Initialize arrays to store the length of increasing and decreasing subsequences ending at each index
        int[] increasing = new int[n];
        int[] decreasing = new int[n];

        // Calculate the length of the longest increasing subsequence ending at each index
        for (int i = 0; i < n; i++) {
            increasing[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    increasing[i] = Math.max(increasing[i], increasing[j] + 1);
                }
            }
        }

        // Calculate the length of the longest decreasing subsequence starting at each index
        for (int i = n - 1; i >= 0; i--) {
            decreasing[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    decreasing[i] = Math.max(decreasing[i], decreasing[j] + 1);
                }
            }
        }

        // Find the maximum length of the mountain subarray
        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            if (increasing[i] > 1 && decreasing[i] > 1) {
                maxLength = Math.max(maxLength, increasing[i] + decreasing[i] - 1);
            }
        }

        // Calculate the minimum number of elements to remove
        return n - maxLength;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 3, 1};
        int expected1 = 0;
        int result1 = minimumMountainRemovals(nums1);
        System.out.println(result1 == expected1 ? "Pass" : "Fail");

        // Test case 2
        int[] nums2 = {2, 1, 1, 5, 6, 2, 3, 1};
        int expected2 = 3;
        int result2 = minimumMountainRemovals(nums2);
        System.out.println(result2 == expected2 ? "Pass" : "Fail");
    }
}