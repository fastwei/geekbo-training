package com.geekbo.training.leetcode.binarytree;

/**
 * 404. Sum of Left Leaves
 * Easy
 * Given the root of a binary tree, return the sum of all left leaves.
 * <p>
 * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 24
 * Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 1000].
 * -1000 <= Node.val <= 1000
 */
class SumLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return sumOfLeftLeavesHelper(root, false);
    }

    private int sumOfLeftLeavesHelper(TreeNode node, boolean isLeft) {
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null && isLeft) {
            return node.val;
        }

        int leftSum = sumOfLeftLeavesHelper(node.left, true);
        int rightSum = sumOfLeftLeavesHelper(node.right, false);

        return leftSum + rightSum;
    }
}