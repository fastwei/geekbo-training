package com.geekbo.training.leetcode.binarytree;

/**
 * 110. Balanced Binary Tree
 * Easy
 * Given a binary tree, determine if it is
 * height-balanced
 * .
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,2,3,3,null,null,4,4]
 * Output: false
 * Example 3:
 * <p>
 * Input: root = []
 * Output: true
 */
public class BalancedBinaryTree {
    /**
     * 解题思路：
     * <p>
     * 一个二叉树是平衡的，当且仅当它的左右子树都是平衡的，并且左右子树的高度差不超过 1。
     * 我们可以使用递归的方式来判断二叉树是否平衡。
     * 首先，判断当前节点的左子树是否平衡，然后判断右子树是否平衡，最后判断左右子树的高度差是否不超过 1。
     * 递归地判断每个节点的左右子树是否平衡，直到遍历完整个二叉树。
     * 算法的时间复杂度是 O(nlogn)，其中 n 是二叉树中的节点数。
     * 每次递归都需要遍历二叉树的一部分，递归的次数是 O(logn)，每次递归需要 O(n)的时间复杂度，
     * 所以总的时间复杂度是 O(nlogn)。空间复杂度是 O(logn)，递归调用的栈空间。
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public static void main(String[] args) {
        // 构造测试用例
        // 测试用例1: [3,9,20,null,null,15,7]
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        // 测试用例2: [1,2,2,3,3,null,null,4,4]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        root2.left.left.right = new TreeNode(4);

        // 测试用例3: []
        TreeNode root3 = null;

        // 调用判断二叉树是否平衡的方法
        BalancedBinaryTree solution = new BalancedBinaryTree();
        boolean result1 = solution.isBalanced(root1);
        boolean result2 = solution.isBalanced(root2);
        boolean result3 = solution.isBalanced(root3);

        // 打印结果
        System.out.println("测试用例1: " + result1);
        System.out.println("测试用例2: " + result2);
        System.out.println("测试用例3: " + result3);
    }
}
