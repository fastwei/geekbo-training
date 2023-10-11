package com.geekbo.training.leetcode.top150.hard;

/**
 *
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * 题目描述:
 * 给定一个链表的头结点 head，将该链表每 k 个节点一组反转，并返回修改后的链表。
 * 如果节点总数不是 k 的整数倍，则保持最后剩余的节点原样排列。
 * 注意：只能修改节点本身，不能修改节点的值。
 *
 * 示例:
 * 输入: head = [1,2,3,4,5], k = 2
 * 输出: [2,1,4,3,5]
 *
 * 输入: head = [1,2,3,4,5], k = 3
 * 输出: [3,2,1,4,5]
 *
 *
 * 解题思路：
 *
 * 创建虚拟头结点dummy，以简化边界条件处理。
 * 计算链表长度，将其存储在count中。
 * 使用循环，每次反转k个节点的组，直到剩余节点数量小于k。
 * 在每个组内，采用反转链表的方法进行反转。
 * 连接前一组的尾部与当前组的头部，以及当前组的尾部与下一组的头部。
 * 更新count，重复执行步骤3和4，直到整个链表被反转。
 * 返回反转后的链表。
 * 算法复杂度分析：
 *
 * 时间复杂度：O(n)，其中n是链表的长度。
 * 空间复杂度：O(1)。
 *
 * todo:需要花时间加深理解
 *
 */

public class ReverseNodesInKGroup {
    // 定义链表节点类
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        // 创建虚拟头结点，简化边界条件处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupTail = dummy;
        ListNode currGroupHead = head;
        int count = 0;

        // 计算链表长度
        while (head != null) {
            count++;
            head = head.next;
        }

        // 开始反转每个k个节点的组
        while (count >= k) {
            ListNode groupPrev = null;
            ListNode groupCurr = currGroupHead;

            for (int i = 0; i < k; i++) {
                ListNode next = groupCurr.next;
                groupCurr.next = groupPrev;
                groupPrev = groupCurr;
                groupCurr = next;
            }

            prevGroupTail.next = groupPrev;
            currGroupHead.next = groupCurr;

            prevGroupTail = currGroupHead;
            currGroupHead = groupCurr;
            count -= k;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // 创建测试用例1：链表 [1,2,3,4,5]
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);

        // 测试用例1，k=2
        int k1 = 2;
        ListNode result1 = reverseKGroup(head1, k1);

        // 打印测试用例1的结果
        System.out.print("Test Case 1: ");
        while (result1 != null) {
            System.out.print(result1.val + " ");
            result1 = result1.next;
        }
        System.out.println();

        // 创建测试用例2：链表 [1,2,3,4,5]
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);

        // 测试用例2，k=3
        int k2 = 3;
        ListNode result2 = reverseKGroup(head2, k2);

        // 打印测试用例2的结果
        System.out.print("Test Case 2: ");
        while (result2 != null) {
            System.out.print(result2.val + " ");
            result2 = result2.next;
        }
        System.out.println();
    }
}
