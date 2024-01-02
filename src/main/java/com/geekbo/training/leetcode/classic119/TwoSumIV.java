package com.geekbo.training.leetcode.classic119;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 653. Two Sum IV - Input is a BST
 * Easy
 * Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that their sum is equal to k, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 104].
 * -104 <= Node.val <= 104
 * root is guaranteed to be a valid binary search tree.
 * -105 <= k <= 105
 */
public class TwoSumIV {

    /**
     * 两数之和 IV - 输入 BST
     * <p>
     * 解题思路：
     * <p>
     * 首先，我们可以使用中序遍历将 BST 中的节点值存储到一个有序数组中。
     * 然后，使用双指针法在有序数组中找到两个元素之和等于 k 的情况。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：中序遍历需要花费 O(n) 的时间，其中 n 是 BST 的节点数。
     * 同时，双指针法需要花费 O(n) 的时间。因此，整体的时间复杂度为 O(n)。
     * 空间复杂度：中序遍历需要使用 O(n) 的空间来存储节点值的有序数组。因此，整体的空间复杂度为 O(n)。
     *
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);

        int left = 0;
        int right = nums.size() - 1;

        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }

        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    public static void main(String[] args) {
        TwoSumIV solution = new TwoSumIV();

        // Test case 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        int k1 = 9;
        boolean result1 = solution.findTarget(root1, k1);
        System.out.println("Test case 1: " + result1);

        // Test case 2
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);
        int k2 = 28;
        boolean result2 = solution.findTarget(root2, k2);
        System.out.println("Test case 2: " + result2);
    }
}