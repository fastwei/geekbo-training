package com.geekbo.training.leetcode.daily;

/**
 * 365. Water and Jug Problem
 * Medium
 * You are given two jugs with capacities jug1Capacity and jug2Capacity liters. There is an infinite amount of water supply available. Determine whether it is possible to measure exactly targetCapacity liters using these two jugs.
 * <p>
 * If targetCapacity liters of water are measurable, you must have targetCapacity liters of water contained within one or both buckets by the end.
 * <p>
 * Operations allowed:
 * <p>
 * Fill any of the jugs with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
 * Output: true
 * Explanation: The famous Die Hard example
 * Example 2:
 * <p>
 * Input: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
 * Output: false
 * Example 3:
 * <p>
 * Input: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
 * Output: true
 */
class WaterAndJugProblem {
    /**
     * 解题思路：
     * <p>
     * 该问题可以通过Bézout's identity和欧几里得算法来解决。
     * 首先，如果目标容量大于两个水壶容量之和，则无法测量，直接返回false。
     * 其次，如果目标容量能够被两个水壶容量的最大公约数整除，则可以测量，返回true。
     * 否则，返回false。
     * 最大公约数的计算使用了辗转相除法。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 设两个水壶的容量分别为m和n。
     * 在辗转相除法中，两个数的最大公约数的计算时间复杂度为O(log(min(m,n)))。
     * 因此，总的时间复杂度为O(log(min(m,n)))。
     * 请注意，上述复杂度分析假设水壶的容量是大于0的常数。
     * 如果水壶的容量可以变化，则需要考虑这些因素对算法复杂度的影响。
     *
     * @param jug1Capacity
     * @param jug2Capacity
     * @param targetCapacity
     * @return
     */
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        // 如果目标容量大于两个水壶容量之和，则无法测量
        if (targetCapacity > jug1Capacity + jug2Capacity) {
            return false;
        }

        // 如果目标容量能够被两个水壶容量的最大公约数整除，则可以测量
        if (targetCapacity % gcd(jug1Capacity, jug2Capacity) == 0) {
            return true;
        }

        return false;
    }

    // 使用辗转相除法计算最大公约数
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        WaterAndJugProblem solution = new WaterAndJugProblem();

        int jug1Capacity1 = 3, jug2Capacity1 = 5, targetCapacity1 = 4;
        // 预期输出: true
        System.out.println(solution.canMeasureWater(jug1Capacity1, jug2Capacity1, targetCapacity1));

        int jug1Capacity2 = 2, jug2Capacity2 = 6, targetCapacity2 = 5;
        // 预期输出: false
        System.out.println(solution.canMeasureWater(jug1Capacity2, jug2Capacity2, targetCapacity2));

        int jug1Capacity3 = 1, jug2Capacity3 = 2, targetCapacity3 = 3;
        // 预期输出: true
        System.out.println(solution.canMeasureWater(jug1Capacity3, jug2Capacity3, targetCapacity3));
    }
}
