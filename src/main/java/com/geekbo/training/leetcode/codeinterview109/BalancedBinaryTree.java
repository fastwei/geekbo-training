package com.geekbo.training.leetcode.codeinterview109;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 *
 * 面试题 04.04. 检查平衡性
 * 简单
 * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 *
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *       1
 *      / \
 *     2   2
 *    / \
 *   3   3
 *  / \
 * 4   4
 * 返回 false 。
 *
 */
class BalancedBinaryTree {
    /**
     * 判断二叉树是否平衡
     * 解题思路：使用递归的方式判断二叉树是否平衡。
     * 递归地判断每个节点的左右子树的高度差是否超过1，并且递归地判断每个节点的左右子树是否平衡。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n^2)，其中 n 是二叉树的节点个数。
     * 因为在判断每个节点的平衡性时，需要计算其左右子树的高度，而计算高度的时间复杂度是 O(n)。
     *
     * @param root 二叉树的根节点
     * @return 如果二叉树平衡则返回true，否则返回false
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        // 计算左右子树的高度差
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // 判断当前节点的左右子树高度差是否超过1
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        // 递归判断左右子树是否平衡
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 计算二叉树的高度
     *
     * @param node 二叉树的节点
     * @return 二叉树的高度
     */
    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
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

        // 创建一个 BalancedBinaryTree 实例
        BalancedBinaryTree solution = new BalancedBinaryTree();

        // 计算每个测试用例的二叉树是否平衡
        boolean result1 = solution.isBalanced(root1);
        boolean result2 = solution.isBalanced(root2);

        // 打印结果
        System.out.println("测试用例1: " + result1);
        System.out.println("测试用例2: " + result2);
    }
}
