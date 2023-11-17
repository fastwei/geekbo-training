package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1877. Minimize Maximum Pair Sum in Array
 * The pair sum of a pair (a,b) is equal to a + b.
 * The maximum pair sum is the largest pair sum in a list of pairs.
 * <p>
 * For example, if we have pairs (1,5), (2,3), and (4,4),
 * the maximum pair sum would be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
 * Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:
 * <p>
 * Each element of nums is in exactly one pair, and
 * The maximum pair sum is minimized.
 * Return the minimized maximum pair sum after optimally pairing up the elements.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,5,2,3]
 * Output: 7
 * Explanation: The elements can be paired up into pairs (3,3) and (5,2).
 * The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.
 * Example 2:
 * <p>
 * Input: nums = [3,5,4,2,4,6]
 * Output: 8
 * Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
 * The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.
 */
public class MinimizeMaximumPairSum {

    /**
     * 解题思路： 要使最大的配对和最小化，我们可以将数组中的最大值和最小值配对，次大值和次小值配对，以此类推。
     * 这样可以确保最大的配对和不会太大。
     * <p>
     * 具体实现步骤：
     * <p>
     * 对数组进行排序，以便能够方便地找到最大值和最小值。
     * 使用双指针法，一个指针指向最小值，另一个指针指向最大值。
     * 不断将最小值和最大值进行配对，并计算配对和的最大值，更新结果。
     * 移动指针，直到指针相遇为止。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：排序数组的时间复杂度为O(nlogn)，配对过程的时间复杂度为O(n/2)，因此总时间复杂度为O(nlogn)。
     * 空间复杂度：排序所需的额外空间为O(n)，而除此之外没有使用额外空间，所以总空间复杂度为O(n)。
     *
     * @param nums
     * @return
     */
    public static int minPairSum(int[] nums) {
        // 对数组进行排序
        Arrays.sort(nums);

        int n = nums.length;
        int maxPairSum = 0;
        int left = 0, right = n - 1;

        while (left < right) {
            // 计算配对和的最大值，并更新结果
            maxPairSum = Math.max(maxPairSum, nums[left] + nums[right]);
            left++;
            right--;
        }

        return maxPairSum;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {3, 5, 2, 3};
        // 配对为(2, 5)和(3, 3)，最大配对和为7
        int expected1 = 7;
        int result1 = minPairSum(nums1);
        System.out.println(result1 == expected1); // Output: true

        // Test case 2
        int[] nums2 = {3, 5, 4, 2, 4, 6};
        // 配对为(2, 6)，(3, 5)和(4, 4)，最大配对和为8
        int expected2 = 8;
        int result2 = minPairSum(nums2);
        System.out.println(result2 == expected2); // Output: true
    }
}