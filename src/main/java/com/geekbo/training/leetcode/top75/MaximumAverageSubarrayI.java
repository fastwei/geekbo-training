package com.geekbo.training.leetcode.top75;

/**
 * Sliding Window
 *
 * You are given an integer array nums consisting of n elements, and an integer k.
 * <p>
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [5], k = 1
 * Output: 5.00000
 * <p>
 * <p>
 * 这个算法的思路是遍历数组，针对每个可能的子数组，计算其平均值，并不断更新最大平均值。最终，返回最大平均值。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(nk)，其中n是数组长度，k是给定的子数组长度。因为我们需要遍历n-k+1个子数组，每个子数组长度为k，所以总共需要的计算次数为O(nk)。
 * 空间复杂度：O(1)，除了常数额外的空间用于存储一些变量，算法的空间复杂度是常数级别的。
 * <p>
 * <p>
 * 滑动窗口算法优化
 * <p>
 * 通过使用滑动窗口，我们避免了重复计算子数组的总和，从而提高了性能。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(n)，其中n是数组长度。我们只需要遍历数组一次来计算最大平均值。
 * 空间复杂度：O(1)，除了常数额外的空间用于存储一些变量，算法的空间复杂度是常数级别的。
 * 这个优化后的算法能够更有效地计算最大平均值，提高了性能。
 * <p>
 *
 * Sliding Window
 * todo:需要加深理解
 */
public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double maxAverage = Integer.MIN_VALUE;

        // 遍历数组，计算每个可能的子数组的平均值并找到最大值
        for (int i = 0; i <= n - k; i++) {
            int sum = 0;

            // 计算当前子数组的总和
            for (int j = i; j < i + k; j++) {
                sum += nums[j];
            }

            // 计算当前子数组的平均值
            double average = (double) sum / k;

            // 更新最大平均值
            maxAverage = Math.max(maxAverage, average);
        }

        return maxAverage;
    }


    /**
     * 我们可以采用滑动窗口的方法，以避免不必要的重复计算。
     * 通过使用滑动窗口，我们避免了重复计算子数组的总和，从而提高了性能。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是数组长度。我们只需要遍历数组一次来计算最大平均值。
     * 空间复杂度：O(1)，除了常数额外的空间用于存储一些变量，算法的空间复杂度是常数级别的。
     * 这个优化后的算法能够更有效地计算最大平均值，提高了性能。
     *
     * Sliding Window
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverageSlidingWindows(int[] nums, int k) {
        int n = nums.length;

        // 计算初始窗口的总和
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        // 初始化最大平均值为初始窗口的平均值
        double maxAverage = (double) sum / k;

        // 滑动窗口，每次向右移动一位
        for (int i = k; i < n; i++) {
            // 新窗口的总和等于旧窗口总和减去左边界的值再加上右边界的值
            sum = sum - nums[i - k] + nums[i];

            // 计算新窗口的平均值
            double average = (double) sum / k;

            // 更新最大平均值
            maxAverage = Math.max(maxAverage, average);
        }

        return maxAverage;
    }


    public static void main(String[] args) {
        MaximumAverageSubarrayI solution = new MaximumAverageSubarrayI();
        int[] nums1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        System.out.println(solution.findMaxAverage(nums1, k1)); // Output: 12.75

        int[] nums2 = {5};
        int k2 = 1;
        System.out.println(solution.findMaxAverage(nums2, k2)); // Output: 5.0
    }
}
