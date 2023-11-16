package com.geekbo.training.leetcode.daily;


import java.util.Arrays;

/**
 * 260. Single Number III
 * Medium
 * Given an integer array nums, in which exactly two elements appear only once
 * and all the other elements appear exactly twice.
 * Find the two elements that appear only once. You can return the answer in any order.
 * <p>
 * You must write an algorithm that runs in linear runtime complexity
 * and uses only constant extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 * Example 2:
 * <p>
 * Input: nums = [-1,0]
 * Output: [-1,0]
 * Example 3:
 * <p>
 * Input: nums = [0,1]
 * Output: [1,0]
 */
public class SingleNumberIII {

    /**
     * 给定一个整数数组nums，其中只有两个元素出现一次，其他所有元素都出现两次。
     * 找出只出现一次的这两个元素。你可以以任何顺序返回答案。
     * <p>
     * 要求：
     * <p>
     * 算法的时间复杂度必须为线性，即O(n)，其中n是数组nums的长度。
     * 算法只能使用常数额外空间。
     * 解题思路： 我们可以使用异或运算来解决这个问题。
     * 异或运算的特点是对于相同的数字进行异或运算，结果为0。
     * 我们可以将所有的元素进行异或运算，得到的结果是两个只出现一次的元素的异或结果。
     * <p>
     * 由于这两个元素是不同的，所以它们的异或结果中至少有一位是1。
     * 我们可以找到这个结果中最低位的1，并将数组中的元素按照该位是否为1进行分组。
     * 这样，相同的元素会被分到同一组，而只出现一次的两个元素会被分到不同的组。
     * <p>
     * 接下来，我们可以对每个分组进行异或运算，得到两个只出现一次的元素。
     * 最后，我们将这两个元素作为结果返回即可。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历数组nums的时间复杂度为O(n)，其中n是数组的长度。
     * 空间复杂度为O(1)，因为我们只使用了常数额外空间。
     *
     * @param nums
     * @return
     */
    public static int[] singleNumber(int[] nums) {
        // 对所有元素进行异或运算，得到两个只出现一次的元素的异或结果
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        // 找到异或结果中最低位的1
        int lowestBit = xor & -xor;

        // 将数组元素按照最低位是否为1进行分组，并对每个分组进行异或运算
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & lowestBit) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 2, 1, 3, 2, 5};
        // 只出现一次的元素为3和5
        int[] expected1 = {3, 5};
        int[] result1 = singleNumber(nums1);
        System.out.println(Arrays.equals(result1, expected1)); // Output: true

        // Test case 2
        int[] nums2 = {-1, 0};
        // 只出现一次的元素为-1和0
        int[] expected2 = {-1, 0};
        int[] result2 = singleNumber(nums2);
        System.out.println(Arrays.equals(result2, expected2)); // Output: true

        // Test case 3
        int[] nums3 = {0,

                1}; // 只出现一次的元素为0和1
        int[] expected3 = {1, 0};
        int[] result3 = singleNumber(nums3);
        System.out.println(Arrays.equals(result3, expected3));
        // Output: true
    }
}