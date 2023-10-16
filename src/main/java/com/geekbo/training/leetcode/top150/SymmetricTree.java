package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;


public class SymmetricTree {

    /**
     * 解题思路： 使用深度优先搜索（DFS）来判断二叉树是否是对称的。
     * 可以通过比较每个节点的左右子树来判断二叉树是否对称。
     * 对称的条件是：两个节点的值相等，并且一个节点的左子树与另一个节点的右子树对称，
     * 一个节点的右子树与另一个节点的左子树对称。
     * <p>
     * 算法步骤：
     * <p>
     * 定义一个递归函数isMirror，接收两个参数，代表两个节点。
     * 在递归函数中，判断两个节点是否同时为空，如果是则返回true。
     * 判断两个节点是否有一个为空，或者两个节点的值不相等，如果是则返回false。
     * 递归地调用isMirror函数，分别传入两个节点的左右子节点，并判断左子节点的左子树是否与右子节点的右子树对称，
     * 以及左子节点的右子树是否与右子节点的左子树对称。
     * 如果两个都成立，则返回true；否则返回false。
     * 在主函数中调用isMirror函数，传入根节点的左右子节点，判断二叉树是否对称。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历整个二叉树的每个节点，所以时间复杂度为O(n)，其中n为节点的个数。
     * 空间复杂度：递归调用的栈空间取决于二叉树的高度，最坏情况下，当二叉树为一条斜线时，高度为n，所以空间复杂度为O(n)。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }

        return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }


    /**
     * 解题思路： 使用广度优先搜索（BFS）来判断二叉树是否是对称的。
     * 可以通过比较每一层节点的值来判断二叉树是否对称。
     * 在每一层，将节点按照对称的顺序加入队列，然后逐个比较队列中的节点值是否相等。
     * <p>
     * 算法步骤：
     * <p>
     * 定义一个队列，并将根节点的左右子节点按照相反的顺序加入队列。
     * 进入循环，直到队列为空。
     * 在每一次循环中，从队列中取出两个节点，分别判断它们的值是否相等。
     * 如果两个节点的值不相等，或者有一个节点为空而另一个节点不为空，则返回false。
     * 如果两个节点的值相等，将它们的左右子节点按照相反的顺序加入队列。
     * 循环结束后，如果没有返回false，则返回true。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历整个二叉树的每个节点，所以时间复杂度为O(n)，其中n为节点的个数。
     * 空间复杂度：队列中的节点数最多为每层的节点数，
     * 最坏情况下，当二叉树为满二叉树时，每层的节点数是二的幂次方，所以空间复杂度为O(2^h)，
     * 其中h为二叉树的高度。
     *
     * @param root
     * @return
     */
    public boolean isSymmetricBFS(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if (node1 == null && node2 == null) {
                continue;
            }

            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }

            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);
        }

        return true;
    }

    public static void main(String[] args) {
        SymmetricTree symmetricTree = new SymmetricTree();

        // Test Case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);
        boolean result1 = symmetricTree.isSymmetric(root1);
        System.out.println("Test Case 1:");
        System.out.println("Input: [1, 2, 2, 3, 4, 4, 3]");
        System.out.println("Output: " + result1);

        // Test Case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);
        boolean result2 = symmetricTree.isSymmetric(root2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: [1, 2, 2, null, 3, null, 3]");
        System.out.println("Output: " + result2);

        // Test Case 3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(3);
        root3.left.right = new TreeNode(4);
        root3.right.left = new TreeNode(3);
        root3.right.right = new TreeNode(4);
        boolean result3 = symmetricTree.isSymmetric(root3);
        System.out.println("\nTest Case 3:");
        System.out.println("Input: [1, 2, 2, 3, 4, 3, 4]");
        System.out.println("Output: " + result3);
    }
}