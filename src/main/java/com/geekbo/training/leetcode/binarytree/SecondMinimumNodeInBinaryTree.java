package com.geekbo.training.leetcode.binarytree;

class SecondMinimumNodeInBinaryTree {


    int min;
    long res = Long.MAX_VALUE;

    public void dfs(TreeNode root) {
        if (root != null) {

            if (min < root.val && root.val < res) {
                res = root.val;
            } else if (min == root.val) {
                dfs(root.left);
                dfs(root.right);
            }

        }
    }

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        min = root.val;
        dfs(root);
        return res < Long.MAX_VALUE ? (int) res : -1;
    }

    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2147483647);
        int result1 = new SecondMinimumNodeInBinaryTree().findSecondMinimumValue(root1);
        System.out.println("Test case 1: " + result1);
    }

}
