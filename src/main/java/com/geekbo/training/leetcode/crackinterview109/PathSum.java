package com.geekbo.training.leetcode.crackinterview109;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 *
 * 面试题 04.12. 求和路径
 * 中等
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * 3
 * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
 * 提示：
 *
 * 节点总数 <= 10000
 *
 */
class PathSum {

    /**
     * 解题思路：
     * <p>
     * 使用递归进行深度优先搜索。
     * 对于每个节点，分别计算以该节点为起点的路径数量。
     * 将结果累加到总数上。
     * 时间复杂度：O(N^2)，其中N是二叉树的节点数。
     * 每个节点都需要计算一次以该节点为起点的路径数量，而每次计算的时间复杂度是O(N)。
     * 空间复杂度：O(H)，其中H是二叉树的高度。递归调用的栈空间最多为H层。
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        // 使用递归进行深度优先搜索
        // 对于每个节点，分别计算以该节点为起点的路径数量
        // 将结果累加到总数上
        int count = dfs(root, sum);
        count += pathSum(root.left, sum);
        count += pathSum(root.right, sum);

        return count;
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.val == sum) {
            count++;
        }

        count += dfs(node.left, sum - node.val);
        count += dfs(node.right, sum - node.val);

        return count;
    }

    public static void main(String[] args) {
        // 创建示例树
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        int sum = 22;
        PathSum solution = new PathSum();
        int result = solution.pathSum(root, sum);
        System.out.println(result);
    }
}
