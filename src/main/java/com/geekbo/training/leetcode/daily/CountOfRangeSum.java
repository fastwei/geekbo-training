package com.geekbo.training.leetcode.daily;

/**
 * 327. Count of Range Sum
 * Hard
 * Given an integer array nums and two integers lower and upper,
 * return the number of range sums that lie in [lower, upper] inclusive.
 * <p>
 * Range sum S(i, j) is defined as the sum of the elements in nums
 * between indices i and j inclusive, where i <= j.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,5,-1], lower = -2, upper = 2
 * Output: 3
 * Explanation: The three ranges are: [0,0], [2,2], and [0,2]
 * and their respective sums are: -2, -1, 2.
 * Example 2:
 * <p>
 * Input: nums = [0], lower = 0, upper = 0
 * Output: 1
 */
public class CountOfRangeSum {
    private int lower;
    private int upper;
    private int count = 0;
    private long[] prefixSums;

    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        this.lower = lower;
        this.upper = upper;

        this.prefixSums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        mergeSort(0, n);
        return count;
    }

    private void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;

        mergeSort(low, mid);
        mergeSort(mid + 1, high);

        int i = mid + 1, j = mid + 1;
        for (int k = low; k <= mid; k++) {
            while (i <= high && prefixSums[i] - prefixSums[k] < lower) {
                i++;
            }
            while (j <= high && prefixSums[j] - prefixSums[k] <= upper) {
                j++;
            }

            count += j - i;
        }

        merge(low, mid, high);
    }

    private void merge(int low, int mid, int high) {
        long[] helper = new long[high - low + 1];
        for (int i = low; i <= high; i++) {
            helper[i - low] = prefixSums[i];
        }

        int i = low, j = mid + 1;
        int idx = low;

        while (i <= mid && j <= high) {
            if (helper[i - low] < helper[j - low]) {
                prefixSums[idx++] = helper[i++ - low];
            } else {
                prefixSums[idx++] = helper[j++ - low];
            }
        }

        while (i <= mid) {
            prefixSums[idx++] = helper[i++ - low];
        }
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {-2, 5, -1};
        int lower1 = -2;
        int upper1 = 2;
        CountOfRangeSum solution = new CountOfRangeSum();
        int result1 = solution.countRangeSum(nums1, lower1, upper1);
        System.out.println("测试用例1: " + result1); // 预期输出: 3

        // 测试用例2
        int[] nums2 = {0};
        int lower2 = 0;
        int upper2 = 0;
        int result2 = solution.countRangeSum(nums2, lower2, upper2);
        System.out.println("测试用例2: " + result2); // 预期输出: 1
    }
}
