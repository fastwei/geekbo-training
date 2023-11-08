package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.list.ListNode;

/**
 *
 * Given the head of a linked list and a value x,
 * partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 *
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 *
 */
public class PartitionList {
    /**
     * 解题思路：
     * <p>
     * 使用两个指针before和after来分别记录小于x的节点和大于等于x的节点。
     * 创建两个哑节点beforeHead和afterHead，并将before指向beforeHead，after指向afterHead。
     * 遍历链表，当节点的值小于x时，将其添加到before链表中，否则将其添加到after链表中。
     * 遍历完整个链表后，将after链表的最后一个节点的next指针置为空。
     * 将before链表的最后一个节点的next指针指向afterHead的下一个节点，即连接两个链表。
     * 返回beforeHead的下一个节点，即为分隔后的链表。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历一次链表需要O(N)的时间，其中N为链表的长度。
     * 空间复杂度：只使用了常数级的额外空间
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode beforeHead = new ListNode(0); // 哑节点，用于存储小于x的节点
        ListNode before = beforeHead; // 指向before链表的尾节点
        ListNode afterHead = new ListNode(0); // 哑节点，用于存储大于等于x的节点
        ListNode after = afterHead; // 指向after链表的尾节点

        while (head != null) {
            if (head.val < x) { // 当节点的值小于x时，将其添加到before链表中
                before.next = head;
                before = before.next;
            } else { // 当节点的值大于等于x时，将其添加到after链表中
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }

        after.next = null; // 将after链表的最后一个节点的next指针置为空
        before.next = afterHead.next; // 将before链表的最后一个节点的next指针指向afterHead的下一个节点，即连接两个链表

        return beforeHead.next; // 返回beforeHead的下一个节点，即为分隔后的链表
    }

    public static void main(String[] args) {
        PartitionList solution = new PartitionList();

        // Test Case 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(2);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(2);
        int x1 = 3;
        ListNode result1 = solution.partition(head1, x1);
        ListNode expected1 = new ListNode(1);
        expected1.next = new ListNode(2);
        expected1.next.next = new ListNode(2);
        expected1.next.next.next = new ListNode(4);
        expected1.next.next.next.next = new ListNode(3);
        expected1.next.next.next.next.next = new ListNode(5);
        System.out.println(isEqual(result1, expected1)); // true

        // Test Case 2
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(1);
        int x2 = 2;
        ListNode result2 = solution.partition(head2, x2);
        ListNode expected2 = new ListNode(1);
        expected2.next = new ListNode(2);
        System.out.println(isEqual(result2, expected2)); // true
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