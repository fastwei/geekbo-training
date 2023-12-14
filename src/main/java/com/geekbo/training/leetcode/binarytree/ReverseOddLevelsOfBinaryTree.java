package com.geekbo.training.leetcode.binarytree;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2415. Reverse Odd Levels of Binary Tree 翻转二叉树的奇数层
 * 给定一个完全二叉树的根节点，翻转树的每个奇数层的节点值。
 * 例如，假设树的第三层的节点值为[2,1,3,4,7,11,29,18]，则它应该变为[18,29,11,7,4,3,1,2]。
 * 返回翻转后的树的根节点。
 * 一个二叉树是完全二叉树，如果所有的父节点都有两个子节点，并且所有的叶子节点都在同一层。
 * 节点的层级是从它到根节点的路径上的边的数量。
 * <p>
 * 示例 1：
 * 输入: root = [2,3,5,8,13,21,34]
 * 输出: [2,5,3,8,13,21,34]
 * 解释：
 * 树只有一层奇数。
 * 第一层的节点值为3, 5，翻转后变为5, 3。
 * <p>
 * 示例 2：
 * 输入: root = [7,13,11]
 * 输出: [7,11,13]
 * 解释：
 * 第一层的节点值为13, 11，翻转后变为11, 13。
 * <p>
 * 示例 3：
 * 输入: root = [0,1,2,0,0,0,0,1,1,1,1,2,2,2,2]
 * 输出: [0,2,1,0,0,0,0,2,2,2,2,1,1,1,1]
 * 解释：
 * 奇数层的节点值不为0。
 * 第一层的节点值为1, 2，翻转后变为2, 1。
 * 第三层的节点值为1, 1, 1, 1, 2, 2, 2, 2，翻转后变为2, 2, 2, 2, 1, 1, 1, 1。
 */
public class ReverseOddLevelsOfBinaryTree {

    /**
     * 翻转二叉树的奇数层
     * 解题思路：
     * <p>
     * 使用递归遍历二叉树的每个节点，通过判断节点所在的层级来决定是否翻转节点值。
     * 如果节点所在层级是奇数，则交换节点的左右子节点的值。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N)，其中 N 是二叉树的节点数。需要遍历所有节点。
     * 空间复杂度：O(H)，其中 H 是二叉树的高度。递归调用栈的最大深度为树的高度。
     *
     * @param root 二叉树的根节点
     * @return 翻转后的二叉树的根节点
     */
    public TreeNode reverseOddLevels(TreeNode root) {
        // We call the function from lvl 0, and everything starts from lvl 1
        traverse(root.left, root.right, 1);
        return root;
    }

    public void traverse(TreeNode node1, TreeNode node2, int lvl) {
        if (node1 == null || node2 == null) {
            return;
        }
        if (lvl % 2 == 1){
            int temp = node1.val;
            node1.val = node2.val;
            node2.val = temp;
        }

        traverse(node1.left, node2.right, lvl + 1);
        traverse(node1.right, node2.left, lvl + 1);
    }

    public TreeNode reverseOddLevelsBFS(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            level++;
            if (level % 2 == 1 && !q.isEmpty()) {
                int[] nums = new int[q.size()];
                int i = 0;
                for (TreeNode node : q) {
                    nums[i++] = node.val;
                }

                int j = q.size() - 1;
                for (TreeNode node : q) {
                    node.val = nums[j--];
                }
            }
        }

        return root;
    }

    /**
     * 测试用例
     */
    public static void main(String[] args) {
        ReverseOddLevelsOfBinaryTree solution = new ReverseOddLevelsOfBinaryTree();

        // 测试用例1
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(8);
        root1.left.right = new TreeNode(13);
        root1.right.left = new TreeNode(21);
        root1.right.right = new TreeNode(34);
        TreeNode result1 = solution.reverseOddLevels(root1);
        System.out.println(result1); // [2, 5, 3, 8, 13, 21, 34]

        // 测试用例2
        TreeNode root2 = new TreeNode(7);
        root2.left = new TreeNode(13);
        root2.right = new TreeNode(11);
        TreeNode result2 = solution.reverseOddLevels(root2);
        System.out.println(result2); // [7, 11, 13]

        // 测试用例3
        TreeNode root3 = new TreeNode(0);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(0);
        root3.left.right = new TreeNode(0);
        root3.right.left = new TreeNode(0);
        root3.right.right = new TreeNode(0);
        root3.left.left.left = new TreeNode(1);
        root3.left.left.right = new TreeNode(1);
        root3.left.right.left = new TreeNode(1);
        root3.left.right.right = new TreeNode(1);
        root3.right.left.left = new TreeNode(2);
        root3.right.left.right = new TreeNode(2);
        root3.right.right.left = new TreeNode(2);
        root3.right.right.right = new TreeNode(2);
        TreeNode result3 = solution.reverseOddLevels(root3);
        System.out.println(result3); // [0, 2, 1, 0, 0, 0, 0, 2, 2, 2, 2, 1, 1, 1, 1]
    }
}
