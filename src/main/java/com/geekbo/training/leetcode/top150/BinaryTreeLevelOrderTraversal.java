package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class BinaryTreeLevelOrderTraversal {

    /**
     * 解题思路： 这个问题可以通过广度优先搜索（BFS）来解决。
     * 我们可以使用队列来逐层遍历二叉树的节点。
     * 在每一层中，我们将该层节点的值存储在一个列表中，并将其添加到结果列表中。
     * 然后，我们将该层节点的左子节点和右子节点放入队列中，以便下一层的遍历。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：我们需要遍历二叉树的所有节点，因此时间复杂度为O(n)，其中n是二叉树的节点数量。
     * 空间复杂度：我们使用一个队列来存储每一层的节点，最多会存储二叉树的最后一层节点，因此空间复杂度为O(w)，其中w是二叉树的最大宽度。
     * levelOrder方法返回一个列表，其中包含二叉树节点的值，按层级从左到右的顺序排列。
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelNodes = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelNodes.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(levelNodes);
        }

        return result;
    }

    public static void main(String[] args) {
        // 构建测试用例
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(1);

        TreeNode root3 = null;

        // 调用方法
        List<List<Integer>> result1 = levelOrder(root1);
        List<List<Integer>> result2 = levelOrder(root2);
        List<List<Integer>> result3 = levelOrder(root3);

        // 打印结果
        System.out.println(result1); // 输出：[[3], [9, 20], [15, 7]]
        System.out.println(result2); // 输出：[[1]]
        System.out.println(result3); // 输出：[]
    }
}
