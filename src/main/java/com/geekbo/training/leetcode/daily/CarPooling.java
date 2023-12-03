package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1094. Car Pooling
 * Medium
 * <p>
 * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
 * <p>
 * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.
 * <p>
 * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 * Example 2:
 * <p>
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi <= 100
 * 0 <= fromi < toi <= 1000
 * 1 <= capacity <= 105
 */
public class CarPooling {
    /**
     * 解题思路：
     * <p>
     * 首先对 trips 数组按照位置进行排序，确保按照顺序处理每个位置的乘客上下车情况。
     * 使用一个数组记录每个位置上的乘客数量变化，即乘客上车时在该位置数量增加，乘客下车时在该位置数量减少。
     * 遍历位置数组，检查每个位置上的乘客数量是否超过车的容量，如果超过则返回 false，否则返回 true。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：对 trips 数组进行排序的时间复杂度为 O(nlogn)，
     * 遍历位置数组的时间复杂度为 O(1001)，因此总的时间复杂度为 O(nlogn+1001)。
     * 空间复杂度：使用了一个大小为 1001的数组来记录乘客数量变化，因此空间复杂度为 O(1001)。
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        // 对 trips 数组按照位置进行排序
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

        // 使用一个数组记录每个位置上的乘客数量变化
        int[] passengers = new int[1001];

        for (int[] trip : trips) {
            // 乘客上车
            passengers[trip[1]] += trip[0];
            // 乘客下车
            passengers[trip[2]] -= trip[0];
        }

        // 遍历位置数组，判断是否超过车的容量
        for (int i = 0; i < passengers.length; i++) {
            capacity -= passengers[i];
            if (capacity < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        CarPooling carPooling = new CarPooling();
        int[][] trips1 = {{2, 1, 5}, {3, 3, 7}};
        int capacity1 = 4;
        boolean result1 = carPooling.carPooling(trips1, capacity1);
        System.out.println(result1);  // false

        int[][] trips2 = {{2, 1, 5}, {3, 3, 7}};
        int capacity2 = 5;
        boolean result2 = carPooling.carPooling(trips2, capacity2);
        System.out.println(result2);  // true
    }
}
