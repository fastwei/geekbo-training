package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 * <p>
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * <p>
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 * <p>
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence:
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 * todo:加深理解
 */
public class KSmallestPairs {
    /**
     * 返回和最小的k个数对
     * 解题思路：
     * <p>
     * 首先定义一个最小堆（PriorityQueue）来存储数对，堆顶元素是和最小的数对。
     * 遍历第一个数组的前k个元素，将每个元素与第二个数组的第一个元素组成数对，并加入最小堆。
     * 循环k次，每次从最小堆中取出一个数对，加入到结果集中，并将下一个可能的数对加入最小堆。
     * 返回结果集。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历第一个数组的前k个元素需要O(k)的时间，每次操作最小堆的时间复杂度是O(log k)。
     * 因此，总体时间复杂度是O(k log k)。
     * 空间复杂度：除了结果集外，算法使用了最小堆来存储数对，堆的大小最多为k。因此，空间复杂度为O(k)。
     *
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @param k     数对的个数
     * @return 和最小的k个数对
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // 使用最小堆来存储数对，堆顶元素是和最小的数对
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

        // 特殊情况处理
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return result;
        }

        // 将第一个数组的每个元素与第二个数组的第一个元素组成数对，并加入最小堆
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0});
        }

        // 循环k次，每次从最小堆中取出一个数对，加入到结果集中，并将下一个可能的数对加入最小堆
        while (k > 0 && !minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            int i = pair[2];
            result.add(Arrays.asList(pair[0], pair[1]));

            // 将下一个可能的数对加入最小堆
            if (i + 1 < nums2.length) {
                minHeap.offer(new int[]{pair[0], nums2[i + 1], i + 1});
            }

            k--;
        }

        return result;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;
        List<List<Integer>> expected1 = new ArrayList<>();
        expected1.add(Arrays.asList(1, 2));
        expected1.add(Arrays.asList(1, 4));
        expected1.add(Arrays.asList(1, 6));
        List<List<Integer>> result1 = kSmallestPairs(nums1, nums2, k);
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);
        System.out.println();

        // 测试用例2
        int[] nums3 = {1, 1, 2};
        int[] nums4 = {1, 2, 3};
        int k2 = 2;
        List<List<Integer>> expected2 = new ArrayList<>();
        expected2.add(Arrays.asList(1, 1));
        expected2.add(Arrays.asList(1, 1));
        List<List<Integer>> result2 = kSmallestPairs(nums3, nums4, k2);
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);
        System.out.println();

        // 测试用例3
        int[] nums5 = {1, 2};
        int[] nums6 = {3};
        int k3 = 3;
        List<List<Integer>> expected3 = new ArrayList<>();
        expected3.add(Arrays.asList(2, 3));
        List<List<Integer>> result3 = kSmallestPairs(nums5, nums6, k3);
        System.out.println("Expected: " + expected3);
        System.out.println("Result: " + result3);
    }
}
