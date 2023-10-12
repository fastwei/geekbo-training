package com.geekbo.training.leetcode.top75;

/**
 * 删除BST中指定节点的实现
 * 
 * 解题思路：
 * 1. 如果根节点为空，直接返回null。
 * 2. 比较要删除的节点值与当前节点值，分三种情况：
 *    - 如果要删除的值小于当前节点值，在左子树中递归删除。
 *    - 如果要删除的值大于当前节点值，在右子树中递归删除。
 *    - 如果要删除的值等于当前节点值，分三种情况：
 *      a. 如果当前节点没有左子树，直接返回右子树。
 *      b. 如果当前节点没有右子树，直接返回左子树。
 *      c. 如果当前节点既有左子树又有右子树，找到右子树中的最小节点，将其值复制到当前节点，然后在右子树中递归删除该最小节点。
 * 3. 返回根节点。
 * 
 * 时间复杂度分析：平均情况下为O(log N)，最坏情况下为O(N)。
 */
public class DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.val = findMin(root.right);
            root.right = deleteNode(root.right, root.val);
        }

        return root;
    }

    private int findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }

    public static void main(String[] args) {
        DeleteNodeInBST deleteNodeInBST = new DeleteNodeInBST();

        // 测试用例
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        TreeNode result1 = deleteNodeInBST.deleteNode(root1, 3);

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        TreeNode result2 = deleteNodeInBST.deleteNode(root2, 0);

        TreeNode root3 = null;
        TreeNode result3 = deleteNodeInBST.deleteNode(root3, 0);
    }
}
