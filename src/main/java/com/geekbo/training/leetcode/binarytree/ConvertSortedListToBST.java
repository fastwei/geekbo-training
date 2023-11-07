package com.geekbo.training.leetcode.binarytree;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

/**
 * 109. Convert Sorted List to Binary Search Tree
 * Medium
 * Topics
 * Companies
 * Given the head of a singly linked list where elements are sorted in ascending order,
 * convert it to a
 * height-balanced
 * binary search tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: One possible answer is [0,-3,9,-10,null,5],
 * which represents the shown height balanced BST.
 * Example 2:
 * <p>
 * Input: head = []
 * Output: []
 */
public class ConvertSortedListToBST {
    private ListNode curr;

    /**
     * 解题思路：
     * <p>
     * 我们可以使用递归的方式将有序链表转换为平衡二叉搜索树。
     * 首先，我们需要找到链表的中间节点，作为树的根节点。
     * 然后，我们将链表分为两部分，左半部分作为根节点的左子树，右半部分作为根节点的右子树。
     * 递归地处理左子链表和右子链表，得到左子树和右子树。
     * 最后，将根节点、左子树和右子树组合成一棵平衡二叉搜索树。
     * 算法的时间复杂度是 O(nlogn)，其中 n 是链表中的节点数。
     * 每次递归都需要遍历链表找到中间节点，递归的次数是 O(logn)，
     * 每次递归都需要遍历链表找到中间节点，所以总的时间复杂度是 O(nlogn)。
     * 空间复杂度是 O(logn)，递归调用的栈空间。
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        int size = findSize(head);
        curr = head;
        return convertToBST(0, size - 1);
    }

    private int findSize(ListNode head) {
        int size = 0;
        ListNode curr = head;
        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }

    private TreeNode convertToBST(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;

        TreeNode leftNode = convertToBST(left, mid - 1);

        TreeNode node = new TreeNode(curr.val);
        node.left = leftNode;

        curr = curr.next;

        node.right = convertToBST(mid + 1, right);

        return node;
    }

    public static void main(String[] args) {
        // 构造测试用例
        // 测试用例1: [-10,-3,0,5,9]
        ListNode head1 = new ListNode(-10);
        head1.next = new ListNode(-3);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(5);
        head1.next.next.next.next = new ListNode(9);

        // 测试用例2: []
        ListNode head2 = null;

        // 调用将有序链表转换为二叉搜索树的方法
        ConvertSortedListToBST solution = new ConvertSortedListToBST();
        TreeNode result1 = solution.sortedListToBST(head1);
        TreeNode result2 = solution.sortedListToBST(head2);

        // 打印结果
        System.out.println("测试用例1: " + result1);
        System.out.println("测试用例2: " + result2);
    }
}
