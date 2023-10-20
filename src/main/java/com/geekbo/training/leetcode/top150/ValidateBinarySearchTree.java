package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * A valid BST is defined as follows:
 * <p>
 * The left
 * subtree
 * of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        // 测试用例1
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println(isValidBST(root1)); // 预期输出：true

        // 测试用例2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println(isValidBST(root2)); // 预期输出：false
    }

    /**
     * 判断二叉树是否是合法的二叉搜索树
     * 解题思路： 要判断一个二叉树是否是合法的二叉搜索树，需要满足以下条件：
     * <p>
     * 左子树中的所有节点的值都小于根节点的值；
     * 右子树中的所有节点的值都大于根节点的值；
     * 左子树和右子树也必须是合法的二叉搜索树。
     * 可以使用递归的方式判断每个节点是否满足以上条件。在递归过程中，需要传入每个节点的上界和下界，确保节点的值在合法范围内。
     * <p>
     * 算法复杂度分析： 该算法遍历了二叉树的每个节点，时间复杂度为O(n)
     *
     * @param root 二叉树的根节点
     * @return 如果是合法的二叉搜索树则返回true，否则返回false
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /**
     * 辅助方法，判断指定子树是否是合法的二叉搜索树
     *
     * @param root  子树的根节点
     * @param lower 子树中节点的下界，即节点值必须大于lower
     * @param upper 子树中节点的上界，即节点值必须小于upper
     * @return 如果是合法的二叉搜索树则返回true，否则返回false
     */
    public static boolean isValidBST(TreeNode root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }

        int val = root.val;

        // 判断当前节点是否满足二叉搜索树的定义
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }

        // 递归判断左右子树是否是合法的二叉搜索树
        if (!isValidBST(root.left, lower, val)) {
            return false;
        }
        if (!isValidBST(root.right, val, upper)) {
            return false;
        }

        return true;
    }
}
