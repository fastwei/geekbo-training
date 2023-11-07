package com.geekbo.training.leetcode.binarysearch;

/**
 * 81. Search in Rotated Sorted Array II
 * Medium
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 * <p>
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length)
 * such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
 * For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 * <p>
 * Given the array nums after the rotation and an integer target,
 * return true if target is in nums, or false if it is not in nums.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums is guaranteed to be rotated at some pivot.
 * -104 <= target <= 104
 * <p>
 * <p>
 * Follow up:
 * This problem is similar to Search in Rotated Sorted Array,
 * but nums may contain duplicates.
 * Would this affect the runtime complexity? How and why?
 */
public class SearchInRotatedSortedArrayII {
    /**
     * 在一个非递减排序的数组中查找目标值，数组在某个未知的旋转点进行了旋转。
     * <p>
     * 解题思路：
     * <p>
     * 由于数组在某个旋转点进行了旋转，因此我们无法直接使用二分查找来搜索目标值。
     * 我们可以使用一种变体的二分查找算法来解决这个问题。具体步骤如下：
     * 1. 初始化左指针left为数组的起始位置，右指针right为数组的结束位置。
     * 2. 在每一次迭代中，我们通过计算中间索引mid来确定中间元素的位置。
     * 3. 如果中间元素等于目标值，则返回true。
     * 4. 如果左半部分是有序的（即nums[left] <= nums[mid]），并且目标值在左半部分的范围内（即nums[left] <= target < nums[mid]），则将右指针right移动到mid-1的位置，否则将左指针left移动到mid+1的位置。
     * 5. 如果右半部分是有序的（即nums[mid] <= nums[right]），并且目标值在右半部分的范围内（即nums[mid] < target <= nums[right]），则将左指针left移动到mid+1的位置，否则将右指针right移动到mid-1的位置。
     * 6. 重复步骤2-5，直到左指针left大于右指针right，此时数组中不存在目标值，返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(log n)，其中n是数组的长度。
     * 空间复杂度：O(1)。
     */
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArrayII solution = new SearchInRotatedSortedArrayII();

        // 测试用例1
        int[] nums1 = {2, 5, 6, 0, 0, 1, 2};
        int target1 = 0;
        boolean expected1 = true;
        boolean result1 = solution.search(nums1, target1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2


        int[] nums2 = {2, 5, 6, 0, 0, 1, 2};
        int target2 = 3;
        boolean expected2 = false;
        boolean result2 = solution.search(nums2, target2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);
    }
}


