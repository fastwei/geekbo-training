package com.geekbo.training.leetcode.greedy;

import java.util.Arrays;

/**
 * 410. Split Array Largest Sum
 * Hard
 * Given an integer array nums and an integer k, split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
 * <p>
 * Return the minimized largest sum of the split.
 * <p>
 * A subarray is a contiguous part of the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [7,2,5,10,8], k = 2
 * Output: 18
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,5], k = 2
 * Output: 9
 * Explanation: There are four ways to split nums into two subarrays.
 * The best way is to split it into [1,2,3] and [4,5], where the largest sum among the two subarrays is only 9.
 */
public class SplitArrayLargestSum {

    /**
     * 解题思路： 给定一个整数数组nums和一个整数k，将nums分割成k个非空子数组，使得任何子数组的最大和尽可能小。
     * 我们要求的是最小化的最大和。
     * <p>
     * 我们可以使用贪心算法来解决这个问题。贪心算法的思路是从第一行开始逐行构建阶梯，直到剩余的硬币不足以构建下一行。
     * 我们使用一个变量completeRows来记录已经构建的完整行的数量。
     * 在每一行中，我们将completeRows增加1，并从总硬币数量n中减去当前行的硬币数量。
     * 当剩余的硬币数量不足以构建下一行时，循环结束。最后，我们返回completeRows即为构建的完整行的数量。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(sqrt(n))，其中n为总硬币数量。我们最多需要构建sqrt(n)行阶梯。
     * 空间复杂度：O(1)，我们只使用了常量级的额外空间。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int splitArray(int[] nums, int k) {
        int left = Arrays.stream(nums).max().getAsInt(); // left boundary
        int right = Arrays.stream(nums).sum(); // right boundary

        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 1; // number of subarrays
            int currSum = 0; // current sum of the subarray

            for (int num : nums) {
                currSum += num;
                if (currSum > mid) {
                    count++;
                    currSum = num;
                }
            }

            if (count > k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] nums1 = {7, 2, 5, 10, 8};
        int k1 = 2;
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + splitArray(nums1, k1));

        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 2;
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + splitArray(nums2, k2));
    }
}