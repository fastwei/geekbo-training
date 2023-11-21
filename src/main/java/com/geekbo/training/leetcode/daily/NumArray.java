package com.geekbo.training.leetcode.daily;

/**
 * 307. Range Sum Query - Mutable
 * Medium
 * Given an integer array nums, handle multiple queries of the following types:
 * <p>
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 * <p>
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 * <p>
 * <p>
 * We can optimize the solution by using a segment tree data structure.
 */
class NumArray {
    private int[] nums;
    private int[] segmentTree;
    private int n;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        this.segmentTree = new int[4 * n];
        buildSegmentTree(0, n - 1, 0);
    }

    public void update(int index, int val) {
        updateSegmentTree(0, n - 1, index, val, 0);
    }

    public int sumRange(int left, int right) {
        return querySegmentTree(0, n - 1, left, right, 0);
    }

    private void buildSegmentTree(int start, int end, int current) {
        if (start == end) {
            segmentTree[current] = nums[start];
            return;
        }

        int mid = start + (end - start) / 2;
        buildSegmentTree(start, mid, 2 * current + 1);
        buildSegmentTree(mid + 1, end, 2 * current + 2);

        segmentTree[current] = segmentTree[2 * current + 1] + segmentTree[2 * current + 2];
    }

    private void updateSegmentTree(int start, int end, int index, int val, int current) {
        if (start == end) {
            segmentTree[current] = val;
            return;
        }

        int mid = start + (end - start) / 2;
        if (index <= mid) {
            updateSegmentTree(start, mid, index, val, 2 * current + 1);
        } else {
            updateSegmentTree(mid + 1, end, index, val, 2 * current + 2);
        }

        segmentTree[current] = segmentTree[2 * current + 1] + segmentTree[2 * current + 2];
    }

    private int querySegmentTree(int start, int end, int left, int right, int current) {
        if (start > right || end < left) {
            return 0;
        }

        if (left <= start && right >= end) {
            return segmentTree[current];
        }

        int mid = start + (end - start) / 2;
        int leftSum = querySegmentTree(start, mid, left, right, 2 * current + 1);
        int rightSum = querySegmentTree(mid + 1, end, left, right, 2 * current + 2);

        return leftSum + rightSum;
    }
}