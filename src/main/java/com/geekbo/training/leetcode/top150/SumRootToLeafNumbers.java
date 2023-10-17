package com.geekbo.training.leetcode.top150;


import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * <p>
 * Each root-to-leaf path in the tree represents a number.
 * <p>
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers.
 * Test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * A leaf node is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3]
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [4,9,0,5,1]
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
public class SumRootToLeafNumbers {

    /**
     * 我们使用深度优先搜索（DFS）来遍历二叉树。
     * <p>
     * 在DFS的过程中，我们维护一个currentSum变量来表示当前路径上的数字的累加和。
     * 对于每个节点，我们将currentSum乘以10，并加上当前节点的值，得到新的累加和newSum。
     * 如果当前节点是叶子节点，则返回newSum作为路径表示的数字。
     * <p>
     * 如果当前节点不是叶子节点，则继续递归调用DFS函数，分别计算左子树和右子树的路径表示的数字的总和，并将它们相加。
     * <p>
     * 最后，将左子树和右子树的路径表示的数字的总和相加，即为所有从根节点到叶子节点的路径表示的数字的总和。
     * <p>
     * 根据代码的实现和题目要求，可以得出以下解题思路：
     * <p>
     * 使用深度优先搜索（DFS）遍历二叉树。
     * 在DFS的过程中，维护一个currentSum变量来表示当前路径上的数字的累加和。
     * 对于每个节点，将currentSum乘以10，并加上当前节点的值，得到新的累加和newSum。
     * 如果当前节点是叶子节点，则返回newSum作为路径表示的数字。
     * 如果当前节点不是叶子节点，则继续递归调用DFS函数，计算左子树和右子树的路径表示的数字的总和，并将它们相加。
     * 返回左子树和右子树的路径表示的数字的总和。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历二叉树的时间复杂度为O(n)，
     *
     * @param root
     * @return
     */
    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private static int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }

        int newSum = currentSum * 10 + node.val;

        if (node.left == null && node.right == null) {
            return newSum;
        }

        int leftSum = dfs(node.left, newSum);
        int rightSum = dfs(node.right, newSum);

        return leftSum + rightSum;
    }

    /**
     * 在BFS的过程中，我们使用两个队列：nodeQueue用于存储节点，numQueue用于存储节点所代表的路径表示的数字。
     * <p>
     * 初始时，将根节点和根节点的值分别入队。然后进行循环，直到队列为空。
     * <p>
     * 在每次循环中，从节点队列和数字队列中分别取出一个节点和一个数字。
     * 如果当前节点是叶子节点，则将数字加到总和中。
     * <p>
     * 如果当前节点有左子节点，将左子节点和新的数字（当前数字乘以10加上左子节点的值）分别入队。
     * 如果当前节点有右子节点，将右子节点和新的数字（当前数字乘以10加上右子节点的值）分别入队。
     * <p>
     * 最后，返回总和。
     *
     * @param root
     * @return
     */
    public static int sumNumbersBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);

        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int num = numQueue.poll();

            if (node.left == null && node.right == null) {
                sum += num;
            }

            if (node.left != null) {
                nodeQueue.offer(node.left);
                numQueue.offer(num * 10 + node.left.val);
            }

            if (node.right != null) {
                nodeQueue.offer(node.right);
                numQueue.offer(num * 10 + node.right.val);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println(sumNumbers(root1)); // Output: 25

        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(0);
        root2.left.left = new TreeNode(5);
        root2.left.right = new TreeNode(1);
        System.out.println(sumNumbers(root2)); // Output: 1026
    }
}
