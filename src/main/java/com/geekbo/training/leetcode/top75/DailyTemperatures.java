package com.geekbo.training.leetcode.top75;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 *
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 *
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 *
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 *
 * Constraints:
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 */
public class DailyTemperatures {

    /**
     * Monotonic Stack
     *
     * 解题思路：
     * <p>
     * 使用栈来记录等待天数。遍历温度数组，如果当前温度高于栈顶元素对应的温度，
     * 则表示栈顶元素找到了比它高的温度，计算等待天数并更新结果数组。
     * 将当前索引入栈，表示等待下一个更高温度的天数。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n)，其中n是温度数组的长度。我们只需要遍历一次温度数组，并且每个温度最多入栈和出栈一次。
     * 空间复杂度：O(n)，最坏情况下，栈的大小可以达到温度数组的长度。
     *
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length]; // 用于存储结果的数组
        Deque<Integer> stack = new ArrayDeque<>(); // 使用栈来记录等待天数

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                // 如果当前温度高于栈顶元素对应的温度，则表示栈顶元素找到了比它高的温度
                int index = stack.pop();
                answer[index] = i - index; // 计算等待天数
            }

            stack.push(i); // 将当前索引入栈，表示等待下一个更高温度的天数
        }

        return answer;
    }

    public static void main(String[] args) {
        // 测试用例 1
        int[] temperatures1 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected1 = {1, 1, 4, 2, 1, 1, 0, 0};
        int[] result1 = dailyTemperatures(temperatures1);
        System.out.println(Arrays.equals(result1, expected1)); // true

        // 测试用例 2
        int[] temperatures2 = {30, 40, 50, 60};
        int[] expected2 = {1, 1, 1, 0};
        int[] result2 = dailyTemperatures(temperatures2);
        System.out.println(Arrays.equals(result2, expected2)); // true

        // 测试用例 3
        int[] temperatures3 = {30, 60, 90};
        int[] expected3 = {1, 1, 0};
        int[] result3 = dailyTemperatures(temperatures3);
        System.out.println(Arrays.equals(result3, expected3)); // true
    }
}