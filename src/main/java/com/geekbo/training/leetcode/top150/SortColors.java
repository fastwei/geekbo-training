package com.geekbo.training.leetcode.top150;

/**
 * Given an array nums with n objects colored red, white, or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 * <p>
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * <p>
 * You must solve this problem without using the library's sort function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 * <p>
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 * <p>
 * <p>
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
public class SortColors {
    /**
     * 解题思路：
     * <p>
     * 使用三个指针，left、right和curr，
     * 其中left指向已经排好序的0的右边界，right指向已经排好序的2的左边界，curr指向当前元素。
     * 遍历数组，如果当前元素是0，则将其交换到左边界，并将left和curr都向右移动一位。
     * 如果当前元素是2，则将其交换到右边界，并将right向左移动一位。
     * 如果当前元素是1，则直接跳过。
     * 遍历结束后，数组中的元素按照红、白、蓝的顺序排列。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n)，其中n是数组的长度。
     * 空间复杂度：O(1)，只使用了常数额外空间。
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int left = 0; // 指向已经排好序的0的右边界
        int right = nums.length - 1; // 指向已经排好序的2的左边界
        int curr = 0; // 当前元素的索引

        while (curr <= right) {
            if (nums[curr] == 0) {
                // 如果当前元素是0，将其交换到左边界
                swap(nums, curr, left);
                curr++;
                left++;
            } else if (nums[curr] == 2) {
                // 如果当前元素是2，将其交换到右边界
                swap(nums, curr, right);
                right--;
            } else {
                // 如果当前元素是1，直接跳过
                curr++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        SortColors sortColors = new SortColors();

        // Test Case 1
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        sortColors.sortColors(nums1);
        System.out.println("Test Case 1:");
        System.out.println("Input: [2, 0, 2, 1, 1, 0]");
        System.out.print("Output: [");
        for (int num : nums1) {
            System.out.print(num + ", ");
        }
        System.out.println("]");

        // Test Case 2
        int[] nums2 = {2, 0, 1};
        sortColors.sortColors(nums2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: [2, 0, 1]");
        System.out.print("Output: [");
        for (int num : nums2) {
            System.out.print(num + ", ");
        }
        System.out.println("]");
    }
}
