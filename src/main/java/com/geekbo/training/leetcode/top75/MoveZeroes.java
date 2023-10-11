package com.geekbo.training.leetcode.top75;


import java.util.Arrays;

/**
 * Two Pointers
 *
 *Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 *
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 *
 *解决思路：
 *
 * 我们使用一个指针nonZeroIndex来记录非零元素应该放置的位置。
 * 遍历数组，当遇到非零元素时，将其移到nonZeroIndex的位置，并将原位置置零，然后nonZeroIndex递增。
 * 这样，所有非零元素都会被移到数组的前面，而零元素会留在后面，保持了相对顺序。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(n)，其中n是数组的长度。我们只需要遍历数组一次。
 * 空间复杂度：O(1)，我们没有使用额外的空间，只使用了常量空间。
 *
 *
 *Two Pointers
 *
 */
public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int nonZeroIndex = 0; // 用于记录非零元素的位置

        // 遍历数组，将非零元素移到数组的前面
        //nums = [0,1,0,3,12]
        for (int i = 0; i < nums.length; i++) {
            //当遇到非零元素时，将其移到nonZeroIndex的位置，并将原位置置零，然后nonZeroIndex递增。
            if (nums[i] != 0) {
                nums[nonZeroIndex] = nums[i];
                if (nonZeroIndex != i) {
                    nums[i] = 0;
                }
                nonZeroIndex++;
            }
        }
    }
    public static void main(String[] args) {
        MoveZeroes solution = new MoveZeroes();

        int[] nums1 = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1)); // 应输出 [1, 3, 12, 0, 0]

        int[] nums2 = {0};
        solution.moveZeroes(nums2);
        System.out.println(Arrays.toString(nums2)); // 应输出 [0]
    }

}
