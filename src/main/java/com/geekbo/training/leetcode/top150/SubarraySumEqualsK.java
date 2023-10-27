package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k,
 * return the total number of subarrays whose sum equals to k.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 * <p>
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 */
public class SubarraySumEqualsK {

    /**
     * 解题思路： 本题可以使用哈希表来解决。
     * 我们需要计算子数组的和等于k的个数，可以通过计算前缀和的方式来实现。
     * <p>
     * 具体做法如下：
     * <p>
     * 使用一个哈希表prefixSumMap来记录前缀和出现的次数。我们初始化前缀和为0的次数为1。
     * 使用两个变量count和prefixSum，将count初始化为0，prefixSum初始化为0。
     * 遍历数组nums，对于每个元素num，将prefixSum加上num，得到当前位置的前缀和。
     * 然后判断prefixSum - k是否在哈希表prefixSumMap中，
     * 如果存在，则说明存在前缀和等于k的子数组，将对应的次数累加到count中。
     * 将当前前缀和出现的次数加入到哈希表prefixSumMap中，如果已经存在，则次数+1；否则，将次数初始化为1。
     * 遍历结束后，返回count即为子数组的和等于k的个数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是数组nums的长度。
     * 我们需要遍历一次数组nums，并在遍历过程中更新哈希表prefixSumMap，时间复杂度是O(n)。
     * 空间复杂度：O(n)，我们需要使用一个哈希表prefixSumMap来存储前缀和及其出现的次数，
     * 最多需要存储n个前缀和，所以空间复杂度是O(n)。
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        // 使用哈希表记录前缀和出现的次数
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0, 1);  // 初始前缀和为0的次数为1
        int count = 0;
        int prefixSum = 0;

        // 遍历数组，计算当前位置的前缀和，并统计前缀和等于k的次数
        for (int num : nums) {
            prefixSum += num;
            if (prefixSumMap.containsKey(prefixSum - k)) {
                count += prefixSumMap.get(prefixSum - k);
            }
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        SubarraySumEqualsK subarraySumEqualsK = new SubarraySumEqualsK();

        // Test Case 1
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        int result1 = subarraySumEqualsK.subarraySum(nums1, k1);
        System.out.println(result1);  // Expected output: 2

        // Test Case 2
        int[] nums2 = {1, 2, 3};
        int k2 = 3;
        int result2 = subarraySumEqualsK.subarraySum(nums2, k2);
        System.out.println(result2);  // Expected output: 2
    }
}
