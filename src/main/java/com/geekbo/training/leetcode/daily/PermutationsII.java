package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers, nums, that might contain duplicates,
 * return all possible unique permutations in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * Example 2:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class PermutationsII {
    /**
     * 求解给定数组的所有可能唯一排列的算法。给定一个包含重复元素的数组 nums，返回其中所有可能的唯一排列。
     * <p>
     * 解题思路：
     * <p>
     * 首先对数组进行排序，这样重复的数字就会连在一起；
     * 使用回溯算法，遍历数组中的每个数字，同时使用一个布尔数组记录该数字是否已经被使用过；
     * 在回溯过程中，如果当前数字已经被使用过或者当前数字与前一个数字相等且前一个数字未被使用过，则跳过当前数字，以避免重复的排列；
     * <p>
     * 如果当前排列的长度等于数组的长度，说明已经得到一个完整的排列，将其加入结果集；
     * 否则，将当前数字加入临时列表，标记为已使用，继续递归下一个数字的选择；
     * 递归完成后，需要将临时列表中的最后一个数字移除，并将当前数字标记为未使用，以便尝试其他的选择。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n * n!)，其中 n 是数组的长度。
     * 由于存在重复数字，需要对数组进行排序，时间复杂度为 O(n log n)。
     * 每个排列的长度为 n，总共有 n! 个排列，所以总时间复杂度为 O(n * n!)。
     * 空间复杂度：O(n)，其中 n 是数组的长度。除了存储结果以外，算法使用了一个临时列表和一个布尔数组，它们的空间复杂度均为 O(n)。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, boolean[] used, List<Integer> tempList, List<List<Integer>> result) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;
            }

            used[i] = true;
            tempList.add(nums[i]);
            backtrack(nums, used, tempList, result);
            tempList.remove(tempList.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {1, 1, 2};
        // 可能的唯一排列为：
        // [1, 1, 2]
        // [1, 2, 1]
        // [2, 1, 1]
        // 所以预期结果是 [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
        System.out.println("Test Case 1 - Expected: [[1, 1, 2], [1, 2, 1], [2, 1, 1]], Output: " + permuteUnique(nums1));

        // 测试用例2
        int[] nums2 = {1, 2, 3};
        // 可能的唯一排列为：
        // [1, 2, 3]
        // [1, 3, 2]
        // [2, 1, 3]
        // [2, 3, 1]
        // [3, 1, 2]
        // [3, 2, 1]
        // 所以预期结果是 [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        System.out.println("Test Case 2 - Expected: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]], Output: " + permuteUnique(nums2));
    }
}
