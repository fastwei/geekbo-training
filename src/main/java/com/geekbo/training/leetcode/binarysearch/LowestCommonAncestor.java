package com.geekbo.training.leetcode.binarysearch;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Medium
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2,
 * since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 * <p>
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 */
public class LowestCommonAncestor {

    /**
     * 寻找二叉搜索树的最近公共祖先
     * 解题思路：
     * <p>
     * 根据二叉搜索树的性质，节点的左子树的值都小于根节点的值，节点的右子树的值都大于根节点的值。
     * 从根节点开始遍历，如果根节点的值大于p和q的值，说明p和q都在根节点的左子树中，递归遍历左子树。
     * 如果根节点的值小于p和q的值，说明p和q都在根节点的右子树中，递归遍历右子树。
     * <p>
     * 如果根节点的值既不大于p和q的值，也不小于p和q的值，说明根节点就是最近的公共祖先。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：在最坏情况下，需要遍历整个二叉搜索树，时间复杂度为O(n)，其中n是二叉树的节点数。
     * 空间复杂度：在递归过程中，栈的最大深度是二叉搜索树的高度，最坏情况下为O(n)，其中n是二叉树的节点数。
     *
     * @param root 二叉搜索树的根节点
     * @param p    第一个节点
     * @param q    第二个节点
     * @return 最近公共祖先节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        LowestCommonAncestor solution = new LowestCommonAncestor();

        // 测试用例1
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.left.right.left = new TreeNode(3);
        root1.left.right.right = new TreeNode(5);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);
        TreeNode p1 = new TreeNode(2);
        TreeNode q1 = new TreeNode(8);
        TreeNode result1 = solution.lowestCommonAncestor(root1, p1, q1);
        System.out.println(result1.val); // 输出：6

        // 测试用例2
        TreeNode root2 = new TreeNode(6);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(8);
        root2.left.left = new TreeNode(0);
        root2.left.right = new TreeNode(4);
        root2.left.right.left = new TreeNode(3);
        root2.left.right.right = new TreeNode(5);
        root2.right.left = new TreeNode(7);
        root2.right.right = new TreeNode(9);
        TreeNode p2 = new TreeNode(2);
        TreeNode q2 = new TreeNode(4);
        TreeNode result2 = solution.lowestCommonAncestor(root2, p2, q2);
        System.out.println(result2.val); // 输出：2

        // 测试用例3
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(1);
        TreeNode p3 = new TreeNode(2);
        TreeNode q3 = new TreeNode(1);
        TreeNode result3 = solution.lowestCommonAncestor(root3, p3, q3);
        System.out.println(result3.val); // 输出：2
    }
}
