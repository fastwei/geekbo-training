package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;


/**
 *
 * 480. Sliding Window Median
 * Solved
 * Hard
 * The median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle values.
 *
 * For examples, if arr = [2,3,4], the median is 3.
 * For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 * You are given an integer array nums and an integer k.
 * There is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array.
 * Answers within 10-5 of the actual value will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 * Explanation:
 * Window position                Median
 * ---------------                -----
 * [1  3  -1] -3  5  3  6  7        1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7        3
 *  1  3  -1  -3 [5  3  6] 7        5
 *  1  3  -1  -3  5 [3  6  7]       6
 * Example 2:
 *
 * Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 * Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]
 *
 */
public class SlidingWindowMedian {
    /**
     * 求滑动窗口中的中位数数组
     * <p>
     * <p>
     * 解题思路：
     * - 使用两个优先队列（最大堆和最小堆）来维护滑动窗口中的元素。
     * - 初始化两个空堆：`maxHeap` 和 `minHeap`。
     * - 对于数组中的每个元素 `num`：
     * - 如果 `maxHeap` 的大小为0或者 `num` 小于等于 `maxHeap` 的最大元素，则将 `num` 添加到 `maxHeap` 中。
     * - 否则，将 `num` 添加到 `minHeap` 中。
     * - 平衡两个堆的大小：
     * - 如果 `maxHeap` 的大小大于 `minHeap` 的大小加1，则将 `maxHeap` 的最大元素移除并添加到 `minHeap` 中。
     * - 如果 `minHeap` 的大小大于 `maxHeap` 的大小，则将 `minHeap` 的最小元素移除并添加到 `maxHeap` 中。
     * - 如果窗口的大小大于等于 `k`，则计算中位数：
     * - 如果 `k` 是奇数，则中位数是 `maxHeap` 的最大元素。
     * - 如果 `k` 是偶数，则中位数是 `maxHeap` 的最大元素和 `minHeap` 的最小元素的平均值。
     * - 将中位数添加到结果数组中。
     * - 从堆中移除窗口的第一个元素。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：每个元素最多被添加和移除一次，因此时间复杂度为 `O(nlogk)`，其中 `n` 是数组的长度，`k` 是窗口的大小。
     * - 空间复杂度：除了存储结果的数组外，我们还需要两个堆来维护滑动窗口中的元素，因此空间复杂度为 `O(k)`。
     *
     * todo: 方法还有些问题，通不过所有leetcode测试用例
     * @param nums 数组
     * @param k    窗口大小
     * @return 滑动窗口中的中位数数组
     */
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] result = new double[n - k + 1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            if (i >= k) {
                // 移除窗口左边界的元素
                if (maxHeap.contains(nums[i - k])) {
                    maxHeap.remove(nums[i - k]);
                } else {
                    minHeap.remove(nums[i - k]);
                }
            }

            // 将元素添加到对应的堆中
            if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                maxHeap.add(nums[i]);
            } else {
                minHeap.add(nums[i]);
            }

            // 平衡两个堆的大小
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            // 计算中位数并添加到结果数组
            if (i >= k - 1) {
                if (k % 2 == 0) {
                    result[i - k + 1] = (double) (maxHeap.peek() + minHeap.peek()) / 2.0;
                } else {
                    result[i - k + 1] = (double) maxHeap.peek();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMedian solution = new SlidingWindowMedian();

        // 测试用例1
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        // 滑动窗口中的中位数数组为 [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
        double[] expected1 = {1.0, -1.0, -1.0, 3.0, 5.0, 6.0};
        double[] result1 = solution.medianSlidingWindow(nums1, k1);
        System.out.println(Arrays.equals(result1, expected1) ? "Test case 1 passed" : "Test case 1 failed");

        // 测试用例2
        int[] nums2 = {1, 2, 3, 4, 2, 3, 1, 4, 2};
        int k2 = 3;
        // 滑动窗口中的中位数数组为 [2.0, 3.0, 3.0, 3.0, 2.0, 3.0, 2.0]
        double[] expected2 = {2.0, 3.0, 3.0, 3.0, 2.0, 3.0, 2.0};
        double[] result2 = solution.medianSlidingWindow(nums2, k2);
        System.out.println(Arrays.equals(result2, expected2) ? "Test case 2 passed" : "Test case 2 failed");
    }
}

