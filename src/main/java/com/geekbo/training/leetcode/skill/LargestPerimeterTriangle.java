package com.geekbo.training.leetcode.skill;

import java.util.Arrays;

/**
 * Given an integer array nums,
 * return the largest perimeter of a triangle with a non-zero area,
 * formed from three of these lengths.
 * If it is impossible to form any triangle of a non-zero area, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,1,2]
 * Output: 5
 * Explanation: You can form a triangle with three side lengths: 1, 2, and 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,10]
 * Output: 0
 * Explanation:
 * You cannot use the side lengths 1, 1, and 2 to form a triangle.
 * You cannot use the side lengths 1, 1, and 10 to form a triangle.
 * You cannot use the side lengths 1, 2, and 10 to form a triangle.
 * As we cannot use any three side lengths to form a triangle of non-zero area, we return 0.
 */
public class LargestPerimeterTriangle {
    /**
     * 解题思路：
     * 为了构成一个三角形，任意两边之和必须大于第三边。
     * 我们可以将数组排序，然后从后往前遍历，每次取最大的三个边长进行判断。
     * 如果找到了满足三角形条件的边长组合，则返回其周长。如果遍历完整个数组都没有找到满足条件的边长组合，则返回0。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：对数组进行排序需要O(nlogn)的时间复杂度，其中n是nums数组的长度。
     * 遍历数组需要O(n)的时间复杂度。所以总时间复杂度为O(nlogn)。
     * - 空间复杂度：使用了常数个变量，所以空间复杂度为O(1)。
     *
     * @param nums
     * @return
     */
    public int largestPerimeter(int[] nums) {
        // 对数组进行排序
        Arrays.sort(nums);

        // 从后往前遍历数组，找到第一个满足三角形条件的边长组合
        for (int i = nums.length - 1; i >= 2; i--) {
            int a = nums[i];
            int b = nums[i - 1];
            int c = nums[i - 2];

            // 判断是否满足三角形条件
            if (a < b + c) {
                return a + b + c;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        LargestPerimeterTriangle triangle = new LargestPerimeterTriangle();

        // Test Case 1
        int[] nums = {2, 1, 2};
        // 预期输出为5
        System.out.println(triangle.largestPerimeter(nums));

        // Test Case 2
        nums = new int[]{1, 2, 1, 10};
        // 预期输出为0
        System.out.println(triangle.largestPerimeter(nums));
    }
}

