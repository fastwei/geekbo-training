package com.geekbo.training.leetcode.daily;

import java.util.Stack;

/**
 * 430. 扁平化多级双向链表
 * 中等
 * <p>
 * 你会得到一个双链表，其中包含的节点有一个下一个指针、一个前一个指针和一个额外的 子指针 。
 * 这个子指针可能指向一个单独的双向链表，也包含这些特殊的节点。这些子列表可以有一个或多个自己的子列表，以此类推，以生成如下面的示例所示的 多层数据结构 。
 * <p>
 * 给定链表的头节点 head ，将链表 扁平化 ，以便所有节点都出现在单层双链表中。让 curr 是一个带有子列表的节点。
 * 子列表中的节点应该出现在扁平化列表中的 curr 之后 和 curr.next 之前 。
 * <p>
 * 返回 扁平列表的 head 。列表中的节点必须将其 所有 子指针设置为 null 。
 */
public class FlattenMultilevelDoublyLinkedList {
    /**
     * 节点类
     */
    static class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * 扁平化多级双向链表
     *
     * @param head 多级双向链表的头节点
     * @return 扁平化后的链表的头节点
     */
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }

        Node dummy = new Node(0);
        dummy.next = head;

        Node curr = head;
        Node prev = dummy;

        Stack<Node> stack = new Stack<>();

        while (curr != null) {
            if (curr.child != null) {
                if (curr.next != null) {
                    stack.push(curr.next);
                }
                curr.next = curr.child;
                curr.next.prev = curr;
                curr.child = null;
            }

            prev = curr;
            curr = curr.next;

            if (curr == null && !stack.isEmpty()) {
                curr = stack.pop();
                prev.next = curr;
                curr.prev = prev;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // 创建多级双向链表
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);

        head.next = node2;
        node2.prev = head;

        node2.next = node3;
        node3.prev = node2;

        node3.next = node4;
        node4.prev = node3;

        node4.next = node5;
        node5.prev = node4;

        node5.next = node6;
        node6.prev = node5;

        node3.child = node7;
        node7.next = node8;
        node8.prev = node7;

        node8.next = node9;
        node9.prev = node8;

        node9.next = node10;
        node10.prev = node9;

        node8.child = node11;
        node11.next = node12;
        node12.prev = node11;

        // 测试扁平化多级双向链表方法
        FlattenMultilevelDoublyLinkedList solution = new FlattenMultilevelDoublyLinkedList();
        Node flattened = solution.flatten(head);

        // 打印扁平化后的链表
        Node node = flattened;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}