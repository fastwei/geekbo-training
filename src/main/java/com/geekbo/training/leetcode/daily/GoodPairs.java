package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array of integers nums, return the number of good pairs.
 *
 * A pair (i, j) is called good if nums[i] == nums[j] and i < j.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1,1,3]
 * Output: 4
 * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
 * Example 2:
 *
 * Input: nums = [1,1,1,1]
 * Output: 6
 * Explanation: Each pair in the array are good.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 0
 *
 */
public class GoodPairs {
    /**
     * 解题思路： 这道题可以使用哈希表来解决。
     * 我们遍历数组nums，对于每个元素num，我们在哈希表中查找是否已经存在相同的元素，
     * 如果存在，将其对应的计数加到结果goodPairs中，然后将num的计数加1；如果不存在，将num加入哈希表中。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是数组nums的长度。
     * 我们需要遍历数组nums一次，并在哈希表中进行查找和更新操作，每个操作的时间复杂度为O(1)。
     * 空间复杂度：O(n)，其中n是数组nums的长度。哈希表最多存储n个元素。
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int goodPairs = 0;

        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            goodPairs += count;
            countMap.put(num, count + 1);
        }

        return goodPairs;
    }

    public static void main(String[] args) {
        GoodPairs goodPairs = new GoodPairs();

        // Test Case 1
        int[] nums1 = {1, 2, 3, 1, 1, 3};
        int result1 = goodPairs.numIdenticalPairs(nums1);
        System.out.println(result1);  // Expected output: 4

        // Test Case 2
        int[] nums2 = {1, 1, 1, 1};
        int result2 = goodPairs.numIdenticalPairs(nums2);
        System.out.println(result2);  // Expected output: 6

        // Test Case 3
        int[] nums3 = {1, 2, 3};
        int result3 = goodPairs.numIdenticalPairs(nums3);
        System.out.println(result3);  // Expected output: 0
    }
}
