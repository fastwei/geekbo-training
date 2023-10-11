package com.geekbo.training.leetcode.top75;

/**
 *Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 1
 *
 *
 * 解题思路：
 *
 * 使用位运算中的异或操作（XOR）。异或操作有一个重要的性质，即相同数字异或结果为0，0与任何数异或结果为该数本身。因此，将所有数字进行异或操作，最终结果即为出现一次的数字。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(n)，需要遍历整个数组。
 * 空间复杂度：O(1)，只使用常数额外空间。
 *
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;

        // 使用异或操作，相同的数字异或结果为0，0与任何数异或结果为该数本身
        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    public static void main(String[] args) {
        SingleNumber solution = new SingleNumber();

        // 测试用例1
        int[] nums1 = {2, 2, 1};
        int result1 = solution.singleNumber(nums1);
        System.out.println(result1); // 预期输出: 1

        // 测试用例2
        int[] nums2 = {4, 1, 2, 1, 2};
        int result2 = solution.singleNumber(nums2);
        System.out.println(result2); // 预期输出: 4

        // 测试用例3
        int[] nums3 = {1};
        int result3 = solution.singleNumber(nums3);
        System.out.println(result3); // 预期输出: 1
    }
}
