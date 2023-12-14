package com.geekbo.training.leetcode.codinginterview109;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 面试题 04.03. 特定深度节点链表
 * 中等
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 *
 * 示例：
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *        1
 *       /  \
 *      2    3
 *     / \    \
 *    4   5    7
 *   /
 *  8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 */
class ListOfDepth {

    public ListNode[] listOfDepth2(TreeNode tree) {
        List<ListNode> result = new ArrayList<>();

        if (tree == null) {
            return result.toArray(new ListNode[0]);
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);

        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                curr.next = new ListNode(node.val);
                curr = curr.next;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(dummy.next);
        }

        return result.toArray(new ListNode[0]);
    }

    /**
     * 解题思路：
     * 使用广度优先搜索（BFS）遍历二叉树，在遍历的过程中将每一层的节点保存到链表中。
     *
     * 算法复杂度分析：
     * 时间复杂度：O(n)，其中 n 是二叉树的节点个数。每个节点都会被遍历一次。
     * 空间复杂度：O(n)，最坏情况下，当二叉树为满二叉树时，队列中的元素个数最多为 n/2，即 O(n)。
     *
     * @param root
     * @return
     */
    public List<List<TreeNode>> listOfDepth(TreeNode root) {
        List<List<TreeNode>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(level);
        }

        return result;
    }

    // 测试用例
    public static void main(String[] args) {
        ListOfDepth solution = new ListOfDepth();

        // 构造测试用例
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);

        // 调用方法
        List<List<TreeNode>> result = solution.listOfDepth(root);

        // 打印结果
        for (List<TreeNode> list : result) {
            List<Integer> values = new ArrayList<>();
            for (TreeNode node : list) {
                values.add(node.val);
            }
            System.out.println(values);
        }
    }
}