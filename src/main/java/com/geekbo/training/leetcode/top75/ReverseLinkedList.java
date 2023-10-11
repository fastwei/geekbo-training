package com.geekbo.training.leetcode.top75;

/**
 *
 * 题目描述:
 * 给定一个单链表的头节点，反转该链表，并返回反转后的链表。
 * 
 * 示例:
 * 输入: head = [1,2,3,4,5]
 * 输出: [5,4,3,2,1]
 * 
 * 解题思路:
 * 使用三个指针prev、current和next来遍历和反转链表。
 * 1. 初始化prev为null，current为头节点head，next为null。
 * 2. 遍历链表，每次迭代都将current的next指向prev，然后prev和current分别向后移动一个位置。
 * 3. 最终，prev指向反转后的头节点。
 * 
 * 算法复杂度分析:
 * - 时间复杂度：O(n)，其中n是链表的长度，需要遍历整个链表。
 * - 空间复杂度：O(1)，只使用了常数额外空间。
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        // 初始化prev为null
        ListNode prev = null;
        // 初始化current为头节点
        ListNode current = head;
        
        while (current != null) {
            // 保存current的下一个节点
            ListNode next = current.next;
            // 反转current的next指向prev
            current.next = prev;
            // prev和current分别向后移动一个位置
            prev = current;
            current = next;
        }
        
        // prev指向反转后的头节点
        return prev;
    }

    /**
     * 反转链表的递归实现
     *
     * @param head 链表头节点
     * @return 反转后的链表头节点
     */
    public ListNode reverseListRecursive(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回该节点
        if (head == null || head.next == null) {
            return head;
        }

        // 递归调用，反转子链表
        ListNode reversed = reverseListRecursive(head.next);
        // 将当前节点的下一个节点的next指向当前节点，完成反转
        head.next.next = head;
        head.next = null;

        return reversed;
    }

    /**
     * 打印链表的通用方法
     *
     * @param head 链表头节点
     */
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
    // 测试用例
    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();
        
        // 示例1
        ListNode head1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode reversed1 = solution.reverseList(head1);
        // 输出: [5,4,3,2,1]
        printList(reversed1);
        
        // 示例2
        ListNode head2 = new ListNode(1, new ListNode(2));
        ListNode reversed2 = solution.reverseList(head2);
        // 输出: [2,1]
        printList(reversed2);
        
        // 示例3
        ListNode head3 = null;
        ListNode reversed3 = solution.reverseList(head3);
        // 输出: []
        printList(reversed3);
    }
}
