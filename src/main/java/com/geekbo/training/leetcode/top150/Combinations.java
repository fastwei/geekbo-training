package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 * <p>
 * You may return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 * <p>
 * Example 2:
 * <p>
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 */
public class Combinations {

    /**
     * 我们可以进行以下两个优化：
     * <p>
     * 剪枝操作：在回溯过程中，如果当前组合的大小加上剩余可选择的数字的数量小于等于k，
     * 那么就没有必要继续遍历剩余的数字了，因为即使将剩余的数字全部添加到当前组合中，也无法满足所需的k个数字。
     * 因此，我们可以在递归调用前进行剪枝操作。
     * 减少遍历的范围：在回溯过程中，我们可以限制当前数字的范围，以减少不必要的遍历。
     * 具体来说，我们可以让当前数字的最大值为n - (k - current.size()) + 1，
     * 这样即使将剩余的数字全部添加到当前组合中，也无法满足所需的k个数字。
     * 因此，我们可以在循环中加入条件来限制当前数字的范围。
     * <p>
     * 通过以上优化，我们减少了不必要的遍历和递归调用，从而提高了算法的效率。
     * <p>
     * 经过优化后的算法复杂度如下：
     * <p>
     * 时间复杂度：O(C(n, k))，其中C(n, k)表示从n个元素中选择k个元素的组合数。
     * 回溯算法的时间复杂度取决于生成所有组合的数量，即C(n, k)。
     * 在每个组合中，我们最多进行k次递归调用，因此总的时间复杂度为O(k * C(n, k))。然而，由于C(n, k)的数量级远大于k，
     * 因此可以将时间复杂度近似为O(C(n, k))。
     * 空间复杂度：O(k)，这是因为在回溯过程中，我们最多同时保存k个元素的组合。
     * 通过优化后的算法，我们减少了不必要的遍历和递归调用，从而提高了算法的效率。
     * 这些优化操作在处理大数据集时尤其有用，因为它们可以减少计算时间和内存消耗。
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack(result, current, n, k, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int n, int k, int start) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n - (k - current.size()) + 1; i++) {
            current.add(i);
            backtrack(result, current, n, k, i + 1);
            current.remove(current.size() - 1);
        }
    }


    /**
     * 我们使用回溯算法来生成组合。
     * 我们从1开始遍历每个数字，将其添加到当前组合中，并递归生成下一个数字的组合。
     * 当前组合的大小达到k时，我们将其添加到结果列表中。
     * 然后，我们从当前组合中移除最后一个数字，继续遍历下一个数字。通过这种方式，我们可以生成所有可能的组合。
     * <p>
     * 该算法的时间复杂度为O(n^k)，空间复杂度为O(k)，其中n是范围的最大值。
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack2(result, current, n, k, 1);
        return result;
    }

    private void backtrack2(List<List<Integer>> result, List<Integer> current, int n, int k, int start) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            backtrack(result, current, n, k, i + 1);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combinations solution = new Combinations();

        // Test Case 1
        int n1 = 4;
        int k1 = 2;
        List<List<Integer>> result1 = solution.combine(n1, k1);
        System.out.println("Test Case 1:");
        System.out.println("Input: n = " + n1 + ", k = " + k1);
        System.out.println("Output: " + result1);

        // Test Case 2
        int n2 = 1;
        int k2 = 1;
        List<List<Integer>> result2 = solution.combine(n2, k2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: n = " + n2 + ", k = " + k2);
        System.out.println("Output: " + result2);

        // Additional Test Cases
        int n3 = 5;
        int k3 = 3;
        List<List<Integer>> result3 = solution.combine(n3, k3);
        System.out.println("\nAdditional Test Case 1:");
        System.out.println("Input: n = " + n3 + ", k = " + k3);
        System.out.println("Output: " + result3);

        int n4 = 3;
        int k4 = 2;
        List<List<Integer>> result4 = solution.combine(n4, k4);
        System.out.println("\nAdditional Test Case 2:");
        System.out.println("Input: n = " + n4 + ", k = " + k4);
        System.out.println("Output: " + result4);
    }
}
