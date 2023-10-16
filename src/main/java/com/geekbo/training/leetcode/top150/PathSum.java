package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;


/**
 *
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.
 * Example 2:
 *
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Explanation: There two root-to-leaf paths in the tree:
 * (1 --> 2): The sum is 3.
 * (1 --> 3): The sum is 4.
 * There is no root-to-leaf path with sum = 5.
 * Example 3:
 *
 * Input: root = [], targetSum = 0
 * Output: false
 * Explanation: Since the tree is empty, there are no root-to-leaf paths.
 *
 */
public class PathSum {
    /**
     * 使用DFS（深度优先搜索）判断二叉树中是否存在根节点到叶子节点的路径，使得路径上的节点值之和等于目标和。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：在二叉树中，DFS方法的时间复杂度为O(n)，其中n为节点的个数。
     * 因为我们需要遍历所有的节点，所以时间复杂度为O(n)。
     * <p>
     * 空间复杂度：DFS方法的空间复杂度为O(h)，其中h为二叉树的高度
     *
     * @param root      二叉树的根节点
     * @param targetSum 目标和
     * @return 如果存在路径使得路径上的节点值之和等于目标和，返回true；否则返回false
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            // 当前节点是叶子节点
            return root.val == targetSum;
        }

        // 递归判断左子树和右子树中是否存在路径使得节点值之和等于目标和
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 使用BFS（广度优先搜索）判断二叉树中是否存在根节点到叶子节点的路径，使得路径上的节点值之和等于目标和。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：在二叉树中，BFS方法的时间复杂度为O(n)，其中n为节点的个数。
     * 因为我们需要遍历所有的节点，所以时间复杂度为O(n)。
     * <p>
     * 空间复杂度：BFS方法的空间复杂度取决于队列中最多的节点数。
     * 在最坏的情况下，当二叉树为一条斜线时，队列中的节点数为n/2，其中n为节点的个数。因此，空间复杂度为O(n)。
     *
     * @param root      二叉树的根节点
     * @param targetSum 目标和
     * @return 如果存在路径使得路径上的节点值之和等于目标和，返回true；否则返回false
     */
    public boolean hasPathSumBFS(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();
        nodeQueue.offer(root);
        sumQueue.offer(root.val);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int currSum = sumQueue.poll();

            if (node.left == null && node.right == null) {
                // 当前节点是叶子节点
                if (currSum == targetSum) {
                    return true;
                }
                continue;
            }

            if (node.left != null) {
                nodeQueue.offer(node.left);
                sumQueue.offer(currSum + node.left.val);
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                sumQueue.offer(currSum + node.right.val);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        PathSum pathSum = new PathSum();

        // Test Case 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(1);
        int targetSum1 = 22;
        boolean result1 = pathSum.hasPathSum(root1, targetSum1);
        System.out.println("Test Case 1:");
        System.out.println("Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22");
        System.out.println("Output: " + result1);

        // Test Case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        int targetSum2 = 5;
        boolean result2 = pathSum.hasPathSum(root2, targetSum2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: root = [1,2,3], targetSum = 5");
        System.out.println("Output: " + result2);

        // Test Case 3
        TreeNode root3 = null;
        int targetSum3 = 0;
        boolean result3 = pathSum.hasPathSum(root3, targetSum3);
        System.out.println("\nTest Case 3:");
        System.out.println("Input: root = [], targetSum = 0");
        System.out.println("Output: " + result3);
    }
}


