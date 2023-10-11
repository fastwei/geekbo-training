package com.geekbo.training.leetcode.top75;

/**
 *
 *Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,2,1,1,3]
 * Output: true
 * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
 * Example 2:
 *
 * Input: arr = [1,2]
 * Output: false
 * Example 3:
 *
 * Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * Output: true
 *
 * 题目描述：
 * 给定一个整数数组 arr，如果数组中每个值的出现次数都是独一无二的，则返回 true；否则返回 false。
 * 
 * 解题思路：
 * 使用一个哈希表（HashMap）来记录每个值出现的次数，然后检查这些出现次数是否都是独一无二的。
 * 
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中 n 为数组的长度，需要遍历整个数组并构建哈希表。
 * - 空间复杂度：O(m)，其中 m 为数组中不同值的数量，哈希表需要存储每个值的出现次数。
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueNumberOfOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        // 使用哈希表记录每个值的出现次数
        Map<Integer, Integer> countMap = new HashMap<>();

        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // 使用集合（Set）来检查出现次数是否独一无二
        Set<Integer> uniqueCounts = new HashSet<>(countMap.values());

        // 如果哈希表的值的数量等于独一无二的出现次数的数量，则返回true，否则返回false
        return countMap.size() == uniqueCounts.size();
    }

    public static void main(String[] args) {
        UniqueNumberOfOccurrences solution = new UniqueNumberOfOccurrences();

        // 测试用例1
        int[] arr1 = {1, 2, 2, 1, 1, 3};
        boolean result1 = solution.uniqueOccurrences(arr1);
        System.out.println("Example 1: Output: " + result1); // 预期输出：true

        // 测试用例2
        int[] arr2 = {1, 2};
        boolean result2 = solution.uniqueOccurrences(arr2);
        System.out.println("Example 2: Output: " + result2); // 预期输出：false

        // 测试用例3
        int[] arr3 = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        boolean result3 = solution.uniqueOccurrences(arr3);
        System.out.println("Example 3: Output: " + result3); // 预期输出：true
    }
}
