package com.geekbo.training.leetcode.contest;
//381.2
public class ArraySorter {
    /**
     * 判断是否可以通过交换相邻元素来对数组进行排序。
     * 每次操作可以交换具有相同位数的相邻元素。
     * 
     * @param nums 给定的整数数组
     * @return 如果可以排序，则返回 true；否则返回 false。
     */
    public boolean canSortArray(int[] nums) {
        int n = nums.length;
        int[] bitCounts = new int[n];
        
        // 计算每个元素的位数，并存储在 bitCounts 数组中
        for (int i = 0; i < n; i++) {
            bitCounts[i] = Integer.bitCount(nums[i]);
        }

        // 检查通过交换是否可以排序数组
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (bitCounts[i] > bitCounts[j]) {
                    // 如果找到位数更少的元素，则无法排序
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArraySorter sorter = new ArraySorter();

        // 测试用例
        int[] nums1 = {8, 4, 2, 30, 15};
        int[] nums2 = {1, 2, 3, 4, 5};
        int[] nums3 = {3, 16, 8, 4, 2};

        // 输出预期结果
        System.out.println("Test case 1: " + sorter.canSortArray(nums1)); // 预期输出：true
        System.out.println("Test case 2: " + sorter.canSortArray(nums2)); // 预期输出：true
        System.out.println("Test case 3: " + sorter.canSortArray(nums3)); // 预期输出：false
    }
}
