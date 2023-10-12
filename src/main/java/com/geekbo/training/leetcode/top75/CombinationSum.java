package com.geekbo.training.leetcode.top75;

import java.util.ArrayList;
import java.util.List;

/**
 * 39.Combination Sum
 * <p>
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the
 * frequency
 * of at least one of the chosen numbers is different.
 * <p>
 * The test cases are generated such that the number of unique combinations
 * that sum up to target is less than 150 combinations for the given input.
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * <p>
 * Example 2:
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 * <p>
 * Input: candidates = [2], target = 1
 * Output: []
 * <p>
 * 解题思路：
 * <p>
 * 使用回溯算法，从数组candidates中选择元素，不断尝试所有可能的组合。
 * 递归过程中，维护一个当前组合current和当前位置start。
 * 每次递归都尝试选择candidates[start]，并将其添加到current中，然后递归继续寻找剩余部分的组合。
 * 当current的和等于target时，将当前组合加入结果列表。
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(2^n)，其中n是candidates数组的长度，最坏情况下，需要尝试所有可能的组合。
 * 空间复杂度：O(n)，递归调用栈的深度最多为n。
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, candidates, target, new ArrayList<>(), 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, int[] candidates, int target, List<Integer> current, int start) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(result, candidates, target - candidates[i], current, i);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum solution = new CombinationSum();

        // 测试用例1
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        List<List<Integer>> result1 = solution.combinationSum(candidates1, target1);
        System.out.println(result1); // 预期输出: [[2,2,3],[7]]

        // 测试用例2
        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        List<List<Integer>> result2 = solution.combinationSum(candidates2, target2);
        System.out.println(result2); // 预期输出: [[2,2,2,2],[2,3,3],[3,5]]

        // 测试用例3
        int[] candidates3 = {2};
        int target3 = 1;
        List<List<Integer>> result3 = solution.combinationSum(candidates3, target3);
        System.out.println(result3); // 预期输出: []
    }
}
