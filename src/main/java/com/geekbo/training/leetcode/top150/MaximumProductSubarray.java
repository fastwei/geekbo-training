package com.geekbo.training.leetcode.top150;


/**
 *
 * Given an integer array nums, find a
 * subarray
 *  that has the largest product, and return the product.
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 */
public class MaximumProductSubarray {
    /**
     * 解题思路：
     * 使用动态规划的思想。
     * 定义两个变量，maxProduct和minProduct，分别表示以当前元素结尾的子数组的最大乘积和最小乘积。
     * 遍历数组，对于每个元素，有两种情况：
     * 1. 当前元素为正数，最大乘积为max(nums[i], maxProduct * nums[i])，最小乘积为min(nums[i], minProduct * nums[i])。
     * 2. 当前元素为负数，最大乘积为max(nums[i], minProduct * nums[i])，最小乘积为min(nums[i], maxProduct * nums[i])。
     * 取出所有最大乘积的结果中最大的一个作为最终结果。
     * <p>
     * 算法的时间复杂度为O(n)，其中n是数组的长度。
     *
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            maxProduct = Math.max(nums[i], maxProduct * nums[i]);
            minProduct = Math.min(nums[i], minProduct * nums[i]);

            result = Math.max(result, maxProduct);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {2, 3, -2, 4};
        // 预期输出为6
        System.out.println(maxProduct(nums1));

        // Test case 2
        int[] nums2 = {-2, 0, -1};
        // 预期输出为0
        System.out.println(maxProduct(nums2));
    }
}
