package com.geekbo.training.leetcode.top150;


/**
 *Array / String
 *
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 *
 * 解题思路：
 *
 * 我们维护一个变量 maxReach，表示当前能到达的最远位置。
 * 遍历数组，对于每个位置，检查是否超过了 maxReach，如果超过了，说明无法到达末尾，返回 false。否则，更新 maxReach 为当前位置能到达的最远位置。
 * 如果遍历完整个数组，说明可以到达末尾，返回 true。
 * 算法复杂度：
 *
 * 时间复杂度：O(n)，其中 n 是数组的长度，因为我们只需要一次遍历数组。
 * 空间复杂度：O(1)，因为我们只需要常数个额外变量来保存状态。
 *
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxReach = 0; // 记录当前能到达的最远位置

        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                return false; // 如果当前位置已经超过了最远能到达的位置，则无法到达末尾
            }
            maxReach = Math.max(maxReach, i + nums[i]); // 更新最远能到达的位置
        }

        return true; // 如果能遍历完数组，说明可以到达末尾
    }

    public static void main(String[] args) {
        JumpGame solution = new JumpGame();

        // 测试用例1
        int[] nums1 = {2, 3, 1, 1, 4};
        boolean result1 = solution.canJump(nums1);
        System.out.println("Example 1: " + result1); // 预期输出：true

        // 测试用例2
        int[] nums2 = {3, 2, 1, 0, 4};
        boolean result2 = solution.canJump(nums2);
        System.out.println("Example 2: " + result2); // 预期输出：false
    }
}
