package com.geekbo.training.leetcode.top75;

/**
 * 删除链表中间节点并返回修改后的链表头。
 * 
 * 链表大小为n时，中间节点是第⌊n/2⌋个节点，其中⌊x⌋表示不大于x的最大整数。
 * 
 * 示例：
 * Input: head = [1,3,4,7,1,2,6]
 * Output: [1,3,4,1,2,6]
 * 
 * 解题思路：
 * 使用快慢指针找到中间节点，然后删除中间节点。
 * 
 * 时间复杂度分析：
 * 快慢指针遍历一次链表，时间复杂度为O(n)。
 * 
 * 空间复杂度分析：
 * 仅使用常数额外空间，空间复杂度为O(1)。
 */
public class DeleteMiddleNode {
    public ListNode deleteMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return null; // 链表为空或只有一个节点，无需删除
        }
        
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        // 快慢指针寻找中间节点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        // 删除中间节点
        prev.next = slow.next;

        return head;
    }

    public static void main(String[] args) {
        DeleteMiddleNode solution = new DeleteMiddleNode();
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(4);
        head1.next.next.next = new ListNode(7);
        head1.next.next.next.next = new ListNode(1);
        head1.next.next.next.next.next = new ListNode(2);
        head1.next.next.next.next.next.next = new ListNode(6);
        ListNode result1 = solution.deleteMiddleNode(head1);

        // 输出链表
        while (result1 != null) {
            System.out.print(result1.val + " -> ");
            result1 = result1.next;
        }
        System.out.println("null");
    }
}
