package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.ListNode;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 */
public class RemoveDuplicatesFromSortedList {

    /**
     * 解题思路：
     * <p>
     * 使用双指针的方法来删除有重复数字的节点。
     * 创建一个虚拟头节点dummy，并将其next指针指向头节点。
     * 使用两个指针prev和curr分别指向当前节点的前一个节点和当前节点。
     * 遍历链表，当发现有重复数字时，将curr指针移动到下一个不重复的节点。
     * 判断prev的next指针是否仍然指向curr，如果是，则将prev指针向前移动一步；
     * 如果不是，则将prev的next指针指向curr的下一个节点。
     * 继续向前移动curr指针，直到遍历完整个链表。
     * 返回虚拟头节点dummy的下一个节点，即为去除重复节点后的链表。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历一次链表需要O(N)的时间，其中N为链表的长度。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
            }

            if (prev.next == curr) {
                prev = prev.next;
            } else {
                prev.next = curr.next;
            }

            curr = curr.next;
        }

        return dummy.next;
    }
    private static boolean isEqual(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == null && l2 == null;
    }
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList solution = new RemoveDuplicatesFromSortedList();

        // Test Case 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(4);
        head1.next.next.next.next.next = new ListNode(4);
        head1.next.next.next.next.next.next = new ListNode(5);
        ListNode result1 = solution.deleteDuplicates(head1);
        ListNode expected1 = new ListNode(1);
        expected1.next = new ListNode(2);
        expected1.next.next = new ListNode(5);
        System.out.println(isEqual(result1, expected1)); // true

        // Test Case 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(1);
        head2.next.next.next = new ListNode(2);
        head2.next.next.next.next = new ListNode(3);
        ListNode result2 = solution.deleteDuplicates(head2);
        ListNode expected2 = new ListNode(2);
        expected2.next = new ListNode(3);
        System.out.println(isEqual(result2, expected2)); // true
    }

    // 辅助方法：判断两个链表是否相等

}