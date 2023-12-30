package com.geekbo.training.leetcode.crackinterview109;

import java.util.Arrays;

/**
 *
 * 面试题 10.11. 峰与谷
 * 已解答
 * 中等
 * 在一个整数数组中，“峰”是大于或等于相邻整数的元素，相应地，“谷”是小于或等于相邻整数的元素。
 * 例如，在数组{5, 8, 4, 2, 3, 4, 6}中，{8, 6}是峰， {5, 2}是谷。
 * 现在给定一个整数数组，将该数组按峰与谷的交替顺序排序。
 *
 * 示例:
 *
 * 输入: [5, 3, 1, 2, 3]
 * 输出: [5, 1, 3, 2, 3]
 * 提示：
 *
 * nums.length <= 10000
 *
 */
public class PeakAndValley {

    public static void main(String[] args) {
        int[] nums = {5, 3, 1, 2, 3};
        System.out.println(Arrays.toString(peakAndValley(nums)));
    }

    /**
     * 将数组按峰与谷的交替顺序排序
     * 解题思路：
     * 首先对数组进行排序，然后遍历数组，每次取出两个元素，将较大的元素放到奇数位置，较小的元素放到偶数位置。
     * 时间复杂度：O(nlogn)，其中n为数组的长度，主要是排序的时间复杂度。
     * 空间复杂度：O(1)
     *
     * @param nums 给定的整数数组
     * @return 排序后的数组
     */
    public static int[] peakAndValley(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
        return nums;
    }

    /**
     * 交换数组中两个位置的元素
     *
     * @param nums 数组
     * @param i    位置i
     * @param j    位置j
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}