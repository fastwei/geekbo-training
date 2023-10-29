package com.geekbo.training.leetcode.template;

/**
 * binarySearch方法接收一个已排序的整数数组nums和目标值target作为输入。
 * 它使用了左右两个指针来缩小搜索范围，直到找到目标值或搜索范围为空。
 * 如果找到目标值，则返回其索引；否则，返回-1表示未找到。
 *
 */
public class BinarySearchTemplate {
    public int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // 如果未找到目标元素，则返回-1
    }
}