package com.geekbo.training.leetcode.top75;

/**
 * 题目描述：将单链表中的奇数位置节点和偶数位置节点分别组合在一起，保持原有相对顺序。
 * 第一个节点视为奇数位置，第二个节点视为偶数位置，以此类推。
 * 必须在O(1)的额外空间复杂度和O(n)的时间复杂度内解决。
 * <p>
 * 解题思路：
 * 1. 首先判断链表是否为空或只有一个节点，如果是，直接返回原链表。
 * 2. 使用两个指针odd和even分别表示奇数位置和偶数位置的节点。
 * 3. 初始化evenHead为第一个偶数位置节点，以便后面将偶数位置节点连接到奇数位置节点后面。
 * 4. 遍历链表，依次将奇数位置节点和偶数位置节点分别串联起来。
 * 5. 最后，将奇数位置节点的末尾连接到偶数位置节点的开头，返回新链表的头部。
 * <p>
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中n是链表的节点数。我们需要遍历整个链表一次。
 * - 空间复杂度：O(1)，我们只使用了常数额外的空间。
 *
 */

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
        return head;
    }

    // 定义单链表节点
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        OddEvenLinkedList solution = new OddEvenLinkedList();

        // 测试用例1
        ListNode input1 = solution.new ListNode(1);
        input1.next = solution.new ListNode(2);
        input1.next.next = solution.new ListNode(3);
        input1.next.next.next = solution.new ListNode(4);
        input1.next.next.next.next = solution.new ListNode(5);

        ListNode output1 = solution.oddEvenList(input1);
        while (output1 != null) {
            System.out.print(output1.val + " ");
            output1 = output1.next;
        }
        System.out.println();

        // 测试用例2
        ListNode input2 = solution.new ListNode(2);
        input2.next = solution.new ListNode(1);
        input2.next.next = solution.new ListNode(3);
        input2.next.next.next = solution.new ListNode(5);
        input2.next.next.next.next = solution.new ListNode(6);
        input2.next.next.next.next.next = solution.new ListNode(4);
        input2.next.next.next.next.next.next = solution.new ListNode(7);

        ListNode output2 = solution.oddEvenList(input2);
        while (output2 != null) {
            System.out.print(output2.val + " ");
            output2 = output2.next;
        }
    }

}
