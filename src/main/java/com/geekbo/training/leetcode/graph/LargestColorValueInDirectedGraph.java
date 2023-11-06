package com.geekbo.training.leetcode.graph;
/**
 * 1857. Largest Color Value in a Directed Graph
 * Hard
 * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
 * <p>
 * You are given a string colors where colors[i] is a lowercase English letter
 * representing the color of the ith node in this graph (0-indexed).
 * You are also given a 2D array edges where edges[j] = [aj, bj] indicates
 * that there is a directed edge from node aj to node bj.
 * <p>
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk
 * such that there is a directed edge from xi to xi+1 for every 1 <= i < k.
 * The color value of the path is the number of nodes that are colored
 * the most frequently occurring color along that path.
 * <p>
 * Return the largest color value of any valid path in the given graph,
 * or -1 if the graph contains a cycle.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: colors = "a", edges = [[0,0]]
 * Output: -1
 * Explanation: There is a cycle from 0 to 0.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LargestColorValueInDirectedGraph {

    /**
     * BFS实现
     * 首先构建图的邻接表和节点入度数组，然后将入度为0的节点加入队列。
     * 在BFS过程中，更新每个节点的颜色频次，并将入度减一。当所有节点都被访问后，判断是否存在环。
     * 最后，在dp数组中找到最大的颜色频次作为结果返回。
     *
     * @param colors
     * @param edges
     * @return
     */
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[][] dp = new int[n][26];
        int[] indegree = new int[n];
        int result = 0;

        // 构建图的邻接表和节点入度数组
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        // 将入度为0的节点加入队列
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited++;

            dp[u][colors.charAt(u) - 'a']++;

            for (int v : graph.get(u)) {
                for (int i = 0; i < 26; i++) {
                    dp[v][i] = Math.max(dp[v][i], dp[u][i]);
                }
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (visited != n) {
            return -1; // 存在环
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }

    /**
     * 解题思路：
     * <p>
     * 首先，根据输入的colors和edges构建图的邻接表。
     * 创建一个dp数组，dp[i][j]表示以节点i为起点的路径中颜色j的最大频次。
     * 创建一个visited数组，visited[i]表示节点i的访问状态，0表示未访问，1表示正在访问，2表示已访问。
     * 遍历每个节点，使用深度优先搜索（DFS）计算以当前节点为起点的所有路径中各种颜色的最大频次，并判断是否存在环。
     * 如果存在环，则返回-1。
     * 遍历完成后，在dp数组中找 到最大的颜色频次，作为结果返回。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 构建邻接表的时间复杂度为O(m)，其中m是边的数量。
     * 遍历每个节点的时间复杂度为O(n)，其中n是节点的数量。
     * 在DFS中，每个节点最多访问一次，且每次访问都要更新dp数组，时间复杂度为O(n)。
     * 因此，总的时间复杂度为O(n + m)。
     * 由于使用了额外的dp和visited数组，空间复杂度为O(n)。
     *
     * @param colors
     * @param edges
     * @return
     */
    public int largestPathValueDfs(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[][] dp = new int[n][26];
        int[] visited = new int[n];
        int result = 0;

        // 构建图的邻接表
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
        }

        // 遍历每个节点，计算每种颜色的最大频次
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                if (dfs(i, colors, graph, dp, visited)) {
                    return -1; // 存在环，返回-1
                }
            }
            for (int j = 0; j < 26; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }

    private boolean dfs(int u, String colors, List<List<Integer>> graph, int[][] dp, int[] visited) {
        visited[u] = 1;

        for (int v : graph.get(u)) {
            if (visited[v] == 1) {
                return true; // 存在环
            }
            if (visited[v] == 0) {
                if (dfs(v, colors, graph, dp, visited)) {
                    return true; // 存在环
                }
            }
            for (int i = 0; i < 26; i++) {
                dp[u][i] = Math.max(dp[u][i], dp[v][i]);
            }
        }

        dp[u][colors.charAt(u) - 'a']++;

        visited[u] = 2;

        return false;
    }

    public static void main(String[] args) {
        // 创建解法对象
        LargestColorValueInDirectedGraph solution = new LargestColorValueInDirectedGraph();

        // 测试用例1
        // 预期输出: 3
        String colors1 = "abaca";
        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {3, 4}};
        int result1 = solution.largestPathValue(colors1, edges1);
        System.out.println(result1);

        // 测试用例2
        // 预期输出: -1
        String colors2 = "a";
        int[][] edges2 = {{0, 0}};
        int result2 = solution.largestPathValue(colors2, edges2);
        System.out.println(result2);
    }
}
