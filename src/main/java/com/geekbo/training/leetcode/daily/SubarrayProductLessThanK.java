package com.geekbo.training.leetcode.daily;

/**
 * 713. Subarray Product Less Than K
 * Medium
 * Given an array of integers nums and an integer k,
 * return the number of contiguous subarrays where the product of all the elements
 * in the subarray is strictly less than k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 */
class SubarrayProductLessThanK {
    /**
     * 解题思路： 首先，我们需要定义两个指针left和right，表示子数组的左右边界。
     * 然后，我们使用一个循环来遍历数组nums，并以每个元素为起点，计算以该元素为起点的连续子数组的乘积。
     * 对于每个起点，我们需要用一个内层循环来计算以该起点为左边界的连续子数组的乘积，并更新乘积的结果。
     * 如果乘积小于k，我们就增加子数组的数量。 最后，我们返回子数组的数量即可。
     * <p>
     * 算法复杂度分析： 外层循环的时间复杂度是O(n)。 内层循环的时间复杂度是O(n)。 因此，总的时间复杂度是O(n^2)。
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int product = 1;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (product >= k && left <= right) {
                product /= nums[left];
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}