package com.geekbo.training.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 1218. Longest Arithmetic Subsequence of Given Difference
 * Medium
 * Topics
 * Companies
 * Hint
 * Given an integer array arr and an integer difference,
 * return the length of the longest subsequence in arr
 * which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.
 * <p>
 * A subsequence is a sequence that can be derived from arr by deleting some
 * or no elements without changing the order of the remaining elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4], difference = 1
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [1,2,3,4].
 * Example 2:
 * <p>
 * Input: arr = [1,3,5,7], difference = 1
 * Output: 1
 * Explanation: The longest arithmetic subsequence is any single element.
 * Example 3:
 * <p>
 * Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * Output: 4
 * Explanation: The longest arithmetic subsequence is [7,5,3,1].
 */
public class LongestArithmeticSubsequenceGivenDifference {
    /**
     * 计算具有给定差值的最长等差子序列的长度
     * 解题思路：
     * <p>
     * 首先，我们使用一个哈希表 dp，其中 dp[num] 表示以数字 num 结尾的最长等差子序列的长度。
     * 然后，我们遍历数组 arr，对于每个数字 num，我们更新 dp[num] 的值为 dp[num - difference] + 1。
     * 这意味着以数字 num 结尾的最长等差子序列的长度为以数字 num - difference 结尾的最长等差子序列的长度加上 1。
     * 最后，我们找到 dp 哈希表中的最大值，即为具有给定差值的最长等差子序列的长度。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 初始化时间复杂度为 O(n)，其中 n 是数组的长度。
     * 遍历数组的时间复杂度为 O(n)。
     *
     * @param arr        原始数组
     * @param difference 给定差值
     * @return 具有给定差值的最长等差子序列的长度
     */
    public int longestSubsequence(int[] arr, int difference) {
        int maxLength = 1;

        // 创建一个哈希表，用于存储数字的最长等差子序列的长度
        Map<Integer, Integer> dp = new HashMap<>();

        // 遍历数组，计算每个数字的最长等差子序列的长度
        for (int num : arr) {
            dp.put(num, dp.getOrDefault(num - difference, 0) + 1);
            maxLength = Math.max(maxLength, dp.get(num));
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // 创建测试用例
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {1, 3, 5, 7};
        int[] arr3 = {1, 5, 7, 8, 5, 3, 4, 2, 1};

        // 创建解法对象
        LongestArithmeticSubsequenceGivenDifference longestArithmeticSubsequenceGivenDifference = new LongestArithmeticSubsequenceGivenDifference();

        // 测试用例1
        // 预期输出: 4
        System.out.println(longestArithmeticSubsequenceGivenDifference.longestSubsequence(arr1, 1));

        // 测试用例2
        // 预期输出: 1
        System.out.println(longestArithmeticSubsequenceGivenDifference.longestSubsequence(arr2, 1));

        // 测试用例3
        // 预期输出: 4
        System.out.println(longestArithmeticSubsequenceGivenDifference.longestSubsequence(arr3, -2));
    }
}
