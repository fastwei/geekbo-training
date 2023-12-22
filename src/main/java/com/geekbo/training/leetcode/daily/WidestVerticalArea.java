package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 *
 * 1637. Widest Vertical Area Between Two Points Containing No Points
 * Medium
 * Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such that no points are inside the area.
 *
 * A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The widest vertical area is the one with the maximum width.
 *
 * Note that points on the edge of a vertical area are not considered included in the area.
 *
 *
 *
 * Example 1:
 *
 * ​
 * Input: points = [[8,7],[9,9],[7,4],[9,7]]
 * Output: 1
 * Explanation: Both the red and the blue area are optimal.
 * Example 2:
 *
 * Input: points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * n == points.length
 * 2 <= n <= 105
 * points[i].length == 2
 * 0 <= xi, yi <= 109
 *
 */
public class WidestVerticalArea {

    /**
     *
     * 解题思路：
     *
     * 首先，我们需要提取二维数组points中的所有x坐标，并将它们存储在一个单独的数组xCoordinates中。
     * 然后，我们对xCoordinates进行升序排序。
     * 接下来，我们遍历xCoordinates数组，计算相邻x坐标之间的差值，并将其与当前最大宽度进行比较，更新最大宽度。
     * 最后，返回最大宽度作为结果。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(nlogn)，其中n是points数组的长度。
     * 提取x坐标和排序的时间复杂度为O(nlogn)，计算最大宽度的时间复杂度为O(n)。
     * 空间复杂度：O(n)，我们使用了一个额外的数组xCoordinates来存储x坐标。
     *
     * @param points
     * @return
     */
    public static int maxWidthOfVerticalArea(int[][] points) {
        int[] xCoordinates = new int[points.length];

        // Extract the x-coordinates from the points array
        for (int i = 0; i < points.length; i++) {
            xCoordinates[i] = points[i][0];
        }

        // Sort the x-coordinates in ascending order
        Arrays.sort(xCoordinates);

        int maxWidth = 0;
        // Calculate the maximum difference between adjacent x-coordinates
        for (int i = 1; i < xCoordinates.length; i++) {
            int width = xCoordinates[i] - xCoordinates[i - 1];
            maxWidth = Math.max(maxWidth, width);
        }

        return maxWidth;
    }

    public static void main(String[] args) {
        int[][] points1 = {{8, 7}, {9, 9}, {7, 4}, {9, 7}};
        System.out.println("Input: " + Arrays.deepToString(points1));
        System.out.println("Output: " + maxWidthOfVerticalArea(points1));
        // Expected output: 1

        int[][] points2 = {{3, 1}, {9, 0}, {1, 0}, {1, 4}, {5, 3}, {8, 8}};
        System.out.println("Input: " + Arrays.deepToString(points2));
        System.out.println("Output: " + maxWidthOfVerticalArea(points2));
        // Expected output: 3
    }
}

