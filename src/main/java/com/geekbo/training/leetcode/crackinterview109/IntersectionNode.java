package com.geekbo.training.leetcode.crackinterview109;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
 * 如果两个链表没有交点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 * <p>
 * 题目数据 保证 整个链式结构中不存在环。
 * <p>
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 */
public class IntersectionNode {
    /**
     * 解题思路：
     * <p>
     * 创建两个指针pA和pB，分别指向链表headA和headB的头节点。
     * 同时遍历两个链表，当pA到达链表末尾时，将其指针指向headB，当pB到达链表末尾时，将其指针指向headA。
     * 当两个指针相遇时，说明有相交节点，或者两个链表都为空。
     * 返回相交节点pA或pB。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(m + n)，其中m和n分别为两个链表的长度。最坏情况下，需要遍历两个链表的所有节点。
     * 空间复杂度：O(1)，只使用了常数级别的额外空间。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA;
        ListNode pB = headB;

        // 如果两个指针相遇，则说明有相交节点，或者两个链表都为空
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA;
    }

    public static void main(String[] args) {
        // 创建链表A：4->1->8->4->5
        ListNode nodeA1 = new ListNode(4);
        ListNode nodeA2 = new ListNode(1);
        ListNode nodeA3 = new ListNode(8);
        ListNode nodeA4 = new ListNode(4);
        ListNode nodeA5 = new ListNode(5);
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;
        nodeA3.next = nodeA4;
        nodeA4.next = nodeA5;

        // 创建链表B：5->0->1->8->4->5
        ListNode nodeB1 = new ListNode(5);
        ListNode nodeB2 = new ListNode(0);
        nodeB1.next = nodeB2;
        nodeB2.next = nodeA3; // 相交节点

        // 找到相交节点
        ListNode intersectionNode = new IntersectionNode().getIntersectionNode(nodeA1, nodeB1);

        // 打印相交节点的值
        System.out.println(intersectionNode != null ? "Intersected at '" + intersectionNode.val + "'" : "No intersection");
    }
}