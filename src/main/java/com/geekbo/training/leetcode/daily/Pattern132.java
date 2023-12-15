package com.geekbo.training.leetcode.daily;

import java.util.Stack;

/**
 * 456. 132 Pattern
 * Medium
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 * <p>
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 * Example 2:
 * <p>
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * Example 3:
 * <p>
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */
public class Pattern132 {
    /**
     * 判断数组中是否存在132模式
     * 解题思路： 首先，我们需要找到132模式中的2和3。
     * 我们可以从后往前遍历数组，使用一个栈来维护132模式中的3的候选值。
     * 遍历到的每个数字，都可以作为可能的2的值。如果当前数字小于候选的3的值，那么就找到了132模式，直接返回true。
     * 如果当前数字大于候选的3的值，那么就更新候选的3的值，并将当前数字压入栈中。
     * 最后，如果遍历完整个数组都没有找到132模式，那么返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是数组的长度。我们需要遍历数组一次，同时在栈中进行操作。
     * 空间复杂度：O(n)，其中n是数组的长度。需要使用一个栈来维护132模式中的3的候选值。
     *
     * @param nums 给定的整数数组
     * @return 存在132模式返回true，否则返回false
     */
    public static boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        // 用一个栈来维护132模式中的3
        Stack<Integer> stack = new Stack<>();
        int third = Integer.MIN_VALUE;

        for (int i = nums.length - 1; i >= 0; i--) {
            // 如果当前数字小于third，说明找到了132模式
            if (nums[i] < third) {
                return true;
            } else {
                // 如果当前数字大于栈顶元素，说明找到了更大的third，更新third的值
                while (!stack.isEmpty() && nums[i] > stack.peek()) {
                    third = stack.pop();
                }
                // 将当前数字压入栈中，作为可能的2的值
                stack.push(nums[i]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {1, 2, 3, 4};
        // 预期输出：false
        System.out.println(find132pattern(nums1));

        // 测试用例2
        int[] nums2 = {3, 1, 4, 2};
        // 预期输出：true
        System.out.println(find132pattern(nums2));

        // 测试用例3
        int[] nums3 = {-1, 3, 2, 0};
        // 预期输出：true
        System.out.println(find132pattern(nums3));
    }
}


