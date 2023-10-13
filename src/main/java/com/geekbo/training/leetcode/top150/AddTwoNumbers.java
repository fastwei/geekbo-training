package com.geekbo.training.leetcode.top150;

import java.util.Stack;

/**
 *
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 * Example 2:
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [8,0,7]
 * Example 3:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 */
public class AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 解题思路：
     * <p>
     * 题目要求对两个非空链表进行相加，链表中的数字按照低位到高位的顺序表示，每个节点包含一个单个的数字。
     * 将两个数字相加后，返回一个新的链表来表示和。
     * 使用栈来对链表中的数字进行逆序处理。将两个链表中的数字按照低位到高位的顺序入栈。
     * 创建一个新的链表头节点 head，将结果从低位到高位逐位相加，并生成新的链表。
     * 使用进位变量 carry 来记录进位的值。
     * 如果两个链表都为空且进位为0，则相加结束。
     * 如果某个链表已经遍历完，但另一个链表还有剩余数字，则将剩余的数字与进位相加。
     * 将相加的结果取模得到当前位的值，将其创建为新的链表节点，并将其插入到新链表的头部。
     * 返回新链表的头节点。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(max(m, n))，其中 m 和 n 分别是两个链表的长度。需要遍历两个链表中较长的那个链表。
     * 空间复杂度：O(m + n)，需要使用两个
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        // 将链表中的数字按照低位到高位的顺序入栈
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0; // 进位
        ListNode head = null; // 结果链表的头节点

        // 从低位到高位逐位相加，生成新的链表
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int sum = carry;
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            carry = sum / 10; // 计算进位
            ListNode newNode = new ListNode(sum % 10); // 当前位的值
            newNode.next = head;
            head = newNode;
        }

        return head;
    }

    public static void main(String[] args) {
        // 测试用例
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}