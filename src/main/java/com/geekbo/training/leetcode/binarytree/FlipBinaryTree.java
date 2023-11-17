package com.geekbo.training.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 971. Flip Binary Tree To Match Preorder Traversal
 * Medium
 * You are given the root of a binary tree with n nodes,
 * where each node is uniquely assigned a value from 1 to n.
 * You are also given a sequence of n values voyage,
 * which is the desired pre-order traversal of the binary tree.
 * <p>
 * Any node in the binary tree can be flipped by swapping its left and right subtrees.
 * For example, flipping node 1 will have the following effect:
 * <p>
 * <p>
 * Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
 * <p>
 * Return a list of the values of all flipped nodes. You may return the answer in any order.
 * If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage,
 * return the list [-1].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2], voyage = [2,1]
 * Output: [-1]
 * Explanation: It is impossible to flip the nodes such that the pre-order traversal matches voyage.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [1,2,3], voyage = [1,3,2]
 * Output: [1]
 * Explanation: Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal matches voyage.
 * Example 3:
 * <p>
 * <p>
 * Input: root = [1,2,3], voyage = [1,2,3]
 * Output: []
 * Explanation: The tree's pre-order traversal already matches voyage, so no nodes need to be flipped.
 */
public class FlipBinaryTree {
    private List<Integer> flipped;

    /**
     * 解题思路：
     * <p>
     * 首先，我们使用深度优先搜索（DFS）来遍历二叉树，并同时遍历预期的前序遍历数组 voyage。
     * 在遍历的过程中，我们比较当前节点的值和 voyage 数组中对应位置的值：
     * 如果节点的值不匹配，说明无法通过翻转节点来使前序遍历匹配 voyage，返回 false。
     * 如果节点的值匹配，我们继续递归地遍历左子树和右子树。
     * 当遍历到某个节点时，如果该节点的左子节点的值不匹配 voyage 数组中对应位置的值，说明需要翻转该节点的左右子树。
     * 如果遍历完成后，整个二叉树的前序遍历和 voyage 数组完全匹配，说明可以通过翻转节点来使前序遍历匹配 voyage。
     * <p>
     * 在遍历过程中，如果需要翻转节点的左右子树，则将该节点的值添加到 flipped 列表中。
     * 如果最终遍历完成后，flipped 列表为空，则说明不需要翻转节点；否则，返回 flipped 列表作为结果。
     * 如果遍历过程中发现节点的值不匹配，或者遍历完成后 index[0] 的值不等于 voyage 数组的长度，说明无法通过翻转节点来使前序遍历匹配 voyage，返回 false。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历二叉树的时间复杂度为 O(n)，其中 n 是二叉树的节点数。
     * 空间复杂度：除了存储结果列表 flipped 和递归调用栈外，算法的空间复杂度为 O(1)。
     *
     * @param root
     * @param voyage
     * @return
     */
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        flipped = new ArrayList<>();
        int[] index = {0};
        if (dfs(root, voyage, index)) {
            return flipped;
        } else {
            flipped.clear();
            flipped.add(-1);
            return flipped;
        }
    }

    private boolean dfs(TreeNode node, int[] voyage, int[] index) {
        if (node == null) {
            return true;
        }
        if (node.val != voyage[index[0]]) {
            return false;
        }
        index[0]++;
        if (node.left != null && node.left.val != voyage[index[0]]) {
            flipped.add(node.val);
            return dfs(node.right, voyage, index) && dfs(node.left, voyage, index);
        }
        return dfs(node.left, voyage, index) && dfs(node.right, voyage, index);
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        int[] voyage1 = {2, 1};
        FlipBinaryTree flipBinaryTree = new FlipBinaryTree();
        System.out.println("Input: root = [1, 2], voyage = [2, 1]");
        System.out.println("Output: " + flipBinaryTree.flipMatchVoyage(root1, voyage1)); // Output: [-1]

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        int[] voyage2 = {1, 3, 2};
        System.out.println("\nInput: root = [1, 2, 3], voyage = [1, 3, 2]");
        System.out.println("Output: " + flipBinaryTree.flipMatchVoyage(root2, voyage2)); // Output: [1]

        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        int[] voyage3 = {1, 2, 3};
        System.out.println("\nInput: root = [1, 2, 3], voyage = [1, 2, 3]");
        System.out.println("Output: " + flipBinaryTree.flipMatchVoyage(root3, voyage3)); // Output: []
    }
}