package com.geekbo.training.leetcode.top75;

// 定义二叉树节点
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 *
 * 104. Maximum Depth of Binary Tree
 *
 * 解题思路：
 *
 * 递归计算二叉树的最大深度。
 * 如果根节点为空，深度为0，否则，递归计算左子树和右子树的深度，取较大值加1作为根节点的深度。
 * 递归终止条件是根节点为空。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(N)，其中N是二叉树的节点数量，因为我们需要访问每个节点一次。
 * 空间复杂度：O(H)，其中H是二叉树的高度。在最坏情况下，二叉树为一条直线，H等于N；在最好情况下，二叉树为平衡树，H等于log(N)。
 *
 */
public class MaxDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // 如果根节点为空，深度为0
        } else {
            int leftDepth = maxDepth(root.left); // 计算左子树深度
            int rightDepth = maxDepth(root.right); // 计算右子树深度
            return Math.max(leftDepth, rightDepth) + 1; // 返回左右子树深度的较大值加1
        }
    }

    public static void main(String[] args) {
        MaxDepthOfBinaryTree solution = new MaxDepthOfBinaryTree();

        // 构建示例1的二叉树
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        int result1 = solution.maxDepth(root1);
        System.out.println("Test Case 1: " + result1); // 预期输出：3

        // 构建示例2的二叉树
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);

        int result2 = solution.maxDepth(root2);
        System.out.println("Test Case 2: " + result2); // 预期输出：2
    }
}
