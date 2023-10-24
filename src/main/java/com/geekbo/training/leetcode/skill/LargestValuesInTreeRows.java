package com.geekbo.training.leetcode.skill;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.*;


public class LargestValuesInTreeRows {
    /**
     * 返回二叉树每一行的最大值组成的数组
     * <p>
     * 解题思路：
     * 使用BFS（广度优先搜索）遍历二叉树，记录每一行的最大值。
     * 在遍历每一行的过程中，比较每个节点的值，更新当前行的最大值。
     * 将每一行的最大值存入结果列表中。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：在二叉树中，BFS方法的时间复杂度为O(n)，其中n为节点的个数。
     * 因为我们需要遍历所有的节点，所以时间复杂度为O(n)。
     * 空间复杂度：BFS方法的空间复杂度为O(w)，其中w为二叉树的最大宽度，也就是最后一层的节点个数。
     * 在最坏的情况下，最后一层的节点个数为2^h，其中h为树的高度，所以空间复杂度为O(2^h)。
     */
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(max);
        }

        return result;
    }

    public static void main(String[] args) {
        // 测试用例1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(9);
        List<Integer> expected1 = Arrays.asList(1, 3, 9);
        List<Integer> result1 = largestValues(root1);
        System.out.println(result1.equals(expected1) ? "Test case 1 passed" : "Test case 1 failed");

        // 测试用例2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        List<Integer> expected2 = Arrays.asList(1, 3);
        List<Integer> result2 = largestValues(root2);
        System.out.println(result2.equals(expected2) ? "Test case 2 passed" : "Test case 2 failed");
    }
}