package com.geekbo.training.leetcode.daily;

/**
 * 223. Rectangle Area
 * Medium
 * Given the coordinates of two rectilinear rectangles in a 2D plane,
 * return the total area covered by the two rectangles.
 * <p>
 * The first rectangle is defined by its bottom-left corner (ax1, ay1)
 * and its top-right corner (ax2, ay2).
 * <p>
 * The second rectangle is defined by its bottom-left corner (bx1, by1)
 * and its top-right corner (bx2, by2).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Rectangle Area
 * Input: ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
 * Output: 45
 * Example 2:
 * <p>
 * Input: ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
 * Output: 16
 */
public class RectangleArea {
    /**
     * 解题思路：
     * <p>
     * 首先计算两个矩形的面积，分别为area1和area2。
     * 然后计算两个矩形的重叠部分的宽度和高度，如果宽度或高度小于等于0，则表示两个矩形不重叠，重叠面积为0。
     * 最后计算总面积，即两个矩形的面积之和减去重叠面积。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(1)，因为只需要进行一次计算。
     * 空间复杂度：O(1)，只需要存储一些变量。
     *
     * @param ax1
     * @param ay1
     * @param ax2
     * @param ay2
     * @param bx1
     * @param by1
     * @param bx2
     * @param by2
     * @return
     */
    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = (ax2 - ax1) * (ay2 - ay1);
        int area2 = (bx2 - bx1) * (by2 - by1);

        int overlapWidth = Math.max(0, Math.min(ax2, bx2) - Math.max(ax1, bx1));
        int overlapHeight = Math.max(0, Math.min(ay2, by2) - Math.max(ay1, by1));

        int overlapArea = overlapWidth * overlapHeight;

        int totalArea = area1 + area2 - overlapArea;

        return totalArea;
    }

    public static void main(String[] args) {
        // 测试用例1
        int ax1 = -3;
        int ay1 = 0;
        int ax2 = 3;
        int ay2 = 4;
        int bx1 = 0;
        int by1 = -1;
        int bx2 = 9;
        int by2 = 2;

        int result1 = computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
        System.out.println("测试用例1: " + result1); // 预期输出: 45

        // 测试用例2
        ax1 = -2;
        ay1 = -2;
        ax2 = 2;
        ay2 = 2;
        bx1 = -2;
        by1 = -2;
        bx2 = 2;
        by2 = 2;

        int result2 = computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2);
        System.out.println("测试用例2: " + result2); // 预期输出: 16
    }
}


