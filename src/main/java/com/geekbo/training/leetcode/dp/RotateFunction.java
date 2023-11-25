package com.geekbo.training.leetcode.dp;

/**
 * 396. Rotate Function
 * Medium
 * You are given an integer array nums of length n.
 * <p>
 * Assume arrk to be an array obtained by rotating nums by k positions clock-wise. We define the rotation function F on nums as follow:
 * <p>
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1].
 * Return the maximum value of F(0), F(1), ..., F(n-1).
 * <p>
 * The test cases are generated so that the answer fits in a 32-bit integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,2,6]
 * Output: 26
 * Explanation:
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 * Example 2:
 * <p>
 * Input: nums = [100]
 * Output: 0
 */
public class RotateFunction {

    /**
     * 解题思路：
     * <p>
     * 首先，计算数组元素的和以及初始的F(0)值。
     * 数组元素的和可以通过遍历数组累加得到，初始的F(0)值可以通过遍历数组元素乘以其下标并累加得到。
     * 然后，通过遍历计算F(k)的值，其中k表示数组旋转的次数。
     * 每次计算F(k)时，可以通过上一次F(k-1)的值以及数组元素的和和最后一个元素的值来得到。
     * 具体计算公式为：F(k)=F(k-1)+sum-n*nums[n-k]。
     * 最后，返回最大的F(k)值作为结果。
     * <p>
     * 复杂度分析：
     * <p>
     * 时间复杂度：遍历数组元素的和以及计算F(k)的值都需要遍历整个数组，所以时间复杂度为O(n)，其中n为数组的长度。
     * 空间复杂度：除了常数级别的额外空间，不需要使用额外的空间，所以空间复杂度为O(1)。
     *
     * @param nums
     * @return
     */
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int f = 0;

        // 计算数组元素的和以及初始的F(0)值
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f += i * nums[i];
        }

        int maxF = f;

        // 遍历计算F(k)的值，更新最大值
        for (int k = 1; k < n; k++) {
            f = f + sum - n * nums[n - k];
            maxF = Math.max(maxF, f);
        }

        return maxF;
    }

    public static void main(String[] args) {
        RotateFunction solution = new RotateFunction();

        // 测试用例1
        int[] nums1 = {4, 3, 2, 6};
        int result1 = solution.maxRotateFunction(nums1);
        System.out.println("Test Case 1:");
        System.out.println(result1); // Output: 26

        // 测试用例2
        int[] nums2 = {100};
        int result2 = solution.maxRotateFunction(nums2);
        System.out.println("Test Case 2:");
        System.out.println(result2); // Output: 0
    }
}
