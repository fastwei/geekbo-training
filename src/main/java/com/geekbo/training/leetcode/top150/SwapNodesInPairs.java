package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.ListNode;

/**
 *
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes
 * (i.e., only nodes themselves may be changed.)
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 *
 *
 */
public class SwapNodesInPairs {
    /**
     * 解题思路：
     * 使用迭代的方式进行链表节点两两交换。
     * 首先定义一个哑节点dummy，将其指向链表的头节点head。
     * 然后定义两个指针prev和curr，分别指向哑节点和头节点。
     * 循环遍历链表，每次迭代交换curr和curr.next两个节点。
     * 迭代的终止条件是curr为null或者curr.next为null。
     * 在每次迭代中，需要更新prev、curr以及curr.next的指向。
     * 最后返回dummy.next作为交换后的链表头节点。
     *
     * 算法的时间复杂度为O(n)，其中n是链表的长度。
     *
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null && curr.next != null) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = curr;
            prev.next = next;
            prev = curr;
            curr = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Test case 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        // 预期输出为[2,1,4,3]
        ListNode result1 = swapPairs(head1);
        printList(result1);

        // Test case 2
        ListNode head2 = null;
        // 预期输出为[]
        ListNode result2 = swapPairs(head2);
        printList(result2);

        // Test case 3
        ListNode head3 = new ListNode(1);
        // 预期输出为[1]
        ListNode result3 = swapPairs(head3);
        printList(result3);
    }

    // Helper method to print the linked list
    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
