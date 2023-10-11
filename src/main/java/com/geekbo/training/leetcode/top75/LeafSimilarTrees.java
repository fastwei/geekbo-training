package com.geekbo.training.leetcode.top75;


import java.util.ArrayList;
import java.util.List;

/**
 * 872. Leaf-Similar Trees
 *
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 *
 *
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 * Example 2:
 *
 *
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 *
 * 解题思路：
 *
 * 使用深度优先搜索（DFS）遍历两颗树，分别获取它们的叶子值序列。
 * 比较两颗树的叶子值序列是否相同。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(N1 + N2)，其中N1和N2分别是两颗二叉树的节点数量。我们需要遍历两颗树以获取叶子值序列。
 * 空间复杂度：O(H1 + H2)，其中H1和H2分别是两颗二叉树的高度。空间复杂度取决于DFS递归调用的最大深度。
 *
 */
public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // 使用深度优先搜索（DFS）遍历两颗树，获取叶子值序列
        List<Integer> leafValues1 = new ArrayList<>();
        List<Integer> leafValues2 = new ArrayList();
        dfs(root1, leafValues1);
        dfs(root2, leafValues2);

        // 比较两颗树的叶子值序列是否相同
        return leafValues1.equals(leafValues2);
    }

    // DFS遍历获取叶子值序列
    private void dfs(TreeNode root, List<Integer> leafValues) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leafValues.add(root.val);
        }
        dfs(root.left, leafValues);
        dfs(root.right, leafValues);
    }

    public static void main(String[] args) {
        LeafSimilarTrees solution = new LeafSimilarTrees();

        // 构建示例1的两颗二叉树
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);
        root1.right = new TreeNode(1);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(8);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(5);
        root2.left.left = new TreeNode(6);
        root2.left.right = new TreeNode(7);
        root2.left.right.left = new TreeNode(4);
        root2.left.right.right = new TreeNode(2);
        root2.right = new TreeNode(1);
        root2.right.right = new TreeNode(8);
        root2.right.right.left = new TreeNode(9);

        boolean result1 = solution.leafSimilar(root1, root2);
        System.out.println("Test Case 1: " + result1); // 预期输出：true

        // 构建示例2的两颗二叉树
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);

        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(3);
        root4.right = new TreeNode(2);

        boolean result2 = solution.leafSimilar(root3, root4);
        System.out.println("Test Case 2: " + result2); // 预期输出：false
    }
}
