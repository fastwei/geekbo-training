package com.geekbo.training.leetcode.top75;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * There are some spherical balloons taped onto a flat wall that represents the XY-plane. T
 * he balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend.
 * You do not know the exact y-coordinates of the balloons.
 *
 * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
 * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
 * There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
 *
 * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
 * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
 * Example 2:
 *
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
 * Example 3:
 *
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Explanation: The balloons can be burst by 2 arrows:
 * - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
 * - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 *
 */
public class MinimumNumberArrowsToBurstBalloons {

    /**
     * todo:有空再加深理解
     * <p>
     * 这个实现使用了贪心算法来解决问题。我们的目标是找到一种策略，尽可能用一支箭射爆多个气球，从而达到最小的箭数。
     * <p>
     * 首先，我们将气球数组按照气球的结束位置进行排序，这样可以确保在射出箭时，箭能够射到尽可能多的气球。
     * <p>
     * 然后，我们初始化箭数为1，即至少需要一支箭来射爆气球。我们将当前箭能够射到的最远位置设为第一个气球的结束位置。
     * <p>
     * 接下来，我们遍历气球数组中的每个气球。对于每个气球，
     * 如果它的开始位置在当前箭能够射到的范围内，那么它可以被当前箭射爆，我们不需要额外的箭。
     * 否则，我们需要射出一支新的箭，并更新当前箭能够射到的最远位置为当前气球的结束位置。
     * <p>
     * 最后，返回箭数即为最小的箭数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：排序的时间复杂度为O(nlogn)，其中n为气球的个数。遍历气球数组的时间复杂度为O(n)。
     * 所以总体的时间复杂度为O(nlogn + n)，即O(nlogn)。
     * 空间复杂度：除了输入的气球数组外，算法只使用了常数级别的额外空间，因此空间复杂度为O(1)。
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }

        // 按照气球的结束位置进行排序
        Arrays.sort(points, Comparator.comparingInt(point -> point[1]));

        int arrows = 1; // 记录需要射出的箭数
        int end = points[0][1]; // 当前箭能够射到的最远位置

        // 遍历气球数组，尽可能用一支箭射爆多个气球
        for (int i = 1; i < points.length; i++) {
            // 如果当前气球的开始位置在当前箭能够射到的范围内，则不需要额外的箭
            if (points[i][0] <= end) {
                continue;
            }
            // 否则，需要射出一支新的箭
            arrows++;
            end = points[i][1]; // 更新当前箭能够射到的最远位置
        }

        return arrows;
    }

    public static void main(String[] args) {
        MinimumNumberArrowsToBurstBalloons solution = new MinimumNumberArrowsToBurstBalloons();

        // 测试用例1
        int[][] points1 = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        int expected1 = 2;
        int result1 = solution.findMinArrowShots(points1);
        System.out.println(result1 == expected1); // true

        // 测试用例2
        int[][] points2 = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        int expected2 = 4;
        int result2 = solution.findMinArrowShots(points2);
        System.out.println(result2 == expected2); // true

        // 测试用例3
        int[][] points3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        int expected3 = 2;
        int result3 = solution.findMinArrowShots(points3);
        System.out.println(result3 == expected3); // true
    }
}