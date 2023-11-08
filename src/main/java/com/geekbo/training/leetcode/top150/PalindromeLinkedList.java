package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.list.ListNode;

/**
 * Given the head of a singly linked list, return true if it is a
 * palindrome
 * or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,2,1]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 9
 * <p>
 * <p>
 * Follow up: Could you do it in O(n) time and O(1) space?
 */

public class PalindromeLinkedList {
    /**
     * 解题思路：
     * 首先使用快慢指针找到链表的中间节点。
     * 然后将后半部分链表反转。
     * 最后比较前半部分链表和反转后的后半部分链表是否相等。
     * <p>
     * 算法的时间复杂度为O(n)，其中n是链表的长度。
     * 算法的空间复杂度为O(1)。
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        // 使用快慢指针找到链表的中间节点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转后半部分链表
        ListNode secondHalf = reverseLinkedList(slow.next);

        // 比较前半部分链表和反转后的后半部分链表是否相等
        ListNode p1 = head;
        ListNode p2 = secondHalf;
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    private static ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        // Test case 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);
        // 预期输出为true
        System.out.println(isPalindrome(head1));

        // Test case 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        // 预期输出为false
        System.out.println(isPalindrome(head2));
    }
}