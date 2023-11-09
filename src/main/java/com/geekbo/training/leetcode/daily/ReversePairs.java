package com.geekbo.training.leetcode.daily;

/**
 * <p>
 * 493. Reverse Pairs
 * Hard
 * Given an integer array nums, return the number of reverse pairs in the array.
 * <p>
 * A reverse pair is a pair (i, j) where:
 * <p>
 * 0 <= i < j < nums.length and
 * nums[i] > 2 * nums[j].
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
 * Example 2:
 * <p>
 * Input: nums = [2,4,3,5,1]
 * Output: 3
 * Explanation: The reverse pairs are:
 * (1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
 * (2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
 * (3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 */
class ReversePairs {
    /**
     * uses the merge sort algorithm to count the number of reverse pairs in the given array.
     * It recursively divides the array into two halves, sorts them,
     * and then merges them while counting the reverse pairs.
     * The merge step is where the reverse pairs are counted.
     * The time complexity of this algorithm is O(n log n), where n is the length of the array.
     *
     * @param nums
     * @return
     */
    public static int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);

        int[] merged = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if ((long) nums[i] > 2 * (long) nums[j]) {
                count += mid - i + 1;
                j++;
            } else {
                i++;
            }
        }

        i = left;
        j = mid + 1;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                merged[k++] = nums[i++];
            } else {
                merged[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            merged[k++] = nums[i++];
        }

        while (j <= right) {
            merged[k++] = nums[j++];
        }

        System.arraycopy(merged, 0, nums, left, merged.length);

        return count;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {1, 3, 2, 3, 1};
        System.out.println("Test case 1: " + reversePairs(nums1)); // Expected output: 2

        // 测试用例2
        int[] nums2 = {2, 4, 3, 5, 1};
        System.out.println("Test case 2: " + reversePairs(nums2)); // Expected output: 3
    }
}
