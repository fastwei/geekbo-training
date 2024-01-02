package com.geekbo.training.leetcode.classic119;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 814. 二叉树剪枝
 * 中等
 * <p>
 * 题目描述：
 * 给定一个二叉树的根节点 root，返回该二叉树的剪枝结果。
 * 二叉树剪枝的定义如下：移除所有不包含 1 的子树（子树指该节点加上它的所有后代）。
 * <p>
 * 示例 1:
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 * <p>
 * 示例 2:
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 * <p>
 * 示例 3:
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 * <p>
 * 约束条件：
 * - 树中节点数在范围 [1, 200] 内。
 * - 节点的值为 0 或 1。
 * <p>
 */
public class BinaryTreePruning {

    /**
     *
     * 解题思路：
     * 使用递归的方法遍历二叉树的每个节点，如果当前节点的左右子树都不包含 1，则将该节点剪掉。
     * 递归结束条件是遍历到空节点。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(n)，其中 n 是二叉树的节点数。需要遍历所有的节点。
     * 空间复杂度：O(h)，其中 h 是二叉树的高度。递归调用的栈空间取决于二叉树的高度。
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        return root;
    }

    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(0);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(1);
        TreeNode result1 = new BinaryTreePruning().pruneTree(root1);
        System.out.println("Test case 1: " + result1);

        // Test case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(0);
        root2.left.right = new TreeNode(0);
        root2.right.left = new TreeNode(0);
        root2.right.right = new TreeNode(1);
        TreeNode result2 = new BinaryTreePruning().pruneTree(root2);
        System.out.println("Test case 2: " + result2);
    }
}