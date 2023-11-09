package com.geekbo.training.leetcode.binarysearch;


import com.geekbo.training.leetcode.base.TreeNode;

class KthSmallestElementInBST {
    private int count;
    private int result;

    public int kthSmallest(TreeNode root, int k) {
        count = 0;
        result = 0;
        inorder(root, k);
        return result;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        
        inorder(node.left, k);
        
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        
        inorder(node.right, k);
    }
}