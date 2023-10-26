package com.geekbo.training.leetcode.top150.hard;

/**
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * <p>
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,0]
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 * Example 2:
 * <p>
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 * Example 3:
 * <p>
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 */
public class FirstMissingPositive {
    /**
     * 解题思路： 我们可以使用原地哈希的思想来解决这个问题。
     * 首先，我们将所有负数和零替换为n+1，其中n是数组的长度。
     * 这样，数组中的所有元素都是正数。
     * 然后，我们遍历数组，将出现的正数对应的位置标记为负数。
     * 最后，我们再次遍历数组，找到第一个未被标记的正数，即为最小缺失的正数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是数组的长度。我们需要遍历数组两次。
     * 空间复杂度：O(1)，我们只需要常数个额外的空间。
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        // 将所有负数和零替换为n+1，其中n是数组的长度
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        // 标记出现的正数
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // 找到第一个未被标记的正数
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // 如果数组中的所有数都被标记，则返回n+1
        return n + 1;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums = {1, 2, 0};
        // 预期输出为3
        System.out.println(firstMissingPositive(nums));

        // Test Case 2
        nums = new int[]{3, 4, -1, 1};
        // 预期输出为2
        System.out.println(firstMissingPositive(nums));

        // Test Case 3
        nums = new int[]{7, 8, 9, 11, 12};
        // 预期输出为1
        System.out.println(firstMissingPositive(nums));
    }
}
