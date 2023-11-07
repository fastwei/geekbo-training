package com.geekbo.training.leetcode.binarytree;

/**
 * 111. Minimum Depth of Binary Tree
 * Easy
 * <p>
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Example 2:
 * <p>
 * Input: root = [2,null,3,null,4,null,5,null,6]
 * Output: 5
 */
public class MinimumDepthBinaryTree {
    /**
     * The algorithm
     * works as follows:
     * <p>
     * If the root is null,the minimum depth is 0.
     * If the root is a leaf node(i.e.,it has no children),the minimum depth is 1.
     * Otherwise,calculate the minimum depth of the left and right subtrees recursively.
     * Return the minimum depth between the left and right subtrees plus 1.
     * The time complexity of the algorithm is O(n),
     * where n is the number of nodes in the binary tree.
     * This is because we need to visit each node exactly once.
     * The space complexity is O(h),where h is the height of the binary tree.
     * In the worst case,the height of the binary tree can be n,so the space complexity can be O(n).
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // If the current node is a leaf node, return 1
        if (root.left == null && root.right == null) {
            return 1;
        }

        int leftDepth = root.left != null ? minDepth(root.left) : Integer.MAX_VALUE;
        int rightDepth = root.right != null ? minDepth(root.right) : Integer.MAX_VALUE;

        // Return the minimum depth between the left and right subtree
        return Math.min(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        // Construct the test cases
        // Test case 1: [3,9,20,null,null,15,7]
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        // Test case 2: [2,null,3,null,4,null,5,null,6]
        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(4);
        root2.right.right.right = new TreeNode(5);
        root2.right.right.right.right = new TreeNode(6);

        // Create an instance of the MinimumDepthBinaryTree class
        MinimumDepthBinaryTree solution = new MinimumDepthBinaryTree();

        // Calculate the minimum depth for each test case
        int result1 = solution.minDepth(root1);
        int result2 = solution.minDepth(root2);

        // Print the results
        System.out.println("Test case 1: " + result1);
        System.out.println("Test case 2: " + result2);
    }
}
