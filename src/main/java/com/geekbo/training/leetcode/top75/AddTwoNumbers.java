package com.geekbo.training.leetcode.top75;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 * <p>
 * 将两个逆序链表相加的功能，以下是解题思路和算法复杂度分析：
 * <p>
 * 解题思路：
 * <p>
 * 创建一个虚拟头节点 dummyHead 和一个指针 current 用于构建结果链表。
 * 使用 carry 变量来跟踪进位情况，初始化为 0。
 * 遍历两个链表 l1 和 l2，同时模拟逐位相加的过程：
 * 取出 l1 和 l2 当前节点的值，如果其中一个链表已经遍历完，对应值设为 0。
 * 将 l1 和 l2 当前节点的值以及进位相加，计算出当前位的和 sum 和新的进位。
 * 创建一个新节点，值为 sum % 10，并将其连接到结果链表中。
 * 更新进位为 sum / 10。
 * 移动 l1 和 l2 指针到下一个节点。
 * 如果遍历完成后，仍然存在进位，创建一个新节点表示进位的值。
 * 返回结果链表的头节点。
 * 算法复杂度：
 * <p>
 * 时间复杂度：O(max(n, m))，其中 n 和 m 分别是两个链表的长度，因为我们需要遍历两个链表一次。
 * 空间复杂度：O(max(n, m))，除了存储结果链表外，不需要额外的空间。
 * 优化空间：
 * 可以在不使用虚拟头节点的情况下实现该功能。
 * 在代码中，虚拟头节点 dummyHead 用于简化对结果链表的操作，但如果要优化空间，可以不使用虚拟头节点，并直接创建新节点，然后使用一个变量来跟踪结果链表的头节点。
 * 这需要更多的特殊处理，但可以减小空间复杂度。
 */

class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = x + y + carry;
            carry = sum / 10;

            current.next = new ListNode(sum % 10);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry > 0) {
            current.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        // 测试用例1
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result1 = addTwoNumbers(l1, l2);
        printList(result1); // 7 -> 0 -> 8

        // 测试用例2
        ListNode l3 = new ListNode(0);
        ListNode l4 = new ListNode(0);

        ListNode result2 = addTwoNumbers(l3, l4);
        printList(result2); // 0

        // 测试用例3
        ListNode l5 = new ListNode(9);
        l5.next = new ListNode(9);
        l5.next.next = new ListNode(9);
        l5.next.next.next = new ListNode(9);
        l5.next.next.next.next = new ListNode(9);
        l5.next.next.next.next.next = new ListNode(9);
        l5.next.next.next.next.next.next = new ListNode(9);

        ListNode l6 = new ListNode(9);
        l6.next = new ListNode(9);
        l6.next.next = new ListNode(9);
        l6.next.next.next = new ListNode(9);

        ListNode result3 = addTwoNumbers(l5, l6);
        printList(result3); // 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
    }

    // 打印链表
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
