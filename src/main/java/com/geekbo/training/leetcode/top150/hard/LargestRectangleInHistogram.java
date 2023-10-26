package com.geekbo.training.leetcode.top150.hard;

import java.util.Stack;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 * <p>
 * <p>
 * Input: heights = [2,4]
 * Output: 4
 */
public class LargestRectangleInHistogram {
    /**
     * 解题思路： 我们可以使用单调栈的思想来解决这个问题。
     * 首先，我们定义一个栈，用于存储非递减的高度索引。
     * 然后，我们遍历每个高度，如果当前高度小于栈顶的高度，则计算以栈顶高度为高的矩形的面积，并更新最大面积。
     * 最后，我们将当前高度的索引压入栈中。
     * 遍历结束后，栈中剩余的索引对应的高度都是递增的，
     * 我们可以将栈中所有的索引依次出栈，计算以每个高度为高的矩形的面积，并更新最大面积。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是数组的长度。我们需要遍历数组一次。
     * 空间复杂度：O(n)，我们使用了一个栈来存储非递减的高度索引，最坏情况下，栈的大小为n。
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // 定义一个栈，用于存储非递减的高度索引
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;

        // 遍历每个高度
        for (int i = 0; i <= n; i++) {
            // 获取当前高度或者设置一个较小的高度作为计算面积的起点
            int h = (i == n) ? 0 : heights[i];

            // 如果当前高度小于栈顶高度，则计算以栈顶高度为高的矩形的面积
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = (stack.isEmpty()) ? i : (i - stack.peek() - 1);
                int area = height * width;
                maxArea = Math.max(maxArea, area);
            }

            // 将当前高度的索引压入栈中
            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] heights = {2, 1, 5, 6, 2, 3};
        // 预期输出为10
        System.out.println(largestRectangleArea(heights));

        // Test Case 2
        heights = new int[]{2, 4};
        // 预期输出为4
        System.out.println(largestRectangleArea(heights));
    }
}
