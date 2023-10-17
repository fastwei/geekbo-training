package com.geekbo.training.leetcode.top150;


import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 106.Construct Binary Tree from Inorder and Postorder Traversal
 * <p>
 * <p>
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,
 * construct and return the binary tree.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 * <p>
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    /**
     * 我们知道，对于二叉树的中序遍历和后序遍历，可以通过一些规律来构建出原始的二叉树。
     * <p>
     * 首先，我们观察到中序遍历的性质：对于任意一棵二叉树，其中序遍历的结果中，根节点的左边是左子树的节点，
     * 根节点的右边是右子树的节点。
     * <p>
     * 然后，我们观察到后序遍历的性质：对于任意一棵二叉树，后序遍历的结果中，根节点总是在最后一个位置。
     * <p>
     * 基于以上观察，我们可以使用递归来构建二叉树。具体实现思路如下：
     * <p>
     * 在后序遍历结果中找到根节点的值，即后序遍历的最后一个元素。
     * 在中序遍历结果中找到根节点的位置，即中序遍历的根节点值的索引。
     * 根据根节点在中序遍历结果中的位置，将中序遍历结果分为左子树部分和右子树部分。
     * 根据左子树部分的长度，将后序遍历结果分为左子树部分和右子树部分。
     * 递归地构建左子树和右子树，将它们分别作为当前根节点的左子节点和右子节点。
     * 返回当前根节点。
     * 通过递归的方式，我们可以一层一层地构建二叉树。最终得到的根节点就是所求的二叉树的根节点。
     * <p>
     * 在实现过程中，我们需要传入中序遍历数组、中序遍历的起始和结束索引，后序遍历数组、后序遍历的起始和结束索引这几个参数。
     * 递归的终止条件是当起始索引大于结束索引时，返回null。
     * <p>
     * 这种递归的实现思路能够有效地构建出二叉树，并且时间复杂度为O(n^2)，其中n是二叉树的节点数量。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：在每个递归调用中，我们需要遍历inorder数组来找到根节点的索引，这需要O(n)的时间复杂度。
     * 递归调用的次数取决于二叉树的高度，最坏情况下，二叉树是一个单链表，高度为n，因此递归调用的次数为O(n)。
     * 因此，总的时间复杂度为O(n^2)。
     * 空间复杂度：每个递归调用中，我们需要创建一个新的节点作为当前子树的根节点，因此空间复杂度为O(n)。
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private static TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        // 递归终止条件
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // 当前子树的根节点的值为 postorder[postEnd]
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        // 在 inorder 数组中找到根节点的索引 rootIndex
        int rootIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        // 计算左子树的大小
        int leftSize = rootIndex - inStart;

        // 递归构建左子树和右子树
        root.left = build(inorder, inStart, rootIndex - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = build(inorder, rootIndex + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);

        return root;
    }

    public static void main(String[] args) {
        int[] inorder1 = {9, 3, 15, 20, 7};
        int[] postorder1 = {9, 15, 7, 20, 3};
        TreeNode root1 = buildTree(inorder1, postorder1);
        // Output: [3,9,20,null,null,15,7]
        printTree(root1);

        int[] inorder2 = {-1};
        int[] postorder2 = {-1};
        TreeNode root2 = buildTree(inorder2, postorder2);
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
