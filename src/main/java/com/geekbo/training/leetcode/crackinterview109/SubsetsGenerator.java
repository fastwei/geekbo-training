package com.geekbo.training.leetcode.crackinterview109;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 *  输入： nums = [1,2,3]
 *  输出：
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
class SubsetsGenerator {

    /**
     * 解题思路：
     * <p>
     * 使用回溯算法来生成所有的子集。
     * 遍历数组中的每个元素，将其添加到当前子集中，并递归地生成下一个元素的子集。
     * 每次回溯完成后，将当前子集添加到结果集中。
     * 初始时，结果集只包含一个空子集。
     * 最终返回结果集。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(2^n)，其中n是数组nums的长度。因为每个元素在子集中有两种状态，即存在或不存在，所以一共有2^n个子集。
     * 空间复杂度：O(n)，回溯算法使用的空间是递归栈的深度，最多为n。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<Integer>(), result);
        return result;
    }

    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsGenerator solution = new SubsetsGenerator();

        // 测试用例1
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> result1 = solution.subsets(nums1);
        System.out.println(result1);
        // 预期输出: [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]

        // 测试用例2
        int[] nums2 = {4, 5, 6};
        List<List<Integer>> result2 = solution.subsets(nums2);
        System.out.println(result2);
        // 预期输出: [[], [4], [4, 5], [4, 5, 6], [4, 6], [5], [5, 6], [6]]
    }
}


