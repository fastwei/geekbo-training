package com.geekbo.training.leetcode.top150;

import java.util.*;


/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * <p>
 * <p>
 * Follow up: Your algorithm's time complexity must be better than O(n log n),
 * where n is the array's size.
 */
public class TopKFrequentElements {

    /**
     * 解题思路： 本题可以使用哈希表和优先队列来解决。
     * 首先，我们使用哈希表统计每个元素的出现频率。
     * 然后，我们使用优先队列来找出出现频率最高的k个元素。
     * <p>
     * 具体做法如下：
     * <p>
     * 使用一个哈希表frequencyMap来统计每个元素的出现频率。
     * 遍历数组nums，对于每个元素num，如果num已经在哈希表中，则将其频率+1；
     * 否则，将num添加到哈希表中，并将其频率初始化为1。
     * 使用一个优先队列pq来存储出现频率最高的k个元素。
     * 遍历哈希表frequencyMap，对于每个元素num，将num添加到优先队列pq中。
     * 如果pq的大小超过了k，就移除出现频率最低的元素。
     * 将优先队列pq中的元素转换为数组并返回。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(nlogk)，其中n是数组nums的长度。
     * 我们需要遍历数组nums一次来统计元素的频率，时间复杂度是O(n)。
     * 然后，我们需要将元素添加到优先队列pq中，每次添加的时间复杂度是O(logk)。
     * 总共需要添加n个元素，所以时间复杂度是O(nlogk)。
     * 空间复杂度：O(n)，我们需要使用一个哈希表来存储元素的频率
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequentNormal(int[] nums, int k) {
        // 使用哈希表统计每个元素的出现频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 使用优先队列找出出现频率最高的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> frequencyMap.get(a) - frequencyMap.get(b));
        for (int num : frequencyMap.keySet()) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // 将优先队列中的元素转换为数组并返回
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }


    /**
     * In this updated implementation,
     * we use a bucket sort approach to achieve a time complexity of O(n),
     * where n is the size of the input array.
     * We first count the frequency of each element using a hash map.
     * Then, we place the elements in the corresponding frequency buckets.
     * Finally, we extract the top k frequent elements from the buckets.
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // Use a hash map to count the frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Use a list of lists to represent the frequency buckets
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            buckets.add(new ArrayList<>());
        }

        // Place elements in the corresponding frequency bucket
        for (int num : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(num);
            buckets.get(frequency).add(num);
        }

        // Extract the top k frequent elements from the buckets
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.size() - 1; i >= 0 && result.size() < k; i--) {
            result.addAll(buckets.get(i));
        }

        // Convert the list to an array
        int[] topKFrequentElements = new int[k];
        for (int i = 0; i < k; i++) {
            topKFrequentElements[i] = result.get(i);
        }

        return topKFrequentElements;
    }

    public static void main(String[] args) {
        TopKFrequentElements topKFrequentElements = new TopKFrequentElements();

        // Test Case 1
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        int[] result1 = topKFrequentElements.topKFrequent(nums1, k1);
        System.out.println(Arrays.toString(result1));  // Expected output: [1, 2]

        // Test Case 2
        int[] nums2 = {1};
        int k2 = 1;
        int[] result2 = topKFrequentElements.topKFrequent(nums2, k2);
        System.out.println(Arrays.toString(result2));  // Expected output: [1]
    }
}
