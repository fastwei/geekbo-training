package com.geekbo.training.leetcode.crackinterview109;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 面试题 04.08. 首个共同祖先
 * 难度：中等
 *
 * 题目描述：
 * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。
 * 注意：这不一定是二叉搜索树。
 *
 * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
 *     3
 *    / \
 *   5   1
 *  / \ / \
 * 6  2 0  8
 *   / \
 *  7   4
 *
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * 解题思路：
 * 1. 递归法。
 *    - 从根节点开始遍历二叉树，如果当前节点是 p 或者 q，那么返回当前节点。
 *    - 递归遍历左子树，如果左子树中存在 p 或者 q，将返回值保存到 left 变量中。
 *    - 递归遍历右子树，如果右子树中存在 p 或者 q，将返回值保存到 right 变量中。
 *    - 如果 left 和 right 都不为空，说明 p 和 q 分别位于左子树和右子树，那么当前节点就是最近公共祖先。
 *    - 如果 left 和 right 中只有一个不为空，说明 p 和 q 都位于该子树中，那么返回不为空的那个节点。
 *    - 如果 left 和 right 都为空，说明 p 和 q 都不在该子树中，返回 null。
 *    - 时间复杂度为 O(N)，其中 N 是二叉树的节点数。
 *
 * 算法复杂度分析：
 * - 时间复杂度：O(N)，其中 N 是二叉树的节点数。最差情况下，我们需要访问二叉树的所有节点。
 * - 空间复杂度：O(N)，空间复杂度取决于递归调用的层数，递归调用的层数不会超过 N。
 */
class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }
    public static void main(String[] args) {
        // 创建二叉树 [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);

        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node2.left = node7;
        node2.right = node4;
        node1.left = node0;
        node1.right = node8;

        LowestCommonAncestor solution = new LowestCommonAncestor();
        TreeNode result1 = solution.lowestCommonAncestor(node3, node5, node1);
        TreeNode result2 = solution.lowestCommonAncestor(node3, node5, node4);

        System.out.println("Expected: 3");
        System.out.println("Output: " + result1.val);
        System.out.println("Expected: 5");
        System.out.println("Output: " + result2.val);
    }
}