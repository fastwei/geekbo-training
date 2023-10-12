package com.geekbo.training.leetcode.top75;

/**
 * 1372. Longest ZigZag Path in a Binary Tree
 * Attempted
 * Medium
 * Topics
 * Companies
 * Hint
 * You are given the root of a binary tree.
 * <p>
 * A ZigZag path for a binary tree is defined as follow:
 * <p>
 * Choose any node in the binary tree and a direction (right or left).
 * If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
 * Change the direction from right to left or from left to right.
 * Repeat the second and third steps until you can't move in the tree.
 * Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).
 * <p>
 * Return the longest ZigZag path contained in that tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
 * Output: 3
 * Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,1,1,null,1,null,null,1,1,null,1]
 * Output: 4
 * Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: 0
 * <p>
 * 解题思路:
 * <p>
 * 这个问题要求找到二叉树中的最长ZigZag路径，其中ZigZag路径是从一个节点开始，
 * 可以选择向左或向右子树，然后交替改变方向，直到不能继续移动为止。路径的长度是指经过的节点数。
 * 我们可以使用深度优先搜索（DFS）来解决这个问题。
 * 对于每个节点，我们可以分别考虑从该节点开始向左和向右的ZigZag路径，同时维护一个变量来记录最长路径的长度。
 * 在DFS遍历中，我们需要记录当前路径的长度以及当前方向（左或右）。
 * 如果当前节点没有左子树或右子树，那么我们更新最长路径的长度。否则，我们继续向下递归，并根据方向更新路径长度。
 * 算法复杂度:
 * <p>
 * 时间复杂度: 由于我们使用DFS遍历整个二叉树，所以时间复杂度为O(N)，其中N是二叉树中的节点数。
 * 空间复杂度: 递归的空间复杂度取决于树的高度。在最坏情况下，树是不平衡的，空间复杂度为O(N)。
 * 如果树是平衡的，空间复杂度为O(logN)。
 *
 *
 */
public class LongestZigZagPathInBinaryTree {
    int max = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root, true, 0);
        dfs(root, false, 0);
        return max;
    }

    public void dfs(TreeNode root, boolean isLeft, int length) {
        if (root == null) {
            return;
        }

        max = Math.max(max, length);

        if (isLeft) {
            dfs(root.left, true, 1);
            dfs(root.right, false, length + 1);
        } else {
            dfs(root.left, true, length + 1);
            dfs(root.right, false, 1);
        }
    }

    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(1);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(1);
        root1.right.left.left = new TreeNode(1);
        int result1 = new LongestZigZagPathInBinaryTree().longestZigZag(root1);
        System.out.println("Test case 1: " + result1); // Expected output: 3

        // Test case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(1);
        root2.right.right = new TreeNode(1);
        root2.left.left.left = new TreeNode(1);
        int result2 = new LongestZigZagPathInBinaryTree().longestZigZag(root2);
        System.out.println("Test case 2: " + result2); // Expected output: 4

        // Test case 3
        TreeNode root3 = new TreeNode(1);
        int result3 = new LongestZigZagPathInBinaryTree().longestZigZag(root3);
        System.out.println("Test case 3: " + result3); // Expected output: 0
    }
}
