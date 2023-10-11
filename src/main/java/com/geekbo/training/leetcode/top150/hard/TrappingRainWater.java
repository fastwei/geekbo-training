package com.geekbo.training.leetcode.top150.hard;

/**
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 * 接雨水问题
 * 
 * 给定一个表示地形高度的数组，每个柱子的宽度为1，计算能够接住多少雨水。
 *
 * 解题思路：
 *
 * 创建两个数组 leftMax 和 rightMax 分别用于存储每个柱子左侧和右侧的最大高度。
 * 初始化 leftMax 数组，从左向右遍历，每个位置的最大高度是其左侧最大高度和当前柱子高度的较大者。
 * 初始化 rightMax 数组，从右向左遍历，每个位置的最大高度是其右侧最大高度和当前柱子高度的较大者。
 * 遍历每个位置，计算该位置可以接住的雨水量，即取其左侧和右侧最大高度的较小值减去当前柱子的高度，并累加到结果中。
 * 算法复杂度：
 *
 * 时间复杂度：O(N)，其中 N 为地形高度数组的长度。
 * 空间复杂度：O(N)，用于存储左侧和右侧最大高度的数组。
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int n = height.length;
        if (n <= 2) {
            return 0;
        }

        int[] leftMax = new int[n]; // 用于存储每个柱子左侧的最大高度
        int[] rightMax = new int[n]; // 用于存储每个柱子右侧的最大高度

        // 初始化左侧最大高度数组
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 初始化右侧最大高度数组
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int trappedWater = 0;

        // 计算每个位置上的雨水量，并累加
        for (int i = 0; i < n; i++) {
            int minHeight = Math.min(leftMax[i], rightMax[i]);
            trappedWater += minHeight - height[i];
        }

        return trappedWater;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();
        // 测试用例
        int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution.trap(height1)); // 输出：6

        int[] height2 = {4,2,0,3,2,5};
        System.out.println(solution.trap(height2)); // 输出：9
    }
}
