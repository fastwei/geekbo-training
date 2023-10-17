package com.geekbo.training.leetcode.top150;

/**
 * Monotonic Array
 * Easy
 * Topics
 * Companies
 * An array is monotonic if it is either monotone increasing or monotone decreasing.
 * <p>
 * An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j].
 * An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
 * <p>
 * Given an integer array nums, return true if the given array is monotonic, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,2,3]
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [6,5,4,4]
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,3,2]
 * Output: false
 */
public class MonotonicArray {

    /**
     * 我们使用两个布尔变量increasing和decreasing来分别表示数组是否单调递增和单调递减。
     * <p>
     * 通过遍历数组，我们检查相邻元素之间的关系。
     * 如果存在任何一个相邻元素的大小关系不满足单调递增的条件（即后一个元素小于前一个元素），则将increasing设置为false。
     * 同样，如果存在任何一个相邻元素的大小关系不满足单调递减的条件（即后一个元素大于前一个元素），则将decreasing设置为false。
     * <p>
     * 最后，如果increasing或decreasing中有一个为true，则说明数组是单调的，返回true；否则，返回false。
     * <p>
     * 根据代码的实现和题目要求，可以得出以下解题思路：
     * <p>
     * 遍历数组，检查相邻元素的大小关系。
     * 如果存在任何一个相邻元素的大小关系不满足单调递增的条件，则数组不是单调递增的。
     * 如果存在任何一个相邻元素的大小关系不满足单调递减的条件，则数组不是单调递减的。
     * 如果数组既不是单调递增也不是单调递减，则数组不是单调的。
     * 如果数组满足单调递增或单调递减的条件，返回true；否则，返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历数组的时间复杂度为O(n)，其中n是数组的长度。
     * 空间复杂度为O(1)，不需要额外的空间。
     *
     * @param nums
     * @return
     */
    public static boolean isMonotonic(int[] nums) {
        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                increasing = false;
            }
            if (nums[i] > nums[i - 1]) {
                decreasing = false;
            }
        }

        return increasing || decreasing;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 3};
        System.out.println(isMonotonic(nums1)); // Output: true

        int[] nums2 = {6, 5, 4, 4};
        System.out.println(isMonotonic(nums2)); // Output: true

        int[] nums3 = {1, 3, 2};
        System.out.println(isMonotonic(nums3)); // Output: false
    }
}
