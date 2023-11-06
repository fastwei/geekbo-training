package com.geekbo.training.leetcode.daily;

import java.util.PriorityQueue;

/**
 * 1845. Seat Reservation Manager
 * Medium
 * Design a system that manages the reservation state of n seats that are numbered from 1 to n.
 * <p>
 * Implement the SeatManager class:
 * <p>
 * SeatManager(int n) Initializes a SeatManager object that will manage n seats numbered from 1 to n.
 * All seats are initially available.
 * int reserve() Fetches the smallest-numbered unreserved seat, reserves it, and returns its number.
 * void unreserve(int seatNumber) Unreserves the seat with the given seatNumber.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["SeatManager", "reserve", "reserve", "unreserve", "reserve", "reserve", "reserve", "reserve", "unreserve"]
 * [[5], [], [], [2], [], [], [], [], [5]]
 * Output
 * [null, 1, 2, null, 2, 3, 4, 5, null]
 * <p>
 * Explanation
 * SeatManager seatManager = new SeatManager(5); // Initializes a SeatManager with 5 seats.
 * seatManager.reserve();    // All seats are available, so return the lowest numbered seat, which is 1.
 * seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
 * seatManager.unreserve(2); // Unreserve seat 2, so now the available seats are [2,3,4,5].
 * seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
 * seatManager.reserve();    // The available seats are [3,4,5], so return the lowest of them, which is 3.
 * seatManager.reserve();    // The available seats are [4,5], so return the lowest of them, which is 4.
 * seatManager.reserve();    // The only available seat is seat 5, so return 5.
 * seatManager.unreserve(5); // Unreserve seat 5, so now the available seats are [5].
 */
public class SeatManager {
    private PriorityQueue<Integer> availableSeats;

    /**
     * 初始化 SeatManager 对象，管理从 1 到 n 的 n 个座位。所有座位初始状态为可用。
     * 解题思路：
     * <p>
     * 我们使用一个最小堆来存储可用座位的编号。初始化时，将所有座位的编号加入到最小堆中。
     * reserve() 方法从最小堆中取出最小编号的未预订座位，并将其预订。返回座位编号。
     * unreserve(int seatNumber) 方法将指定编号的座位加入到最小堆中，表示该座位可用。
     * 在测试用例中，我们创建一个 SeatManager 对象，座位数为 5。然后进行一系列的座位预订和取消预订操作，观察输出结果是否符合预期。
     * 算法复杂度分析：
     * <p>
     * 初始化时，将 n 个座位的编号加入到最小堆中的时间复杂度为 O(n * log n)。
     * reserve() 方法从最小堆中取出座位编号的时间复杂度为 O(log n)。
     *
     * @param n 座位数
     */
    public SeatManager(int n) {
        // 创建一个最小堆，用于存储可用座位的编号
        availableSeats = new PriorityQueue<>();

        // 将座位编号从 1 到 n 添加到最小堆中
        for (int i = 1; i <= n; i++) {
            availableSeats.offer(i);
        }
    }

    /**
     * 获取最小编号的未预订座位，预订该座位并返回其编号。
     *
     * @return 最小编号的未预订座位的编号
     */
    public int reserve() {
        // 从最小堆中获取最小编号的未预订座位的编号
        int seatNumber = availableSeats.poll();

        return seatNumber;
    }

    /**
     * 取消预订指定编号的座位。
     *
     * @param seatNumber 座位编号
     */
    public void unreserve(int seatNumber) {
        // 将指定编号的座位加入到最小堆中，表示座位可用
        availableSeats.offer(seatNumber);
    }

    public static void main(String[] args) {
        // 创建 SeatManager 对象，座位数为 5
        SeatManager seatManager = new SeatManager(5);

        // 预订座位
        System.out.println(seatManager.reserve());    // 预期输出: 1
        System.out.println(seatManager.reserve());    // 预期输出: 2

        // 取消预订座位
        seatManager.unreserve(2);

        // 预订座位
        System.out.println(seatManager.reserve());    // 预期输出: 2
        System.out.println(seatManager.reserve());    // 预期输出: 3
        System.out.println(seatManager.reserve());    // 预期输出: 4
        System.out.println(seatManager.reserve());    // 预期输出: 5

        // 取消预订座位
        seatManager.unreserve(5);
    }
}
