package com.geekbo.training.leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 432. All O`one Data Structure
 * Hard
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.
 * <p>
 * Implement the AllOne class:
 * <p>
 * AllOne() Initializes the object of the data structure.
 * inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
 * dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 * getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 * getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 * Output
 * [null, null, null, "hello", "hello", null, "hello", "leet"]
 * <p>
 * Explanation
 * AllOne allOne = new AllOne();
 * allOne.inc("hello");
 * allOne.inc("hello");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "hello"
 * allOne.inc("leet");
 * allOne.getMaxKey(); // return "hello"
 * allOne.getMinKey(); // return "leet"
 */
public class AllOne {
    static class Node {
        String key;
        int count;
        Node pre;
        Node next;

        public Node(String key, int count) {
            this.key = key;
            this.count = count;
        }
    }

    private Node head = null;
    private Node tail = null;
    private Map<String, Node> map = new HashMap<>();

    public AllOne() {

    }

    public void inc(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.count++;
            moveUp(node);
        } else {
            Node node = new Node(key, 1);
            add(node);
            map.put(key, node);
        }
    }

    public void dec(String key) {
        Node node = map.get(key);
        if (node.count == 1) {
            map.remove(key);
            remove(node);
        } else {
            node.count--;
            moveDown(node);
        }
    }

    public String getMaxKey() {
        if (map.isEmpty())
            return "";
        return tail.key;
    }

    public String getMinKey() {
        if (map.isEmpty())
            return "";
        return head.key;
    }

    void add(Node n) {
        assert n.count == 1;
        if (head == null) {
            head = n;
            tail = n;
        } else {
            n.next = head;
            head.pre = n;
            head = n;
        }
    }

    void remove(Node n) {
        if (n == head && n == tail) {
            head = null;
            tail = null;
            return;
        }
        Node p = head;
        while (p != n) {
            p = p.next;
        }
        if (p == head) {
            head = p.next;
            head.pre = null;
        } else {
            if (p == tail) {
                tail = tail.pre;
                tail.next = null;
            } else {
                p.pre.next = p.next;
                p.next.pre = p.pre;
            }
        }
    }

    void moveUp(Node n) {
        Node p = n.next;
        while (p != null && p.count < n.count) {
            p = p.next;
        }
        if (p != n.next) {
            if (n == head) {
                head = n.next;
            } else {
                n.pre.next = n.next;
            }
            n.next.pre = n.pre;

            n.next = p;
            if (p == null) {
                n.pre = tail;
                tail.next = n;
                tail = n;
            } else {
                n.pre = p.pre;
                p.pre.next = n;
                p.pre = n;
            }
        }
    }

    void moveDown(Node n) {
        Node p = n.pre;
        while (p != null && p.count > n.count) {
            p = p.pre;
        }
        if (p != n.pre) {
            if (n == tail) {
                tail = n.pre;
            } else {
                n.next.pre = n.pre;
            }
            n.pre.next = n.next;

            n.pre = p;
            if (p == null) {
                n.next = head;
                head.pre = n;
                head = n;
            } else {
                n.next = p.next;
                p.next.pre = n;
                p.next = n;
            }
        }
    }
}
