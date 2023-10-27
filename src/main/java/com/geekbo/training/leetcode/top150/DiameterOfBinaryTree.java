package com.geekbo.training.leetcode.top150;

//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//
//    TreeNode(int val) {
//        this.val = val;
//    }
//}

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * <p>
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * <p>
 * The length of a path between two nodes is represented by the number of edges between them.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 * <p>
 * Input: root = [1,2]
 * Output: 1
 */
public class DiameterOfBinaryTree {
    int maxDiameter;

    /***
     *
     * 解题思路： 这道题是二叉树的经典问题，可以使用递归来解决。对于每个节点，
     *         我们计算其左子树的深度和右子树的深度，并更新当前的最大直径。递归的结束条件是节点为空，此时深度为0。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(n)，其中n是二叉树中的节点数。每个节点都会被访问一次。
     * 空间复杂度：O(n)，递归调用栈的深度最大为树的高度。
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        depth(root);
        return maxDiameter;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree diameterOfBinaryTree = new DiameterOfBinaryTree();

        // Test Case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        int result1 = diameterOfBinaryTree.diameterOfBinaryTree(root1);
        System.out.println(result1);  // Expected output: 3

        // Test Case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        int result2 = diameterOfBinaryTree.diameterOfBinaryTree(root2);
        System.out.println(result2);  // Expected output: 1
    }
}
