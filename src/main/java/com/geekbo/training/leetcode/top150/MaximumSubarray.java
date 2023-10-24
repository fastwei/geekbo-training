package com.geekbo.training.leetcode.top150;

/**
 * Given an integer array nums, find the
 * subarray
 * with the largest sum, and return its sum.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 * <p>
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Kadane's Algorithm
 *
 */
public class MaximumSubarray {

    /**
     * 找到数组中具有最大和的子数组，并返回其和。
     * <p>
     * 优化算法的时间复杂度到 O(n)，可以使用动态规划的思想。
     * <p>
     * 解题思路：
     * <p>
     * 定义一个变量 maxSum 表示当前的最大子数组和，初始化为数组的第一个元素。
     * 定义一个变量 currSum 表示当前的子数组和，初始化为数组的第一个元素。
     * 从数组的第二个元素开始遍历，对于每个元素：
     * 如果当前的 currSum 加上当前元素大于当前元素本身，则更新 currSum 为当前的 currSum 加上当前元素。
     * 否则，更新 currSum 为当前元素。
     * 更新 maxSum 为当前的 maxSum 和 currSum 中的较大值。
     * 最后返回 maxSum。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历整个数组一次，时间复杂度为 O(n)。
     * 只使用了常数个额外变量，空间复杂度为 O(1)。
     *
     * @param nums 整数数组
     * @return 最大和的子数组的和
     */
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];   // 当前的最大子数组和
        int currSum = nums[0];  // 当前的子数组和

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(currSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }

    /**
     * 找到数组中具有最大和的子数组，并返回其和。
     * <p>
     * 解题思路：
     * <p>
     * 使用分治法来解决问题，将数组划分为左右两个子数组。
     * 分别求解左半部分的最大子数组和、右半部分的最大子数组和、以及跨越中间位置的子数组的最大和。
     * 最大子数组和要么位于左半部分，要么位于右半部分，要么跨越中间位置。
     * 递归地求解左半部分和右半部分的最大子数组和，然后再求解跨越中间位置的子数组的最大和。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 假设数组的长度为n。
     * 分治法的时间复杂度为O(nlogn)，因为每次递归都把数组分成两半，递归的深度为logn，每层的处理时间复杂度为O(n)。
     * 跨越中间位置的子数组的最大和的计算时间复杂度为O(n)，因为需要遍历数组的一半。
     * 因此，总的时间复杂度为O(nlogn)。
     * 空间复杂度为O(logn)，用于递归调用栈的空间。
     *
     * @param nums 整数数组
     * @return 最大和的子数组的和
     */
    public static int maxSubArrayOld(int[] nums) {
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    private static int divideAndConquer(int[] nums, int left, int right) {
        // 基本情况，数组只有一个元素
        if (left == right) {
            return nums[left];
        }

        // 找到数组的中间位置
        int mid = left + (right - left) / 2;

        // 分别求解左半部分、右半部分、和跨越中间位置的子数组的最大和
        int leftSum = divideAndConquer(nums, left, mid);
        int rightSum = divideAndConquer(nums, mid + 1, right);
        int crossSum = crossSum(nums, left, right, mid);

        // 返回三者中的最大值
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    private static int crossSum(int[] nums, int left, int right, int mid) {
        // 处理跨越中间位置的子数组的情况

        // 从中间位置向左扩展，找到左半部分的最大和
        int leftSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = mid; i >= left; i--) {
            currSum += nums[i];
            leftSum = Math.max(leftSum, currSum);
        }

        // 从中间位置向右扩展，找到右半部分的最大和
        int rightSum = Integer.MIN_VALUE;
        currSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            currSum += nums[i];
            rightSum = Math.max(rightSum, currSum);
        }

        // 返回左半部分的最大和、右半部分的最大和以及两者相加的和
        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        // 预期输出: 6
        System.out.println(maxSubArray(nums1));

        int[] nums2 = {1};
        // 预期输出: 1
        System.out.println(maxSubArray(nums2));

        int[] nums3 = {5, 4, -1, 7, 8};
        // 预期输出: 23
        System.out.println(maxSubArray(nums3));
    }
}
