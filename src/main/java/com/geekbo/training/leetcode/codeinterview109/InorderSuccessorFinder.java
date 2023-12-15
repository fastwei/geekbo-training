package com.geekbo.training.leetcode.codeinterview109;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 *
 * 面试题 04.06. 后继者
 * 中等
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 *
 * 输入: root = [2,1,3], p = 1
 *
 *   2
 *  / \
 * 1   3
 *
 * 输出: 2
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 *
 * 输出: null
 */
class InorderSuccessorFinder {
    /**
     * 解题思路：
     * <p>
     * 在二叉搜索树中，中序遍历的结果是一个递增的序列。因此，对于给定的节点p，其后继节点（中序后继）是大于p值的最小的节点。
     * 从根节点开始，遍历二叉搜索树，如果p的值小于当前节点的值，则将当前节点设为可能的后继节点，并继续搜索左子树；如果p的值大于等于当前节点的值，则直接搜索右子树。
     * 当搜索到叶子节点时，返回可能的后继节点。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(h)，其中h是二叉搜索树的高度。
     * 空间复杂度：O(1)。除了存储结果的变量，算法的空间复杂度是常数级别的。
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (p.val < root.val) {
                // 当p的值小于当前节点的值时，将当前节点设为可能的后继节点，并继续搜索左子树
                successor = root;
                root = root.left;
            } else {
                // 当p的值大于等于当前节点的值时，直接搜索右子树
                root = root.right;
            }
        }
        return successor;
    }
    public static void main(String[] args) {
        // 创建示例二叉树
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        InorderSuccessorFinder finder = new InorderSuccessorFinder();
        TreeNode result = finder.inorderSuccessor(root, root.left.right);

        System.out.println("后继节点的值为：" + (result != null ? result.val : "null"));
    }
}