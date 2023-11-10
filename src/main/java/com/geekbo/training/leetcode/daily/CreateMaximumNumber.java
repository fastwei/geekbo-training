package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 321. Create Maximum Number
 * Hard
 * You are given two integer arrays nums1 and nums2 of lengths m and n respectively.
 * nums1 and nums2 represent the digits of two numbers. You are also given an integer k.
 * <p>
 * Create the maximum number of length k <= m + n from digits of the two numbers.
 * The relative order of the digits from the same array must be preserved.
 * <p>
 * Return an array of the k digits representing the answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
 * Output: [9,8,6,5,3]
 * Example 2:
 * <p>
 * Input: nums1 = [6,7], nums2 = [6,0,4], k = 5
 * Output: [6,7,6,0,4]
 * Example 3:
 * <p>
 * Input: nums1 = [3,9], nums2 = [8,9], k = 3
 * Output: [9,8,9]
 */
public class CreateMaximumNumber {
    /**
     * 拼接最大数
     * 解题思路：
     * - 该问题可以分解为两个子问题：
     * 1. 从一个数组中选择长度为 k 的子序列，使得该子序列的数字组成最大。
     * 2. 合并两个子序列，使得合并后的序列最大。
     * - 对于问题1，我们可以使用单调栈的方法，从原数组中选择尽可能大的数字。具体做法如下：
     * - 维护一个单调递减的栈，遍历原数组，如果当前元素大于栈顶的元素，就将栈顶元素弹出，
     * 直到栈为空或者栈顶元素大于当前元素。
     *
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @param k     需要拼接的数字个数
     * @return 拼接后的最大数
     */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[k];
        for (int i = Math.max(0, k - n); i <= k && i <= m; i++) {
            int[] sequence1 = getMaxSequence(nums1, i);
            int[] sequence2 = getMaxSequence(nums2, k - i);
            int[] merged = merge(sequence1, sequence2);
            if (compare(merged, 0, result, 0) > 0) {
                result = merged;
            }
        }
        return result;
    }

    /**
     * 在给定的数组中选择长度为k的子序列，使得子序列的数字组成最大
     *
     * @param nums 数组
     * @param k    子序列的长度
     * @return 最大子序列
     */
    private static int[] getMaxSequence(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[k];
        int i = 0, j = 0;
        for (; i < n; i++) {
            while (n - i + j > k && j > 0 && result[j - 1] < nums[i]) {
                j--;
            }
            if (j < k) {
                result[j++] = nums[i];
            }
        }
        return result;
    }

    /**
     * 合并两个序列，使得合并后的序列最大
     *
     * @param nums1 第一个序列
     * @param nums2 第二个序列
     * @return 合并后的最大序列
     */
    private static int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m == 0) {
            return nums2;
        }
        if (n == 0) {
            return nums1;
        }
        int[] result = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (compare(nums1, i, nums2, j) > 0) {
                result[k++] = nums1[i++];
            } else {
                result[k++] = nums2[j++];
            }
        }
        while (i < m) {
            result[k++] = nums1[i++];
        }
        while (j < n) {
            result[k++] = nums2[j++];
        }
        return result;
    }

    /**
     * 比较两个序列的大小
     *
     * @param nums1  第一个序列
     * @param start1 第一个序列的起始位置
     * @param nums2  第二个序列
     * @param start2 第二个序列的起始位置
     * @return 如果第一个序列大于第二个序列，返回正数；如果第一个序列小于第二个序列，返回负数；如果两个
     * <p>
     * <p>
     * 序列相等，返回0。
     */
    private static int compare(int[] nums1, int start1, int[] nums2, int start2) {
        int m = nums1.length;
        int n = nums2.length;
        while (start1 < m && start2 < n) {
            int diff = nums1[start1] - nums2[start2];
            if (diff != 0) {
                return diff;
            }
            start1++;
            start2++;
        }
        return (m - start1) - (n - start2);
    }


    public static void main(String[] args) {
        int[] nums11 = {3, 4, 6, 5};
        int[] nums12 = {9, 1, 2, 5, 8, 3};
        int k1 = 5;
        // 从nums1中取6和5，从nums2中取9、8和3，拼接后得到的最大数为9、8、6、5、3
        int[] expected1 = {9, 8, 6, 5, 3};

        int[] nums21 = {6, 7};
        int[] nums22 = {6, 0, 4};
        int k2 = 5;
        // 从nums1中取6和7，从nums2中取6、0和4，拼接后得到的最大数为6、7、6、0、4
        int[] expected2 = {6, 7, 6, 0, 4};

        int[] nums31 = {3, 9};
        int[] nums32 = {8, 9};
        int k3 = 3;
        // 从nums1中取9，从nums2中取8和9，拼接后得到的最大数为9、8、9
        int[] expected3 = {9, 8, 9};

        // 测试用例1
        int[] result1 = maxNumber(nums11, nums12, k1);
        System.out.println(Arrays.toString(result1)); // 输出：[9, 8, 6, 5, 3]
        System.out.println(Arrays.equals(result1, expected1)); // true

        // 测试用例2
        int[] result2 = maxNumber(nums21, nums22, k2);
        System.out.println(Arrays.toString(result2)); // 输出：[6, 7, 6, 0, 4]
        System.out.println(Arrays.equals(result2, expected2)); // true

        // 测试用例3
        int[] result3 = maxNumber(nums31, nums32, k3);
        System.out.println(Arrays.toString(result3)); // 输出：[9, 8, 9]
        System.out.println(Arrays.equals(result3, expected3)); // true
    }
}
