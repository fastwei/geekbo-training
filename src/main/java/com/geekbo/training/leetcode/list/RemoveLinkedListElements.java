package com.geekbo.training.leetcode.list;


import com.geekbo.training.leetcode.base.ListNode;

/**
 * 203. Remove Linked List Elements
 * Solved
 * Given the head of a linked list and an integer val,
 * remove all the nodes of the linked list that has Node.val == val, and return the new head.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 * Example 2:
 * <p>
 * Input: head = [], val = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [7,7,7,7], val = 7
 * Output: []
 */
public class RemoveLinkedListElements {
    ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null) {
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return dummy.next;
    }
}