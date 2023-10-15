package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

/**
 * Convert Sorted Array to Binary Search Tree
 * <p>
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a
 * height-balanced binary search tree.
 * <p>
 * Example 1:
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 * <p>
 * 给定一个按升序排列的整数数组nums，将其转换为一个高度平衡的二叉搜索树。
 * <p>
 * 示例1： 输入：nums = [-10,-3,0,5,9] 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也是一种合法的答案。
 * <p>
 * 示例2： 输入：nums = [1,3] 输出：[3,1] 解释：[1,null,3] 和 [3,1] 都是高度平衡的二叉搜索树。
 *
 * Divide & Conquer
 */
public class ConvertSortedArrayToBST {

    /**
     * 使用递归的方式将有序数组转换为高度平衡的二叉搜索树。
     * 我们选择数组的中间元素作为根节点，然后递归地构建左子树和右子树。
     * 每次递归时，我们将数组的左半部分构建为左子树，右半部分构建为右子树。
     * <p>
     * 为了方便表示二叉树，我们定义了一个TreeNode类。
     * 在sortedArrayToBST方法中，我们首先判断数组是否为空，如果是，则返回null。
     * 然后调用buildBST方法来构建二叉搜索树，传入数组、左边界和右边界作为参数。
     * 在buildBST方法中，我们首先判断左边界是否大于右边界，如果是，则返回null。
     * 然后计算中间元素的位置，并创建一个节点作为根节点。接下来，递归地构建左子树和右子树，并将它们连接到根节点上。
     * <p>
     * 该算法的时间复杂度是O(n)，其中n是输入数组nums的长度。
     * 这是因为在每次递归调用中，我们将数组的一半作为左子树，一半作为右子树，而且每次递归都会遍历数组中的每个元素一次。
     * <p>
     * 空间复杂度是O(logn)，其中n是输入数组nums的长度。
     * 这是因为在每次递归调用中，我们需要维护递归调用栈，而栈的深度取决于树的高度，树的高度最多为logn。
     * 此外，我们还需要使用一些额外的空间来存储树节点的值。因此，总的空间复杂度是O(logn)。
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, left, mid - 1);
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }

    public static void main(String[] args) {
        ConvertSortedArrayToBST convertSortedArrayToBST = new ConvertSortedArrayToBST();

        // 测试样例
        int[] nums1 = {-10, -3, 0, 5, 9};
        TreeNode tree1 = convertSortedArrayToBST.sortedArrayToBST(nums1);
        // 输出树的前序遍历结果
        preOrderTraversal(tree1);  // 输出: [0,-3,9,-10,null,5]

        int[] nums2 = {1, 3};
        TreeNode tree2 = convertSortedArrayToBST.sortedArrayToBST(nums2);
        // 输出树的前序遍历结果
        preOrderTraversal(tree2);  // 输出: [3,1]
    }

    private static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
}
