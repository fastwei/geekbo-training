package com.geekbo.training.leetcode.codeinterview109;


import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 04.01. 节点间通路
 * 中等
 * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 * <p>
 * 示例1:
 * <p>
 * 输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 * 输出：true
 * 示例2:
 * <p>
 * 输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
 * 输出 true
 * 提示：
 * <p>
 * 节点数量n在[0, 1e5]范围内。
 * 节点编号大于等于 0 小于 n。
 * 图中可能存在自环和平行边。
 */
class PathFinder {

    /**
     * 这是一道关于有向图的路径搜索问题。可以使用深度优先搜索（DFS）来解决。
     * <p>
     * 解题思路：
     * <p>
     * 创建一个布尔型数组visited，用于记录每个节点是否被访问过。
     * 从起始节点开始进行DFS搜索，递归地访问所有与当前节点相邻的未被访问过的节点。
     * 如果找到目标节点，则返回true。
     * 如果遍历完所有的节点都没有找到目标节点，则返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(V+E)，其中V是节点数量，E是边的数量。需要遍历所有的节点和边。
     * 空间复杂度：O(V)，需要使用visited数组来记录节点的访问状态。
     *
     * @param n
     * @param graph
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        // 创建一个邻接表来存储图的边
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        // 将图的边添加到邻接表中
        for (int[] edge : graph) {
            adjList.get(edge[0]).add(edge[1]);
        }

        // 创建一个布尔型数组来记录节点的访问状态
        boolean[] visited = new boolean[n];

        // 调用DFS函数进行搜索
        return dfs(adjList, visited, start, target);
    }

    private boolean dfs(List<List<Integer>> adjList, boolean[] visited, int curr, int target) {
        // 如果当前节点就是目标节点，则返回true
        if (curr == target) {
            return true;
        }

        // 标记当前节点为已访问
        visited[curr] = true;

        // 递归地访问所有与当前节点相邻的未被访问过的节点
        for (int next : adjList.get(curr)) {
            if (!visited[next]) {
                if (dfs(adjList, visited, next, target)) {
                    return true;
                }
            }
        }

        // 如果遍历完所有的节点都没有找到目标节点，则返回false
        return false;
    }

    public static void main(String[] args) {
        PathFinder solution = new PathFinder();

        // 测试用例1
        int n1 = 3;
        int[][] graph1 = {{0, 1}, {0, 2}, {1, 2}, {1, 2}};
        int start1 = 0;
        int target1 = 2;
        boolean result1 = solution.findWhetherExistsPath(n1, graph1, start1, target1);
        System.out.println(result1); // 输出：true

    }
}