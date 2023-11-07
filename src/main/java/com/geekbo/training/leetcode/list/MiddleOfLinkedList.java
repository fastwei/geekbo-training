package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

/**
 * 876. Middle of the Linked List
 * Easy
 * Given the head of a singly linked list, return the middle node of the linked list.
 * <p>
 * If there are two middle nodes, return the second middle node.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [3,4,5]
 * Explanation: The middle node of the list is node 3.
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5,6]
 * Output: [4,5,6]
 * Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.
 */
class MiddleOfLinkedList {
    /**
     * 解题思路：
     * <p>
     * 使用快慢指针找到链表的中间节点。
     * 快指针每次移动两步，慢指针每次移动一步。当快指针到达链表末尾时，慢指针指向的节点就是链表的中间节点。
     * 如果链表的长度为奇数，那么中间节点就是唯一的；如果链表的长度为偶数，那么有两个中间节点，返回第二个中间节点。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度为 O(n)，其中 n 是链表的长度。
     * 空间复杂度为 O(1)。
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        // 使用快慢指针找到链表的中间节点
        ListNode slow = head;
        ListNode fast = head;

        // 快指针每次移动两步，慢指针每次移动一步
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 返回慢指针所指向的节点作为中间节点
        return slow;
    }

    public static void main(String[] args) {
        MiddleOfLinkedList middleOfLinkedList = new MiddleOfLinkedList();

        // Create the linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode result1 = middleOfLinkedList.middleNode(head1);
        printList(result1); // Output: 3 -> 4 -> 5

        // Create the linked list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        ListNode head2 = new ListNode(1);
        ListNode node6 = new ListNode(6);
        node5.next = node6;

        ListNode result2 = middleOfLinkedList.middleNode(head2);
        printList(result2); // Output: 4 -> 5 -> 6
    }

    // Helper function to print the elements of the linked list
    private static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("null");
    }
}
