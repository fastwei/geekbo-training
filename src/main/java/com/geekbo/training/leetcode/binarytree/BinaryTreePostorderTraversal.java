package com.geekbo.training.leetcode.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. Binary Tree Postorder Traversal
 * Easy
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of the nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
class BinaryTreePostorderTraversal {
    /**
     * 这个优化的版本中，我们只使用一个栈。
     * 我们使用 curr 变量来跟踪当前访问的节点，使用 prev 变量来跟踪前一个访问的节点。
     * 在每个迭代中，我们首先将左子节点入栈，然后检查当前节点的右子节点。
     * 如果右子节点为空或者已经访问过（即右子节点等于 prev），我们将当前节点弹出栈并将其值添加到结果列表中。
     * 否则，我们将当前节点的右子节点设置为当前节点，然后继续迭代。
     * <p>
     * 这个优化的算法的时间复杂度仍然是O(N)，其中N是二叉树中的节点数。
     * 但是，由于我们只使用了一个栈，空间复杂度降低为O(1)。
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.peek();

            if (curr.right == null || curr.right == prev) {
                stack.pop();
                result.add(curr.val);
                prev = curr;
                curr = null;
            } else {
                curr = curr.right;
            }
        }

        return result;
    }

    /**
     * 这个问题的解题思路是使用一个栈来模拟递归的过程。我们使用一个 prev 变量来跟踪前一个访问的节点。
     * 当栈顶节点是叶子节点或者是从下面返回的节点时，我们将其弹出栈并将其值添加到结果列表中。
     * 否则，我们将当前节点的右子节点和左子节点依次入栈。
     * <p>
     * 这个算法的时间复杂度是O(N)，其中N是二叉树中的节点数。空间复杂度是O(N)，因为在最坏的情况下，栈的大小可以达到N。
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = null;

        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();

            if (prev == null || prev.left == curr || prev.right == curr) {
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    stack.pop();
                    result.add(curr.val);
                }
            } else if (curr.left == prev) {
                if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    stack.pop();
                    result.add(curr.val);
                }
            } else if (curr.right == prev) {
                stack.pop();
                result.add(curr.val);
            }

            prev = curr;
        }

        return result;
    }
}

