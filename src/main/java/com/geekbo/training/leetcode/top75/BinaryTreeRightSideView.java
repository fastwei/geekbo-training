package com.geekbo.training.leetcode.top75;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 解题思路和算法复杂度分析：
 * <p>
 * 我们使用层序遍历（BFS）来遍历二叉树，从右侧视角看到的节点将会是每层的最右节点。
 * 在每层遍历时，我们维护一个指向最右节点的指针（rightmost），并将其添加到结果列表中。
 * 时间复杂度：O(N)，其中 N 是二叉树中的节点数量，因为我们需要访问每个节点一次。
 * 空间复杂度：O(M)，其中 M 是二叉树中一层的最大节点数，即队列的大小。
 */
public class BinaryTreeRightSideView {


    /**
     * 这个优化后的代码使用了广度优先搜索和队列，可以更好地应对不同的测试用例，包括大规模二叉树。
     * <p>
     * 使用广度优先搜索（BFS）算法：对于这个问题，使用BFS可能更加简单和高效。
     * <p>
     * 避免递归：递归在处理大规模的二叉树时可能会导致栈溢出。使用迭代的方式来实现算法。
     * <p>
     * 使用队列：BFS通常使用队列来实现。将每一层的节点放入队列，以确保按层遍历。
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode rightmost = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                rightmost = node;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(rightmost.val);
        }

        return result;
    }

    /**
     *
     * todo:这个方法有些测试用例通不过
     * @param root
     * @return
     */
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode rightmost = null;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    rightmost = node; // The rightmost node at each level
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(rightmost.val);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);

        BinaryTreeRightSideView solution = new BinaryTreeRightSideView();
        List<Integer> result1 = solution.rightSideView(root1);
        System.out.println(result1); // Output: [1, 3, 4]

        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(3);

        List<Integer> result2 = solution.rightSideView(root2);
        System.out.println(result2); // Output: [1, 3]

        TreeNode root3 = null;
        List<Integer> result3 = solution.rightSideView(root3);
        System.out.println(result3); // Output: []
    }
}