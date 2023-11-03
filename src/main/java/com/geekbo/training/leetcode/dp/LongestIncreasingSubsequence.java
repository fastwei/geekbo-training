package com.geekbo.training.leetcode.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int [] dp = new int[n];
        int [] count = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(count,1);
        int max = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                    max = Math.max(dp[i],max);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == max) {
                ans += count[i];
            }
        }
        return ans;

    }

    /**
     * 解题思路：
     * <p>
     * 使用动态规划来解决问题。
     * 定义两个数组lengths和counts，其中lengths[i]表示以nums[i]为结尾的最长递增子序列的长度，counts[i]表示以nums[i]为结尾的最长递增子序列的个数。
     * 初始化lengths和counts数组，将所有元素的初始值都设置为1，表示以该元素为结尾的最长递增子序列的长度为1，个数也为1。
     * 遍历数组nums，对于每个元素nums[i]，再遍历其之前的元素nums[j]，如果nums[i]大于nums[j]，则更新lengths[i]和counts[i]的值。
     * 如果nums[i]等于nums[j]，则更新counts[i]的值。
     * 最后，遍历lengths数组，找到最长递增子序列的最大长度maxLength，然后再遍历一次数组，将长度等于maxLength的递增子序列的个数累加到结果result中。
     * 返回result作为最终结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中n是数组nums的长度。需要两层循环来计算最长递增子序列的长度和个数。
     * 空间复杂度：O(n)，使用了两个长度为n的数组lengths和counts来存储最长递增子序列的长度和个数。
     *
     * todo:这个方法还有些问题，比如nums数组中有重复元素
     *
     * @param nums
     * @return
     */
    public static int findNumberOfLIS2(int[] nums) {
        int n = nums.length;
        int[] lengths = new int[n];
        int[] counts = new int[n];

        Arrays.fill(counts, 1);
        int maxLength = 1;
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (lengths[j] + 1 > lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        counts[i] += counts[j];
                    }
                } else if (nums[i] == nums[j]) {
                    if (lengths[j] + 1 > lengths[i]) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    }
                }
            }
            maxLength = Math.max(maxLength, lengths[i]);
        }

        for (int i = 0; i < n; i++) {
            if (lengths[i] == maxLength) {
                result += counts[i];
            }
        }

        return result;
    }


    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 3, 5, 4, 7};
        int expected1 = 2;
        int result1 = findNumberOfLIS(nums1);
        System.out.println(result1 == expected1 ? "Pass" : "Fail");

        // Test case 2
        int[] nums2 = {2, 2, 2, 2, 2};
        int expected2 = 5;
        int result2 = findNumberOfLIS(nums2);
        System.out.println(result2 == expected2 ? "Pass" : "Fail");
    }
}