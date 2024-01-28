package com.geekbo.training.leetcode.contest;

import java.util.*;

//381.2
public class BinarySortChecker {
    /**
     * 检查是否可以通过交换使数组有序。
     *
     * @param nums 要检查的数组。
     * @return 如果可以通过交换使数组有序，则为 true；否则为 false。
     */
    public static boolean canSortArray(int[] nums) {
        int n = nums.length;
        Integer[] sortedNums = new Integer[n];

        // 复制原数组
        for (int i = 0; i < n; i++) {
            sortedNums[i] = nums[i];
        }

        // 按照二进制表示中1的个数对数组进行排序
        Arrays.sort(sortedNums, (a, b) -> Integer.compare(countOnes(a), countOnes(b)));

        // 检查排序后的数组是否与原数组一致
        for (int i = 0; i < n; i++) {
            if (nums[i] != sortedNums[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算一个整数的二进制表示中1的个数。
     *
     * @param n 要计算的数字。
     * @return n的二进制表示中1的个数。
     */
    private static int countOnes(int n) {
        return Integer.bitCount(n);
    }

    public static void main(String[] args) {
        // 测试用例
        int[] example1 = {8, 4, 2, 30, 15};
        int[] example2 = {1, 2, 3, 4, 5};
        int[] example3 = {3, 16, 8, 4, 2};
        int[] example4 = {20,16};
        int[] example5 = {160,247,127};


        // 测试结果
        System.out.println("Example 1 (Expected: true): " + canSortArray(example1));
        System.out.println("Example 2 (Expected: true): " + canSortArray(example2));
        System.out.println("Example 3 (Expected: false): " + canSortArray(example3));
        System.out.println("Example 4 (Expected: false): " + canSortArray(example4));
        System.out.println("Example 5 (Expected: false): " + canSortArray(example5));
    }
}
