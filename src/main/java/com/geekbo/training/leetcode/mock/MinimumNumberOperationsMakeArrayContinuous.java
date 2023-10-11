package com.geekbo.training.leetcode.mock;

import java.util.Arrays;

/**
 *
 * Code
 *
 * Testcase
 * Testcase
 * Result
 *
 * 2009. Minimum Number of Operations to Make Array Continuous
 *
 * 首先，它去除重复元素并对数组进行排序，然后使用双指针来找到满足条件的最长连续子数组长度，
 * 最终返回 n 减去这个长度即为最小操作次数。算法的时间复杂度为 O(NlogN)，其中 N 是 nums 的长度。
 *
 * 首先，通过去重和排序操作，得到数组 unique，它包含了 nums 中的不重复元素，并按升序排序。
 *
 * 接下来，使用双指针技巧来找到满足条件的最长连续子数组长度。左指针 left 和右指针 right 初始都指向 unique 数组的开头。
 *
 * 随着右指针的移动，不断计算当前子数组的最大差值（unique[right] - unique[left]），并与 n - 1（数组长度减一）比较。如果当前子数组的差值大于 n - 1，则左指针需要向右移动，以缩短子数组长度，直到满足条件。
 *
 * 在整个过程中，维护最长连续子数组的长度 maxContinuous，不断更新为满足条件的子数组的长度。
 *
 * 最后，返回 n - maxContinuous 作为最小操作次数，因为我们需要补充元素使数组成为连续数组。
 *
 * 这个算法的时间复杂度是 O(NlogN)，其中 N 是 nums 的长度。这主要是因为去重和排序步骤的复杂度为 O(NlogN)，然后双指针遍历 unique 数组的时间复杂度是 O(N)。所以整体的时间复杂度是 O(NlogN)。
 *
 */
class MinimumNumberOperationsMakeArrayContinuous {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int[] unique = Arrays.stream(nums).distinct().toArray();
        Arrays.sort(unique);
        
        int maxContinuous = 0;
        int left = 0;
        
        for (int right = 0; right < unique.length; right++) {
            while (unique[right] - unique[left] > n - 1) {
                left++;
            }
            maxContinuous = Math.max(maxContinuous, right - left + 1);
        }
        
        return n - maxContinuous;
    }
    
    public static void main(String[] args) {
        MinimumNumberOperationsMakeArrayContinuous minimumNumberOperationsMakeArrayContinuous = new MinimumNumberOperationsMakeArrayContinuous();
        
        int[] test1 = {4, 2, 5, 3};
        System.out.println("Test 1: " + minimumNumberOperationsMakeArrayContinuous.minOperations(test1)); // Output: 0
        
        int[] test2 = {1, 2, 3, 5, 6};
        System.out.println("Test 2: " + minimumNumberOperationsMakeArrayContinuous.minOperations(test2)); // Output: 1
        
        int[] test3 = {1, 10, 100, 1000};
        System.out.println("Test 3: " + minimumNumberOperationsMakeArrayContinuous.minOperations(test3)); // Output: 3
    }
}
