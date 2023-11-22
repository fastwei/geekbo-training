package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 2216. Minimum Deletions to Make Array Beautiful
 * You are given a 0-indexed integer array nums. The array nums is beautiful if:
 *
 * nums.length is even.
 * nums[i] != nums[i + 1] for all i % 2 == 0.
 * Note that an empty array is considered beautiful.
 *
 * You can delete any number of elements from nums. When you delete an element,
 * all the elements to the right of the deleted element will be shifted one unit
 * to the left to fill the gap created and all the elements to the left of the deleted element
 * will remain unchanged.
 *
 * Return the minimum number of elements to delete from nums to make it beautiful.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,5]
 * Output: 1
 * Explanation: You can delete either nums[0] or nums[1] to make nums = [1,2,3,5] which is beautiful.
 * It can be proven you need at least 1 deletion to make nums beautiful.
 * Example 2:
 *
 * Input: nums = [1,1,2,2,3,3]
 * Output: 2
 * Explanation: You can delete nums[0] and nums[5] to make nums = [1,2,2,3] which is beautiful.
 * It can be proven you need at least 2 deletions to make nums beautiful.
 *
 */
public class MinimumDeletionsToMakeArrayBeautiful {
    public int minimumDeletions(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int minDeletions = 0;

        for (int i = 0; i < nums.length; i += 2) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        int maxCount = 0;
        for (int count : countMap.values()) {
            maxCount = Math.max(maxCount, count);
        }

        minDeletions = nums.length / 2 - maxCount;

        return minDeletions;
    }


    public static void main(String[] args) {
        MinimumDeletionsToMakeArrayBeautiful solution = new MinimumDeletionsToMakeArrayBeautiful();

        int[] nums1 = {1, 1, 2, 3, 5};
        System.out.println(solution.minimumDeletions(nums1));  // Output: 1

        int[] nums2 = {1, 1, 2, 2, 3, 3};
        System.out.println(solution.minimumDeletions(nums2));  // Output: 2
    }
}