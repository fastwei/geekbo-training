package com.geekbo.training.leetcode.daily;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 2265. Count Nodes Equal to Average of Subtree
 * <p>
 * Given the root of a binary tree, return the number of nodes where the value of
 * the node is equal to the average of the values in its subtree.
 * <p>
 * Note:
 * <p>
 * The average of n elements is the sum of the n elements divided by n
 * and rounded down to the nearest integer.
 * A subtree of root is a tree consisting of root and all of its descendants.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [4,8,5,0,1,null,6]
 * Output: 5
 * Explanation:
 * For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
 * For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
 * For the node with value 0: The average of its subtree is 0 / 1 = 0.
 * For the node with value 1: The average of its subtree is 1 / 1 = 1.
 * For the node with value 6: The average of its subtree is 6 / 1 = 6.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1]
 * Output: 1
 * Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
 */
public class CountNodesEqualToAverageSubtree {
    /**
     * 解题思路：
     *
     * 我们使用深度优先搜索（DFS）来遍历二叉树的每个节点。
     * 对于每个节点，我们递归地计算其左子树和右子树的节点数和节点值之和。
     * 然后，我们将当前节点的值与子树节点值之和的平均值进行比较。
     * 如果当前节点的值等于平均值，我们将计数器加1。
     * 最后，我们返回子树节点数和节点值之和以供父节点使用。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：算法的时间复杂度为O(N)，其中N是二叉树中的节点数。我们需要遍历每个节点一次。
     * 空间复杂度：算法的空间复杂度为O(H)，其中H是二叉树的高度。在最坏情况下，二叉树是一个链表，空间复杂度为O(N)。
     *
     * @param root
     * @return
     */
    public static int averageOfSubtree(TreeNode root) {
        int[] count = new int[1];
        dfs(root, count);
        return count[0];
    }

    private static int[] dfs(TreeNode node, int[] count) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = dfs(node.left, count);
        int[] right = dfs(node.right, count);

        int totalNodes = left[0] + right[0] + 1;
        int totalSum = left[1] + right[1] + node.val;

        if (node.val == totalSum / totalNodes) {
            count[0]++;
        }

        return new int[]{totalNodes, totalSum};
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(8);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(1);
        root1.right.right = new TreeNode(6);
        // 预期输出: 5
        System.out.println(averageOfSubtree(root1));

        TreeNode root2 = new TreeNode(1);
        // 预期输出: 1
        System.out.println(averageOfSubtree(root2));
    }
}
