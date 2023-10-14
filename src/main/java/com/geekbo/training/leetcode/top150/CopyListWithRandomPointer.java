package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 * <p>
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes,
 * where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list
 * such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 * <p>
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y,
 * then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * <p>
 * Return the head of the copied linked list.
 * <p>
 * The linked list is represented in the input/output as a list of n nodes.
 * Each node is represented as a pair of [val, random_index] where:
 * <p>
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to,
 * or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * Example 3:
 * <p>
 * <p>
 * <p>
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 */
public class CopyListWithRandomPointer {
    /**
     * 复制带随机指针的链表
     * <p>
     * 实现思路：
     * <p>
     * 首先创建一个哈希表，用于存储原始节点和复制节点之间的映射关系。
     * 然后遍历原始链表，创建复制节点，并设置复制节点的值和next指针。
     * 在创建复制节点的过程中，将原始节点和复制节点的映射关系存储到哈希表中。
     * 最后再次遍历原始链表，根据原始节点的random指针，设置复制节点的random指针，
     * 同时根据哈希表的映射关系来获取对应的复制节点。
     * 返回复制链表的头结点。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N)，其中N是链表的长度。需要遍历两次链表，分别用于创建复制节点和设置random指针。
     * 空间复杂度：O(N)，需要使用哈希表来存储原始节点和复制节点之间的映射关系，最坏情况下需要存储所有的节点。
     *
     * @param head 原始链表的头结点
     * @return 复制链表的头结点
     */
    public NodeRandom copyRandomList(NodeRandom head) {
        if (head == null) {
            return null;
        }

        // 创建一个哈希表，用于存储原始节点和复制节点之间的映射关系
        Map<NodeRandom, NodeRandom> map = new HashMap<>();

        // 创建一个新链表，复制原始链表的值
        NodeRandom newHead = new NodeRandom(head.val);
        NodeRandom curr = newHead;
        NodeRandom oldCurr = head;
        map.put(oldCurr, curr);

        // 遍历原始链表，创建复制节点，并设置复制节点的next指针和random指针
        while (oldCurr != null) {
            curr.next = getOrCreateCopy(map, oldCurr.next);
            curr.random = getOrCreateCopy(map, oldCurr.random);
            oldCurr = oldCurr.next;
            curr = curr.next;
        }

        return newHead;
    }

    /**
     * 从哈希表中获取复制节点，如果不存在则创建新的复制节点
     *
     * @param map      哈希表，存储原始节点和复制节点之间的映射关系
     * @param Node 原始节点
     * @return 复制节点
     */
    private NodeRandom getOrCreateCopy(Map<NodeRandom, NodeRandom> map, NodeRandom Node) {
        if (Node == null) {
            return null;
        }

        if (map.containsKey(Node)) {
            return map.get(Node);
        }

        NodeRandom copy = new NodeRandom(Node.val);
        map.put(Node, copy);
        return copy;
    }

    public static void main(String[] args) {
        CopyListWithRandomPointer solution = new CopyListWithRandomPointer();

        // 测试用例
        // 创建原始链表
        NodeRandom Node1 = new NodeRandom(7);
        Node1.next = new NodeRandom(13);
        Node1.next.next = new NodeRandom(11);
        Node1.next.next.next = new NodeRandom(10);
        Node1.next.next.next.next = new NodeRandom(1);
        Node1.random = null;
        Node1.next.random = Node1;
        Node1.next.next.random = Node1.next.next.next.next;
        Node1.next.next.next.random = Node1.next.next;
        Node1.next.next.next.next.random = Node1;
        // 复制链表
        NodeRandom result1 = solution.copyRandomList(Node1);
        printLinkedList(result1); // [[7,null],[13,0],[11,4],[10,2],[1,0]]

        NodeRandom Node2 = new NodeRandom(1);
        Node2.next = new NodeRandom(2);
        Node2.random = Node2.next;
        Node2.next.random = Node2.next;
        NodeRandom result2 = solution.copyRandomList(Node2);
        printLinkedList(result2); // [[1,1],[2,1]]

        NodeRandom Node3 = new NodeRandom(3);
        Node3.next = new NodeRandom(3);
        Node3.next.next = new NodeRandom(3);
        Node3.random = null;
        Node3.next.random = Node3;
        Node3.next.next.random = null;
        NodeRandom result3 = solution.copyRandomList(Node3);
        printLinkedList(result3); //
    }

    private static void printLinkedList(NodeRandom result1) {
        NodeRandom curr = result1;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
}
class NodeRandom {
    int val;
    NodeRandom next;

    NodeRandom random;

    NodeRandom(int val) {
        this.val = val;
    }
}