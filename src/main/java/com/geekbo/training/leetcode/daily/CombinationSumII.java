package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * Medium
 * Topics
 * Companies
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 */
public class CombinationSumII {
    /**
     * 求解给定数组中和为目标值的唯一组合的算法。
     * 给定一个候选数字的集合 candidates 和一个目标值 target，找出候选数字中所有唯一的组合，使其和等于目标值。
     * <p>
     * 解题思路：
     * <p>
     * 首先对数组进行排序，这样重复的数字就会连在一起；
     * 使用回溯算法，遍历数组中的每个数字；
     * 在回溯过程中，如果当前数字与前一个数字相等且前一个数字未被使用过，则跳过当前数字，以避免重复的组合；
     * 如果当前数字大于目标值，则可以提前终止遍历，因为之后的数字都会大于目标值；
     * 如果当前数字等于目标值，则将当前组合加入结果集；
     * 如果当前数字小于目标值，则将当前数字加入临时列表，继续递归下一个数字的选择。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(2^n)，其中 n 是数组的长度。
     * 在最坏的情况下，需要考虑所有可能的组合，而每个数字可以选择使用或不使用，所以总共有 2^n 种组合。
     * 空间复杂度：O(n)，其中 n 是数组的长度。
     * 除了存储结果以外，算法使用了一个临时列表来保存每个组合，临时列表的长度最多为 n。
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> tempList, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > target) {
                break;
            }

            tempList.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, tempList, result);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        // 唯一的组合为：
        // [1, 1, 6]
        // [1, 2, 5]
        // [1, 7]
        // [2, 6]
        // 所以预期结果是 [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        System.out.println("Test Case 1 - Expected: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]], Output: " + combinationSum2(candidates1, target1));

        // 测试用例2
        int[] candidates2 = {2, 5, 2, 1, 2};
        int target2 = 5;
        // 唯一的组合为：
        // [1, 2, 2]
        // [5]
        // 所以预期结果是 [[1, 2, 2], [5]]
        System.out.println("Test Case 2 - Expected: [[1, 2, 2], [5]], Output: " + combinationSum2(candidates2, target2));
    }
}