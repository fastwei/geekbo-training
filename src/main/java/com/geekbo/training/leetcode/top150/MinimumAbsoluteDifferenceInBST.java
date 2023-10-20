package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 *
 * Given the root of a Binary Search Tree (BST),
 * return the minimum absolute difference between the values of any two different nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 * Example 2:
 *
 *
 * Input: root = [1,0,48,null,null,12,49]
 * Output: 1
 *
 */
public class MinimumAbsoluteDifferenceInBST {
    private static int minDiff;

    /**
     * 解题思路： 这个问题可以通过二叉搜索树（BST）的中序遍历来解决。
     * 因为在中序遍历中，树中的节点值是按递增顺序排列的。通过比较相邻节点的差值，我们可以找到最小的绝对差值。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：我们需要遍历BST的所有节点，因此时间复杂度为O(n)，其中n是BST的节点数量。
     * 空间复杂度：我们使用了递归来进行中序遍历，递归调用的最大深度为BST的高度，因此空间复杂度为O(h)，其中h是BST的高度。
     * <p>
     * getMinimumDifference方法返回BST中任意两个不同节点值的最小绝对差值。
     *
     * @param root
     * @return
     */
    public static int getMinimumDifference(TreeNode root) {
        minDiff = Integer.MAX_VALUE;
        inorderTraversal(root, null);
        return minDiff;
    }

    private static TreeNode inorderTraversal(TreeNode node, TreeNode prev) {
        if (node == null) {
            return prev;
        }

        prev = inorderTraversal(node.left, prev);

        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev.val);
        }

        prev = node;

        return inorderTraversal(node.right, prev);
    }

    public static void main(String[] args) {
        // 构建测试用例
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(0);
        root2.right = new TreeNode(48);
        root2.right.left = new TreeNode(12);
        root2.right.right = new TreeNode(49);

        // 调用方法
        int result1 = getMinimumDifference(root1);
        int result2 = getMinimumDifference(root2);

        // 打印结果
        System.out.println(result1); // 输出：1
        System.out.println(result2); // 输出：1
    }
}
