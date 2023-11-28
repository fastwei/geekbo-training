package com.geekbo.training.leetcode.daily;

import java.util.LinkedList;

/**
 * 解题思路：
 * <p>
 * 使用一个LinkedList来实现队列，队列的头部表示前面，尾部表示后面。
 * pushFront方法直接将元素添加到LinkedList的头部。
 * pushMiddle方法找到中间位置，将元素插入到中间位置。
 * pushBack方法直接将元素添加到LinkedList的尾部。
 * popFront方法从LinkedList的头部移除并返回元素。
 * popMiddle方法找到中间位置，从LinkedList中移除并返回元素。
 * popBack方法从LinkedList的尾部移除并返回元素。
 * <p>
 * 算法复杂度：
 * <p>
 * pushFront、pushBack、popFront、popBack方法的时间复杂度都是O(1)，因为它们只涉及对LinkedList头部或尾部的操作，不需要遍历整个链表。
 * pushMiddle方法的时间复杂度是O(n)，其中n是队列的长度。因为它需要找到中间位置，而LinkedList的插入操作需要遍历链表，最坏情况下需要遍历n/2个节点。
 * popMiddle方法的时间复杂度是O(n)，因为它需要找到中间位置，然后从LinkedList中移除该节点，最坏情况下需要遍历n/2个节点。
 * 空间复杂度：
 * <p>
 * 使用了一个LinkedList来存储队列元素，所以空间复杂度是O(n)，其中n是队列的长度。
 */
class FrontMiddleBackQueue {
    private LinkedList<Integer> queue;

    public FrontMiddleBackQueue() {
        queue = new LinkedList<>();
    }

    /**
     * 将元素添加到队列的前面
     *
     * @param val 要添加的元素
     */
    public void pushFront(int val) {
        queue.addFirst(val);
    }

    /**
     * 将元素添加到队列的中间
     *
     * @param val 要添加的元素
     */
    public void pushMiddle(int val) {
        int mid = queue.size() / 2;
        queue.add(mid, val);
    }

    /**
     * 将元素添加到队列的末尾
     *
     * @param val 要添加的元素
     */
    public void pushBack(int val) {
        queue.addLast(val);
    }

    /**
     * 移除并返回队列前面的元素
     *
     * @return 队列前面的元素，如果队列为空则返回-1
     */
    public int popFront() {
        if (!queue.isEmpty()) {
            return queue.removeFirst();
        }
        return -1;
    }

    /**
     * 移除并返回队列中间的元素
     *
     * @return 队列中间的元素，如果队列为空则返回-1
     */
    public int popMiddle() {
        if (!queue.isEmpty()) {
            int mid = (queue.size() - 1) / 2;
            return queue.remove(mid);
        }
        return -1;
    }

    /**
     * 移除并返回队列末尾的元素
     *
     * @return 队列末尾的元素，如果队列为空则返回-1
     */
    public int popBack() {
        if (!queue.isEmpty()) {
            return queue.removeLast();
        }
        return -1;
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue queue = new FrontMiddleBackQueue();

        // Test case 1
        queue.pushFront(1);
        queue.pushBack(2);
        queue.pushMiddle(3);
        queue.pushMiddle(4);
        System.out.println(queue.popFront());    // Expected: 1
        System.out.println(queue.popMiddle());   // Expected: 3
        System.out.println(queue.popMiddle());   // Expected: 4
        System.out.println(queue.popBack());     // Expected: 2
        System.out.println(queue.popFront());    // Expected: -1

        // Test case 2
        queue.pushFront(10);
        queue.pushBack(20);
        System.out.println(queue.popMiddle());   // Expected: 10
        queue.pushMiddle(15);
        System.out.println(queue.popFront());    // Expected: 10
        System.out.println(queue.popBack());     // Expected: 20
    }
}