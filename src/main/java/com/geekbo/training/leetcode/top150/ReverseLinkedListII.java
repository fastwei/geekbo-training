package com.geekbo.training.leetcode.top150;

/**
 *
 * Given the head of a singly linked list and two integers left and right where left <= right,
 * reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 *
 */
public class ReverseLinkedListII {

    /**
     * 解题思路：
     * <p>
     * 首先定位到左边界的前一个节点和右边界的节点。
     * 截取需要反转的链表段，将其断开。
     * 反转链表段。
     * 将反转后的链表段与原链表连接起来。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历链表需要O(n)的时间，其中n为链表的长度。
     * 定位到左边界的前一个节点需要遍历left-1次，定位到右边界的节点需要遍历right次，因此总体时间复杂度为O(n)。
     * 空间复杂度：使用了常数级别的额外空间。只使用了几个额外的指针变量来存储节点，所以空间复杂度为O(1)。
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 创建一个虚拟头节点，用于处理左边界为1的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 找到左边界的前一个节点
        ListNode pre = dummy;
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }

        // 定位到左边界节点和右边界节点
        ListNode leftNode = pre.next;
        ListNode rightNode = leftNode;
        for (int i = left; i < right; i++) {
            rightNode = rightNode.next;
        }

        // 截取需要反转的链表段
        ListNode nextNode = rightNode.next;
        rightNode.next = null;

        // 反转链表段
        reverseList(leftNode);

        // 将反转后的链表段与原链表连接起来
        pre.next = rightNode;
        leftNode.next = nextNode;

        return dummy.next;
    }

    private void reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
    }

    public static void main(String[] args) {
        ReverseLinkedListII solution = new ReverseLinkedListII();

        // Test Case 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        int left1 = 2;
        int right1 = 4;
        ListNode result1 = solution.reverseBetween(head1, left1, right1);
        ListNode expected1 = new ListNode(1);
        expected1.next = new ListNode(4);
        expected1.next.next = new ListNode(3);
        expected1.next.next.next = new ListNode(2);
        expected1.next.next.next.next = new ListNode(5);
        System.out.println(isSameList(result1, expected1)); // true

        // Test Case 2
        ListNode head2 = new ListNode(5);
        int left2 = 1;
        int right2 = 1;
        ListNode result2 = solution.reverseBetween(head2, left2, right2);
        ListNode expected2 = new ListNode(5);
        System.out.println(isSameList(result2, expected2)); // true
    }

    private static boolean isSameList(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == null && l2 == null;
    }
}