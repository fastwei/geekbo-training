package com.geekbo.training.leetcode.skill;

/**
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
 * Check if these points make a straight line in the XY plane.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 */
public class StraightLine {
    /**
     * 解题思路：
     * 我们可以通过计算斜率来判断点是否在同一条直线上。
     * 选择前两个点作为基准点，计算它们的斜率。
     * 然后遍历剩余的点，计算每个点与基准点的斜率，如果存在斜率不相等的点，则说明这些点不在同一条直线上。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度：遍历剩余的点需要O(n)的时间复杂度，其中n是coordinates数组的长度。
     * - 空间复杂度：使用了常数个变量，所以空间复杂度为O(1)。
     *
     * @param coordinates
     * @return
     */
    public boolean checkStraightLine(int[][] coordinates) {
        // 获取前两个点的坐标
        int[] point1 = coordinates[0];
        int[] point2 = coordinates[1];

        // 计算斜率
        int deltaX = point2[0] - point1[0];
        int deltaY = point2[1] - point1[1];

        // 遍历剩余的点，检查是否在同一条直线上
        for (int i = 2; i < coordinates.length; i++) {
            int[] point = coordinates[i];
            int x = point[0];
            int y = point[1];

            // 判断斜率是否相等
            if (deltaX * (y - point1[1]) != deltaY * (x - point1[0])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        StraightLine line = new StraightLine();

        // Test Case 1
        int[][] coordinates = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
        // 预期输出为true
        System.out.println(line.checkStraightLine(coordinates));

        // Test Case 2
        coordinates = new int[][]{{1, 1}, {2, 2}, {3, 4}, {4, 5}, {5, 6}, {7, 7}};
        // 预期输出为false
        System.out.println(line.checkStraightLine(coordinates));
    }
}

