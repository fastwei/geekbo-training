package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 114.Flatten Binary Tree to Linked List
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * <p>
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * Example 3:
 * <p>
 * Input: root = [0]
 * Output: [0]
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 */
public class FlattenBinaryTreeToLinkedList {

    /**
     * 解题思路： 这个问题可以通过迭代的方式来解决。
     * 我们从根节点开始遍历二叉树，对于每个节点，
     * 如果它有左子节点，我们将其右子节点接到其左子节点的最右边节点上，并将其左子节点置为null。
     * 然后，我们将当前节点的右子节点作为下一个要遍历的节点，继续迭代直到遍历完整个二叉树。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：我们需要遍历二叉树的所有节点，因此时间复杂度为O(n)，其中n是二叉树的节点数量。
     * 空间复杂度：我们只使用了常数个额外的节点，因此空间复杂度为O(1)。
     * flatten方法将二叉树展开成链表。
     * 通过迭代的方式遍历二叉树的每个节点，按照题目要求更新节点的左右子节点，使左子节点为null，右子节点为下一个要遍历的节点。
     * 最后得到的链表即为展开后的结果。
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode curr = root;

        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;

                while (prev.right != null) {
                    prev = prev.right;
                }

                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }

            curr = curr.right;
        }
    }

    public static void main(String[] args) {
        // 构建测试用例
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        // 调用方法
        flatten(root);

        // 打印结果
        printList(root);
    }

    private static void printList(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }

        System.out.print(root.val + " -> ");
        printList(root.right);
    }
}


