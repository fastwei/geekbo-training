package com.geekbo.training.leetcode.template;

import java.util.ArrayList;
import java.util.List;

/**
 * dfs函数实现了深度优先搜索的逻辑。
 * 它接受四个参数：result用于存储结果集，path用于保存当前的路径，nums是输入数组，visited用于记录每个元素是否被访问过。
 * 在递归函数中，首先判断是否满足终止条件，即路径的长度等于数组的长度，如果满足则将路径加入结果集。
 * 然后遍历选择列表，对未访问过的元素进行递归调用。在递归调用前后进行选择的操作和撤销选择的操作。
 * 最后在dfsFunction函数中创建结果集和路径，并调用dfs函数进行深度优先搜索。最后输出结果。
 * <p>
 * 在main函数中，我们创建了一个DFSTemplate对象，并调用dfsFunction函数进行深度优先搜索。最后输出结果。
 */
public class DFSTemplate {

    public void dfs(List<List<Integer>> result, List<Integer> path, int[] nums, boolean[] visited) {
        // 终止条件：当路径的长度等于数组的长度时，将路径加入结果集
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 遍历选择列表
        for (int i = 0; i < nums.length; i++) {
            // 跳过已经访问过的元素
            if (visited[i]) {
                continue;
            }

            // 做出选择
            path.add(nums[i]);
            visited[i] = true;

            // 递归进入下一层
            dfs(result, path, nums, visited);

            // 撤销选择
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

    public List<List<Integer>> dfsFunction(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(result, path, nums, visited);
        return result;
    }

    public static void main(String[] args) {
        DFSTemplate template = new DFSTemplate();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = template.dfsFunction(nums);
        System.out.println(result);
    }
}