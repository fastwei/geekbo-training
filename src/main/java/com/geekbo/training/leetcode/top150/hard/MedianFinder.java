package com.geekbo.training.leetcode.top150.hard;

import java.util.PriorityQueue;

public class MedianFinder {
    // 使用两个堆来存储数据，一个最大堆用于存储较小的一半数据，一个最小堆用于存储较大的一半数据
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    /**
     *
     * 解题思路：
     *
     * 使用两个堆来存储数据，一个最大堆用于存储较小的一半数据，一个最小堆用于存储较大的一半数据。
     * 当有新的数加入时，根据数的大小，将数加入到最大堆或最小堆中，然后平衡两个堆的大小。
     * 当两个堆的大小相等时，中位数为两个堆顶元素的平均数；当两个堆的大小不相等时，中位数为最大堆的堆顶元素。
     * 通过这种方式，可以在O(1)时间内找到中位数，同时添加和查找操作的时间复杂度为O(log n)。
     * 算法复杂度分析：
     *
     * 添加操作的时间复杂度为O(log n)。
     * 查找操作的时间复杂度为O(1)。
     * 空间复杂度为O(n)，其中n是数据流中的元素个数。
     *
     */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a); // 最大堆，存储较小的一半数据
        minHeap = new PriorityQueue<>(); // 最小堆，存储较大的一半数据
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // 平衡两个堆的大小
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian()); // Expected: 1.5
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian()); // Expected: 2.0
    }
}
