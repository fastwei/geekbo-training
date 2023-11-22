package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 324. Wiggle Sort II
 * Medium
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * You may assume the input array always has a valid answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 * Example 2:
 * <p>
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5000
 * It is guaranteed that there will be an answer for the given input nums.
 * <p>
 * <p>
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
class WiggleSortII {
    /**
     * 可以通过先排序数组，然后将较大的一半元素放在奇数索引位置，
     * 较小的一半元素放在偶数索引位置来实现。
     */
    public void wiggleSort(int[] nums) {
        int n = nums.length;

        // Copy the original array and sort it
        int[] sorted = Arrays.copyOf(nums, n);
        Arrays.sort(sorted);

        int i = (n + 1) / 2 - 1; // Index for the larger half of the sorted array
        int j = n - 1; // Index for the smaller half of the sorted array

        // Reorder the elements in the original array
        for (int k = 0; k < n; k++) {
            if (k % 2 == 0) {
                nums[k] = sorted[i--];
            } else {
                nums[k] = sorted[j--];
            }
        }
    }

    public static void main(String[] args) {
        WiggleSortII solution = new WiggleSortII();

        int[] nums1 = {1, 5, 1, 1, 6, 4};
        solution.wiggleSort(nums1);
        System.out.println(Arrays.toString(nums1)); // Expected output: [1,6,1,5,1,4]

        int[] nums2 = {1, 3, 2, 2, 3, 1};
        solution.wiggleSort(nums2);
        System.out.println(Arrays.toString(nums2)); // Expected output: [2,3,1,3,1,2]
    }
}