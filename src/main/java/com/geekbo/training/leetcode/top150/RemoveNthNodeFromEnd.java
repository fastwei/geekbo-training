package com.geekbo.training.leetcode.top150;


import com.geekbo.training.leetcode.base.ListNode;

public class RemoveNthNodeFromEnd {

    /**
     * 解题思路：
     * <p>
     * 使用快慢指针的思想来删除链表的倒数第n个节点。
     * 首先创建一个虚拟头节点dummy，并将其next指针指向头节点，这样可以方便处理头节点的删除情况。
     * 接下来，将快指针fast向前移动n+1步，使得快指针与慢指针之间相隔n个节点。
     * 然后，同时移动快指针和慢指针，直到快指针到达链表末尾。
     * 此时，慢指针的下一个节点就是要删除的倒数第n个节点，将其next指针指向下下个节点，即可删除倒数第n个节点。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历一次链表需要O(L)的时间，其中L为链表的长度。
     * 空间复杂度：只使用了常量级的额外空间，因此空间复杂度为O(1)。
     * <p>
     * 优化方法：
     * <p>
     * 可以使用双指针加上一个间隔变量的方法来优化。
     * 首先，将快指针fast向前移动n步，使得快指针与慢指针之间相隔n个节点。
     * 然后，同时移动快指针和慢指针，直到快指针到达链表末尾。
     * 此时，慢指针的下一个节点就是要删除的倒数第n个节点，将其next指针指向下下个节点，即可删除倒数第n个节点。
     * 这种方法只需遍历一次链表，不需要创建虚拟头节点，可以减少空间复杂度。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 创建一个虚拟头节点，方便处理头节点的删除情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        // 快指针先向前移动n+1步
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 快慢指针同时向前移动，直到快指针到达链表末尾
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 删除倒数第n个节点
        slow.next = slow.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEnd solution = new RemoveNthNodeFromEnd();

        // Test Case 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        int n1 = 2;
        ListNode result1 = solution.removeNthFromEnd(head1, n1);
        ListNode expected1 = new ListNode(1);
        expected1.next = new ListNode(2);
        expected1.next.next = new ListNode(3);
        expected1.next.next.next = new ListNode(5);
        System.out.println(isEqual(result1, expected1)); // true

        // Test Case 2
        ListNode head2 = new ListNode(1);
        int n2 = 1;
        ListNode result2 = solution.removeNthFromEnd(head2, n2);
        ListNode expected2 = null;
        System.out.println(isEqual(result2, expected2)); // true

        // Test Case 3
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        int n3 = 1;
        ListNode result3 = solution.removeNthFromEnd(head3, n3);
        ListNode expected3 = new ListNode(1);
        System.out.println(isEqual(result3, expected3)); // true
    }

    // 辅助方法：判断两个链表是否相等
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
}