package com.geekbo.training.leetcode.skill;

import com.geekbo.training.leetcode.base.ListNode;


public class RemoveDuplicatesFromSortedList {
    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。返回删除后的链表，该链表应当保持原始链表的顺序。
     *
     * 解题思路：
     *
     * 由于链表已经排序，因此重复元素会相邻出现。我们可以使用一个指针来遍历链表，如果当前节点的值和下一个节点的值相同，则跳过下一个节点，即删除重复元素。
     * 如果当前节点的值和下一个节点的值不同，则将指针指向下一个节点，继续遍历。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(n)，其中n是链表的长度。
     * 空间复杂度：O(1)。
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;
        while (current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList solution = new RemoveDuplicatesFromSortedList();

        // 测试用例1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(1);
        head1.next.next = new ListNode(2);
        ListNode expected1 = new ListNode(1);
        expected1.next = new ListNode(2);
        ListNode result1 = solution.deleteDuplicates(head1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(2);
        head2.next.next.next = new ListNode(3);
        head2.next.next.next.next = new ListNode(3);
        ListNode expected2 = new ListNode(1);
        expected2.next = new ListNode(2);
        expected2.next.next = new ListNode(3);
        ListNode result2 = solution.deleteDuplicates(head2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);
    }
}