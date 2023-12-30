package com.geekbo.training.leetcode.classic119;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 513. Find Bottom Left Tree Value
 * Medium
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 *
 * 题目：513. 找树左下角的值
 * 难度：中等
 * <p>
 * 题目描述：
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * <p>
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入:
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 * 输出: 7
 * <p>
 */
public class FindBottomLeftTreeValue {
    /**
     *
     * 解题思路：
     * 使用层次遍历的方法遍历二叉树，每次遍历完一层后，将该层的最左边的节点的值记录下来，最后返回记录的值即可。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(n)，其中 n 是二叉树的节点数。需要遍历所有的节点。
     * 空间复杂度：O(m)，其中 m 是二叉树的最大宽度。在最坏的情况下，队列 queue 同时存储二叉树最底层的所有节点。
     * 这种情况下，队列的大小为 m/2，即 O(m)。
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        int leftmostValue = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    leftmostValue = node.val;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return leftmostValue;
    }

    public static void main(String[] args) {
        // Test case 1
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        int result1 = new FindBottomLeftTreeValue().findBottomLeftValue(root1);
        System.out.println("Test case 1: " + result1);

        // Test case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.left = new TreeNode(5);
        root2.right.right = new TreeNode(6);
        root2.right.left.left = new TreeNode(7);
        int result2 = new FindBottomLeftTreeValue().findBottomLeftValue(root2);
        System.out.println("Test case 2: " + result2);
    }
}