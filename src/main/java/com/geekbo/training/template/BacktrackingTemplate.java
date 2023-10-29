package com.geekbo.training.template;

import java.util.ArrayList;
import java.util.List;

/**
 * backtrack函数实现了回溯的逻辑。
 * 它接受四个参数：results是存储所有结果的列表，tempList是当前组合的临时列表，nums是原始数组，start是当前选择列表的起始位置。
 * 在函数中，我们首先将当前组合加入结果集。
 * 然后遍历选择列表，每次选择一个元素，将其加入到临时列表中，然后进入下一层决策树，递归调用backtrack函数。
 * 在下一层决策树返回后，撤销选择，将临时列表的最后一个元素移除，继续遍历选择列表。最后输出结果。
 * <p>
 * 在subsets函数中，我们创建了一个空的结果集results，调用backtrack函数生成所有子集，然后返回结果。
 */
public class BacktrackingTemplate {

    public void backtrack(List<List<Integer>> results, List<Integer> tempList, int[] nums, int start) {
        // 将当前组合加入结果集
        results.add(new ArrayList<>(tempList));

        // 遍历选择列表
        for (int i = start; i < nums.length; i++) {
            // 做出选择
            tempList.add(nums[i]);

            // 进入下一层决策树
            backtrack(results, tempList, nums, i + 1);

            // 撤销选择
            tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(results, new ArrayList<>(), nums, 0);
        return results;
    }

    public static void main(String[] args) {
        BacktrackingTemplate template = new BacktrackingTemplate();
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = template.subsets(nums);
        System.out.println(subsets);
    }
}