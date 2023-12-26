package com.geekbo.training.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 1394. Find Lucky Integer in an Array
 * Easy
 * Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
 * <p>
 * Return the largest lucky integer in the array. If there is no lucky integer return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,2,3,4]
 * Output: 2
 * Explanation: The only lucky number in the array is 2 because frequency[2] == 2.
 * Example 2:
 * <p>
 * Input: arr = [1,2,2,3,3,3]
 * Output: 3
 * Explanation: 1, 2 and 3 are all lucky numbers, return the largest of them.
 * Example 3:
 * <p>
 * Input: arr = [2,2,2,3,3]
 * Output: -1
 * Explanation: There are no lucky numbers in the array.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 500
 * 1 <= arr[i] <= 500
 */
public class LuckyInteger {
    /**
     * 解题思路：
     * 遍历数组，使用哈希表记录每个数字出现的次数。
     * 再次遍历哈希表，找到出现次数与数字相等的最大数字。
     * 如果不存在这样的数字，则返回-1。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(n)，其中n是数组的长度。需要遍历数组两次。
     * 空间复杂度：O(n)，需要使用哈希表来存储数字和出现次数的映射。
     */
    public static int findLucky(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<>();

        // 统计每个数字出现的次数
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int maxLucky = -1;
        // 找到出现次数与数字相等的最大数字
        for (int num : countMap.keySet()) {
            if (countMap.get(num) == num) {
                maxLucky = Math.max(maxLucky, num);
            }
        }

        return maxLucky;
    }

    public static void main(String[] args) {
        // Test Cases
        int[] arr1 = {2, 2, 3, 4};
        // The only lucky number in the array is 2 because frequency[2] == 2.
        int expected1 = 2;
        int result1 = findLucky(arr1);
        System.out.println(result1 == expected1); // true

        int[] arr2 = {1, 2, 2, 3, 3, 3};
        // 1, 2 and 3 are all lucky numbers, return the largest of them.
        int expected2 = 3;
        int result2 = findLucky(arr2);
        System.out.println(result2 == expected2); // true

        int[] arr3 = {2, 2, 2, 3, 3};
        // There are no lucky numbers in the array.
        int expected3 = -1;
        int result3 = findLucky(arr3);
        System.out.println(result3 == expected3); // true
    }
}