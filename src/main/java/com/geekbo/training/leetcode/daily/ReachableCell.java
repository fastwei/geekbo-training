package com.geekbo.training.leetcode.daily;

/**
 * 2849. Determine if a Cell Is Reachable at a Given Time
 * Medium
 * <p>
 * You are given four integers sx, sy, fx, fy, and a non-negative integer t.
 * <p>
 * In an infinite 2D grid, you start at the cell (sx, sy). Each second,
 * you must move to any of its adjacent cells.
 * <p>
 * Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.
 * <p>
 * A cell's adjacent cells are the 8 cells around it that share at least one corner with it.
 * You can visit the same cell several times.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: sx = 2, sy = 4, fx = 7, fy = 7, t = 6
 * Output: true
 * Explanation: Starting at cell (2, 4), we can reach cell (7, 7)
 * in exactly 6 seconds by going through the cells depicted in the picture above.
 * Example 2:
 * <p>
 * <p>
 * Input: sx = 3, sy = 1, fx = 7, fy = 3, t = 3
 * Output: false
 * Explanation: Starting at cell (3, 1), it takes at least 4 seconds to reach cell (7, 3)
 * by going through the cells depicted in the picture above.
 * Hence, we cannot reach cell (7, 3) at the third second.
 */
public class ReachableCell {
    /**
     * 这个优化的算法的时间复杂度为O(1)，空间复杂度也为O(1)。
     * <p>
     * 时间复杂度分析：
     * <p>
     * 计算曼哈顿距离的操作和计算最小距离的操作都只需要常数时间，所以整个算法的时间复杂度是常数级别的。
     * 空间复杂度分析：
     * <p>
     * 这个算法只使用了一些整型变量来保存输入和中间结果，所以空间复杂度是常数级别的。
     *
     * @param sx
     * @param sy
     * @param fx
     * @param fy
     * @param t
     * @return
     */
    public static boolean isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        // Calculate the Manhattan distance between the start and target cells
        int xDistance = Math.abs(sx - fx);
        int yDistance = Math.abs(sy - fy);

        // Calculate the minimum distance required to reach the target
        int minDist = Math.min(xDistance, yDistance) + Math.abs(xDistance - yDistance);

        // If the minimum distance is 0, it's not possible to reach within 1 second
        if (minDist == 0) {
            return t != 1;
        }

        // Check if the given time is greater than or equal to the minimum distance
        return t >= minDist;
    }

    public static void main(String[] args) {
        // 测试用例1: sx = 2, sy = 4, fx = 7, fy = 7, t = 6
        int sx1 = 2;
        int sy1 = 4;
        int fx1 = 7;
        int fy1 = 7;
        int t1 = 6;
        boolean result1 = isReachableAtTime(sx1, sy1, fx1, fy1, t1);
        System.out.println("测试用例1: " + result1);

        // 测试用例2: sx = 3, sy = 1, fx = 7, fy = 3, t = 3
        int sx2 = 3;
        int sy2 = 1;
        int fx2 = 7;
        int fy2 = 3;
        int t2 = 3;
        boolean result2 = isReachableAtTime(sx2, sy2, fx2, fy2, t2);
        System.out.println("测试用例2: " + result2);
    }
}
