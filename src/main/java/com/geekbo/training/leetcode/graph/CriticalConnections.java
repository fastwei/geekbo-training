package com.geekbo.training.leetcode.graph;

import java.util.*;

/**
 * 1192. Critical Connections in a Network
 * Hard
 * There are n servers numbered from 0 to n - 1 connected
 * by undirected server-to-server connections forming a network
 * where connections[i] = [ai, bi] represents a connection between servers ai and bi.
 * Any server can reach other servers directly or indirectly through the network.
 * <p>
 * A critical connection is a connection that, if removed,
 * will make some servers unable to reach some other server.
 * <p>
 * Return all critical connections in the network in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * Example 2:
 * <p>
 * Input: n = 2, connections = [[0,1]]
 * Output: [[0,1]]
 */
public class CriticalConnections {
    private int time = 0;


    /**
     *
     * todo:结果还有些不对，后续再完善
     * @param n
     * @param connections
     * @return
     */
    public List<List<Integer>> criticalConnectionsBfs(int n, List<List<Integer>> connections) {
        // 创建图的邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 构建图的邻接表
        for (List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 初始化访问数组和时间戳数组
        int[] visited = new int[n];
        int[] timestamp = new int[n];

        List<List<Integer>> result = new ArrayList<>();

        // 使用BFS遍历图
        bfs(0, visited, timestamp, graph, result);

        return result;
    }

    private void bfs(int u, int[] visited, int[] timestamp, List<List<Integer>> graph, List<List<Integer>> result) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);
        visited[u] = 1;
        timestamp[u] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int neighbor : graph.get(curr)) {
                if (visited[neighbor] == 0) {
                    queue.offer(neighbor);
                    visited[neighbor] = 1;
                    timestamp[neighbor] = timestamp[curr] + 1;
                }

                // 判断是否为关键连接
                if (timestamp[neighbor] > timestamp[curr]) {
                    result.add(Arrays.asList(curr, neighbor));
                }
            }
        }
    }

    /**
     * 解题思路：
     * <p>
     * 首先，根据输入的连接关系，构建图的邻接表。
     * 然后，通过深度优先搜索（DFS）遍历图，并记录每个节点的访问状态和时间戳。
     * 在DFS遍历过程中，判断每条边是否是关键连接，即判断是否存在一个节点的时间戳小于其相邻节点的时间戳。
     * 如果存在这样的情况，则该边是关键连接，将其记录到结果中。
     * 最后，返回结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 构建图的邻接表的时间复杂度为O(n)，其中n为节点的数量。
     * DFS遍历图的时间复杂度为O(n+m)，其中m为边的数量。
     * 总的时间复杂度为O(n+m)。
     *
     * @param n
     * @param connections
     * @return
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // 创建图的邻接表
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // 构建图的邻接表
        for (List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 初始化访问数组和时间戳数组
        int[] visited = new int[n];
        int[] timestamp = new int[n];

        List<List<Integer>> result = new ArrayList<>();

        // DFS遍历图
        dfs(0, -1, visited, timestamp, graph, result);

        return result;
    }

    private void dfs(int u, int parent, int[] visited, int[] timestamp, List<List<Integer>> graph, List<List<Integer>> result) {
        visited[u] = 1;
        timestamp[u] = time++;
        int currentTime = timestamp[u];

        for (int v : graph.get(u)) {
            if (v == parent) {
                continue;
            }

            if (visited[v] == 0) {
                dfs(v, u, visited, timestamp, graph, result);
            }

            timestamp[u] = Math.min(timestamp[u], timestamp[v]);

            if (currentTime < timestamp[v]) {
                result.add(Arrays.asList(u, v));
            }
        }
    }

    public static void main(String[] args) {
        // 创建解法对象
        CriticalConnections solution = new CriticalConnections();

        // 测试用例1
        // 预期输出: [[1,3]]
        int n1 = 4;
        List<List<Integer>> connections1 = new ArrayList<>();
        connections1.add(Arrays.asList(0, 1));
        connections1.add(Arrays.asList(1, 2));
        connections1.add(Arrays.asList(2, 0));
        connections1.add(Arrays.asList(1, 3));
        List<List<Integer>> result1 = solution.criticalConnections(n1, connections1);
        System.out.println(result1);

        // 测试用例2
        // 预期输出: [[0,1]]
        int n2 = 2;
        List<List<Integer>> connections2 = new ArrayList<>();
        connections2.add(Arrays.asList(0, 1));
        List<List<Integer>> result2 = solution.criticalConnections(n2, connections2);
        System.out.println(result2);
    }
}
