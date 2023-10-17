package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

class Result {
    int maxPathSum;

    Result(int maxPathSum) {
        this.maxPathSum = maxPathSum;
    }
}

/**
 *
 * 124. Binary Tree Maximum Path Sum
 * Hard
 * Topics
 * Companies
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 */
public class BinaryTreeMaximumPathSum {

    /**
     * 解题思路：
     * 这个问题可以通过递归的方式来解决。
     * 我们可以定义一个递归函数getMaxPathSum，它返回以当前节点为根的子树的最大路径和，并更新全局的最大路径和。
     * 对于每个节点，我们计算它的左子树和右子树的最大路径和（如果路径和小于0，则置为0），然后计算当前节点作为路径的最大和。
     * 最后返回以当前节点为根的子树的最大路径和（只能选择左子树或右子树的路径）。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：我们需要遍历二叉树的所有节点，因此时间复杂度为O(n)，其中n是二叉树的节点数量。
     * 空间复杂度：递归调用的最大深度是二叉树的高度，因此空间复杂度为O(h)，其中h是二叉树的高度。
     * maxPathSum方法返回二叉树中任意非空路径的
     *
     * @param root
     * @return
     */
    public static int maxPathSum(TreeNode root) {
        Result result = new Result(Integer.MIN_VALUE);
        getMaxPathSum(root, result);
        return result.maxPathSum;
    }

    private static int getMaxPathSum(TreeNode root, Result result) {
        if (root == null) {
            return 0;
        }

        // 递归计算左子树和右子树的最大路径和，如果路径和小于0，则置为0
        int leftMaxPathSum = Math.max(0, getMaxPathSum(root.left, result));
        int rightMaxPathSum = Math.max(0, getMaxPathSum(root.right, result));

        // 更新当前节点作为路径的最大和
        int currentPathSum = root.val + leftMaxPathSum + rightMaxPathSum;
        result.maxPathSum = Math.max(result.maxPathSum, currentPathSum);

        // 返回以当前节点为根的子树的最大路径和（只能选择左子树或右子树的路径）
        return root.val + Math.max(leftMaxPathSum, rightMaxPathSum);
    }

    public static void main(String[] args) {
        // 构建测试用例
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);

        // 调用方法
        int result1 = maxPathSum(root1);
        int result2 = maxPathSum(root2);

        // 打印结果
        System.out.println(result1); // 输出：6
        System.out.println(result2); // 输出：42
    }
}
