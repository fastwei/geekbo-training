package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

/**
 * 147. Insertion Sort List
 * Medium
 * Given the head of a singly linked list, sort the list using insertion sort,
 * and return the sorted list's head.
 * <p>
 * The steps of the insertion sort algorithm:
 * <p>
 * Insertion sort iterates, consuming one input element each repetition and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data,
 * finds the location it belongs within the sorted list and inserts it there.
 * It repeats until no input elements remain.
 * The following is a graphical example of the insertion sort algorithm.
 * The partially sorted list (black) initially contains only the first element in the list.
 * One element (red) is removed from the input data and inserted in-place into the sorted list with each iteration.
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [-1,5,3,4,0]
 * Output: [-1,0,3,4,5]
 */
class InsertionSortList {
    /**
     * 这个问题的解题思路是使用一个虚拟头节点 dummy，遍历原始链表并将每个节点插入到已排序的链表中的正确位置。
     * <p>
     * 这个算法的时间复杂度是O(N^2)，其中N是链表的长度。空间复杂度是O(1)。
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);

        while (head != null) {
            ListNode next = head.next;
            ListNode curr = dummy;

            // 找到当前节点应该插入的位置
            while (curr.next != null && curr.next.val < head.val) {
                curr = curr.next;
            }

            // 将当前节点插入到合适的位置
            head.next = curr.next;
            curr.next = head;

            head = next;
        }

        return dummy.next;
    }
}
