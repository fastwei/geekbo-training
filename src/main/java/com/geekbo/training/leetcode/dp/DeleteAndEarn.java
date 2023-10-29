package com.geekbo.training.leetcode.dp;

import java.util.Arrays;

/**
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
 * <p>
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 * Example 2:
 * <p>
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 */
public class DeleteAndEarn {
    /**
     * 计算删除元素获得的最大分数
     * 解题思路：
     * <p>
     * 首先，统计每个数字出现的次数，使用数组count来存储。
     * 然后，使用动态规划来计算最大分数。使用数组dp来保存每个数字对应的最大分数。
     * dp[i]表示当选择数字i时，所能获得的最大分数。
     * 动态规划的状态转移方程为：dp[i] = Math.max(dp[i + 1], dp[i + 2] + count[i] * i)，
     * 即选择当前数字i时，可以得到的最大分数为选择i+1和选择i+2的最大值加上选择当前数字i的分数（即count[i] * i）。
     * 最终，返回dp[1]，即选择数字1时所能获得的最大分数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 统计每个数字出现的次数的时间复杂度为O(n)，其中n为数组的长度。
     * 动态规划的时间复杂度为O(maxNum)，其中maxNum为数组中的最大值。
     * 空间复杂度为O(maxNum)，其中maxNum为数组中的最大值。
     *
     * @param nums 整数数组
     * @return 最大分数
     */
    public static int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 统计每个数字出现的次数
        int maxNum = Arrays.stream(nums).max().getAsInt();
        int[] count = new int[maxNum + 1];
        for (int num : nums) {
            count[num]++;
        }

        // 动态规划
        int[] dp = new int[maxNum + 1];
        dp[1] = count[1] * 1;
        for (int i = 2; i <= maxNum; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + count[i] * i);
        }

        return dp[maxNum];
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 2};
        System.out.println(deleteAndEarn(nums1)); // Expected: 6

        int[] nums2 = {2, 2, 3, 3, 3, 4};
        System.out.println(deleteAndEarn(nums2)); // Expected: 9
    }
}