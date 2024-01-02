package com.geekbo.training.leetcode.classic119;

import java.util.PriorityQueue;

/**
 *
 * 703. Kth Largest Element in a Stream
 * Easy
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Implement KthLargest class:
 *
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
 * int add(int val) Appends the integer val to the stream and returns the element representing the kth largest element in the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * Output
 * [null, 4, 5, 5, 8, 8]
 *
 * Explanation
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 *
 * Constraints:
 *
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * At most 104 calls will be made to add.
 * It is guaranteed that there will be at least k elements in the array when you search for the kth element.
 *
 */
class KthLargest {
    private PriorityQueue<Integer> pq;
    private int k;

    /**
     *
     * 解题思路：
     *
     * 使用优先队列（堆）来存储最大的k个元素。
     * 在初始化时，将前k个元素加入优先队列。
     * 当有新元素加入时，如果队列中元素不满k个，则直接加入队列；
     *         如果队列中元素已满k个，且新元素比队列中最小的元素大，则移除队列中最小元素，将新元素加入队列。
     * 每次调用add方法时，返回队列中最小的元素，即第k大的元素。
     *
     * 算法复杂度分析：
     *
     * 初始化时，需要将前k个元素加入优先队列，时间复杂度为O(klogk)。
     * 每次调用add方法时，在队列中插入或删除元素的时间复杂度为O(logk)。
     * 因此，总体的时间复杂度为O(nlogk)，其中n为调用add方法的次数。
     *
     * @param k
     * @param nums
     */
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        if (pq.size() < k) {
            pq.offer(val);
        } else if (val > pq.peek()) {
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));   // return 4
        System.out.println(kthLargest.add(5));   // return 5
        System.out.println(kthLargest.add(10));  // return 5
        System.out.println(kthLargest.add(9));   // return 8
        System.out.println(kthLargest.add(4));   // return 8
    }
}
