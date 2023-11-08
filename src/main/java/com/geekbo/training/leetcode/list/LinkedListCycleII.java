package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

/**
 *
 * 解题思路：
 * 使用快慢指针法找到链表中的相遇点，如果没有循环，两个指针不会相遇。
 * 如果有循环，重新从头开始，一个指针从相遇点继续前进，另一个指针从链表头开始前进，它们会在循环的起始点相遇。
 *
 */
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; // 如果链表为空或只有一个节点，必然没有循环
        }

        ListNode slow = head;
        ListNode fast = head;

        // 使用快慢指针找到相遇点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break; // 快慢指针相遇
            }
        }

        // 如果快指针到达链表末尾，说明没有循环
        if (fast == null || fast.next == null) {
            return null;
        }

        // 重新从头开始，一个指针从相遇点继续前进，一个指针从头开始前进
        // 两个指针相遇的点就是循环起始点
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // 返回循环起始点
    }

    public static void main(String[] args) {
        LinkedListCycleII solution = new LinkedListCycleII();

        // 创建测试用例1：有循环
        ListNode head1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node0 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        head1.next = node2;
        node2.next = node0;
        node0.next = node4;
        node4.next = node2; // 与第2个节点形成循环
        ListNode result1 = solution.detectCycle(head1);
        System.out.println("Test Case 1: tail connects to node index " + result1.val); // 预期输出：1

        // 创建测试用例2：有循环
        ListNode head2 = new ListNode(1);
        ListNode node22 = new ListNode(2);
        head2.next = node22;
        node22.next = head2; // 与第1个节点形成循环
        ListNode result2 = solution.detectCycle(head2);
        System.out.println("Test Case 2: tail connects to node index " + result2.val); // 预期输出：0

        // 创建测试用例3：无循环
        ListNode head3 = new ListNode(1);
        ListNode result3 = solution.detectCycle(head3);
        System.out.println("Test Case 3: " + result3); // 预期输出：null
    }

}
