package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

public class LinkedListRotation {
    // 实现原理：这是一个单链表的右旋转操作。我们首先计算链表的长度，
    // 然后调整旋转值以避免不必要的旋转。接下来，找到新的头部和尾部位置，
    // 并执行旋转操作。
    
    // 时间复杂度：O(n)，其中 n 是链表的长度。
    // 空间复杂度：O(1)。

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head; // 无需旋转
        }

        // 计算链表的长度
        int length = 1;
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
            length++;
        }

        // 调整旋转值以避免不必要的旋转
        k = k % length;
        if (k == 0) {
            return head; // 无需旋转
        }

        // 查找新的头和尾部位置
        int newTailPosition = length - k - 1;
        ListNode newTail = head;
        for (int i = 0; i < newTailPosition; i++) {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;

        // 执行旋转
        newTail.next = null;
        current.next = head;

        return newHead;
    }

    // 辅助方法，用于打印链表
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // 创建一个示例链表：1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2; // 将链表向右旋转2个位置
        ListNode rotatedHead = rotateRight(head, k);

        System.out.println("原始链表：");
        printList(head);

        System.out.println("旋转后的链表：");
        printList(rotatedHead);
    }
}
