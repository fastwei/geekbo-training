package com.geekbo.training.datastructure;


import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Java实现二叉树的前序遍历（preorder）、中序遍历（inorder）和后序遍历（postorder)
 */
public class TreeTraversal {
    // 前序遍历
    public void preorder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " "); // 先访问根节点
        preorder(root.left); // 递归遍历左子树
        preorder(root.right); // 递归遍历右子树
    }

    // 中序遍历
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left); // 递归遍历左子树
        System.out.print(root.val + " "); // 访问根节点
        inorder(root.right); // 递归遍历右子树
    }

    // 后序遍历
    public void postorder(TreeNode root) {
        if (root == null) {
            return;
        }

        postorder(root.left); // 递归遍历左子树
        postorder(root.right); // 递归遍历右子树
        System.out.print(root.val + " "); // 访问根节点
    }

    public void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }



}