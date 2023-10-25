package com.geekbo.training.leetcode.top150;

import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly increasing
 * subsequence
 * .
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 * <p>
 * DP
 */
public class LongestIncreasingSubsequence {

    /**
     * 找到最长严格递增子序列的长度。
     * Algorithm:
     * <p>
     * We use an array dp to store the minimum ending value
     * for increasing subsequences of different lengths.
     * We initialize the length of the longest increasing subsequence as 0.
     * We iterate through each element in the input array nums.
     * For each element, we use binary search to find the position
     * where it should be inserted in the dp array.
     * If the element is already present, the binary search returns its index.
     * Otherwise, it returns the index of the next larger element.
     * We update the dp array at the found index with the current element.
     * If the found index is equal to the current length,
     * it means we have found a longer increasing subsequence, so we increment the length by 1.
     * Finally, we return the length of the longest increasing subsequence.
     * Complexity Analysis:
     * <p>
     * The algorithm uses binary search to find the position of each element in the dp array,
     * which has a time complexity of O(log(n)).
     * Since we perform this operation for each element in the input array,
     * the overall time complexity of the algorithm is O(n log(n)).
     * The space complexity is O(n), where n is the length of the input array,
     * as we need to store the dp array.
     *
     * @param nums 整数数组
     * @return 最长递增子序列的长度
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; // dp[i] 表示长度为 i+1 的递增子序列的最小末尾值
        int len = 0; // 递增子序列的长度

        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);

            if (i < 0) {
                i = -(i + 1);
            }

            dp[i] = num;

            if (i == len) {
                len++;
            }
        }

        return len;
    }

    /**
     * 找到最长严格递增子序列的长度。
     * 解题思路：
     * <p>
     * 使用动态规划来解决问题，定义一个数组 dp，其中 dp[i] 表示以 nums[i] 结尾的最长递增子序列的长度。
     * 初始化 dp 数组的所有元素为 1，因为每个元素本身都可以看作是一个递增子序列。
     * 对于每个元素 nums[i]，遍历它之前的所有元素 nums[j]，如果 nums[i] 大于 nums[j]，
     * 则可以将 nums[i] 加入以 nums[j] 结尾的递增子序列中，此时 dp[i] 的值可以更新为 dp[j] + 1。
     * 遍历整个数组，更新 dp 数组和最长递增子序列的长度。
     * 最后返回最长递增子序列的长度。
     * 算法复杂度分析：
     * <p>
     * 该算法使用了两层循环来计算动态规划数组 dp，因此时间复杂度为 O(n^2)。
     * 空间复杂度为 O(n)，其中 n 是数组的长度，用来存储动态规划数组 dp。
     *
     * @param nums 整数数组
     * @return 最长递增子序列的长度
     */
    public static int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length]; // dp[i] 表示以 nums[i] 结尾的最长递增子序列的长度
        Arrays.fill(dp, 1); // 初始化 dp 数组为 1

        int maxLength = 1; // 最长递增子序列的长度

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // 如果 nums[i] 大于 nums[j]，则可以将 nums[i] 加入以 nums[j] 结尾的递增子序列中
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); // 更新 dp[i]
                }
            }
            maxLength = Math.max(maxLength, dp[i]); // 更新最长递增子序列的长度
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        // 预期输出: 4
        System.out.println(lengthOfLIS(nums1));

        int[] nums2 = {0, 1, 0, 3, 2, 3};
        // 预期输出: 4
        System.out.println(lengthOfLIS(nums2));

        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        // 预期输出: 1
        System.out.println(lengthOfLIS(nums3));
    }
}


