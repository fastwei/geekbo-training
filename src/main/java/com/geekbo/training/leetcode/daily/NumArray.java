package com.geekbo.training.leetcode.daily;

/**
 * 303. Range Sum Query - Immutable
 * Solved
 * Easy
 * Topics
 * Companies
 * Given an integer array nums, handle multiple queries of the following type:
 * <p>
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums
 * between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 * <p>
 * 使用了前缀和数组来实现，其中 prefixSum 数组用于存储从第一个元素到当前位置的元素和。
 * 在构造函数中，我们计算了 prefixSum 数组。
 * 在 sumRange 方法中，我们通过 prefixSum[right + 1] - prefixSum[left] 来计算给定范围内元素的和。
 */
class NumArray {
    private int[] prefixSum;

    public NumArray(int[] nums) {
        int n = nums.length;
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return prefixSum[right + 1] - prefixSum[left];
    }
}