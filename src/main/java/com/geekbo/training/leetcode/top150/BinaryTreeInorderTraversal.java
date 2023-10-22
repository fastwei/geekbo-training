package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * todo:有空加深理解
 */
public class BinaryTreeInorderTraversal {
    // Recursive solution

    /**
     * 解题思路： 这道题可以使用递归和迭代两种方法来实现二叉树的中序遍历。
     * <p>
     * 递归方法：定义一个递归辅助函数 inorderHelper(TreeNode node, List<Integer> result)，用于遍历二叉树的节点。
     * 在递归函数中，首先判断当前节点是否为空，若为空则直接返回。
     * 然后递归调用 inorderHelper 函数来遍历当前节点的左子树，将左子树的遍历结果添加到结果列表中。
     * 接着将当前节点的值添加到结果列表中。最后递归调用 inorderHelper 函数来遍历当前节点的右子树，将右子树的遍历结果添加到结果列表中。
     * <p>
     * <p>
     * 算法复杂度分析：
     * <p>
     * 递归方法的时间复杂度为 O(N)，其中 N 是二叉树中的节点数。需要遍历每个节点一次。
     *
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }

    /**
     *
     * 迭代方法：使用一个栈来辅助实现迭代遍历。
     * 首先将根节点入栈，然后循环进行以下操作：若当前节点不为空，则将当前节点入栈，并将当前节点指向其左子节点。
     * 若当前节点为空，则从栈中弹出一个节点，并将其值添加到结果列表中，然后将当前节点指向弹出节点的右子节点。
     * 直到栈为空且当前节点为空时，遍历结束。
     *
     * @param root
     * @return
     */
    // Iterative solution
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            result.add(node.val);
            node = node.right;
        }

        return result;
    }

    public static void main(String[] args) {
        // Create a binary tree
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        // Recursive solution
        List<Integer> recursiveResult = inorderTraversalRecursive(root);
        System.out.println(recursiveResult); // Output: [1, 3, 2]

        // Iterative solution
        List<Integer> iterativeResult = inorderTraversal(root);
        System.out.println(iterativeResult); // Output: [1, 3, 2]
    }
}