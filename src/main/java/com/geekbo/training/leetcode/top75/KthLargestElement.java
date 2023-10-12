package com.geekbo.training.leetcode.top75;

/**
 *
 * 215. Kth Largest Element in an Array
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Can you solve it without sorting?
 *
 * 题目：寻找数组中的第K大元素
 * 给定一个整数数组 nums 和一个整数 k，返回数组中第 k 个最大的元素。
 * 注意，它是排序后的第 k 个最大元素，而不是第 k 个不同的元素。
 * 
 * 示例 1：
 * 输入: nums = [3,2,1,5,6,4], k = 2
 * 输出: 5
 * 
 * 示例 2：
 * 输入: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 */
public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        // 使用快速选择算法，类似快速排序的思想
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    // 快速选择算法
    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        int pivotIndex = partition(nums, left, right);

        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1;
        int r = right;

        while (true) {
            while (l <= r && nums[l] >= pivot) {
                l++;
            }
            while (l <= r && nums[r] <= pivot) {
                r--;
            }
            if (l <= r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
            } else {
                break;
            }
        }

        int temp = nums[left];
        nums[left] = nums[r];
        nums[r] = temp;

        return r;
    }

    public static void main(String[] args) {
        KthLargestElement solution = new KthLargestElement();

        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        int result1 = solution.findKthLargest(nums1, k1);
        System.out.println(result1); // 预期输出: 5

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        int result2 = solution.findKthLargest(nums2, k2);
        System.out.println(result2); // 预期输出: 4
    }
}
