package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;


/**
 * Construct Binary Tree from Preorder and Inorder Traversal
 * <p>
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 * <p>
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * Example 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    /**
     * 用分治法来构造二叉树。在函数buildTree中，我们使用递归来解决问题。
     * <p>
     * 在每一次递归中，我们首先判断是否需要终止递归。如果preStart大于preEnd或inStart大于inEnd，则返回null。
     * <p>
     * 然后，取出preorder数组中preStart位置的元素作为当前子树的根节点的值。创建一个新的节点作为当前子树的根节点。
     * <p>
     * 接下来，找到当前子树的根节点在inorder数组中的索引rootIndex。根据rootIndex可以将inorder数组分成左子树和右子树。
     * <p>
     * 计算左子树的大小leftSize，它等于rootIndex - inStart。
     * <p>
     * 使用递归分别构建左子树和右子树，左子树的范围是preStart + 1到preStart + leftSize，inStart到rootIndex - 1，
     * 右子树的范围是`preStart + leftSize
     * <p>
     * 时间复杂度分析：
     * <p>
     * 在每个递归调用中，我们需要遍历inorder数组来找到根节点的索引，这需要O(n)的时间复杂度。
     * 在每个递归调用中，我们需要将preorder和inorder数组分成两部分，这需要O(n)的时间复杂度。
     * 递归调用的次数取决于二叉树的高度，最坏情况下，二叉树是一个单链表，高度为n，因此递归调用的次数为O(n)。
     * 因此，总的时间复杂度为O(n^2)。
     * 空间复杂度分析：
     * <p>
     * 在每个递归调用中，我们需要创建一个新的节点作为当前子树的根节点，因此空间复杂度为O(n)。
     * 递归调用的次数取决于二叉树的高度，最坏情况下，二叉树是一个单链表，高度为n，因此递归调用的次数为O(n)。
     * 因此，总的空间复杂度为O(n)。
     * 综上所述，使用分治法实现的构造二叉树的算法的时间复杂度为O(n^2)，空间复杂度为O(n)。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        int rootIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        int leftSize = rootIndex - inStart;

        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIndex - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, rootIndex + 1, inEnd);

        return root;
    }

    public static void main(String[] args) {
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        TreeNode root1 = buildTree(preorder1, inorder1);
        // Output: [3,9,20,null,null,15,7]
        printTree(root1);

        int[] preorder2 = {-1};
        int[] inorder2 = {-1};
        TreeNode root2 = buildTree(preorder2, inorder2);
        // Output: [-1]
        printTree(root2);
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }

        System.out.print(root.val + ",");
        printTree(root.left);
        printTree(root.right);
    }
}