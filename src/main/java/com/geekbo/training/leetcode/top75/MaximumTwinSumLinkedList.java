package com.geekbo.training.leetcode.top75;

/**
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 * <p>
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
 * The twin sum is defined as the sum of a node and its twin.
 * <p>
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [5,4,2,1]
 * Output: 6
 * Explanation:
 * Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
 * There are no other nodes with twins in the linked list.
 * Thus, the maximum twin sum of the linked list is 6.
 * Example 2:
 * <p>
 * <p>
 * Input: head = [4,2,2,3]
 * Output: 7
 * Explanation:
 * The nodes with twins present in this linked list are:
 * - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
 * - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
 * Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
 * Example 3:
 * <p>
 * <p>
 * Input: head = [1,100000]
 * Output: 100001
 * Explanation:
 * There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
 * <p>
 * 题目描述：给定一个偶数长度的链表，其中每个节点的i和(n-1-i)是对应的“twin”节点，其中0 <= i <= (n/2)-1。
 * 求链表中“twin”节点的值之和的最大值。
 * <p>
 * 解题思路：
 * 1. 遍历链表，将链表的值存储在一个数组中。
 * 2. 对于链表长度为n，数组中前n/2个元素与后n/2个元素一一对应，即i与(n-1-i)是“twin”节点。
 * 3. 计算所有“twin”节点的值之和，并找出最大值。
 * <p>
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，需要遍历链表一次并遍历数组一次。
 * - 空间复杂度：O(n)，需要额外的数组来存储链表的值。
 */

public class MaximumTwinSumLinkedList {
    public int maxTwinSum(ListNode head) {
        // 边界条件：链表为空或只有一个节点
        if (head == null || head.next == null) {
            return 0;
        }

        // 遍历链表，将链表的值存储在数组中
        ListNode current = head;
        int length = 0;
        while (current != null) {
            current = current.next;
            length++;
        }
        int[] values = new int[length];
        current = head;
        int index = 0;
        while (current != null) {
            values[index] = current.val;
            current = current.next;
            index++;
        }

        // 计算“twin”节点的值之和的最大值
        int maxSum = 0;
        for (int i = 0; i < length / 2; i++) {
            maxSum = Math.max(maxSum, values[i] + values[length - 1 - i]);
        }

        return maxSum;
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
        MaximumTwinSumLinkedList solution = new MaximumTwinSumLinkedList();

        // 测试用例1
        MaximumTwinSumLinkedList.ListNode input1 = solution.new ListNode(5);
        input1.next = solution.new ListNode(4);
        input1.next.next = solution.new ListNode(2);
        input1.next.next.next = solution.new ListNode(1);

        int output1 = solution.maxTwinSum(input1);
        System.out.println("Output 1: " + output1); // 应输出6

        // 测试用例2
        MaximumTwinSumLinkedList.ListNode input2 = solution.new ListNode(4);
        input2.next = solution.new ListNode(2);
        input2.next.next = solution.new ListNode(2);
        input2.next.next.next = solution.new ListNode(3);

        int output2 = solution.maxTwinSum(input2);
        System.out.println("Output 2: " + output2); // 应输出7

        // 测试用例3
        MaximumTwinSumLinkedList.ListNode input3 = solution.new ListNode(1);
        input3.next = solution.new ListNode(100000);

        int output3 = solution.maxTwinSum(input3);
        System.out.println("Output 3: " + output3); // 应输出100001
    }

}
