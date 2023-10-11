package com.geekbo.training.leetcode.top75;

/**
 *1448. Count Good Nodes in Binary Tree
 *
 *
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * Example 2:
 *
 *
 *
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 *
 *
 * 解题思路：
 *
 * 使用递归方法遍历二叉树，同时传入当前节点和当前路径上的最大值。
 * 在递归过程中，判断当前节点是否是good节点，即当前节点的值大于等于路径上的最大值。
 * 如果是good节点，累加good节点数量，更新路径上的最大值。
 * 继续递归遍历左子树和右子树，累加good节点数量。
 * 返回最终good节点数量。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(N)，其中N是二叉树中的节点数量。
 * 空间复杂度：O(H)，其中H是二叉树的高度。
 *
 *
 */
public class CountGoodNodesInBinaryTree {
    public int goodNodes(TreeNode root) {
        return countGoodNodes(root, root.val);
    }

    // 递归函数，传入当前节点和当前路径上的最大值
    private int countGoodNodes(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }
        
        int count = 0;
        
        // 如果当前节点的值大于等于路径上的最大值，说明它是一个good节点
        if (node.val >= maxSoFar) {
            count++;
            maxSoFar = node.val;
        }
        
        // 递归遍历左子树和右子树，累加good节点的数量
        count += countGoodNodes(node.left, maxSoFar);
        count += countGoodNodes(node.right, maxSoFar);
        
        return count;
    }

    public static void main(String[] args) {
        CountGoodNodesInBinaryTree solution = new CountGoodNodesInBinaryTree();

        // 构建测试树
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(1);
        root1.right.right = new TreeNode(5);

        int result1 = solution.goodNodes(root1);
        System.out.println("Test Case 1: " + result1); // 预期输出：4

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.right.left = new TreeNode(2);

        int result2 = solution.goodNodes(root2);
        System.out.println("Test Case 2: " + result2); // 预期输出：3

        TreeNode root3 = new TreeNode(1);

        int result3 = solution.goodNodes(root3);
        System.out.println("Test Case 3: " + result3); // 预期输出：1
    }
}
