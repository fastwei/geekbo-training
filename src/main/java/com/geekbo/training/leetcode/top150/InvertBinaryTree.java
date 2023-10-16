package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {

    /**
     * 解题思路： 使用深度优先搜索（DFS）来翻转二叉树。
     * 对于每个节点，交换其左右子节点，然后递归地对其左右子树进行翻转。
     * <p>
     * 算法步骤：
     * <p>
     * 如果根节点为空，直接返回null。
     * 否则，交换根节点的左右子节点。
     * 递归地对根节点的左子树和右子树进行翻转。
     * 返回根节点。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历整个二叉树的每个节点，所以时间复杂度为O(n)，其中n为节点的个数。
     * 空间复杂度：递归调用的栈空间取决于二叉树的高度，最坏情况下，当二叉树为一条斜线时，高度为n，所以空间复杂度为O(n)。
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 交换左右子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归地翻转左右子树
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    /**
     * 解题思路： 使用广度优先搜索（BFS）来翻转二叉树。
     * 从根节点开始，逐层遍历二叉树的每个节点，对于每个节点，交换其左右子节点，并将其左右子节点加入队列中。
     * 通过不断交换节点的左右子节点，并遍历整个二叉树，最终实现翻转。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历整个二叉树的每个节点，所以时间复杂度为O(n)，其中n为节点的个数。
     * 空间复杂度：使用了一个队列来存储节点，最坏情况下，队列的大小可以达到二叉树的最大宽度，
     * 所以空间复杂度为O(w)，其中w为二叉树的最大宽度。
     *
     * @param root
     * @return
     */
    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // 交换左右子节点
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // 将左右子节点加入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        InvertBinaryTree invertBinaryTree = new InvertBinaryTree();

        // Test Case 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);
        TreeNode result1 = invertBinaryTree.invertTree(root1);
        System.out.println("Test Case 1:");
        System.out.println("Input: [4, 2, 7, 1, 3, 6, 9]");
        System.out.print("Output: ");
        printTree(result1);

        // Test Case 2
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        TreeNode result2 = invertBinaryTree.invertTree(root2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: [2, 1, 3]");
        System.out.print("Output: ");
        printTree(result2);

        // Test Case 3
        TreeNode root3 = null;
        TreeNode result3 = invertBinaryTree.invertTree(root3);
        System.out.println("\nTest Case 3:");
        System.out.println("Input: []");
        System.out.print("Output: ");
        printTree(result3);
    }

    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.print("[]");
            return;
        }

        System.out.print("[" + root.val + ", ");
        printTree(root.left);
        System.out.print(", ");
        printTree(root.right);
        System.out.print("]");
    }
}