package com.geekbo.training.leetcode.daily;

import java.util.HashSet;
import java.util.Set;

/**
 * 335. Self Crossing
 * Hard
 * You are given an array of integers distance.
 * <p>
 * You start at the point (0, 0) on an X-Y plane,
 * and you move distance[0] meters to the north,
 * then distance[1] meters to the west, distance[2] meters to the south,
 * distance[3] meters to the east, and so on.
 * In other words, after each move, your direction changes counter-clockwise.
 * <p>
 * Return true if your path crosses itself or false if it does not.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: distance = [2,1,1,2]
 * Output: true
 * Explanation: The path crosses itself at the point (0, 1).
 * Example 2:
 * <p>
 * <p>
 * Input: distance = [1,2,3,4]
 * Output: false
 * Explanation: The path does not cross itself at any point.
 * Example 3:
 * <p>
 * <p>
 * Input: distance = [1,1,1,2,1]
 * Output: true
 * Explanation: The path crosses itself at the point (0, 0).
 */
public class SelfCrossing {

    public boolean isSelfCrossing(int[] x) {
        if (x.length <= 3) {
            return false;
        }
        int i = 2;
        // keep spiraling outward
        while (i < x.length && x[i] > x[i - 2]) {
            i++;
        }
        if (i >= x.length) {
            return false;
        }
        // transition from spiraling outward to spiraling inward
        if ((i >= 4 && x[i] >= x[i - 2] - x[i - 4]) ||
                (i == 3 && x[i] == x[i - 2])) {
            x[i - 1] -= x[i - 3];
        }
        i++;
        // keep spiraling inward
        while (i < x.length) {
            if (x[i] >= x[i - 2]) {
                return true;
            }
            i++;
        }
        return false;
    }
    /**
     * 解题思路：
     * <p>
     * 首先，我们需要初始化坐标为(0, 0)，并创建一个集合来存储访问过的坐标。
     * 然后，我们使用一个二维数组来表示四个方向的移动：北(0, 1)、西(-1, 0)、南(0, -1)、东(1, 0)。初始方向为北。
     * 接下来，我们遍历距离数组中的每个元素。
     * 对于每个元素，我们根据当前方向移动指定的步数，并检查当前位置是否已经访问过。
     * 如果访问过，则返回true；否则，将当前位置添加到集合中。
     * 在移动完成后，我们改变方向，并继续遍历下一个元素。
     * 如果没有发现自交点，则返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历距离数组需要O(n)的时间，其中n是数组的长度。
     * 空间复杂度：使用了一个集合来存储访问过的坐标，最坏情况下需要O(n)的额外空间。
     *
     * @param distance
     * @return
     */
    public boolean isSelfCrossing2(int[] distance) {
        // Initialize the coordinates
        int x = 0, y = 0;

        // Create a set to store the visited coordinates
        Set<String> visited = new HashSet<>();

        // Add the starting point to the set
        visited.add(x + "," + y);

        // Initialize the directions
        int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        int direction = 0;

        // Iterate through the distance array
        for (int i = 0; i < distance.length; i++) {
            int steps = distance[i];

            // Move in the current direction
            for (int j = 0; j < steps; j++) {
                x += directions[direction][0];
                y += directions[direction][1];

                // Check if the current position has been visited before
                if (visited.contains(x + "," + y)) {
                    return true;
                }

                // Add the current position to the set
                visited.add(x + "," + y);
            }

            // Change the direction
            direction = (direction + 1) % 4;
        }

        // Return false if no self-crossing is found
        return false;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] distance1 = {2, 1, 1, 2};
        SelfCrossing solution = new SelfCrossing();
        boolean result1 = solution.isSelfCrossing(distance1);
        System.out.println(result1); // Output: true

        // Test case 2
        int[] distance2 = {1, 2, 3, 4};
        boolean result2 = solution.isSelfCrossing(distance2);
        System.out.println(result2); // Output: false

        // Test case 3
        int[] distance3 = {1, 1, 1, 2, 1};
        boolean result3 = solution.isSelfCrossing(distance3);
        System.out.println(result3); // Output: true
    }
}
