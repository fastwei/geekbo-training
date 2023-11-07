package com.geekbo.training.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. Path Sum II
 * Medium
 * Given the root of a binary tree and an integer targetSum,
 * return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
 * Each path should be returned as a list of the node values, not node references.
 * <p>
 * A root-to-leaf path is a path starting from the root and ending at any leaf node.
 * A leaf is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * Example 3:
 * <p>
 * Input: root = [1,2], targetSum = 0
 * Output: []
 */
public class PathSumII {
    /**
     * 解题思路：
     * <p>
     * 使用深度优先搜索（DFS）遍历二叉树的所有路径。
     * 在遍历过程中，记录当前路径的节点值之和，并与目标和进行比较。
     * 如果当前节点是叶子节点且路径节点值之和等于目标和，则将当前路径添加到结果列表中。
     * 最后，返回结果列表。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N)，其中N是二叉树中的节点数。需要遍历所有的节点。
     * 空间复杂度：O(N)，最坏情况下，路径列表的长度为N/2，即最长路径的节点数为N/2。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, targetSum, path, result);
        return result;
    }

    private static void dfs(TreeNode node, int target, List<Integer> path, List<List<Integer>> result) {
        if (node == null) {
            return;
        }

        path.add(node.val);
        target -= node.val;

        if (node.left == null && node.right == null && target == 0) {
            result.add(new ArrayList<>(path));
        }

        dfs(node.left, target, path, result);
        dfs(node.right, target, path, result);

        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.right.left = new TreeNode(5);
        root1.right.right.right = new TreeNode(1);
        int targetSum1 = 22;
        List<List<Integer>> result1 = pathSum(root1, targetSum1);
        System.out.println("Test case 1: " + result1);

        // Test case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        int targetSum2 = 5;
        List<List<Integer>> result2 = pathSum(root2, targetSum2);
        System.out.println("Test case 2: " + result2);

        // Test case 3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        int targetSum3 = 0;
        List<List<Integer>> result3 = pathSum(root3, targetSum3);
        System.out.println("Test case 3: " + result3);
    }
}
