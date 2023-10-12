package com.geekbo.training.leetcode.top75;

import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * 层序遍历二叉树，计算每个层级的和，然后找到最大和对应的层级。
 *
 * 算法的时间复杂度为O(N)，其中N是二叉树中的节点数量。
 *
 */
public class MaxLevelSumBinaryTree {
    public int maxLevelSum(TreeNode root) {
        // 初始化最大和和对应的层级
        int maxSum = root.val;
        int maxLevel = 1;

        // 使用队列进行层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int currentSum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentSum += node.val;

                // 将子节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // 如果当前层级的和大于最大和，更新最大和和对应的层级
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxLevel = level;
            }

            level++;
        }

        return maxLevel;
    }

    public static void main(String[] args) {
        MaxLevelSumBinaryTree solution = new MaxLevelSumBinaryTree();

        // 构建示例树
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(0);
        root1.left.left = new TreeNode(7);
        root1.left.right = new TreeNode(-8);

        int result1 = solution.maxLevelSum(root1);
        System.out.println(result1); // 输出：2

        TreeNode root2 = new TreeNode(989);
        root2.right = new TreeNode(10250);
        root2.right.left = new TreeNode(98693);
        root2.right.right = new TreeNode(-89388);
        root2.right.right.right = new TreeNode(-32127);

        int result2 = solution.maxLevelSum(root2);
        System.out.println(result2); // 输出：2
    }
}
