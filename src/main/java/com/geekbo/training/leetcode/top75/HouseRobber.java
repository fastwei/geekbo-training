package com.geekbo.training.leetcode.top75;

/**
 *198. House Robber
 *
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house,
 * return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 */
public class HouseRobber {

    /**
     * 解题思路：
     * <p>
     * 问题可以看作是一个动态规划问题，要求在不触发警报的情况下，抢劫到最大的金额。
     * 使用动态规划的思想，定义一个dp数组，其中dp[i]表示抢劫到第i个房屋时能够获得的最大金额。
     * 初始化dp数组的前两个位置：dp[0]等于第一个房屋的金额，dp[1]等于第一和第二个房屋中金额较大的那个。
     * 从第三个位置开始遍历，对于每个位置i，有两种选择：抢劫当前房屋或者不抢劫。
     * 如果抢劫当前房屋，那么前一个房屋就不能抢劫，所以金额为dp[i-2]+nums[i]。
     * 如果不抢劫当前房屋，金额为dp[i-1]。
     * 取两者之间的最大值作为dp[i]的值。
     * 返回dp数组的最后一个位置的值，即为能够抢到的最大金额。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历数组一次，时间复杂度为O(n)，其中n为房屋的数量。
     * 空间复杂度：创建了一个长度为n的dp数组，空间复杂度为O(n)，其中n为房屋的数量。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return nums[0];
        }

        // 创建一个长度为n的dp数组，用于保存每个位置能够抢到的最大金额
        int[] dp = new int[n];

        // 初始化dp数组的前两个位置
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // 从第三个位置开始动态规划
        for (int i = 2; i < n; i++) {
            // 在当前位置，有两种选择：抢劫当前房屋或者不抢劫
            // 如果抢劫当前房屋，那么前一个房屋就不能抢劫，所以金额为dp[i-2]+nums[i]
            // 如果不抢劫当前房屋，那么金额为dp[i-1]
            // 取两者之间的最大值作为dp[i]的值
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        // 返回dp数组的最后一个位置的值，即为能够抢到的最大金额
        return dp[n - 1];
    }

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();

        // 测试用例1
        int[] nums1 = {1, 2, 3, 1};
        int result1 = houseRobber.rob(nums1);
        System.out.println(result1); // 输出: 4

        // 测试用例2
        int[] nums2 = {2, 7, 9, 3, 1};
        int result2 = houseRobber.rob(nums2);
        System.out.println(result2); // 输出: 12
    }
}