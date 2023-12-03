package com.geekbo.training.sort;

import java.util.Arrays;

/**
 * 2426. Number of Pairs Satisfying Inequality
 * Hard
 * You are given two 0-indexed integer arrays nums1 and nums2, each of size n,
 * and an integer diff. Find the number of pairs (i, j) such that:
 * <p>
 * 0 <= i < j <= n - 1 and
 * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
 * Return the number of pairs that satisfy the conditions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
 * Output: 3
 * Explanation:
 * There are 3 pairs that satisfy the conditions:
 * 1. i = 0, j = 1: 3 - 2 <= 2 - 2 + 1. Since i < j and 1 <= 1, this pair satisfies the conditions.
 * 2. i = 0, j = 2: 3 - 5 <= 2 - 1 + 1. Since i < j and -2 <= 2, this pair satisfies the conditions.
 * 3. i = 1, j = 2: 2 - 5 <= 2 - 1 + 1. Since i < j and -3 <= 2, this pair satisfies the conditions.
 * Therefore, we return 3.
 * Example 2:
 * <p>
 * Input: nums1 = [3,-1], nums2 = [-2,2], diff = -1
 * Output: 0
 * Explanation:
 * Since there does not exist any pair that satisfies the conditions, we return 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums1.length == nums2.length
 * 2 <= n <= 105
 * -104 <= nums1[i], nums2[i] <= 104
 * -104 <= diff <= 104
 */
public class NumberOfPairs {
    long ans = 0;
    int dif;

    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        dif = diff;
        int[] nums = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            nums[i] = nums1[i] - nums2[i];
        }

        mergeSort(nums);
        return ans;
    }

    public void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(arr, left, right);
        }
    }

    public void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, idx = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                int id = binarySearch(right, left[i] - dif);
                arr[idx++] = left[i++];
                ans += right.length - id;
            } else {
                arr[idx++] = right[j++];
            }
        }

        while (i < left.length) {
            int id = binarySearch(right, left[i] - dif);
            arr[idx++] = left[i++];
            ans += right.length - id;
        }

        while (j < right.length) {
            arr[idx++] = right[j++];
        }
    }

    public int binarySearch(int[] arr, int val) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] >= val) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        NumberOfPairs solution = new NumberOfPairs();

        int[] nums1 = {3, 2, 5};
        int[] nums2 = {2, 2, 1};
        int diff = 1;
        System.out.println("Test Case 1: " + solution.numberOfPairs(nums1, nums2, diff)); // Output: 3

        int[] nums3 = {3, -1};
        int[] nums4 = {-2, 2};
        int diff2 = -1;
        System.out.println("Test Case 2: " + solution.numberOfPairs(nums3, nums4, diff2)); // Output: 0
    }
}