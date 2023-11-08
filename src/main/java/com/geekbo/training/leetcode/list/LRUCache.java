package com.geekbo.training.leetcode.list;

import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 *
 * 实现思路：
 *
 * 首先，创建一个双向链表和一个哈希表。
 * 双向链表用于维护缓存中每个元素的访问顺序，最近访问的元素放在链表头部，最久未访问的元素放在链表尾部。
 * 哈希表用于快速查找指定键的节点在双向链表中的位置。
 * 在get操作中，先通过哈希表查找指定键的节点，如果节点存在，则将该节点移动到链表头部，并返回对应的值。
 * 在put操作中，如果指定键已存在，则更新对应节点的值，并将该节点移动到链表头部。
 * 如果指定键不存在，则创建一个新节点，将其插入到链表头部，并将其添加到哈希表中。
 * 如果插入新节点后，缓存容量超过了限制，则需要移除链表尾部的节点，并从哈希表中删除对应的键。
 *
 * 算法复杂度：
 *
 * get和put操作都可以在O(1)的平均时间复杂度内完成，因为双向链表和哈希表的操作都是常数时间的。
 * 具体来说，get操作需要经过一次哈希表的查找和一次链表节点的移动操作。
 * put操作需要经过一次哈希表的查找、一次链表节点的插入或移动操作以及一次哈希表和链表节点的删除操作。
 * 空间复杂度为O(capacity)，其中capacity是缓存的容量。
 * 需要使用哈希表来存储缓存的键值对，以及双向链表来存储缓存的访问顺序。
 * 在最坏情况下，哈希表和双向链表都需要存储capacity个节点的信息。
 *
 */
public class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    /**
     * Least Recently Used (LRU)缓存
     *
     * @param capacity 缓存容量
     */
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 获取指定key的值
     *
     * @param key 键
     * @return 如果key存在则返回对应的值，否则返回-1
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            removeNode(node);
            addNodeToHead(node);
            return node.value;
        }
        return -1;
    }

    /**
     * 更新或添加键值对到缓存中
     *
     * @param key   键
     * @param value 值
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addNodeToHead(node);
        } else {
            Node newNode = new Node(key, value);
            if (map.size() == capacity) {
                Node tailNode = tail.prev;
                removeNode(tailNode);
                map.remove(tailNode.key);
            }
            addNodeToHead(newNode);
            map.put(key, newNode);
        }
    }

    /**
     * 移除指定节点
     *
     * @param node 要移除的节点
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 将节点添加到头部
     *
     * @param node 要添加的节点
     */
    private void addNodeToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3);
        System.out.println(cache.get(2)); // -1
        cache.put(4, 4);
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
    }
}