package com.geekbo.training.leetcode.top150.hard;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
 * For example, the array nums = [0,1,4,4,5,6,7] might become:
 * <p>
 * [4,5,6,7,0,1,4] if it was rotated 4 times.
 * [0,1,4,4,5,6,7] if it was rotated 7 times.
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]]
 * 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 * <p>
 * Given the sorted rotated array nums that may contain duplicates,
 * return the minimum element of this array.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,5]
 * Output: 1
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,0,1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums is sorted and rotated between 1 and n times.
 * <p>
 * <p>
 * Follow up: This problem is similar to Find Minimum in Rotated Sorted Array,
 * but nums may contain duplicates. Would this affect the runtime complexity? How and why?
 */
public class MinimumInRotatedSortedArrayII {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5};
        System.out.println(findMin(nums1)); // Output: 1

        int[] nums2 = {2, 2, 2, 0, 1};
        System.out.println(findMin(nums2)); // Output: 0
    }

    /**
     * 解题思路：
     * 由于数组可能包含重复元素，我们不能简单地使用二分查找来找到最小元素。
     * 我们可以通过修改二分查找算法来解决这个问题。
     * 当我们遇到重复元素时，我们可以忽略右边界，而不是忽略左边界。
     * 这是因为右边界对应的元素肯定不是最小值，而左边界可能是最小值。
     * 算法的时间复杂度在最坏情况下是O(n)，
     * 其中n是数组的长度。考虑数组中的元素都相等的特殊情况，
     * 即数组中的元素都为1，此时无法通过二分查找的方法判断最小值的位置，
     * 所以需要遍历整个数组，时间复杂度为O(n)。
     *
     * @param nums 旋转排序数组
     * @return 最小元素
     */
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }
}