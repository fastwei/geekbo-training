package com.geekbo.training.leetcode.top75;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1466. Reorder Routes to Make All Paths Lead to the City Zero
 * <p>
 * There are n cities numbered from 0 to n - 1 and n - 1 roads
 * such that there is only one way to travel between two different cities (this network form a tree).
 * Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 * <p>
 * Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.
 * <p>
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 * <p>
 * Your task consists of reorienting some roads such that each city can visit the city 0.
 * Return the minimum number of edges changed.
 * <p>
 * It's guaranteed that each city can reach city 0 after reorder.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 2:
 * <p>
 * <p>
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 * Example 3:
 * <p>
 * Input: n = 3, connections = [[1,0],[2,0]]
 * Output: 0
 */
public class ReorderRoutesMakeAllPathsLeadCityZero {

    /**
     * 解决思路：
     * 1. 创建一个邻接表来表示城市之间的连接关系。
     * 2. 使用DFS遍历城市，从城市0开始，标记已访问的城市，并计算每个城市可到达城市0的数量。
     * 3. 返回未访问的城市数量减1，即为需要改变方向的最小道路数量。
     * <p>
     * 时间复杂度：O(n)，其中n是城市的数量，因为我们只需要遍历一次所有城市。
     * 空间复杂度：O(n)，需要使用邻接表来存储城市之间的连接关系。
     */

    public int minReorder(int n, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            graph.get(from).add(new int[]{to, 1}); // 表示方向需要改变
            graph.get(to).add(new int[]{from, 0}); // 表示方向不需要改变
        }

        boolean[] visited = new boolean[n];
        return dfs(0, graph, visited);
    }

    private int dfs(int city, List<List<int[]>> graph, boolean[] visited) {
        visited[city] = true;
        int changeDirectionCount = 0;
        for (int[] neighbor : graph.get(city)) {
            int neighborCity = neighbor[0];
            int direction = neighbor[1];
            if (!visited[neighborCity]) {
                changeDirectionCount += direction;
                changeDirectionCount += dfs(neighborCity, graph, visited);
            }
        }
        return changeDirectionCount;
    }

    /**
     * 解决思路：
     * 1. 构建一个邻接表来表示城市之间的连接关系。
     * 2. 使用BFS遍历城市，从每个城市出发，确保每个城市都能到达城市0。
     * 3. 统计需要反转的道路数量，即BFS的层数。
     * <p>
     * 时间复杂度：O(n)，其中n是城市的数量，因为每个城市只被遍历一次。
     * 空间复杂度：O(n)，需要使用邻接表和队列来存储城市连接信息。
     */

    public static int minReorderBfs(int n, int[][] connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] connection : connections) {
            int from = connection[0];
            int to = connection[1];
            graph.get(from).add(to);
            graph.get(to).add(-from); // 使用负数表示反向连接
        }

        int changes = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int city = queue.poll();
            visited[Math.abs(city)] = true;
            for (int neighbor : graph.get(Math.abs(city))) {
                if (!visited[Math.abs(neighbor)]) {
                    if (neighbor > 0) {
                        changes++; // 需要反转的道路
                    }
                    queue.offer(neighbor);
                }
            }
        }

        return changes;
    }


    public static void main(String[] args) {
        ReorderRoutesMakeAllPathsLeadCityZero reorderRoutesMakeAllPathsLeadCityZero = new ReorderRoutesMakeAllPathsLeadCityZero();
        int[][] connections1 = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        System.out.println(reorderRoutesMakeAllPathsLeadCityZero.minReorder(6, connections1)); // 应返回3
        System.out.println(reorderRoutesMakeAllPathsLeadCityZero.minReorderBfs(6, connections1)); // 应返回3

        int[][] connections2 = {{1, 0}, {1, 2}, {3, 2}, {3, 4}};
        System.out.println(reorderRoutesMakeAllPathsLeadCityZero.minReorder(5, connections2)); // 应返回2
        System.out.println(reorderRoutesMakeAllPathsLeadCityZero.minReorderBfs(5, connections2)); // 应返回2

        int[][] connections3 = {{1, 0}, {2, 0}};
        System.out.println(reorderRoutesMakeAllPathsLeadCityZero.minReorder(3, connections3)); // 应返回2
        System.out.println(reorderRoutesMakeAllPathsLeadCityZero.minReorderBfs(3, connections3)); // 应返回0
    }
}
