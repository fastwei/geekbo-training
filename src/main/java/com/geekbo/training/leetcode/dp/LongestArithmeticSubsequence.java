package com.geekbo.training.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 1027. Longest Arithmetic Subsequence
 * Medium
 * Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.
 * <p>
 * Note that:
 * <p>
 * A subsequence is an array that can be derived from another array by deleting some
 * or no elements without changing the order of the remaining elements.
 * A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,6,9,12]
 * Output: 4
 * Explanation:  The whole array is an arithmetic sequence with steps of length = 3.
 * Example 2:
 * <p>
 * Input: nums = [9,4,7,2,10]
 * Output: 3
 * Explanation:  The longest arithmetic subsequence is [4,7,10].
 * Example 3:
 * <p>
 * Input: nums = [20,1,15,3,10,5,8]
 * Output: 4
 * Explanation:  The longest arithmetic subsequence is [20,15,10,5].
 */
public class LongestArithmeticSubsequence {
    /**
     * 计算最长等差子序列的长度
     * 解题思路：
     * <p>
     * 首先，我们使用一个二维数组 dp，其中 dp[i][j]表示以索引 i 结尾、公差为 j 的等差子序列的长度。
     * 然后，我们遍历数组 nums，对于每个元素 nums[i]，再遍历它之前的元素 nums[j]，
     * 计算它们之间的公差 diff，并更新 dp[i][diff]的值为 dp[j][diff]+1。
     * 最后，我们找到 dp 数组中的最大值，即为最长等差子序列的长度。
     * 算法复杂度分析：
     * <p>
     * 初始化时间复杂度为 O(n^2)，其中 n 是数组的长度。
     * 遍历数组的时间复杂度为 O(n^2)。
     *
     * @param nums 原始数组
     * @return 最长等差子序列的长度
     */
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int maxLength = 2;

        // 创建一个二维数组，用于存储以每个元素为结尾的等差子序列的长度
        Map<Integer, Integer>[] dp = new HashMap[n];

        // 遍历数组，计算以每个元素为结尾的等差子序列的长度
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                int length = dp[j].getOrDefault(diff, 1) + 1;
                dp[i].put(diff, length);
                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // 创建测试用例
        int[] nums1 = {3, 6, 9, 12};
        int[] nums2 = {9, 4, 7, 2, 10};
        int[] nums3 = {20, 1, 15, 3, 10, 5, 8};

        // 创建解法对象
        LongestArithmeticSubsequence longestArithmeticSubsequence = new LongestArithmeticSubsequence();

        // 测试用例1
        // 预期输出: 4
        System.out.println(longestArithmeticSubsequence.longestArithSeqLength(nums1));

        // 测试用例2
        // 预期输出: 3
        System.out.println(longestArithmeticSubsequence.longestArithSeqLength(nums2));

        // 测试用例3
        // 预期输出: 4
        System.out.println(longestArithmeticSubsequence.longestArithSeqLength(nums3));
    }
}
