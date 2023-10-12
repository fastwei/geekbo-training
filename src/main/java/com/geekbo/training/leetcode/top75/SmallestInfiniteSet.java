package com.geekbo.training.leetcode.top75;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * 2336. Smallest Number in Infinite Set
 *
 * You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].
 *
 * Implement the SmallestInfiniteSet class:
 *
 * SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
 * int popSmallest() Removes and returns the smallest integer contained in the infinite set.
 * void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
 *
 * 使用Java中的HashSet和PriorityQueue（堆）来实现SmallestInfiniteSet类的代码。
 * HashSet用于存储已存在的数字，PriorityQueue（堆）用于获取最小的数字。
 *
 * 这个SmallestInfiniteSet类可以初始化，添加数字，弹出最小的数字，如果没有可弹出的数字则返回-1。
 * 它使用HashSet来存储已存在的数字，使用PriorityQueue（堆）来维护最小的数字，确保始终可以获取最小的数字。
 *
 */
class SmallestInfiniteSet {
    private Set<Integer> numberSet;
    private PriorityQueue<Integer> minHeap;

    public SmallestInfiniteSet() {
        numberSet = new HashSet<>();
        minHeap = new PriorityQueue<>();
        for(int i = 1; i <= 1001; i++) {
            numberSet.add(i);
            minHeap.offer(i);
        }
    }

    public int popSmallest() {
        if (!minHeap.isEmpty()) {
            int smallest = minHeap.poll();
            numberSet.remove(smallest);
            return smallest;
        }
        return -1; // Return -1 if the set is empty
    }

    public void addBack(int num) {
        if (!numberSet.contains(num)) {
            numberSet.add(num);
            minHeap.offer(num);
        }
    }

    public static void main(String[] args) {
        SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
        smallestInfiniteSet.addBack(2);
        System.out.println(smallestInfiniteSet.popSmallest()); // 输出：1
        System.out.println(smallestInfiniteSet.popSmallest()); // 输出：2
        System.out.println(smallestInfiniteSet.popSmallest()); // 输出：3
        smallestInfiniteSet.addBack(1);
        System.out.println(smallestInfiniteSet.popSmallest()); // 输出：1
        System.out.println(smallestInfiniteSet.popSmallest()); // 输出：4
        System.out.println(smallestInfiniteSet.popSmallest()); // 输出：5
    }
}
