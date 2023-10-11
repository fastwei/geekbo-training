package com.geekbo.training.leetcode.top75;

import java.util.HashMap;

/**
 *
 * Array / String
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * 要解决Java中的Two Sum问题，可以使用哈希映射（HashMap）来存储值及其相应的索引，然后在遍历数组时进行操作。
 *
 * 解释：
 *
 * 创建一个HashMap来存储值（nums[i]）作为键和它们的索引（i）作为值。
 * 遍历nums数组。
 * 对于每个元素，通过从目标值中减去它来计算它的补数（complement）。
 * 检查补数是否存在于HashMap中。如果存在，返回这两个元素的索引。
 * 如果找不到解决方案，则抛出IllegalArgumentException异常。
 * 这个解决方案的时间复杂度为O(n)，因为它只遍历数组一次，并且使用HashMap需要常量空间。
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // 创建一个HashMap来存储值及其索引
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // 检查补数是否存在于map中
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            // 将当前值及其索引添加到map中
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("没有两个元素的和等于目标值");
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        // 示例 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println("示例 1: [" + result1[0] + ", " + result1[1] + "]");

        // 示例 2
        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println("示例 2: [" + result2[0] + ", " + result2[1] + "]");

        // 示例 3
        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println("示例 3: [" + result3[0] + ", " + result3[1] + "]");
    }
}
