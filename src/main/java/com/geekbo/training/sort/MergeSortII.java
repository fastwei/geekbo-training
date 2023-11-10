package com.geekbo.training.sort;

import java.util.Arrays;

/**
 * 912. Sort an Array
 * Given an array of integers nums, sort the array in ascending order and return it.
 * <p>
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity
 * and with the smallest space complexity possible.
 */
public class MergeSortII {
    public static int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void mergeSort(int[] nums, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, mid, right);
        }
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k] = nums[i];
                i++;
            } else {
                temp[k] = nums[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = nums[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = nums[j];
            j++;
            k++;
        }

        for (int m = 0; m < temp.length; m++) {
            nums[left + m] = temp[m];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {5, 2, 3, 1};
        int[] sortedNums1 = sortArray(nums1);
        System.out.println(Arrays.toString(sortedNums1));  // Output: [1, 2, 3, 5]

        int[] nums2 = {5, 1, 1, 2, 0, 0};
        int[] sortedNums2 = sortArray(nums2);
        System.out.println(Arrays.toString(sortedNums2));  // Output: [0, 0, 1, 1, 2, 5]
    }
}