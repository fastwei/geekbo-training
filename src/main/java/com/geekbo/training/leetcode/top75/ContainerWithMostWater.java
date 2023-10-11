package com.geekbo.training.leetcode.top75;

/**
 *
 * Two Pointers
 *
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0)
 * and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container,
 * such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 * 题目描述：
 * 给定一个整数数组height，表示垂直线的高度，找到两条线段，与x轴一起构成一个容器，使容器能够容纳最多的水。
 * 返回容器的最大容积。
 * 注意，容器不能倾斜。
 *
 * 解题思路：
 * 使用双指针法，将左指针指向数组的起始位置，右指针指向数组的结束位置。
 * 计算当前容器的容积，容积等于两个指针指向的高度中的较小值乘以两个指针之间的距离。
 * 然后将高度较小的那个指针向内移动一步，继续计算容积。
 * 重复上述步骤，直到左指针和右指针相遇。
 * 在移动指针的过程中，始终保留最大的容积。
 *
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中n为数组的长度，两个指针向中间移动的总步数最多为n-1。
 * - 空间复杂度：O(1)，只需要常数级的额外空间。
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0; // 左指针初始位置
        int right = height.length - 1; // 右指针初始位置
        int maxArea = 0; // 最大容积

        while (left < right) {
            int hLeft = height[left]; // 左指针指向的高度
            int hRight = height[right]; // 右指针指向的高度
            int currentArea = Math.min(hLeft, hRight) * (right - left); // 当前容积

            // 更新最大容积
            maxArea = Math.max(maxArea, currentArea);

            // 移动高度较小的指针
            if (hLeft < hRight) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        // 测试用例1
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result1 = solution.maxArea(height1);
        System.out.println("Example 1: Output: " + result1); // 预期输出：49

        // 测试用例2
        int[] height2 = {1, 1};
        int result2 = solution.maxArea(height2);
        System.out.println("Example 2: Output: " + result2); // 预期输出：1
    }
}
