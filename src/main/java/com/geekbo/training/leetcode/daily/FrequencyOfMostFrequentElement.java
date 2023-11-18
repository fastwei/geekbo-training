package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1838. Frequency of the Most Frequent Element
 * Medium
 * The frequency of an element is the number of times it occurs in an array.
 * <p>
 * You are given an integer array nums and an integer k. In one operation,
 * you can choose an index of nums and increment the element at that index by 1.
 * <p>
 * Return the maximum possible frequency of an element after performing at most k operations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,4], k = 5
 * Output: 3
 * Explanation: Increment the first element three times and the second element two times to make nums = [4,4,4].
 * 4 has a frequency of 3.
 * Example 2:
 * <p>
 * Input: nums = [1,4,8,13], k = 5
 * Output: 2
 * Explanation: There are multiple optimal solutions:
 * - Increment the first element three times to make nums = [4,4,8,13]. 4 has a frequency of 2.
 * - Increment the second element four times to make nums = [1,8,8,13]. 8 has a frequency of 2.
 * - Increment the third element five times to make nums = [1,4,13,13]. 13 has a frequency of 2.
 * Example 3:
 * <p>
 * Input: nums = [3,9,6], k = 2
 * Output: 1
 */
public class FrequencyOfMostFrequentElement {
    /**
     * 解题思路：
     * <p>
     * 首先，对数组 nums 进行排序，以便可以使用滑动窗口来计算最大频率。
     * 使用两个指针 left 和 right 分别表示滑动窗口的左边界和右边界。
     * 初始化 count 为 1，表示当前窗口内的元素个数。
     * 初始化 maxFreq 为 1，表示最大频率。
     * 初始化 operations 为 0，表示已经执行的操作次数。
     * 在循环中，我们计算将右边界的元素增加到窗口内的所有元素所需的操作次数，然后根据操作次数是否超过 k 来缩小窗口。
     * 每次缩小窗口时，我们将窗口内的左边界右移，并减少窗口内的元素个数和操作次数。
     * 在每次循环中，更新窗口内的元素个数和最大频率。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums); // 将nums数组排序
        int left = 0; // 滑动窗口的左边界
        int right = 1; // 滑动窗口的右边界
        int count = 1; // 当前窗口内的元素个数
        int maxFreq = 1; // 最大频率
        int operations = 0; // 已经执行的操作次数

        while (right < nums.length) {
            // 计算需要执行的操作次数，将右边界的元素增加到窗口内的所有元素
            operations += (nums[right] - nums[right - 1]) * count;

            // 如果操作次数超过了k，需要缩小窗口
            while (operations > k) {
                operations -= nums[right] - nums[left];
                left++;
                count--;
            }

            // 更新窗口内的元素个数和最大频率
            count++;
            maxFreq = Math.max(maxFreq, count);

            right++;
        }

        return maxFreq;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 4};
        int k1 = 5;
        // 预期输出: 3
        System.out.println("Input: nums = [1, 2, 4], k = 5");
        System.out.println("Output: " + maxFrequency(nums1, k1));

        int[] nums2 = {1, 4, 8, 13};
        int k2 = 5;
        // 预期输出: 2
        System.out.println("\nInput: nums = [1, 4, 8, 13], k = 5");
        System.out.println("Output: " + maxFrequency(nums2, k2));

        int[] nums3 = {3, 9, 6};
        int k3 = 2;
        // 预期输出: 1
        System.out.println("\nInput: nums = [3, 9, 6], k = 2");
        System.out.println("Output: " + maxFrequency(nums3, k3));
    }
}
