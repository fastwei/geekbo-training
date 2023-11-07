package com.geekbo.training.leetcode.binarytree;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. Binary Tree Level Order Traversal II
 * Medium
 * Given the root of a binary tree,
 * return the bottom-up level order traversal of its nodes' values.
 * (i.e., from left to right, level by level from leaf to root).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[15,7],[9,20],[3]]
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 */
public class BinaryTreeLevelOrderTraversalII {
    /**
     * 解题思路：
     * <p>
     * 我们可以使用广度优先搜索（BFS）来遍历二叉树的每一层节点。
     * 首先，我们创建一个队列，将根节点入队。
     * 然后，我们进入一个循环，直到队列为空。
     * 在每一轮循环中，我们首先记录当前队列的大小，表示当前层的节点数量。
     * 然后，我们遍历当前层的节点，并将它们的值添加到一个临时列表中。
     * 同时，如果当前节点有左右孩子节点，我们将它们入队。
     * 最后，将临时列表添加到结果列表的开头，这样就实现了自底向上的层次遍历。
     * 算法的时间复杂度是 O(n)，其中 n 是二叉树中的节点数。我们需要遍历每个节点一次。
     * 空间复杂度是 O(n)，其中 n 是二叉树中的节点数。在最坏情况下，队列的大小达到 O(n)。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(0, level);
        }

        return result;
    }

    public static void main(String[] args) {
        // 构造测试用例
        // 测试用例1: [3,9,20,null,null,15,7]
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        // 测试用例2: [1]
        TreeNode root2 = new TreeNode(1);

        // 测试用例3: []
        TreeNode root3 = null;

        // 调用自底向上层次遍历的方法
        BinaryTreeLevelOrderTraversalII solution = new BinaryTreeLevelOrderTraversalII();
        List<List<Integer>> result1 = solution.levelOrderBottom(root1);
        List<List<Integer>> result2 = solution.levelOrderBottom(root2);
        List<List<Integer>> result3 = solution.levelOrderBottom(root3);

        // 打印结果
        System.out.println("测试用例1: " + result1);
        System.out.println("测试用例2: " + result2);
        System.out.println("测试用例3: " + result3);
    }
}
