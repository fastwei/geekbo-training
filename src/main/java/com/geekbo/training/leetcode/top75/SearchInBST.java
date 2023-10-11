package com.geekbo.training.leetcode.top75;

/**
 *700. Search in a Binary Search Tree
 *
 * 解题思路：
 *
 * 从根节点开始，如果根节点为空或者根节点的值等于目标值，直接返回根节点。
 * 如果目标值小于根节点的值，递归在左子树中查找。
 * 如果目标值大于根节点的值，递归在右子树中查找。
 * 如果未找到匹配的节点，返回null。
 *
 */
public class SearchInBST {
    public TreeNode searchBST(TreeNode root, int val) {
        // 如果根节点为空或者根节点的值等于目标值，直接返回根节点
        if (root == null || root.val == val) {
            return root;
        }

        // 如果目标值小于根节点的值，递归在左子树中查找
        if (val < root.val) {
            return searchBST(root.left, val);
        }

        // 如果目标值大于根节点的值，递归在右子树中查找
        if (val > root.val) {
            return searchBST(root.right, val);
        }

        // 如果未找到匹配的节点，返回null
        return null;
    }

    public static void main(String[] args) {
        SearchInBST solution = new SearchInBST();

        // 构建测试树
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // 测试用例1
        TreeNode result1 = solution.searchBST(root, 2);
        System.out.println("Test Case 1: " + result1.val); // 预期输出：2

        // 测试用例2
        TreeNode result2 = solution.searchBST(root, 5);
        if (result2 == null) {
            System.out.println("Test Case 2: []"); // 预期输出：[]
        }
    }

}