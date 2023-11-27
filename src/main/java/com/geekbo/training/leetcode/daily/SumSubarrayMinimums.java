package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 907. Sum of Subarray Minimums
 * Medium
 * Given an array of integers arr, find the sum of min(b),
 * where b ranges over every (contiguous) subarray of arr.
 * Since the answer may be large, return the answer modulo 109 + 7.
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 * Example 2:
 * <p>
 * Input: arr = [11,81,94,43,3]
 * Output: 444
 */
public class SumSubarrayMinimums {
    /**
     * 计算数组 arr 中所有子数组的最小值之和
     * <p>
     * 解题思路：
     * <p>
     * 使用单调栈的思想来解决问题。
     * 首先，使用两个数组 left 和 right 分别记录每个元素的前一个较小元素的索引和后一个较小元素的索引。
     * 初始化 left 数组为 -1，right 数组为数组长度。
     * 创建一个栈，用于存储索引。
     * 遍历数组，当当前元素小于栈顶元素时，弹出栈顶元素，并将当前元素作为栈顶元素的后一个较小元素的索引。
     * 同时，如果栈不为空，将当前元素作为栈顶元素的前一个较小元素的索引。
     * <p>
     * 遍历完成后，left 和 right 数组中的值就得到了更新，表示每个元素的前一个较小元素和后一个较小元素的索引。
     * <p>
     * 最后，计算所有子数组的最小值之和。
     * 遍历数组，对于每个元素 arr[i]，计算左边界长度 leftLen（即 i - left[i]）和右边界长度 rightLen（即 right[i] - i），然后将 arr[i] * leftLen * rightLen 累加到结果中。
     * <p>
     * 最终返回结果。
     * <p>
     * 时间复杂度分析：遍历数组需要 O(n) 的时间，其中 n 是数组的长度。因此，整个算法的时间复杂度为 O(n)。
     * <p>
     * 空间复杂度分析：除了给定的输入数组外，算法还使用了两个辅助数组 left 和 right，以及一个栈来存储索引。
     * 所以，额外的空间复杂度为 O(n)。
     *
     * @param arr 输入的整数数组
     * @return 所有子数组的最小值之和，对 10^9 + 7 取模
     */
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;
        int n = arr.length;
        int[] left = new int[n]; // 存储前一个较小元素的索引
        int[] right = new int[n]; // 存储后一个较小元素的索引

        // 初始化 left 和 right 数组
        Arrays.fill(left, -1);
        Arrays.fill(right, n);

        Stack<Integer> stack = new Stack<>(); // 使用栈来存储索引

        // 计算前一个较小元素的索引
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                right[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        // 计算所有子数组最小值之和
        long sum = 0; // 使用 long 类型来存储最终结果，防止溢出
        for (int i = 0; i < n; i++) {
            int leftLen = i - left[i] - 1; // 更新左边界长度
            int rightLen = right[i] - i - 1; // 更新右边界长度
            sum = (sum + (long)arr[i] * (leftLen + 1) * (rightLen + 1)) % MOD;
        }

        return (int)sum; // 强制转换为 int 类型返回结果
    }

    public static void main(String[] args) {
        SumSubarrayMinimums solution = new SumSubarrayMinimums();

        // 测试用例
        int[] arr1 = {3, 1, 2, 4};
        // 所有子数组的最小值为 [3, 1, 2, 4, 1, 1, 2, 1, 1, 1]
        // 最小值之和为 17
        int expected1 = 17;
        int result1 = solution.sumSubarrayMins(arr1);
        System.out.println(result1 == expected1); // 预期输出为 true

        int[] arr2 = {11, 81, 94, 43, 3};
        // 所有子数组的最小值为 [11, 3, 3, 3, 3, 3, 3, 3, 3, 3]
        // 最小值之和为 444
        int expected2 = 444;
        int result2 = solution.sumSubarrayMins(arr2);
        System.out.println(result2 == expected2); // 预期输出为 true
    }
}


