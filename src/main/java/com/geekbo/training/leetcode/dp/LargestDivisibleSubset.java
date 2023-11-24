package com.geekbo.training.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. Largest Divisible Subset
 * Medium
 * Given a set of distinct positive integers nums,
 * return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 * <p>
 * answer[i] % answer[j] == 0, or
 * answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,2]
 * Explanation: [1,3] is also accepted.
 * Example 2:
 * <p>
 * Input: nums = [1,2,4,8]
 * Output: [1,2,4,8]
 */
class LargestDivisibleSubset {
    /**
     * 解题思路：
     * <p>
     * 该问题可以使用动态规划来解决。
     * 首先，对给定的数组nums进行排序，以便处理元素的顺序。
     * 然后，初始化两个数组dp和prev，大小都为nums.length。dp数组将存储以每个索引结尾的最大可整除子集的长度，prev数组将存储最大可整除子集中每个元素的前一个元素的索引。
     * 初始化两个变量maxLen和maxIdx，用于记录最大可整除子集的长度和该子集的最后一个元素的索引。
     * 遍历排序后的数组nums中的每个元素nums[i]：
     * 将dp[i]和prev[i]初始设置为1和-1。
     * 对于每个之前的元素nums[j]（其中j从0到i-1），检查nums[i]是否可被nums[j]整除，即检查nums[i]%nums[j]==0。
     * 如果可整除，检查以nums[j]结尾的可整除子集的长度加1是否大于以nums[i]结尾的可整除子集的长度。如果是，更新dp[i]和prev[i]。
     * 检查dp[i]是否大于maxLen。如果是，更新maxLen和maxIdx。
     * 通过从索引maxIdx开始，并沿着prev数组向后遍历到-1的方式
     * <p>
     * 构建最大可整除子集，将每个元素添加到结果列表中。
     * <p>
     * 返回结果列表。
     * 该方法的时间复杂度是O(n^2)，其中n是输入数组nums的长度。空间复杂度是O(n)。
     *
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        int maxLen = 0;
        int maxIdx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }

            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIdx = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        while (maxIdx != -1) {
            result.add(nums[maxIdx]);
            maxIdx = prev[maxIdx];
        }

        return result;
    }

    public static void main(String[] args) {
        LargestDivisibleSubset solution = new LargestDivisibleSubset();

        int[] nums1 = {1, 2, 3};
        // 预期输出: [1, 2] 或 [1, 3]
        System.out.println(solution.largestDivisibleSubset(nums1));

        int[] nums2 = {1, 2, 4, 8};
        // 预期输出: [1, 2, 4, 8]
        System.out.println(solution.largestDivisibleSubset(nums2));
    }
}


