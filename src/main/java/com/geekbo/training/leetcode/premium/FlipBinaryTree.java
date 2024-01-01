package com.geekbo.training.leetcode.premium;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 156. 上下翻转二叉树
 * 中等
 * 给你一个二叉树的根节点 root ，请你将此二叉树上下翻转，并返回新的根节点。
 * <p>
 * 你可以按下面的步骤翻转一棵二叉树：
 * <p>
 * 原来的左子节点变成新的根节点
 * 原来的根节点变成新的右子节点
 * 原来的右子节点变成新的左子节点
 * <p>
 * 上面的步骤逐层进行。题目数据保证每个右节点都有一个同级节点（即共享同一父节点的左节点）且不存在子节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5]
 * 输出：[4,5,2,null,null,3,1]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [0, 10] 内
 * 1 <= Node.val <= 10
 * 树中的每个右节点都有一个同级节点（即共享同一父节点的左节点）
 * 树中的每个右节点都没有子节点
 * <p>
 */

public class FlipBinaryTree {

    /**
     * 翻转二叉树
     * 思路：递归地交换二叉树节点的左右子节点
     * 算法复杂度：时间复杂度O(n)，其中n是二叉树节点的个数。每个节点都需要被访问和交换一次。
     * 空间复杂度O(n)，空间复杂度取决于递归调用的栈空间，最坏情况下，二叉树退化成链表，递归调用栈的深度为n。
     */
    public static TreeNode flipBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode left = root.left, right = root.right;
        TreeNode ret = flipBinaryTree(left);
        left.left = right;
        left.right = root;
        root.left = root.right = null;
        return ret;
    }

    public static void main(String[] args) {
        // 构造测试用例
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        // 翻转二叉树
        TreeNode newRoot = flipBinaryTree(root);

        // 打印结果
        printTree(newRoot);
    }

    // 打印二叉树
    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}