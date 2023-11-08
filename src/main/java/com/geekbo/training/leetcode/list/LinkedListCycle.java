package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;


/**
 *
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.®
 *
 * 我们使用快慢指针法来检测链表中是否存在循环。
 * 如果链表为空或只有一个节点，则必然没有循环。快指针每次移动两步，慢指针每次移动一步，如果存在循环，它们最终会相遇。
 * 通过检测是否相遇，我们可以确定链表是否有循环。
 *
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // 如果链表为空或只有一个节点，必然没有循环
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false; // 如果fast或fast的下一个节点为null，说明链表没有循环
            }
            slow = slow.next; // 慢指针每次移动一步
            fast = fast.next.next; // 快指针每次移动两步
        }

        return true; // 如果两个指针相遇，说明链表有循环
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // 创建测试用例1：有循环
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node0;
        node0.next = node4;
        node4.next = node2; // 与第2个节点形成循环
        int pos1 = 1;
        boolean result1 = solution.hasCycle(head1);
        System.out.println("Test Case 1: " + result1); // 预期输出：true

        // 创建测试用例2：有循环
        ListNode head2 = new ListNode(1);
        ListNode node22 = new ListNode(2);
        head2.next = node22;
        node22.next = head2; // 与第1个节点形成循环
        int pos2 = 0;
        boolean result2 = solution.hasCycle(head2);
        System.out.println("Test Case 2: " + result2); // 预期输出：true

        // 创建测试用例3：无循环
        ListNode head3 = new ListNode(1);
        int pos3 = -1;
        boolean result3 = solution.hasCycle(head3);
        System.out.println("Test Case 3: " + result3); // 预期输出：false
    }
}
