package com.geekbo.training.leetcode.daily;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * Medium
 * Topics
 * Companies
 * Given an integer array nums that may contain duplicates, return all possible
 * subsets
 * (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class SubsetsII {
    /**
     * 解题思路： 首先对数组进行排序，这样重复的数字就会连在一起；
     * 使用回溯算法，遍历数组中的每个数字，同时使用一个布尔数组记录该数字是否已经被使用过；
     * 在回溯过程中，如果当前数字已经被使用过或者当前数字与前一个数字相等且前一个数字未被使用过，
     * 则跳过当前数字，以避免重复的子集； 如果当前子集的长度等于数组的长度，说明已经得到一个完整的子集，将其加入结果集；
     * 否则，将当前数字加入临时列表，标记为已使用，继续递归下一个数字的选择；
     * 递归完成后，需要将临时列表中的最后一个数字移除，并将当前数字标记为未使用，以便尝试其他的选择。
     * <p>
     * 算法复杂度分析： 时间复杂度：O(n * 2^n)，其中 n 是数组的长度。
     * 由于存在重复数字，需要对数组进行排序，时间复杂度为 O(n log n)。
     * 每个子集的长度最多为 n，总共有 2^n 个子集，所以总时间复杂度为 O(n * 2^n)。
     * 空间复杂度：O(n)，其中 n 是数组的长度。
     * 除了存储结果以外，算法使用了一个临时列表和一个布尔数组，它们的空间复杂度均为 O(n)。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int start, List<Integer> tempList, List<List<Integer>> result) {
        result.add(new ArrayList<>(tempList));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            tempList.add(nums[i]);
            backtrack(nums, i + 1, tempList, result);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 2, 2};
        // Expected output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
        System.out.println(subsetsWithDup(nums1));

        // Test Case 2
        int[] nums2 = {0};
        // Expected output: [[],[0]]
        System.out.println(subsetsWithDup(nums2));
    }
}