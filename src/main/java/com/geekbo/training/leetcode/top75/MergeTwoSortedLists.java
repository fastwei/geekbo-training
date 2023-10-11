package com.geekbo.training.leetcode.top75;

import com.geekbo.training.leetcode.base.ListNode;

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 创建一个虚拟头节点，简化链表操作
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 遍历两个链表，比较节点值，将较小的节点接入新链表
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        // 处理剩余部分，如果有的话
        if (list1 != null) {
            current.next = list1;
        } else {
            current.next = list2;
        }

        // 返回合并后的链表，从虚拟头节点的下一个节点开始
        return dummy.next;
    }

    public static void main(String[] args) {
        // 创建链表1: 1 -> 2 -> 4
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        // 创建链表2: 1 -> 3 -> 4
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        MergeTwoSortedLists solution = new MergeTwoSortedLists();
        ListNode merged = solution.mergeTwoLists(list1, list2);

        // 遍历合并后的链表并输出结果
        while (merged != null) {
            System.out.print(merged.val + " -> ");
            merged = merged.next;
        }
        System.out.println("null");
    }
}