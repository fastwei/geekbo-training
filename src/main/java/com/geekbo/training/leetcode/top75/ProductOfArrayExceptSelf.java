package com.geekbo.training.leetcode.top75;

import java.util.Arrays;


/**
 *
 * Array / String
 *
 * Given an integer array nums, return an array answer
 * such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Example 2:
 *
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity?
 * (The output array does not count as extra space for space complexity analysis.)
 *
 *
 *我们可以使用两个辅助数组，分别存储当前元素左侧和右侧的乘积，然后将这两个数组相乘得到最终结果。
 * 这样可以满足O(n)时间复杂度的要求，而且不使用除法操作。
 *
 * 算法复杂度分析：
 *
 * 时间复杂度：O(n)。我们遍历数组三次，每次都是线性时间。
 * 空间复杂度：O(n)。需要额外的空间来存储左侧和右侧的乘积数组。
 *
 * Array / String
 * todo:暂时没理解代码
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        // 创建左侧和右侧乘积数组
        int[] leftProducts = new int[n];
        int[] rightProducts = new int[n];

        // 初始化左侧乘积数组

        int leftProduct = 1;
        /*
         *[1,2,3,4]
         *
         * i--leftProduct,leftProducts[i],num[i]
         *
         * 0--1,1,1
         * 1--1,1,2
         * 2--2,2,3
         * 3--3,3,4
         *
         *
         */

        for (int i = 0; i < n; i++) {
            leftProducts[i] = leftProduct;
            leftProduct *= nums[i];
        }

        // 初始化右侧乘积数组
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            rightProducts[i] = rightProduct;
            rightProduct *= nums[i];
        }

        // 计算最终结果
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = leftProducts[i] * rightProducts[i];
        }

        return result;
    }


    /**
     * 为了实现O(1)的额外空间复杂度，我们可以进一步优化算法。
     * 我们可以使用输出数组来存储左侧或右侧的乘积，以减少额外的空间使用。具体步骤如下：
     *
     * 创建结果数组result，初始化为全1。
     * 遍历一次数组，计算每个元素左侧的乘积，并将结果存储在result中。
     * 遍历第二次数组，计算每个元素右侧的乘积，然后将其与result相乘，得到最终结果。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // 初始化结果数组为全1
        Arrays.fill(result, 1);

        int leftProduct = 1;

        // 计算每个元素左侧的乘积，存储在result中
        for (int i = 0; i < n; i++) {
            result[i] *= leftProduct;
            leftProduct *= nums[i];
        }

        int rightProduct = 1;

        // 计算每个元素右侧的乘积，与result相乘得到最终结果
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }


    public static void main(String[] args) {
        ProductOfArrayExceptSelf solution = new ProductOfArrayExceptSelf();

        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = solution.productExceptSelf(nums1);
        System.out.println(Arrays.toString(result1)); // 应输出 [24, 12, 8, 6]

        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = solution.productExceptSelf(nums2);
        System.out.println(Arrays.toString(result2)); // 应输出 [0, 0, 9, 0, 0]
    }

}
