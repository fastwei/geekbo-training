package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2487. Remove Nodes From Linked List
 * Medium
 * You are given the head of a linked list.
 * <p>
 * Remove every node which has a node with a greater value anywhere to the right side of it.
 * <p>
 * Return the head of the modified linked list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [5,2,13,3,8]
 * Output: [13,8]
 * Explanation: The nodes that should be removed are 5, 2 and 3.
 * - Node 13 is to the right of node 5.
 * - Node 13 is to the right of node 2.
 * - Node 8 is to the right of node 3.
 * Example 2:
 * <p>
 * Input: head = [1,1,1,1]
 * Output: [1,1,1,1]
 * Explanation: Every node has value 1, so no nodes are removed.
 */
class RemoveNodesFromLinkedList {
    public ListNode removeNodes(ListNode head) {
        ListNode cur = head;
        List<ListNode> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int max = 0;
        int[] arr = new int[list.size()];
        //finding max from the end to include values into result
        for (int i = list.size() - 1; i >= 0; i--) {
            if (max <= list.get(i).val) {
                arr[i] = 1;
                max = list.get(i).val;
            }
        }
        boolean headSet = false;
        cur = head;
        //adding nodes to the result
        for (int i = 0; i < list.size(); i++) {
            if (arr[i] == 1) {
                if (!headSet) {
                    headSet = true;
                    head = list.get(i);
                    cur = head;
                } else {
                    cur.next = list.get(i);
                    cur = cur.next;
                }
            }
        }
        cur.next = null;
        return head;
    }

    public static void main(String[] args) {
        RemoveNodesFromLinkedList removeNodesFromLinkedList = new RemoveNodesFromLinkedList();

        // 创建链表，测试用例1
        ListNode head1 = new ListNode(5);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(13);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(8);
        head1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // 删除右侧有较大值的节点
        ListNode modifiedHead1 = removeNodesFromLinkedList.removeNodes(head1);

        // 遍历链表，输出结果
        ListNode cur1 = modifiedHead1;
        while (cur1 != null) {
            System.out.print(cur1.val + " ");
            cur1 = cur1.next;
        }
        System.out.println();

        // 创建链表，测试用例2
        ListNode head2 = new ListNode(1);
        ListNode node6 = new ListNode(1);
        ListNode node7 = new ListNode(1);
        ListNode node8 = new ListNode(1);
        head2.next = node6;
        node6.next = node7;
        node7.next = node8;

        // 删除右侧有较大值的节点
        ListNode modifiedHead2 = removeNodesFromLinkedList.removeNodes(head2);

        // 遍历链表，输出结果
        ListNode cur2 = modifiedHead2;
        while (cur2 != null) {
            System.out.print(cur2.val + " ");
            cur2 = cur2.next;
        }
        System.out.println();
    }
}
