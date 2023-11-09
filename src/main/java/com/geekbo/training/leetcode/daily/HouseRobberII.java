package com.geekbo.training.leetcode.daily;

/**
 * 213. House Robber II
 * Medium
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed.
 * All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have a security system connected,
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 * because they are adjacent houses.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 * <p>
 * Input: nums = [1,2,3]
 * Output: 3
 */
class HouseRobberII {
    /**
     * 计算在不触发警报的情况下，能够抢劫到的最大金额
     * 解题思路: 这个问题可以将其分解为两个子问题：
     * 一个是第一个房子被抢劫，最后一个房子不被抢劫；
     * 另一个是第一个房子不被抢劫，最后一个房子被抢劫。
     * 然后分别计算这两个子问题的最大金额，最后返回两个最大金额的较大值。
     * <p>
     * 算法复杂度分析:
     * <p>
     * 时间复杂度: O(n)，其中 n 是房子的数量，需要遍历两次房子数组。
     * 空间复杂度: O(1)，只使用了常数级别的额外空间。
     *
     * @param nums 每个房子中的金额数组
     * @return 最大金额
     */
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(robHelper(nums, 0, nums.length - 2), robHelper(nums, 1, nums.length - 1));
    }

    /**
     * 计算在指定范围内的房子中能够抢劫到的最大金额
     *
     * @param nums  每个房子中的金额数组
     * @param start 起始位置
     * @param end   结束位置
     * @return 最大金额
     */
    public static int robHelper(int[] nums, int start, int end) {
        int prevMax = 0;
        int currMax = 0;
        for (int i = start; i <= end; i++) {
            int temp = currMax;
            currMax = Math.max(prevMax + nums[i], currMax);
            prevMax = temp;
        }
        return currMax;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {2, 3, 2};
        System.out.println("测试用例1: " + rob(nums1)); // 预期输出: 3

        // 测试用例2
        int[] nums2 = {1, 2, 3, 1};
        System.out.println("测试用例2: " + rob(nums2)); // 预期输出: 4

        // 测试用例3
        int[] nums3 = {1, 2, 3};
        System.out.println("测试用例3: " + rob(nums3)); // 预期输出: 3
    }
}
