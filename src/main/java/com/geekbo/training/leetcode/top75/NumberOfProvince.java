package com.geekbo.training.leetcode.top75;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvince {

    /**
     * 解决思路：
     * 1. 创建一个布尔数组visited，用于标记城市是否已经被访问过。
     * 2. 遍历每个城市，对于每个未被访问过的城市，从该城市开始进行深度优先搜索。
     * 3. 在深度优先搜索中，访问与当前城市相连的城市，并标记它们为已访问。
     * 4. 搜索完成后，增加省份计数。
     * 5. 重复步骤2，直到所有城市都被访问过。
     * 6. 返回省份计数作为答案。
     *
     * 时间复杂度：O(n^2)，其中n是城市的数量，因为我们需要遍历所有城市以及它们之间的连接关系。
     * 空间复杂度：O(n)，用于存储visited数组。
     */

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true;
        for (int nextCity = 0; nextCity < isConnected.length; nextCity++) {
            if (isConnected[city][nextCity] == 1 && !visited[nextCity]) {
                dfs(isConnected, visited, nextCity);
            }
        }
    }

    /**
     * 解决思路：
     * 1. 使用一个visited数组来跟踪已经访问过的城市。
     * 2. 遍历城市，对于每个未访问的城市，从该城市开始进行BFS。
     * 3. 在BFS中，使用队列来遍历该城市所在的省份，并将访问过的城市标记为已访问。
     * 4. 统计省份的数量，即BFS的次数。
     *
     * 时间复杂度：O(n^2)，其中n是城市的数量，需要遍历n x n的连接矩阵。
     * 空间复杂度：O(n)，需要使用visited数组来跟踪已访问的城市。
     */

    public static int findCircleNumBfs(int[][] isConnected) {
        int n = isConnected.length;
        int provinces = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // 从未访问的城市开始进行BFS
                bfs(isConnected, i, visited);
                provinces++;
            }
        }

        return provinces;
    }

    private static void bfs(int[][] isConnected, int city, boolean[] visited) {
        int n = isConnected.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(city);
        visited[city] = true;

        while (!queue.isEmpty()) {
            int currentCity = queue.poll();
            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (isConnected[currentCity][neighbor] == 1 && !visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        NumberOfProvince numberOfProvinceCounter = new NumberOfProvince();
        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        System.out.println(numberOfProvinceCounter.findCircleNum(isConnected1)); // 应返回2
        System.out.println(numberOfProvinceCounter.findCircleNum(isConnected2)); // 应返回3
    }
}
