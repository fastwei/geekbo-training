package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.TreeNode;

import java.util.Stack;

/**
 * 解题思路： 要实现BSTIterator类，以便对二叉搜索树（BST）进行中序遍历。
 * 在构造函数中，我们将接收BST的根节点，并初始化指针为一个不存在的比BST中任何元素都小的数字。
 * 然后，我们可以使用指针按照中序遍历的顺序遍历BST，通过调用next()方法移动指针并返回当前指针所指向的数字，
 * 通过调用hasNext()方法来检查指针右侧是否还有数字。
 * <p>
 * 具体实现时，可以利用一个栈来模拟中序遍历的过程。在构造函数中，将BST的最左侧路径上的所有节点压入栈中。
 * 然后，每次调用next()方法时，从栈中弹出一个节点并返回该节点的值，然后将其右子树的最左侧路径上的所有节点压入栈中。
 * hasNext()方法只需要判断栈是否为空即可。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 初始化BSTIterator对象的时间复杂度为O(h)，其中h表示BST的高度。
 * next()方法的平均时间复杂度为O(1)，最坏情况下为O(h)，因为每个节点最多被访问两次（一次入栈，一次出栈）。
 * hasNext()方法的时间复杂度为O(1)。
 * 空间复杂度为O(h)，其中h表示BST的高度，主要用于存储栈中的节点。
 */
class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeftNodes(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        TreeNode node = stack.pop();
        pushAllLeftNodes(node.right);
        return node.val;
    }

    private void pushAllLeftNodes(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator bstIterator = new BSTIterator(root);
        System.out.print("Output: ");
        while (bstIterator.hasNext()) {
            System.out.print(bstIterator.next() + " ");
        }
        System.out.println();
    }
}