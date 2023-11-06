package com.geekbo.training.leetcode.dp;

/**
 * 96. Unique Binary Search Trees
 * Given an integer n, return the number of structurally unique BST's (binary search trees)
 * which has exactly n nodes of unique values from 1 to n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: 5
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 19
 */
public class UniqueBinarySearchTrees {
    /**
     * 计算具有 n 个节点的唯一值的结构化二叉搜索树的数量
     * 解题思路：
     * <p>
     * 首先，我们使用一个数组 dp，其中 dp[i] 表示具有 i 个节点的唯一值的结构化二叉搜索树的数量。
     * 然后，我们遍历数组 dp，对于每个 dp[i]，我们计算具有 i 个节点的结构化二叉搜索树的数量。
     * 具体地，我们假设根节点的值为 j，其中 1 <= j <= i，那么左子树的节点数为 j - 1，右子树的节点数为 i - j，
     * 我们可以通过 dp[j - 1] * dp[i - j] 来计算以 j 为根节点的结构化二叉搜索树的数量，
     * 然后将所有可能的 j 的值相加，即为具有 i 个节点的唯一值的结构化二叉搜索树的数量。
     * 最后，我们返回 dp[n]，即为具有 n 个节点的唯一值的结构化二叉搜索树的数量。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 初始化时间复杂度为 O(n)，其中 n 是节点数。
     * 遍历数组的时间复杂度为 O(n^2)。
     * 因此，总的时间复杂度为 O(n^2)。
     *
     * @param n 节点数
     * @return 结构化二叉搜索树的数量
     */
    public int numTrees(int n) {
        // 创建一个数组，用于存储具有 i 个节点的唯一值的结构化二叉搜索树的数量
        int[] dp = new int[n + 1];

        // 初始化 dp[0] 和 dp[1] 的值为 1
        dp[0] = 1;
        dp[1] = 1;

        // 遍历数组，计算具有 i 个节点的唯一值的结构化二叉搜索树的数量
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // 创建解法对象
        UniqueBinarySearchTrees uniqueBinarySearchTrees = new UniqueBinarySearchTrees();

        // 测试用例1
        // 预期输出: 5
        System.out.println(uniqueBinarySearchTrees.numTrees(3));

        // 测试用例2
        // 预期输出: 1
        System.out.println(uniqueBinarySearchTrees.numTrees(1));
    }
}
