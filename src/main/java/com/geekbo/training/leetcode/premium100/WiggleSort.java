package com.geekbo.training.leetcode.premium100;

import java.util.Arrays;

/**
 *
 * 280. 摆动排序
 * 中等
 * 给你一个的整数数组 nums, 将该数组重新排序后使 nums[0] <= nums[1] >= nums[2] <= nums[3]...
 *
 * 输入数组总是有一个有效的答案。
 *
 * 示例 1:
 *
 * 输入：nums = [3,5,2,1,6,4]
 * 输出：[3,5,1,6,2,4]
 * 解释：[1,6,2,5,3,4]也是有效的答案
 * 示例 2:
 *
 * 输入：nums = [6,6,5,6,3,8]
 * 输出：[6,6,5,6,3,8]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 104
 * 输入的 nums 保证至少有一个答案。
 *
 *
 *
 * 进阶：你能在 O(n) 时间复杂度下解决这个问题吗？
 */
public class WiggleSort {

    /**
     * 解题思路：
     * 1. 首先将数组排序
     * 2. 从索引1开始，将奇数索引和前一个偶数索引的元素交换位置，保证nums[i] >= nums[i-1]
     * 3. 从索引2开始，将偶数索引和前一个奇数索引的元素交换位置，保证nums[i] <= nums[i-1]
     *
     * 算法复杂度分析：
     * 时间复杂度：O(nlogn)，其中n是数组的长度，排序的时间复杂度是O(nlogn)。
     * 空间复杂度：O(1)。
     *
     * @param nums 给定的整数数组
     */
    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }

    /**
     * 交换数组中两个元素的位置
     *
     * @param nums 数组
     * @param i    第一个元素的索引
     * @param j    第二个元素的索引
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 5, 2, 1, 6, 4};
        wiggleSort(nums1);
        System.out.println(Arrays.toString(nums1)); // [3, 5, 1, 6, 2, 4]

        int[] nums2 = {6, 6, 5, 6, 3, 8};
        wiggleSort(nums2);
        System.out.println(Arrays.toString(nums2)); // [6, 6, 5, 6, 3, 8]
    }
}