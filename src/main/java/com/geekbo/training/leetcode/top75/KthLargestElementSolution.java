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
 *
 * 解题思路：
 *
 * 使用快速选择算法（QuickSelect），该算法通过分区函数将数组分为两部分，然后根据k的大小决定在哪一部分继续查找。这样可以找到第k大的元素。
 * 算法复杂度分析：
 *
 * 时间复杂度：平均情况下为O(n)，最坏情况下为O(n^2)。
 * 空间复杂度：O(1)，只使用常数额外空间。
 *
 * todo:有空再细看
 *
 */
public class KthLargestElementSolution {
    public int findKthLargest(int[] nums, int k) {
        // 使用快速选择算法，寻找第k大的元素
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int pivotIndex = partition(nums, left, right);

            // 如果第k大的元素的位置等于k-1，则返回该元素
            if (pivotIndex == k - 1) {
                return nums[pivotIndex];
            } else if (pivotIndex < k - 1) {
                // 如果当前位置小于k-1，说明第k大的元素在右侧
                left = pivotIndex + 1;
            } else {
                // 如果当前位置大于k-1，说明第k大的元素在左侧
                right = pivotIndex - 1;
            }
        }

        return -1; // 出错时返回-1
    }

    // 快速选择算法的分区函数
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1;
        int r = right;

        while (true) {
            while (l <= r && nums[l] >= pivot) l++;
            while (l <= r && nums[r] <= pivot) r--;
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
        KthLargestElementSolution solution = new KthLargestElementSolution();

        // 测试用例1
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        int result1 = solution.findKthLargest(nums1, k1);
        System.out.println(result1); // 预期输出: 5

        // 测试用例2
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        int result2 = solution.findKthLargest(nums2, k2);
        System.out.println(result2); // 预期输出: 4
    }
}
