package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

/**
 * 1721. Swapping Nodes in a Linked List
 * Medium
 * You are given the head of a linked list, and an integer k.
 * <p>
 * Return the head of the linked list after swapping the values of the kth node
 * from the beginning and the kth node from the end (the list is 1-indexed).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 */
class SwappingNodesInLinkedList {
    /**
     * 解题思路：
     * <p>
     * 使用快慢指针来找到第k个节点和倒数第k个节点。
     * 定义两个指针slow和fast，初始时都指向虚拟头节点dummy。
     * 将fast指针向前移动k步，然后将slow和fast指针同时向前移动，直到fast指针到达链表的末尾。
     * 此时，slow指针指向第k个节点，fast指针指向倒数第k个节点。
     * 交换第k个节点和倒数第k个节点的值。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度是O(n)，其中n是链表的长度。需要遍历整个链表来找到第k个节点和倒数第k个节点。
     * 空间复杂度是O(1)，只需要使用常数级别的额外空间。
     * 这个实现可以在保持链表结构不变的情况下交换两个节点的值。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;

        // Move fast pointer k steps ahead
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        ListNode firstNode = fast;
        // Move slow and fast pointers together until fast reaches the end
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode secondNode = slow.next;
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;

        return dummy.next;
    }

    public static void main(String[] args) {
        // Create a linked list 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 2;

        SwappingNodesInLinkedList solution = new SwappingNodesInLinkedList();
        ListNode result = solution.swapNodes(head, k);

        // Print the result: 1->4->3->2->5
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}