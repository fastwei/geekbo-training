package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array nums of unique elements, return all possible
 * subsets
 * (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class Subsets {
    /**
     * 解题思路： 这道题是经典的回溯算法问题。我们可以使用递归来生成所有的子集。
     * 回溯算法的核心思想是穷举出所有可能的解，并在穷举的过程中剪枝，以达到减少计算量的目的。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(2^n)，其中n是数组nums的长度。每个元素都有两种选择：选择放入子集或者不选择放入子集。
     * 空间复杂度：O(n)，其中n是数组nums的长度。递归调用的栈空间的深度最大为n。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> subset, List<List<Integer>> result) {
        result.add(new ArrayList<>(subset));
        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            backtrack(nums, i + 1, subset, result);
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets subsets = new Subsets();

        // Test Case 1
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> result1 = subsets.subsets(nums1);
        System.out.println(result1);  // Expected output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

        // Test Case 2
        int[] nums2 = {0};
        List<List<Integer>> result2 = subsets.subsets(nums2);
        System.out.println(result2);  // Expected output: [[],[0]]
    }
}

