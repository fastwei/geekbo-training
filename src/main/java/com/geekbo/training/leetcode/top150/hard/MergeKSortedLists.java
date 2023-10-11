package com.geekbo.training.leetcode.top150.hard;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


/**
 *You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 *
 *
 * 解题思路：
 *
 * 使用优先队列（最小堆）来维护所有链表的当前最小值。
 * 遍历所有链表，将每个链表的元素加入最小堆。
 * 创建虚拟头结点和当前结点，从最小堆中取出元素构建有序链表。
 * 返回合并后的有序链表。
 * 复杂度分析：
 *
 * 时间复杂度：O(N * log(k))，其中 N 是所有链表的总节点数，k 是链表的数量。每次从最小堆中取出元素需要 log(k) 的时间。
 * 空间复杂度：O(k)，最小堆的大小取决于链表的数量。
 *
 *
 *Divide & Conquer
 *
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        // 使用优先队列（最小堆）来维护最小值
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // 遍历所有链表，将元素加入最小堆
        for (ListNode list : lists) {
            while (list != null) {
                minHeap.offer(list.val);
                list = list.next;
            }
        }
        
        // 创建虚拟头结点和当前结点
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        
        // 从最小堆中取出元素，构建有序链表
        while (!minHeap.isEmpty()) {
            current.next = new ListNode(minHeap.poll());
            current = current.next;
        }
        
        return dummy.next;
    }

    public static void main(String[] args) {
        // 创建示例输入的链表数组
        ListNode[] lists = new ListNode[3];
        lists[0] = new ListNode(1);
        lists[0].next = new ListNode(4);
        lists[0].next.next = new ListNode(5);
        lists[1] = new ListNode(1);
        lists[1].next = new ListNode(3);
        lists[1].next.next = new ListNode(4);
        lists[2] = new ListNode(2);
        lists[2].next = new ListNode(6);

        // 调用合并方法
        MergeKSortedLists solution = new MergeKSortedLists();
        ListNode result = solution.mergeKLists(lists);

        // 打印合并后的链表
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
