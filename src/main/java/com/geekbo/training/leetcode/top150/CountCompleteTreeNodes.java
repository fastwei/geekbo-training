package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 * <p>
 * According to Wikipedia, every level, except possibly the last,
 * is completely filled in a complete binary tree,
 * and all nodes in the last level are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.
 * <p>
 * Design an algorithm that runs in less than O(n) time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * Example 2:
 * <p>
 * Input: root = []
 * Output: 0
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: 1
 */
public class CountCompleteTreeNodes {
    /**
     * DFS方式计算完全二叉树的节点数量
     * <p>
     * 解题思路：
     * <p>
     * 我们可以观察到，在完全二叉树中，左子树或者右子树中一定有一棵是满二叉树。
     * 因此，我们可以通过比较左子树和右子树的高度来判断哪一棵子树是满二叉树。
     * 如果左子树的高度等于右子树的高度，则说明左子树是满二叉树，我们可以将其节点数量计算为 2^h，其中 h 是左子树的高度。
     * 然后，我们递归地计算右子树的节点数量，并将两者相加。
     * 如果左子树的高度不等于右子树的高度，则说明右子树是满二叉树.
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：在完全二叉树中，DFS方法的时间复杂度为O(log n * log n)，其中n为节点的个数。
     * 因为我们需要递归地计算树的高度，并且每次递归都会分别计算左子树和右子树的高度。
     * 递归的次数最多为树的高度，即log n，所以时间复杂度为O(log n * log n)。
     *
     * @param root 完全二叉树的根节点
     * @return 完全二叉树中节点的数量
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // 如果左子树的高度等于右子树的高度，说明左子树是满二叉树
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) + countNodes(root.right);
        } else {
            return (1 << rightHeight) + countNodes(root.left);
        }
    }

    // 辅助方法：计算完全二叉树的高度
    private int getHeight(TreeNode root) {
        int height = 0;
        while (root != null) {
            root = root.left;
            height++;
        }
        return height;
    }


    /**
     * 使用BFS（广度优先搜索）计算完全二叉树中节点的个数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：在完全二叉树中，BFS方法的时间复杂度为O(n)，其中n为节点的个数。
     * 因为我们需要遍历所有的节点，所以时间复杂度为O(n)。
     * 空间复杂度：BFS方法的空间复杂度为O(w)，其中w为二叉树的最大宽度，也就是最后一层的节点个数。
     * 在最坏的情况下，最后一层的节点个数为2^h，其中h为树的高度，所以空间复杂度为O(2^h)。
     *
     * @param root 完全二叉树的根节点
     * @return 节点的个数
     */
    public int countNodesBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count++;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        CountCompleteTreeNodes countCompleteTreeNodes = new CountCompleteTreeNodes();

        // Test Case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        int result1 = countCompleteTreeNodes.countNodes(root1);
        System.out.println("Test Case 1:");
        System.out.println("Input: root = [1,2,3,4,5,6]");
        System.out.println("Output: " + result1);

        // Test Case 2
        TreeNode root2 = null;
        int result2 = countCompleteTreeNodes.countNodes(root2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: root = []");
        System.out.println("Output: " + result2);

        // Test Case 3
        TreeNode root3 = new TreeNode(1);
        int result3 = countCompleteTreeNodes.countNodes(root3);
        System.out.println("\nTest Case 3:");
        System.out.println("Input: root = [1]");
        System.out.println("Output: " + result3);
    }
}


