package com.geekbo.training.leetcode.daily;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 二叉搜索树转化为累加树：
 * 给定一个二叉搜索树（BST），将其转化为累加树，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 解题思路：
 * 1. 二叉搜索树的中序遍历是递增的，所以我们可以反向中序遍历二叉搜索树，得到递减的序列。
 * 2. 在反向中序遍历的过程中，维护一个累加和，每次将当前节点的值更新为累加和，并将累加和加上当前节点的值。
 *
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中 n 是二叉搜索树的节点数。每个节点只遍历一次。
 * - 空间复杂度：O(n)，使用了一个栈来存储节点。
 */

public class BSTToGreaterSumTree {
    private int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }

        bstToGst(root.right);
        sum += root.val;
        root.val = sum;
        bstToGst(root.left);

        return root;
    }

    public static void main(String[] args) {
        // 构造测试用例
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(8);

        BSTToGreaterSumTree solution = new BSTToGreaterSumTree();
        TreeNode result = solution.bstToGst(root);

        // 验证结果
        assert result.val == 30;
        assert result.left.val == 36;
        assert result.right.val == 21;
        assert result.left.left.val == 36;
        assert result.left.right.val == 35;
        assert result.right.left.val == 26;
        assert result.right.right.val == 15;
        assert result.left.right.right.val == 33;
        assert result.right.right.right.val == 8;
    }
}