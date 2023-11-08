package com.geekbo.training.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. Binary Tree Paths
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 * <p>
 * A leaf is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5","1->3"]
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: ["1"]
 */
class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root == null) {
            return paths;
        }
        dfs(root, "", paths);
        return paths;
    }

    private void dfs(TreeNode node, String path, List<String> paths) {
        if (node.left == null && node.right == null) {
            // Leaf node, add the path to the list
            paths.add(path + node.val);
        }
        if (node.left != null) {
            // Traverse left child
            dfs(node.left, path + node.val + "->", paths);
        }
        if (node.right != null) {
            // Traverse right child
            dfs(node.right, path + node.val + "->", paths);
        }
    }
}