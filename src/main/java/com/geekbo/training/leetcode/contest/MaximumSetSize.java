package com.geekbo.training.leetcode.contest;

import java.util.*;

//379.3
public class MaximumSetSize {

    /**
     * 计算在从两个数组中各移除 n/2 个元素后，集合 s 的最大可能大小。
     *
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @return 集合 s 的最大可能大小
     */
    public static int maximumSetSize(int[] nums1, int[] nums2) {
        Map<Integer, int[]> freqMap = new HashMap<>();
        for (int num : nums1) {
            freqMap.computeIfAbsent(num, k -> new int[2])[0]++;
        }
        for (int num : nums2) {
            freqMap.computeIfAbsent(num, k -> new int[2])[1]++;
        }

        // 优先移除频率高的元素
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) ->
                Integer.compare(Math.min(b[0], b[1]), Math.min(a[0], a[1])));
        for (Map.Entry<Integer, int[]> entry : freqMap.entrySet()) {
            maxHeap.add(new int[]{entry.getValue()[0], entry.getValue()[1]});
        }

        int n = nums1.length / 2;
        while (n > 0 && !maxHeap.isEmpty()) {
            int[] counts = maxHeap.poll();
            int minCount = Math.min(counts[0], counts[1]);
            if (minCount <= n) {
                n -= minCount;
            } else {
                n = 0;
            }
        }

        return freqMap.size() - maxHeap.size();
    }

    public static void main(String[] args) {
        System.out.println(maximumSetSize(new int[]{1, 2, 1, 2}, new int[]{1, 1, 1, 1})); // 预期输出: 2
        System.out.println(maximumSetSize(new int[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3, 2, 3, 2, 3})); // 预期输出: 5
        System.out.println(maximumSetSize(new int[]{1, 1, 2, 2, 3, 3}, new int[]{4, 4, 5, 5, 6, 6})); // 预期输出: 6
    }
}
