package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 815. Bus Routes
 * <p>
 * Hard
 * <p>
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 * <p>
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 * <p>
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Example 2:
 * <p>
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 */
public class BusRoutes {

    /**
     * 解题思路：
     * <p>
     * 将每个公交车站作为键，存储能够到达该站点的公交车列表作为值的映射关系。
     * 使用广度优先搜索（BFS）来遍历公交车站，从起始站点开始，逐层遍历所有能够到达的公交车站，
     * 直到找到目标站点或者遍历完所有可能的公交车站。
     * 使用一个集合记录已经遍历过的公交车站，避免重复遍历。
     * 使用一个队列来实现广度优先搜索。
     * <p>
     * - 遍历时，对于每个公交车站，获取能够到达该站点的公交车列表。
     * - 对于每辆公交车，如果该公交车已经被访问过，则跳过。
     * - 如果该公交车能够到达目标站点，则返回当前乘坐的公交车数量。
     * - 否则，将该公交车能够到达的公交车站加入队列中。
     * - 如果遍历完所有可能的公交车站仍然没有找到目标站点，则返回-1。
     * <p>
     * 算法复杂度分析：
     * - 假设公交车站的总数为n，公交车的总数为m。
     * - 创建公交车站的映射关系需要O(n * m)的时间复杂度。
     * - 广度优先搜索的时间复杂度为O(n * m)。
     * - 因此，总的时间复杂度为O(n * m)。
     * - 创建映射关系和记录已访问的公交车站需要O(n * m)的空间复杂度。
     *
     * @param routes
     * @param source
     * @param target
     * @return
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        // Create a map to store the buses that can go to each bus stop
        Map<Integer, List<Integer>> busStopMap = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int busStop : routes[i]) {
                List<Integer> buses = busStopMap.getOrDefault(busStop, new ArrayList<>());
                buses.add(i);
                busStopMap.put(busStop, buses);
            }
        }

        // Create a set to track visited bus stops
        Set<Integer> visitedBusStops = new HashSet<>();
        // Create a set to track visited buses
        Set<Integer> visitedBuses = new HashSet<>();
        // Create a queue to perform breadth-first search
        Queue<Integer> queue = new LinkedList<>();
        // Add the source bus stop to the queue
        queue.offer(source);
        // Initialize the number of buses taken to 0
        int numBuses = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currentBusStop = queue.poll();
                // Get the buses that can go to the current bus stop
                List<Integer> buses = busStopMap.get(currentBusStop);
                // Visit each bus that can go to the current bus stop
                for (int bus : buses) {
                    // If the bus has been visited, skip it
                    if (visitedBuses.contains(bus)) {
                        continue;
                    }
                    // Visit the bus
                    visitedBuses.add(bus);
                    // Check if the bus reaches the target bus stop
                    if (Arrays.binarySearch(routes[bus], target) >= 0) {
                        return numBuses + 1;
                    }
                    // Add the bus stops that the bus can go to
                    for (int nextBusStop : routes[bus]) {
                        if (!visitedBusStops.contains(nextBusStop)) {
                            queue.offer(nextBusStop);
                            visitedBusStops.add(nextBusStop);
                        }
                    }
                }
            }
            // Increment the number of buses taken
            numBuses++;
        }

        return -1;
    }

    public static void main(String[] args) {
        BusRoutes busRoutes = new BusRoutes();
        int[][] routes1 = {{1, 2, 7}, {3, 6, 7}};
        int source1 = 1;
        int target1 = 6;
        System.out.println(busRoutes.numBusesToDestination(routes1, source1, target1)); // Output: 2

        int[][] routes2 = {{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}};
        int source2 = 15;
        int target2 = 12;
        System.out.println(busRoutes.numBusesToDestination(routes2, source2, target2)); // Output: -1
    }
}
