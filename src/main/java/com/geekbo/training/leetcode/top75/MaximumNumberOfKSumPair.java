package com.geekbo.training.leetcode.top75;
/**
 *
 * Two Pointer
 *
 *You are given an integer array nums and an integer k.
 *
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 *
 * Return the maximum number of operations you can perform on the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * Example 2:
 *
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 *
 * 题目描述：
 * 给定一个整数数组nums和一个整数k，每次操作可以从数组中选取两个数，使它们的和等于k，并将它们从数组中移除。
 * 返回最大的操作次数。
 *
 * 解题思路：
 * 使用双指针left和right，分别指向数组的开头和结尾。
 * 对数组进行排序。
 * 在排序后的数组中，如果nums[left] + nums[right] > k，则将right指针左移。
 * 如果nums[left] + nums[right] < k，则将left指针右移。
 * 如果nums[left] + nums[right] == k，则找到一对可以进行操作的元素，并将left和right指针分别右移和左移。
 * 统计操作次数。
 * 最终的操作次数即为答案。
 *
 * 算法复杂度分析：
 * - 时间复杂度：O(nlogn)，其中n为数组的长度，排序需要O(nlogn)时间。
 * - 空间复杂度：O(1)，只需要常数额外的空间。
 *
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumNumberOfKSumPair {

    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums); // 对数组进行排序
        int left = 0, right = nums.length - 1; // 双指针分别指向数组的开头和结尾
        int operations = 0; // 操作次数

        // 使用双指针寻找可以进行操作的元素对
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == k) {
                operations++;
                left++;
                right--;
            } else if (sum < k) {
                left++;
            } else {
                right--;
            }
        }

        return operations;
    }

    /**
     *todo:这个方法还有问题
     *
     * 解题思路：
     * 使用哈希表记录数组中每个元素出现的次数。
     * 遍历数组，对于每个元素nums[i]，检查是否存在另一个元素k-nums[i]，如果存在，说明可以进行一次操作，并将两个元素的出现次数减一。
     * 统计操作次数。
     * 最终的操作次数即为答案。
     *
     * 算法复杂度分析：
     * - 时间复杂度：O(n)，其中n为数组的长度，需要遍历整个数组一次。
     * - 空间复杂度：O(n)，哈希表的最坏情况下会包含所有元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxOperationsOfHashmap(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>(); // 哈希表记录元素出现的次数
        int operations = 0; // 操作次数

        // 遍历数组，记录元素出现次数
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 遍历数组，寻找可以进行操作的元素对
        for (int num : nums) {
            int complement = k - num;
            if (countMap.containsKey(complement) && countMap.get(complement) > 0 && countMap.get(num) > 0) {
                operations++;
                countMap.put(num, countMap.get(num) - 1);
                countMap.put(complement, countMap.get(complement) - 1);
            }
        }

        return operations;
    }

    public static void main(String[] args) {
        MaximumNumberOfKSumPair solution = new MaximumNumberOfKSumPair();

        // 测试用例1
        int[] nums1 = {1, 2, 3, 4};
        int k1 = 5;

        int result1 = solution.maxOperationsOfHashmap(nums1, k1);
        int result12 = solution.maxOperations(nums1, k1);
        System.out.println("Example 1: Output: " + result1); // 预期输出：2
        System.out.println("Example 12: Output: " + result12); // 预期输出：2

        // 测试用例2
        int[] nums2 = {3, 1, 3, 4, 3};
        int k2 = 6;
        int result2 = solution.maxOperationsOfHashmap(nums2, k2);
        int result22 = solution.maxOperations(nums2, k2);
        System.out.println("Example 2: Output: " + result2); // 预期输出：1
        System.out.println("Example 22: Output: " + result22); // 预期输出：1
    }
}
