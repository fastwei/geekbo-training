package com.geekbo.training.leetcode.codeinterview109;


import com.geekbo.training.leetcode.base.TreeNode;

/**
 *
 * 面试题 04.02. 最小高度树
 * 简单
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 *
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *           0
 *          / \
 *        -3   9
 *        /   /
 *      -10  5
 *
 */
class BSTBuilder {
    /**
     * 解题思路： 根据有序数组的特点，我们可以选择数组的中间元素作为根节点，
     * 然后将数组分成左右两部分，分别递归构建左子树和右子树。
     * <p>
     * 时间复杂度：O(n)，其中 n 是数组的长度。每个元素都需要创建一个节点。
     * 空间复杂度：O(log n)，递归调用的栈空间。
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, left, mid - 1);
        root.right = buildTree(nums, mid + 1, right);
        return root;
    }

    public static void main(String[] args) {
        BSTBuilder solution = new BSTBuilder();

        // 测试用例
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = solution.sortedArrayToBST(nums);
        // 验证结果
        // 输出：[0,-3,9,-10,null,5]
        System.out.println(root);
    }
}