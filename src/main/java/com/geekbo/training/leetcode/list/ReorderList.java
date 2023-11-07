package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

/**
 * 143. Reorder List
 * Medium
 * You are given the head of a singly linked-list. The list can be represented as:
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 */
class ReorderList {
    /**
     * 这个问题的解题思路是先找到链表的中间节点，然后将链表的后半部分反转，最后将前半部分和反转后的后半部分进行合并。
     * <p>
     * 这个算法的时间复杂度是O(N)，其中N是链表的长度。空间复杂度是O(1)。
     * 请确保你已经导入了 ListNode 类，并且链表的每个节点的 next 指针在连接之前已经初始化为相应的节点。
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        // Find the middle of the linked list
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half of the linked list
        ListNode prev = null;
        ListNode curr = slow.next;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        slow.next = null;

        // Merge the two halves of the linked list
        ListNode first = head;
        ListNode second = prev;
        while (second != null) {
            ListNode next1 = first.next;
            ListNode next2 = second.next;
            first.next = second;
            second.next = next1;
            first = next1;
            second = next2;
        }
    }
}

