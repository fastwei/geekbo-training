package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInBST {
    public static void main(String[] args) {
        // 测试用例1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        int k1 = 1;
        System.out.println(kthSmallest(root1, k1)); // 预期输出：1

        // 测试用例2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);
        int k2 = 3;
        System.out.println(kthSmallest(root2, k2)); // 预期输出：3
    }

    /**
     * 在二叉搜索树中找到第k小的值
     * 解题思路： 要找到二叉搜索树中第k小的值，可以进行中序遍历操作，将节点值按照升序添加到列表中，然后返回第k个值。
     * <p>
     * 中序遍历二叉搜索树的过程是先遍历左子树，再访问根节点，最后遍历右子树。利用递归的方式，可以实现中序遍历操作。
     * <p>
     * 算法复杂度分析： 中序遍历二叉搜索树需要遍历所有的节点，时间复杂度为O(n)，其中n是二叉搜索树中的节点个数。
     * 空间复杂度为O(n)，用于存储节点值的列表。
     * <p>
     * 如果二叉搜索树经常进行插入和删除操作，并且需要频繁地查找第k小的值，
     * 可以考虑在每个节点上维护一个额外的字段，表示以该节点为根的子树中的节点个数。
     * 这样可以在O(log n)的时间内找到第k小的值。
     *
     * @param root 二叉搜索树的根节点
     * @param k    第k小的值
     * @return 第k小的值
     */
    public static int kthSmallest(TreeNode root, int k) {
        // 中序遍历二叉搜索树，得到有序的节点值列表
        List<Integer> nodeList = new ArrayList<>();
        inorder(root, nodeList);

        // 返回第k小的值
        return nodeList.get(k - 1);
    }

    /**
     * 中序遍历二叉搜索树
     *
     * @param root     当前节点
     * @param nodeList 用于存储节点值的列表
     */
    public static void inorder(TreeNode root, List<Integer> nodeList) {
        if (root == null) {
            return;
        }

        inorder(root.left, nodeList);
        nodeList.add(root.val);
        inorder(root.right, nodeList);
    }
}
