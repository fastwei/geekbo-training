package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
 * Answers within 10-5 of the actual answer will be accepted.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * Example 2:
 *
 *
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 *
 */
public class AverageLevelsInBinaryTree {
    /**
     * 解题思路： 这个问题可以通过广度优先搜索（BFS）来解决。
     * 我们可以使用队列来逐层遍历二叉树的节点。
     * 在每一层中，我们计算该层节点的平均值，并将其添加到结果列表中。
     * 我们还使用一个变量来跟踪每一层的节点数，以便计算平均值时除以正确的数目。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：我们需要遍历二叉树的所有节点，因此时间复杂度为O(n)，其中n是二叉树的节点数量。
     * 空间复杂度：我们使用一个队列来存储每一层的节点，最多会存储二叉树的最后一层节点，因此空间复杂度为O(w)，
     * 其中w是二叉树的最大宽度。
     *
     * @param root
     * @return
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> averages = new ArrayList<>();
        if (root == null) {
            return averages;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            double levelAverage = levelSum / levelSize;
            averages.add(levelAverage);
        }

        return averages;
    }

    public static void main(String[] args) {
        // 构建测试用例
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.left.left = new TreeNode(15);
        root2.left.right = new TreeNode(7);

        // 调用方法
        List<Double> result1 = averageOfLevels(root1);
        List<Double> result2 = averageOfLevels(root2);

        // 打印结果
        System.out.println(result1); // 输出：[3.0, 14.5, 11.0]
        System.out.println(result2); // 输出：[3.0, 14.5, 11.0]
    }
}
