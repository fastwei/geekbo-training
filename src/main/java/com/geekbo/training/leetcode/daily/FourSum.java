package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers,
 * return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 */
public class FourSum {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums); // Sort the input array in ascending order.
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, (long) target, 0, result, temp, 4); // Use long data type for target.
        return result; // Return the result list containing unique quadruplets.
    }

    private static void helper(int[] nums, long target, int start, List<List<Integer>> result, List<Integer> temp, int numNeed) {
        if (numNeed != 2) {
            for (int i = start; i < nums.length - numNeed + 1; i++) {
                if (i > start && nums[i] == nums[i - 1]) {
                    continue; // Skip duplicates to avoid duplicate combinations.
                }
                temp.add(nums[i]); // Add the current number to the combination.
                helper(nums, target - nums[i], i + 1, result, temp, numNeed - 1); // Recursively find the next number(s).
                temp.remove(temp.size() - 1); // Remove the last number to backtrack.
            }
            return;
        }

        // If we need exactly 2 numbers, perform a two-pointer approach.
        int l = start;
        int r = nums.length - 1;
        while (l < r) {
            long total = (long) nums[l] + nums[r];
            if (total < target) {
                l++;
            } else if (total > target) {
                r--;
            } else {
                temp.add(nums[l]); // Add the left number to the combination.
                temp.add(nums[r]); // Add the right number to the combination.
                result.add(new ArrayList<>(temp)); // Store the valid quadruplet in the result list.
                temp.remove(temp.size() - 1); // Remove the right number to backtrack.
                temp.remove(temp.size() - 1); // Remove the left number to backtrack.
                l++;
                r--;
                while (l < r && nums[l] == nums[l - 1]) {
                    l++; // Skip duplicates on the left.
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        System.out.println("Test Case 1 - Expected: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]], Actual: " + fourSum(nums1, target1));

        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println("Test Case 2 - Expected: [[2,2,2,2]], Actual: " + fourSum(nums2, target2));
    }
}
