package com.geekbo.training.leetcode.codeinterview109;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 题目：合法二叉搜索树
 * 难度：中等
 * 
 * 题目描述：
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 * 
 * 示例 1：
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 
 * 示例 2：
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * 
 * 解题思路：
 * 二叉搜索树的定义是：对于任意节点，其左子树的值都小于该节点的值，右子树的值都大于该节点的值。
 * 因此，我们可以通过递归的方式来判断一棵二叉树是否为二叉搜索树。
 * 对于每个节点，我们需要判断其值是否在合法的范围内，即要大于左子树的最大值，且要小于右子树的最小值。
 * 可以使用上下界来表示左子树和右子树的合法范围，初始时上下界可以取正负无穷。
 * 对于左子树，下界更新为根节点的值，对于右子树，上界更新为根节点的值。
 * 
 * 算法复杂度分析：
 * 时间复杂度：O(n)，其中 n 是二叉树的节点数。我们需要对每个节点进行一次遍历。
 * 空间复杂度：O(n)，其中 n 是二叉树的节点数。空间复杂度主要取决于递归调用的栈空间，在最坏情况下，二叉树的高度为 n，空间复杂度为 O(n)。
 */
public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode node, Integer min, Integer max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.val <= min) {
            return false;
        }
        if (max != null && node.val >= max) {
            return false;
        }
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    public static void main(String[] args) {
        // 测试用例1：合法的二叉搜索树
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        ValidBST solution = new ValidBST();
        System.out.println(solution.isValidBST(root1));  // 输出: true

        // 测试用例2：非法的二叉搜索树
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);

        System.out.println(solution.isValidBST(root2));  // 输出: false
    }
}