package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. Contiguous Array
 * Medium
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 * Example 2:
 *
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 *
 */
public class ContiguousArray {
    /**
     *
     * 解题思路：
     *
     * 使用一个变量 count 来记录当前子数组中 0 的个数减去 1 的个数。如果 count 的值为 0，则表示当前子数组中 0 和 1 的个数相等。
     * 使用一个 Map 来记录每个 count 值第一次出现的位置。如果 count 值已经存在于 Map 中，表示在当前位置之前有相同的 count 值出现过，这样就可以计算当前子数组的长度。
     * 遍历数组，更新 count 值并更新 maxLength 的值。
     * 返回 maxLength。
     * 算法复杂度分析：
     *
     * 时间复杂度：O(n)，其中 n 是数组的长度。
     * 空间复杂度：O(n)，使用了一个 Map 来存储每个 count 值第一次出现的位置。
     *
     * @param nums
     * @return
     */
    public static int findMaxLength(int[] nums) {
        int maxLength = 0;
        int count = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count--;
            } else {
                count++;
            }
            
            if (countMap.containsKey(count)) {
                maxLength = Math.max(maxLength, i - countMap.get(count));
            } else {
                countMap.put(count, i);
            }
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {0, 1};
        int[] nums2 = {0, 1, 0};
        
        System.out.println(findMaxLength(nums1));  // Output: 2
        System.out.println(findMaxLength(nums2));  // Output: 2
    }
}


