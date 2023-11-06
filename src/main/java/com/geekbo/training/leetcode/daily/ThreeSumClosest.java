package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * <p>
 * Return the sum of the three integers.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 * <p>
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).
 */
public class ThreeSumClosest {
    /**
     * 解题思路： 这是一个双指针问题。
     * 首先，我们对数组nums进行排序，然后固定一个数nums[i]，并使用两个指针left和right指向i+1和nums.length-1。
     * 我们计算当前三个数的和sum，并根据sum与target的大小关系来移动指针。
     * 具体地，如果sum小于target，我们将left指针右移一位；如果sum大于target，我们将right指针左移一位。
     * 同时，我们更新最接近目标和的closestSum。我们重复这个过程直到left和right指针相遇。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：数组nums排序的时间复杂度为O(nlogn)，
     * 遍历数组的时间复杂度为O(n)，所以总时间复杂度为O(nlogn + n^2) = O(n^2)
     * 空间复杂度：排序使用了额外的空间，空间复杂度为O(logn)。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closestSum;
    }

    public static void main(String[] args) {
        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println("Test Case 1 - Expected: 2, Actual: " + threeSumClosest(nums1, target1));

        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println("Test Case 2 - Expected: 0, Actual: " + threeSumClosest(nums2, target2));
    }
}
