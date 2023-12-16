package com.geekbo.training.leetcode.dp;

/**
 * 413. Arithmetic Slices
 * Solved
 * Medium
 * An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * <p>
 * For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 * <p>
 * A subarray is a contiguous subsequence of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: 3
 * Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 */
class ArithmeticSlices {
    public static void main(String[] args) {
        ArithmeticSlices solution = new ArithmeticSlices();

        // 测试用例 1
        int[] nums1 = {1, 2, 3, 4};
        // 预期输出：3
        System.out.println(solution.numberOfArithmeticSlices(nums1));

        // 测试用例 2
        int[] nums2 = {1};
        // 预期输出：0
        System.out.println(solution.numberOfArithmeticSlices(nums2));

        // 测试用例 3
        int[] nums3 = {1, 2, 3, 4, 5, 6};
        // 预期输出：10
        System.out.println(solution.numberOfArithmeticSlices(nums3));
    }

    /**
     * 解题思路：
     * 使用动态规划求解。
     * 定义一个 dp 数组，dp[i] 表示以 nums[i] 结尾的等差子数组的个数。
     * 如果 nums[i] - nums[i-1] == nums[i-1] - nums[i-2]，
     * 说明 nums[i] 可以加入到以 nums[i-1] 结尾的等差子数组中，那么 dp[i] = dp[i-1] + 1。
     * 最终结果为 dp 数组中所有元素的和。
     * <p>
     * 算法复杂度：
     * 时间复杂度：O(n)，其中 n 是数组的长度，需要遍历一次数组求解 dp 数组。
     * 空间复杂度：O(n)，需要使用一个大小为 n 的 dp 数组来保存中间结果。
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }

        int[] dp = new int[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                count += dp[i];
            }
        }

        return count;
    }
}