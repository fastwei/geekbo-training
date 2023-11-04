package com.geekbo.training.leetcode.binarysearch;

import java.util.Arrays;

/**
 * 611. Valid Triangle Number
 * <p>
 * Given an integer array nums, return the number of triplets chosen from the array
 * that can make triangles if we take them as side lengths of a triangle.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Example 2:
 * <p>
 * Input: nums = [4,2,3,4]
 * Output: 4
 */
public class TriangleCount {
    /**
     * 解题思路：
     * <p>
     * 首先，对数组进行排序，以便能够方便地判断三个数是否能够构成一个三角形。
     * 然后，从数组的最后一个元素开始，依次遍历。
     * 对于当前遍历到的元素nums[i]，使用两个指针left和right分别指向数组的起始和结束位置。
     * 在每一次遍历过程中，我们需要判断nums[left]+nums[right]是否大于nums[i]。
     * 如果大于，则说明nums[left]和nums[right]与nums[i]可以构成一个三角形，因为数组已经排序，
     * 所以nums[left]到nums[right-1]与nums[i]也可以构成三角形。
     * 如果nums[left]+nums[right]>nums[i]，则将count增加right-left，然后将right指针向左移动一位；
     * 如果nums[left]+nums[right]<=nums[i]，则将left指针向右移动一位。
     * 最终，返回count的值，即可得到能够构成三角形的三元组的数量。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：数组排序的时间复杂度为O(nlogn)，其中n为数组的长度。之后的遍历过程时间复杂度为O(n^2)。
     * 因此，总的时间复杂度为O(n^2)。
     * 空间复杂度：仅使用了常数个额外变量，因此空间复杂度为O(1)。
     *
     * @param nums
     * @return
     */
    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums); // Sort the array in ascending order
        int count = 0;

        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;

            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    // If the sum of the two smaller sides is greater than the largest side,
                    // then all the combinations with the smaller side and the larger side will also be valid
                    count += right - left;
                    right--; // Move the right pointer to the left
                } else {
                    left++; // Move the left pointer to the right
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {2, 2, 3, 4};
        int expected1 = 3;
        int result1 = triangleNumber(nums1);
        System.out.println("Test case 1:");
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);
        System.out.println();

        // Test case 2
        int[] nums2 = {4, 2, 3, 4};
        int expected2 = 4;
        int result2 = triangleNumber(nums2);
        System.out.println("Test case 2:");
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);
    }
}
