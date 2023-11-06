package com.geekbo.training.leetcode.dp;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * 337. House Robber III
 * Medium
 * The thief has found himself a new place for his thievery again.
 * There is only one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that all houses in this place form a binary tree.
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *
 *
 * Input: root = [3,4,5,1,3,null,1]
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 */
public class HouseRobberIII {
    /**
     * 解题思路：
     * 使用递归的方式，对于每个节点，有两种选择：
     * 1. 抢劫当前节点，那么子节点就不能被抢劫，累计的金额为当前节点的值加上左右子节点不能被抢劫的金额。
     * 2. 不抢劫当前节点，那么可以选择抢劫左右子节点，累计的金额为左右子节点能被抢劫的最大金额之和。
     * 对于每个节点，返回一个长度为2的数组，数组的第一个元素表示不抢劫当前节点的最大金额，第二个元素表示抢劫当前节点的最大金额。
     * 递归的终止条件是节点为空，返回长度为2的数组，初始值都为0。
     * 最终结果为根节点的抢劫最大金额和不抢劫最大金额的较大值。
     *
     * 算法复杂度分析：
     * 递归遍历每个节点，时间复杂度为O(n)，其中n是节点的数量。
     * 递归过程中，每个节点只进行常数次的计算和比较，所以额外的空间复杂度为O(1)。
     * 因此，总的时间复杂度为O(n)，空间复杂度为O(1)。
     */
    public int rob(TreeNode root) {
        int[] result = robHelper(root);
        return Math.max(result[0], result[1]);
    }

    private int[] robHelper(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }

        int[] left = robHelper(node.left);
        int[] right = robHelper(node.right);

        int rob = node.val + left[1] + right[1];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{rob, notRob};
    }

    public static void main(String[] args) {
        // 创建解法对象
        HouseRobberIII solution = new HouseRobberIII();

        // 创建测试用例1
        // 预期输出: 7
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(1);
        int result1 = solution.rob(root1);
        System.out.println(result1);

        // 创建测试用例2
        // 预期输出: 9
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(1);
        int result2 = solution.rob(root2);
        System.out.println(result2);
    }
}

