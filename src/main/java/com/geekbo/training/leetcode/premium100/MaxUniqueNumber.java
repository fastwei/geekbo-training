package com.geekbo.training.leetcode.premium100;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 1133. 最大唯一数
 * 简单
 * 给你一个整数数组 A，请找出并返回在该数组中仅出现一次的最大整数。
 *
 * 如果不存在这个只出现一次的整数，则返回 -1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[5,7,3,9,4,9,8,3,1]
 * 输出：8
 * 解释：
 * 数组中最大的整数是 9，但它在数组中重复出现了。而第二大的整数是 8，它只出现了一次，所以答案是 8。
 * 示例 2：
 *
 * 输入：[9,9,8,8]
 * 输出：-1
 * 解释：
 * 数组中不存在仅出现一次的整数。
 *
 *
 * 提示：
 *
 * 1 <= A.length <= 2000
 * 0 <= A[i] <= 1000
 *
 */
class MaxUniqueNumber {
    /**
     * 找出在数组中仅出现一次的最大整数
     *
     * 解题思路：
     * 遍历整个数组，使用一个HashMap来记录每个数字出现的次数。
     * 然后再遍历HashMap，找到只出现一次的数字中的最大值。
     *
     * 算法复杂度分析：
     * 时间复杂度：遍历整个数组的时间复杂度是O(n)，遍历HashMap的时间复杂度是O(m)，其中m是HashMap中键值对的个数。
     * 最坏情况下，m也是n，所以总的时间复杂度是O(n)。
     * 空间复杂度：使用了一个HashMap来存储数字和出现次数的对应关系，所以空间复杂度是O(n)。
     *
     * @param A 整数数组
     * @return 数组中仅出现一次的最大整数，如果不存在则返回-1
     */
    public int largestUniqueNumber(int[] A) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : A) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int maxUnique = -1;
        for (int num : countMap.keySet()) {
            if (countMap.get(num) == 1) {
                maxUnique = Math.max(maxUnique, num);
            }
        }

        return maxUnique;
    }

    public static void main(String[] args) {
        MaxUniqueNumber solution = new MaxUniqueNumber();

        // 测试用例
        int[] nums1 = {5, 7, 3, 9, 4, 9, 8, 3, 1};
        System.out.println(solution.largestUniqueNumber(nums1)); // 8

        int[] nums2 = {9, 9, 8, 8};
        System.out.println(solution.largestUniqueNumber(nums2)); // -1
    }
}