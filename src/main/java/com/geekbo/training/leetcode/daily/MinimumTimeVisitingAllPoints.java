package com.geekbo.training.leetcode.daily;

/**
 * 1266. Minimum Time Visiting All Points
 * Easy
 * On a 2D plane, there are n points with integer coordinates points[i] = [xi, yi]. Return the minimum time in seconds to visit all the points in the order given by points.
 * <p>
 * You can move according to these rules:
 * <p>
 * In 1 second, you can either:
 * move vertically by one unit,
 * move horizontally by one unit, or
 * move diagonally sqrt(2) units (in other words, move one unit vertically then one unit horizontally in 1 second).
 * You have to visit the points in the same order as they appear in the array.
 * You are allowed to pass through points that appear later in the order, but these do not count as visits.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: points = [[1,1],[3,4],[-1,0]]
 * Output: 7
 * Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
 * Time from [1,1] to [3,4] = 3 seconds
 * Time from [3,4] to [-1,0] = 4 seconds
 * Total time = 7 seconds
 * Example 2:
 * <p>
 * Input: points = [[3,2],[-2,2]]
 * Output: 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * points.length == n
 * 1 <= n <= 100
 * points[i].length == 2
 * -1000 <= points[i][0], points[i][1] <= 1000
 */
public class MinimumTimeVisitingAllPoints {
    /**
     * 解题思路：
     * <p>
     * 给定一个二维平面上的点数组，要求按照给定的顺序依次访问所有的点，并返回访问所有点的最小时间（以秒为单位）。
     * 从第一个点开始，依次计算当前点和前一个点的横坐标和纵坐标的差的绝对值，取最大值并累加，即可得到最小时间。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n为点数组的长度，需要遍历所有点数组中的点。
     * 空间复杂度：O(1)，只需要常数级别的额外空间。
     *
     * @param points
     * @return
     */
    public static int minTimeToVisitAllPoints(int[][] points) {
        int time = 0;
        for (int i = 1; i < points.length; i++) {
            int[] current = points[i];
            int[] previous = points[i - 1];
            int dx = Math.abs(current[0] - previous[0]);
            int dy = Math.abs(current[1] - previous[1]);
            time += Math.max(dx, dy);
        }
        return time;
    }

    public static void main(String[] args) {
        int[][] points1 = {{1, 1}, {3, 4}, {-1, 0}};
        System.out.println(minTimeToVisitAllPoints(points1)); // Output: 7

        int[][] points2 = {{3, 2}, {-2, 2}};
        System.out.println(minTimeToVisitAllPoints(points2)); // Output: 5
    }
}
