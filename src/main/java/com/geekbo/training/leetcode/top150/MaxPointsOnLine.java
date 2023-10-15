package com.geekbo.training.leetcode.top150;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane,
 * return the maximum number of points that lie on the same straight line.
 * <p>
 * Example 1:
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3
 * Example 2:
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 */
public class MaxPointsOnLine {

    /**
     * 该解决方案使用了HashMap来存储斜率与点的映射关系，并统计了斜率相同的点的数量。
     * 最后，通过比较斜率相同的点的数量和其他点的数量，得到在同一条直线上的最大点的数量。
     * <p>
     * 算法的时间复杂度是O(n^2)，其中n是points数组的长度。
     * 由于使用了两个嵌套的循环遍历所有点对，并在每个点对中计算斜率，因此算法的时间复杂度是平方级别的。
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }

        int maxCount = 2;

        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopes = new HashMap<>();
            int samePoints = 0;

            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];

                if (x1 == x2 && y1 == y2) {
                    samePoints++;
                } else {
                    String slope = getSlope(x1, y1, x2, y2);
                    slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
                }
            }

            if (!slopes.isEmpty()) {
                maxCount = Math.max(maxCount, samePoints + 1 + slopes.values().stream().max(Integer::compareTo).get());
            } else {
                maxCount = Math.max(maxCount, samePoints + 1);
            }
        }

        return maxCount;
    }

    /**
     * 解题思路：
     * 修改后的代码中，我们使用了一个 getSlope 方法来计算点之间的斜率。
     * 如果两个点的 x 坐标相等，则将斜率设置为字符串 "inf"，否则计算斜率的浮点数值。
     * <p>
     * 这样修改后的代码应该能够正确处理斜率为零的情况
     * <p>
     * 修复后的代码中，我们修改了 getSlope 方法，使用欧几里得算法来计算斜率的最简形式。
     * 然后将最简形式的斜率转换为字符串进行比较。
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    private String getSlope(int x1, int y1, int x2, int y2) {
        if (x1 == x2) {
            return "inf";
        }

        int dx = x1 - x2;
        int dy = y1 - y2;
        int gcd = gcd(dx, dy);
        dx /= gcd;
        dy /= gcd;

        return dy + "/" + dx;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {

        MaxPointsOnLine solution = new MaxPointsOnLine();

        // 测试用例 1
        int[][] points1 = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println(solution.maxPoints(points1));  // 输出: 3

        // 测试用例 2
        int[][] points2 = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println(solution.maxPoints(points2));  // 输出: 4

        // 测试用例 3
        int[][] points3 = {{2, 3}, {3, 3}, {-5, 3}};
        System.out.println(solution.maxPoints(points3));  // 输出: 3
    }
}
