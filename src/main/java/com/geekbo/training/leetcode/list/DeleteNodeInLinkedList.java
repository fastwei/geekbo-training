package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

/**
 * 237. Delete Node in a Linked List
 * Medium
 * There is a singly-linked list head and we want to delete a node node in it.
 * <p>
 * You are given the node to be deleted node.
 * You will not be given access to the first node of head.
 * <p>
 * All the values of the linked list are unique,
 * and it is guaranteed that the given node node is not the last node in the linked list.
 * <p>
 * Delete the given node. Note that by deleting the node,
 * we do not mean removing it from memory. We mean:
 * <p>
 * The value of the given node should not exist in the linked list.
 * The number of nodes in the linked list should decrease by one.
 * All the values before node should be in the same order.
 * All the values after node should be in the same order.
 * Custom testing:
 * <p>
 * For the input, you should provide the entire linked list head and the node to be given node. node should not be the last node of the list and should be an actual node in the list.
 * We will build the linked list and pass the node to your function.
 * The output will be the entire list after calling your function.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [4,5,1,9], node = 5
 * Output: [4,1,9]
 * Explanation: You are given the second node with value 5,
 * the linked list should become 4 -> 1 -> 9 after calling your function.
 * Example 2:
 * <p>
 * <p>
 * Input: head = [4,5,1,9], node = 1
 * Output: [4,5,9]
 * Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
 */
class DeleteNodeInLinkedList {
    /**
     * 解题思路： 给定的是要删除的节点，而不是头节点。
     * 为了删除节点，我们可以将下一个节点的值复制到当前节点，然后将当前节点的 next 指针指向下下个节点，
     * 相当于跳过了当前节点，实现了删除操作。
     *
     * 算法复杂度：
     *
     * 时间复杂度：O(1)，因为只需要修改节点的值和指针，不需要遍历整个链表。
     * 空间复杂度：O(1)，只使用了常数级别的额外空间。
     */
    /**
     * 删除给定节点
     *
     * @param node 要删除的节点
     */
    public void deleteNode(ListNode node) {
        // 将下一个节点的值复制到当前节点
        node.val = node.next.val;
        // 跳过下一个节点，直接连接到下下个节点，实现删除当前节点
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        DeleteNodeInLinkedList deleteNodeInLinkedList = new DeleteNodeInLinkedList();

        // 创建链表，测试用例1
        ListNode head1 = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(9);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // 删除节点5
        deleteNodeInLinkedList.deleteNode(node2);

        // 遍历链表，输出结果
        ListNode cur1 = head1;
        while (cur1 != null) {
            System.out.print(cur1.val + " ");
            cur1 = cur1.next;
        }
        System.out.println();

        // 创建链表，测试用例2
        ListNode head2 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(1);
        ListNode node7 = new ListNode(9);
        head2.next = node5;
        node5.next = node6;
        node6.next = node7;

        // 删除节点1
        deleteNodeInLinkedList.deleteNode(node6);

        // 遍历链表，输出结果
        ListNode cur2 = head2;
        while (cur2 != null) {
            System.out.print(cur2.val + " ");
            cur2 = cur2.next;
        }
        System.out.println();
    }
}
