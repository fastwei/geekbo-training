package com.geekbo.training.leetcode.top150;


import com.geekbo.training.leetcode.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * Example 3:
 * <p>
 * <p>
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 */
public class SameTree {
    /**
     * 检查两棵二叉树是否相同。
     *
     * @param p 第一棵二叉树的根节点
     * @param q 第二棵二叉树的根节点
     * @return 两棵二叉树是否相同
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 如果两棵二叉树都为空，则它们相同
        if (p == null && q == null) {
            return true;
        }

        // 如果两棵二叉树中只有一棵为空，则它们不相同
        if (p == null || q == null) {
            return false;
        }

        // 如果两棵二叉树的根节点的值不相同，则它们不相同
        if (p.val != q.val) {
            return false;
        }

        // 递归地检查左子树和右子树是否相同
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 使用DFS（深度优先搜索）判断两棵二叉树是否相同。
     *
     * @param p 第一棵二叉树的根节点
     * @param q 第二棵二叉树的根节点
     * @return 两棵二叉树是否相同
     */
    public boolean isSameTreeDFS(TreeNode p, TreeNode q) {
        // 创建两个栈，分别用于存储p和q的节点
        Stack<TreeNode> stackP = new Stack<>();
        Stack<TreeNode> stackQ = new Stack<>();

        // 将p和q的根节点入栈
        stackP.push(p);
        stackQ.push(q);

        // 开始DFS
        while (!stackP.isEmpty() && !stackQ.isEmpty()) {
            TreeNode nodeP = stackP.pop();
            TreeNode nodeQ = stackQ.pop();

            // 如果两个节点都为空，则继续下一个迭代
            if (nodeP == null && nodeQ == null) {
                continue;
            }

            // 如果两个节点中只有一个为空，或者两个节点的值不相同，则返回false
            if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val) {
                return false;
            }

            // 将p和q的左子节点入栈
            stackP.push(nodeP.left);
            stackQ.push(nodeQ.left);

            // 将p和q的右子节点入栈
            stackP.push(nodeP.right);
            stackQ.push(nodeQ.right);
        }

        // 如果p和q的栈都为空，则它们相同
        return stackP.isEmpty() && stackQ.isEmpty();
    }

    /**
     * 使用BFS（广度优先搜索）判断两棵二叉树是否相同。
     *
     * @param p 第一棵二叉树的根节点
     * @param q 第二棵二叉树的根节点
     * @return 两棵二叉树是否相同
     */
    public boolean isSameTreeBFS(TreeNode p, TreeNode q) {
        // 创建两个队列，分别用于存储p和q的节点
        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();

        // 将p和q的根节点入队
        queueP.offer(p);
        queueQ.offer(q);

        // 开始BFS
        while (!queueP.isEmpty() && !queueQ.isEmpty()) {
            TreeNode nodeP = queueP.poll();
            TreeNode nodeQ = queueQ.poll();

            // 如果两个节点都为空，则继续下一个迭代
            if (nodeP == null && nodeQ == null) {
                continue;
            }

            // 如果两个节点中只有一个为空，或者两个节点的值不相同，则返回false
            if (nodeP == null || nodeQ == null || nodeP.val != nodeQ.val) {
                return false;
            }

            // 将p和q的左子节点入队
            queueP.offer(nodeP.left);
            queueQ.offer(nodeQ.left);

            // 将p和q的右子节点入队
            queueP.offer(nodeP.right);
            queueQ.offer(nodeQ.right);
        }

        // 如果p和q的队列都为空，则它们相同
        return queueP.isEmpty() && queueQ.isEmpty();
    }

    public static void main(String[] args) {
        SameTree sameTree = new SameTree();

        // Test Case 1
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);
        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);
        boolean result1 = sameTree.isSameTree(p1, q1);
        System.out.println("Test Case 1:");
        System.out.println("Input: p = [1,2,3], q = [1,2,3]");
        System.out.println("Output: " + result1);

        // Test Case 2
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);
        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);
        boolean result2 = sameTree.isSameTree(p2, q2);
        System.out.println("\nTest Case 2:");
        System.out.println("Input: p = [1,2], q = [1,null,2]");
        System.out.println("Output: " + result2);

        // Test Case 3
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);
        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);
        boolean result3 = sameTree.isSameTree(p3, q3);
        System.out.println("\nTest Case 3:");
        System.out.println("Input: p = [1,2,1], q = [1,1,2]");
        System.out.println("Output: " + result3);
    }
}