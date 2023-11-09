package com.geekbo.training.leetcode.binarytree;

/**
 *
 * 687. Longest Univalue Path
 * Medium
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.
 *
 * The length of the path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,5,1,1,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 5).
 * Example 2:
 *
 *
 * Input: root = [1,4,5,4,4,null,5]
 * Output: 2
 * Explanation: The shown image shows that the longest path of the same value (i.e. 4).
 *
 */
class LongestUnivaluePath {
    private int longest;

    public int longestUnivaluePath(TreeNode root) {
        longest = 0;
        dfs(root);
        return longest;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftLength = dfs(node.left);
        int rightLength = dfs(node.right);

        int leftPath = (node.left != null && node.left.val == node.val) ? leftLength + 1 : 0;
        int rightPath = (node.right != null && node.right.val == node.val) ? rightLength + 1 : 0;

        longest = Math.max(longest, leftPath + rightPath);

        return Math.max(leftPath, rightPath);
    }
}