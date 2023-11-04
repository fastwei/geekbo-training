package com.geekbo.training.leetcode.binarysearch;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once.
 * <p>
 * Return the single element that appears only once.
 * <p>
 * Your solution must run in O(log n) time and O(1) space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 */
public class SingleElement {
    /**
     * 解题思路：
     * <p>
     * 由于数组中除了一个元素外，其他元素都出现了两次，因此可以使用异或运算来找到只出现一次的元素。
     * 异或运算具有以下性质：
     * 1. 任何数和0异或，结果仍然是这个数。
     * 2. 任何数和自身异或，结果是0。
     * 3. 异或运算满足交换律和结合律。
     * <p>
     * 因此，如果将数组中所有的元素进行异或运算，最终的结果就是只出现一次的元素。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历数组的时间复杂度为O(n)，其中n为数组的长度。
     * 空间复杂度：仅使用了常数个额外变量，因此空间复杂度为O(1)。
     *
     * @param nums
     * @return
     */
    public static int singleElement2(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    /**
     * 解题思路：
     * 由于数组中除了一个元素外，其他元素都出现了两次，因此可以使用二分查找来找到只出现一次的元素。
     * 根据题目要求，数组长度为奇数，且除了一个元素外，其他元素都是成对出现的。
     * 因此，对于任意一个成对出现的元素，它的索引位置必定是偶数。
     * 如果一个元素的索引位置是偶数，那么它的下一个元素的索引位置必定是奇数。
     * 反之，如果一个元素的索引位置是奇数，那么它的上一个元素的索引位置必定是偶数。
     * 因此，我们可以通过二分查找，每次判断中间元素的索引位置是奇数还是偶数，来确定只出现一次的元素的位置。
     * 如果中间元素的索引位置是偶数，那么只出现一次的元素在中间元素的右侧；
     * 如果中间元素的索引位置是奇数，那么只出现一次的元素在中间元素的左侧。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：二分查找的时间复杂度为O(log n)，其中n为数组的长度。
     * 空间复杂度：仅使用了常数个额外变量和递归调用栈，因此空间复杂度为O(1)。
     *
     * @param nums
     * @return
     */
    public static int singleElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) {
                mid--; // 将mid调整为偶数，保证查找范围是偶数个元素
            }

            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        int expected1 = 2;
        int result1 = singleElement(nums1);
        System.out.println("Test case 1:");
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);
        System.out.println();

        // Test case 2
        int[] nums2 = {3, 3, 7, 7, 10, 11, 11};
        int expected2 = 10;
        int result2 = singleElement(nums2);
        System.out.println("Test case 2:");
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);
    }
}