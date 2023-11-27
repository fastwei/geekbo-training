package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1283. Find the Smallest Divisor Given a Threshold
 * Medium
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it, and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 * <p>
 * Each result of the division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 * <p>
 * The test cases are generated so that there will be an answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 * Example 2:
 * <p>
 * Input: nums = [44,22,33,11,1], threshold = 5
 * Output: 44
 */
class FindSmallestDivisorGivenThreshold {
    /**
     * 这道题目要求找出一个正整数作为除数，使得将数组中的每个数都除以该除数后，得到的除法结果的和不超过给定的阈值。
     * 我们可以使用二分查找来找到符合条件的最小除数。
     * <p>
     * 首先，确定二分查找的上下界。最小的除数为1，最大的除数为数组中的最大值。
     * <p>
     * 然后，使用二分查找找到符合条件的最小除数。
     * 在每次二分查找中，计算除数为中值，将数组中的每个数都除以该除数后的结果的和。
     * 如果和小于等于阈值，说明当前的除数可能是符合条件的最小除数，我们继续向左搜索，缩小右界；
     * 如果和大于阈值，说明当前的除数太小，我们继续向右搜索，增大左界。
     * <p>
     * 复杂度分析：
     * <p>
     * 时间复杂度：二分查找的时间复杂度为O(log(R - L))，其中R表示数组中的最大值，L表示1。
     * 计算和的时间复杂度为O(n)，其中n表示数组的长度。所以总的时间复杂度为O(nlog(R - L))。
     * 空间复杂度：使用了常数级别的额外空间，空间复杂度为O(1)。
     */
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = Arrays.stream(nums).max().getAsInt();

        while (left < right) {
            int mid = left + (right - left) / 2;
            int sum = calculateSum(nums, mid);

            if (sum <= threshold) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int calculateSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += Math.ceil((double) num / divisor);
        }
        return sum;
    }
}