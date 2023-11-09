package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 315. Count of Smaller Numbers After Self
 * Hard

 * Given an integer array nums, return an integer array counts where counts[i] is the number of smaller elements to the right of nums[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 *
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 *
 * Input: nums = [-1,-1]
 * Output: [0,0]
 *
 */
public class CountOfSmallerNumbersAfterSelf {
    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n];
        int[] sortedNums = new int[n];
        int[] indexes = new int[n];

        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }

        mergeSort(nums, indexes, counts, sortedNums, 0, n - 1);

        List<Integer> result = new ArrayList<>();
        for (int count : counts) {
            result.add(count);
        }
        return result;
    }

    private static void mergeSort(int[] nums, int[] indexes, int[] counts, int[] sortedNums, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, indexes, counts, sortedNums, start, mid);
        mergeSort(nums, indexes, counts, sortedNums, mid + 1, end);
        merge(nums, indexes, counts, sortedNums, start, mid, end);
    }

    private static void merge(int[] nums, int[] indexes, int[] counts, int[] sortedNums, int start, int mid, int end) {
        int left = start;
        int right = mid + 1;
        int rightCount = 0;
        int sortedIndex = 0;
        int[] newIndexes = new int[end - start + 1];

        while (left <= mid && right <= end) {
            if (nums[indexes[right]] < nums[indexes[left]]) {
                newIndexes[sortedIndex] = indexes[right];
                rightCount++;
                right++;
            } else {
                newIndexes[sortedIndex] = indexes[left];
                counts[indexes[left]] += rightCount;
                left++;
            }
            sortedIndex++;
        }

        while (left <= mid) {
            newIndexes[sortedIndex] = indexes[left];
            counts[indexes[left]] += rightCount;
            left++;
            sortedIndex++;
        }

        while (right <= end) {
            newIndexes[sortedIndex] = indexes[right];
            right++;
            sortedIndex++;
        }

        for (int i = start; i <= end; i++) {
            indexes[i] = newIndexes[i - start];
        }
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {5, 2, 6, 1};
        List<Integer> result1 = countSmaller(nums1);
        System.out.println("测试用例1: " + result1); // 预期输出: [2, 1, 1, 0]

        // 测试用例2
        int[] nums2 = {-1};
        List<Integer> result2 = countSmaller(nums2);
        System.out.println("测试用例2: " + result2); // 预期输出: [0]

        // 测试用例3
        int[] nums3 = {-1, -1};
        List<Integer> result3 = countSmaller(nums3);
        System.out.println("测试用例3: " + result3); // 预期输出: [0, 0]
    }
}
