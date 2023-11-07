package com.geekbo.training.leetcode.daily;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 99. Recover Binary Search Tree
 * Medium
 * You are given the root of a binary search tree (BST),
 * where the values of exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3.
 * Swapping 2 and 3 makes the BST valid.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [2, 1000].
 * -231 <= Node.val <= 231 - 1
 * <p>
 * <p>
 * Follow up: A solution using O(n) space is pretty straight-forward.
 * Could you devise a constant O(1) space solution?
 */
public class RecoverBinarySearchTree {
    private TreeNode firstElement;
    private TreeNode secondElement;
    private TreeNode prevElement;

    /**
     * 该解决方案使用了中序遍历的方法来遍历二叉搜索树，并在遍历的过程中找到被交换的两个节点。
     * 交换两个节点的值后，二叉搜索树就恢复了原来的正确结构。
     * <p>
     * 解题思路：
     * <p>
     * 使用中序遍历遍历二叉搜索树，得到一个升序的节点序列。
     * 在遍历的过程中，记录下第一个比前一个节点值小的节点（firstElement），以
     * 便于在后续遍历中找到第二个比前一个节点值小的节点（secondElement）。
     * 只要找到第一个交换的节点，我们就可以通过不断更新第二个交换的节点来找到第二个交换的节点（secondElement）。
     * 交换这两个节点的值，就可以恢复二叉搜索树的正确结构。
     * 算法的时间复杂度是 O(n)，其中 n 是二叉搜索树中的节点数。
     * 在最坏情况下，我们需要遍历所有的节点。
     * 空间复杂度是 O(1)，因为我们只使用了常数个额外的指针变量。
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        // 初始化变量
        firstElement = null;
        secondElement = null;
        prevElement = new TreeNode(Integer.MIN_VALUE);

        // 中序遍历二叉搜索树，找到交换的两个节点
        inorderTraversal(root);

        // 交换两个节点的值
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    private void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        // 中序遍历左子树
        inorderTraversal(node.left);

        // 处理当前节点
        if (firstElement == null && node.val < prevElement.val) {
            firstElement = prevElement;
        }
        if (firstElement != null && node.val < prevElement.val) {
            secondElement = node;
        }
        prevElement = node;

        // 中序遍历右子树
        inorderTraversal(node.right);
    }

    public static void main(String[] args) {
        // 构造测试用例
        // 测试用例1: [1,3,null,null,2]
        // 交换节点1和节点3的值，使得二叉搜索树恢复正确
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(2);

        // 测试用例2: [3,1,4,null,null,2]
        // 交换节点2和节点3的值，使得二叉搜索树恢复正确
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(2);

        // 调用恢复二叉搜索树的方法
        RecoverBinarySearchTree solution = new RecoverBinarySearchTree();
        solution.recoverTree(root1);
        solution.recoverTree(root2);

        // 打印恢复后的二叉搜索树
        System.out.print("恢复后的二叉搜索树1: ");
        printInorderTraversal(root1);
        System.out.print("恢复后的二叉搜索树2: ");
        printInorderTraversal(root2);
    }

    private static void printInorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        printInorderTraversal(node.left);
        System.out.print(node.val + " ");
        printInorderTraversal(node.right);
    }
}
