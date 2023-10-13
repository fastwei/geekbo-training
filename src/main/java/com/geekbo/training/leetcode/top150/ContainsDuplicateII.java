package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an integer array nums and an integer k,
 * return true if there are two distinct indices i and j in the array
 * such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 */
public class ContainsDuplicateII {

    /**
     * 给定一个整数数组nums和一个整数k，如果数组中存在两个不同的索引i和j，
     * 使得nums[i] == nums[j]且abs(i - j) <= k，则返回true。
     * <p>
     * 示例： 输入：nums = [1,2,3,1], k = 3 输出：true
     * <p>
     * 输入：nums = [1,0,1,1], k = 1 输出：true
     * <p>
     * 输入：nums = [1,2,3,1,2,3], k = 2 输出：false
     * <p>
     * 解题思路：
     * <p>
     * 遍历数组nums，使用一个HashMap来记录每个元素及其最近出现的索引。
     * 对于当前遍历到的元素nums[i]，如果该元素已经在HashMap中存在，
     * 并且当前索引i与HashMap中记录的最近出现索引的差值小于等于k，则返回true。
     * 如果遍历完成后仍然没有找到满足条件的元素对，则返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历整个数组nums需要O(n)的时间，其中n为数组的长度。
     * 在遍历过程中，我们需要将每个元素及其最近出现的索引记录到HashMap中，HashMap的插入和查询操作的时间复杂度均为O(1)。
     * 因此，总体时间复杂度为O(n)。
     * 空间复杂度：HashMap中最多存储n个元素，因此空间复杂度为O(n)。
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateII solution = new ContainsDuplicateII();

        int[] nums1 = {1, 2, 3, 1};
        int k1 = 3;
        boolean expected1 = true;
        boolean result1 = solution.containsNearbyDuplicate(nums1, k1);
        System.out.println(result1 == expected1); // true

        int[] nums2 = {1, 0, 1, 1};
        int k2 = 1;
        boolean expected2 = true;
        boolean result2 = solution.containsNearbyDuplicate(nums2, k2);
        System.out.println(result2 == expected2); // true

        int[] nums3 = {1, 2, 3, 1, 2, 3};
        int k3 = 2;
        boolean expected3 = false;
        boolean result3 = solution.containsNearbyDuplicate(nums3, k3);
        System.out.println(result3 == expected3); // true
    }
}