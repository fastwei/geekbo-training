package com.geekbo.training.leetcode.top75;

import java.util.ArrayList;
import java.util.List;


/**
 * 216. Combination Sum III
 * <p>
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * <p>
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations.
 * The list must not contain the same combination twice, and the combinations may be returned in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 * Example 3:
 * <p>
 * Input: k = 4, n = 1
 * Output: []
 * Explanation: There are no valid combinations.
 * Using 4 different numbers in the range [1,9],
 * the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 使用回溯算法，从1到9的数字中选择k个数字，使它们的和等于n。
 * 在回溯过程中，维护一个列表current来存储当前的组合，一个参数start表示当前可以选择的数字的起始位置。
 * 在每一步，如果k为0且target为0，表示找到一个有效的组合，将其添加到结果列表中。
 * 否则，从start到9的范围内选择一个数字i，将其加入current中，继续递归搜索，然后将i从current中移除。
 * 在搜索过程中，根据剩余的k和target，以及当前的start位置，剪枝，提前结束不可能得到有效解的分支。
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(C(9, k))，其中C(9, k)表示从9个数字中选择k个的组合数。
 * 空间复杂度：O(k)，递归调用栈的深度最多为k。
 *
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    // 回溯函数
    private void backtrack(List<List<Integer>> result, List<Integer> current, int k, int target, int start) {
        if (k == 0 && target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (target - i < 0) {
                // 若i超过了target，提前结束循环，因为后面的数字更大
                break;
            }
            current.add(i);
            backtrack(result, current, k - 1, target - i, i + 1);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumIII solution = new CombinationSumIII();

        // 测试用例1
        int k1 = 3, n1 = 7;
        List<List<Integer>> result1 = solution.combinationSum3(k1, n1);
        System.out.println(result1); // 预期输出: [[1, 2, 4]]

        // 测试用例2
        int k2 = 3, n2 = 9;
        List<List<Integer>> result2 = solution.combinationSum3(k2, n2);
        System.out.println(result2); // 预期输出: [[1, 2, 6], [1, 3, 5], [2, 3, 4]]

        // 测试用例3
        int k3 = 4, n3 = 1;
        List<List<Integer>> result3 = solution.combinationSum3(k3, n3);
        System.out.println(result3); // 预期输出: []
    }
}
