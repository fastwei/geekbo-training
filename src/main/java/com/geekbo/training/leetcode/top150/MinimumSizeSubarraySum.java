package com.geekbo.training.leetcode.top150;

/**
 *
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * subarray
 *  whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 */
public class MinimumSizeSubarraySum {

    /**
     *
     * 解题思路：
     *
     * 使用滑动窗口的技巧来找到满足条件的子数组。
     * 定义两个指针，left 和 right，分别表示窗口的左边界和右边界，初始时都指向数组的第一个元素。
     * 使用一个变量 sum 来维护窗口内元素的和，初始为0。
     * 依次将 right 指向的元素加入窗口，并更新 sum。
     * 如果 sum 大于等于目标值 target，说明满足条件，计算当前子数组的长度，更新最小长度 minLen。
     * 接着，收缩窗口，将 left 指向的元素从窗口中移除，并更新 sum。
     * 重复步骤4到6，直到 right 到达数组的末尾。
     * 返回最小长度 minLen，如果没有找到满足条件的子数组，返回0。
     * 算法复杂度：
     *
     * 时间复杂度：O(n)，其中n是数组的长度。
     * 空间复杂度：O(1)。只使用了常数额外空间。
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0; // 滑动窗口的左边界
        int sum = 0; // 当前窗口内的元素和
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right]; // 扩展窗口
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left]; // 收缩窗口
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        int target1 = 7;
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int result1 = minSubArrayLen(target1, nums1);
        System.out.println(result1); // 预期输出：2

        int target2 = 4;
        int[] nums2 = {1, 4, 4};
        int result2 = minSubArrayLen(target2, nums2);
        System.out.println(result2); // 预期输出：1

        int target3 = 11;
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int result3 = minSubArrayLen(target3, nums3);
        System.out.println(result3); // 预期输出：0
    }
}
