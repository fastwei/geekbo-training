package com.geekbo.training.leetcode.top150.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given an array of integers nums,
 * there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 */
public class SlidingWindowMaximum {
    /**
     * 解题思路： 我们可以使用双端队列来解决这个问题。
     * 首先，我们创建一个双端队列，用于存储滑动窗口中的元素索引。
     * 然后，我们遍历数组，对于每个元素，我们进行以下操作：
     * <p>
     * 如果队列中的首个元素超出滑动窗口范围，移除首个元素。
     * 从队列的末尾开始，移除所有小于当前元素的索引。
     * 将当前元素的索引添加到队列的末尾。
     * 如果滑动窗口的大小达到k，将队列中的首个元素添加到结果数组中。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是数组的长度。我们需要遍历数组一次。
     * 空间复杂度：O(k)，我们使用了一个双端队列来存储滑动窗口中的元素索引，最坏情况下，队列的大小为k。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        // 创建一个双端队列，用于存储滑动窗口中的元素索引
        Deque<Integer> deque = new ArrayDeque<>();

        // 定义结果数组
        int[] result = new int[n - k + 1];

        int index = 0;

        // 遍历数组
        for (int i = 0; i < n; i++) {
            // 如果队列中的首个元素超出滑动窗口范围，移除首个元素
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }

            // 从队列的末尾开始，移除所有小于当前元素的索引
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }

            // 将当前元素的索引添加到队列的末尾
            deque.offerLast(i);

            // 如果滑动窗口的大小达到k，将队列中的首个元素添加到结果数组中
            if (i >= k - 1) {
                result[index++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        // 预期输出为[3, 3, 5, 5, 6, 7]
        int[] result = maxSlidingWindow(nums, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Test Case 2
        nums = new int[]{1};
        k = 1;
        // 预期输出为[1]
        result = maxSlidingWindow(nums, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
