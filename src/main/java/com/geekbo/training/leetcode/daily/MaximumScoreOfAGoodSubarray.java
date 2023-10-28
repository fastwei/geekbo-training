package com.geekbo.training.leetcode.daily;

/**
 * You are given an array of integers nums (0-indexed) and an integer k.
 * <p>
 * The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.
 * <p>
 * Return the maximum possible score of a good subarray.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,4,3,7,4,5], k = 3
 * Output: 15
 * Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.
 * Example 2:
 * <p>
 * Input: nums = [5,5,4,5,4,1,1,1], k = 0
 * Output: 20
 * Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
 */
public class MaximumScoreOfAGoodSubarray {
    /**
     * 解题思路：
     * 首先找到以k为中心，向左右两边扩展的最大区间[left, right]，使得nums[left]和nums[right]都大于等于nums[k]。
     * 那么以k为中心，左右两边都是符合条件的good subarray，取其中较小的值作为当前区间的最小值，
     * 乘以当前区间的长度(right - left + 1)即可得到当前区间的分数。
     * 遍历数组，找到所有以不同的k为中心的区间的最大分数，取其中的最大值即可。
     * <p>
     * 算法的时间复杂度为O(n)，其中n为数组的长度。
     * 算法的空间复杂度为O(1)。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int maximumScore(int[] nums, int k) {
        int maxScore = nums[k];
        int left = k;
        int right = k;
        int minVal = nums[k];

        while (left > 0 || right < nums.length - 1) {
            if (left == 0) {
                right++;
                minVal = Math.min(minVal, nums[right]);
            } else if (right == nums.length - 1) {
                left--;
                minVal = Math.min(minVal, nums[left]);
            } else if (nums[left - 1] < nums[right + 1]) {
                right++;
                minVal = Math.min(minVal, nums[right]);
            } else {
                left--;
                minVal = Math.min(minVal, nums[left]);
            }
            maxScore = Math.max(maxScore, minVal * (right - left + 1));
        }

        return maxScore;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 4, 3, 7, 4, 5};
        int k1 = 3;
        // 预期输出为15
        System.out.println(maximumScore(nums1, k1));

        int[] nums2 = {5, 5, 4, 5, 4, 1, 1, 1};
        int k2 = 0;
        // 预期输出为20
        System.out.println(maximumScore(nums2, k2));
    }
}