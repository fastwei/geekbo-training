package com.geekbo.training.leetcode.crackinterview109;

/**
 * 面试题 02.04. 分割链表
 * 中等
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你不需要 保留 每个分区中各节点的初始相对位置。
 */
class SplitList {
    /**
     * 目标是将链表中小于给定值x的节点放在大于或等于x的节点之前。
     * 可以使用两个指针，一个用于维护小于x的节点，另一个用于维护大于或等于x的节点。
     * 遍历链表，根据节点的值将节点添加到对应的指针后面。
     * 最后，将小于x的指针指向大于或等于x的指针的下一个节点，即完成了分割。
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode smallerHead = new ListNode(0);
        ListNode smallerTail = smallerHead;
        ListNode greaterOrEqualHead = new ListNode(0);
        ListNode greaterOrEqualTail = greaterOrEqualHead;

        while (head != null) {
            if (head.val < x) {
                smallerTail.next = head;
                smallerTail = smallerTail.next;
            } else {
                greaterOrEqualTail.next = head;
                greaterOrEqualTail = greaterOrEqualTail.next;
            }
            head = head.next;
        }

        greaterOrEqualTail.next = null;
        smallerTail.next = greaterOrEqualHead.next;

        return smallerHead.next;
    }
}