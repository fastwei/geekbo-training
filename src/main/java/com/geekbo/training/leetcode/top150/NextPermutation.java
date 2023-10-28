package com.geekbo.training.leetcode.top150;

import java.util.Arrays;

/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * <p>
 * For example, for arr = [1,2,3], the following are
 * all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer.
 * More formally, if all the permutations of the array are sorted in one container according to their lexicographical order,
 * then the next permutation of that array is the permutation that follows it in the sorted container.
 * If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 * <p>
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 * <p>
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 */
public class NextPermutation {

    /**
     * 解题思路：
     * <p>
     * 首先从右向左找到第一个减小的元素，记为nums[i]，此时nums[i+1:]是降序排列。
     * 如果找不到减小的元素，说明整个数组是降序排列的，直接将数组反转即可得到最小的排列。
     * 否则，从右向左找到第一个大于nums[i]的元素，记为nums[j]，将nums[i]和nums[j]交换。
     * 最后将nums[i+1:]部分反转，得到下一个排列。
     * <p>
     * 算法的时间复杂度为O(n)，其中n是数组的长度。
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        // Find the first decreasing element from the right
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // If no decreasing element is found, reverse the entire array
        if (i < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // Find the next greater element than nums[i] from the right
        int j = nums.length - 1;
        while (j > i && nums[j] <= nums[i]) {
            j--;
        }

        // Swap nums[i] and nums[j]
        swap(nums, i, j);

        // Reverse the elements from i+1 to the end of the array
        reverse(nums, i + 1, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 2, 3};
        nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1));
        // Output: [1, 3, 2]

        // Test case 2
        int[] nums2 = {3, 2, 1};
        nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2));
        // Output: [1, 2, 3]

        // Test case 3
        int[] nums3 = {1, 1, 5};
        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3));
        // Output: [1, 5, 1]
    }
}
