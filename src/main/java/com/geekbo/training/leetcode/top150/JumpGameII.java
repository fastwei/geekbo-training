package com.geekbo.training.leetcode.top150;

/**
 *Array / String
 *
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *解题思路：
 *
 * 使用贪心算法，维护三个变量：jumps（跳跃次数）、farthest（当前能跳到的最远位置）、currentEnd（当前阶段的结束位置）。
 * 遍历数组，对于每个位置，更新 farthest 为当前位置能跳到的最远位置。
 * 当遍历到 currentEnd 位置时，说明当前阶段结束，需要进行一次跳跃，将 currentEnd 更新为 farthest，并增加 jumps。
 * 最后返回 jumps 即为最小的跳跃次数。
 * 算法复杂度：
 *
 * 时间复杂度：O(n)，其中 n 是数组的长度，因为我们只需要一次遍历数组。
 * 空间复杂度：O(1)，因为我们只需要常数个额外变量来保存状态。
 *
 */
public class JumpGameII {
    public int jump(int[] nums) {
        int n = nums.length;
        int jumps = 0; // 记录跳跃次数
        int farthest = 0; // 记录当前能跳到的最远位置
        int currentEnd = 0; // 当前阶段的结束位置

        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        JumpGameII solution = new JumpGameII();

        // 测试用例1
        int[] nums1 = {2, 3, 1, 1, 4};
        int result1 = solution.jump(nums1);
        System.out.println("Example 1: " + result1); // 预期输出：2

        // 测试用例2
        int[] nums2 = {2, 3, 0, 1, 4};
        int result2 = solution.jump(nums2);
        System.out.println("Example 2: " + result2); // 预期输出：2
    }
}
