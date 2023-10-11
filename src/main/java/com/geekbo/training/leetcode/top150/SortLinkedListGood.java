package com.geekbo.training.leetcode.top150;


import com.geekbo.training.leetcode.base.ListNode;

/**
 * Divide & Conquer
 *
 * 将链表排序在O(n log n)的时间复杂度和O(1)的空间复杂度内是一个经典的问题。
 * 通常，最有效的方法是使用归并排序（Merge Sort），但要实现O(1)的空间复杂度，需要一些技巧。
 *
 *解题思路：
 *
 * 使用自底向上的归并排序算法，迭代地将链表拆分、排序和合并。
 * 在每个步骤中，根据步长拆分链表，然后合并两个有序链表。
 * 重复以上步骤，直到完成排序。
 * 复杂度分析：
 *
 * 时间复杂度：O(n log n)，归并排序的时间复杂度。
 * 空间复杂度：O(1)，只需要常数级别的额外空间。
 *
 *
 */
public class SortLinkedListGood {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head; // 如果链表为空或只有一个节点，无需排序
        }
        
        // 使用自底向上的归并排序对链表进行排序
        int length = getLength(head);
        ListNode dummy = new ListNode();
        dummy.next = head;
        
        for (int step = 1; step < length; step *= 2) {
            ListNode prev = dummy;
            ListNode current = dummy.next;
            
            while (current != null) {
                ListNode left = current;
                ListNode right = split(left, step);
                current = split(right, step);
                prev = merge(left, right, prev);
            }
        }
        
        return dummy.next;
    }
    
    // 获取链表长度
    private int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }
    
    // 分割链表，返回后半部分的头节点
    private ListNode split(ListNode head, int step) {
        if (head == null) {
            return null;
        }
        for (int i = 1; head.next != null && i < step; i++) {
            head = head.next;
        }
        ListNode right = head.next;
        head.next = null;
        return right;
    }
    
    // 合并两个有序链表，返回合并后的头节点
    private ListNode merge(ListNode left, ListNode right, ListNode prev) {
        ListNode current = prev;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        current.next = (left != null) ? left : right;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    public static void main(String[] args) {
        // 创建示例输入的链表
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        // 调用排序方法
        SortLinkedListGood solution = new SortLinkedListGood();
        ListNode sortedHead = solution.sortList(head);

        // 打印排序后的链表
        while (sortedHead != null) {
            System.out.print(sortedHead.val + " ");
            sortedHead = sortedHead.next;
        }
    }
}
