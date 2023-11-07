package com.geekbo.training.leetcode.graph;

/**
 * 785. Is Graph Bipartite?
 * Medium
 * <p>
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
 * You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
 * More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 * <p>
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that
 * there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B
 * such that every edge in the graph connects a node in set A and a node in set B.
 * <p>
 * Return true if and only if it is bipartite.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: There is no way to partition the nodes into two independent sets
 * such that every edge connects a node in one and a node in the other.
 * Example 2:
 * <p>
 * <p>
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 */
public class IsGraphBipartite {

    /**
     * 检查图是否为二分图
     * 解题思路：
     * <p>
     * 使用深度优先搜索（DFS）来判断图是否为二分图。
     * 创建一个大小为n的数组colors，用于记录每个节点的颜色。初始时，所有节点都未染色，用0表示。
     * 对于每个未染色的节点，从该节点开始进行DFS，并将其染成红色（用1表示），然后递归遍历其邻居节点。
     * 如果邻居节点已经染成了红色，说明不是二分图，返回false。
     * 如果邻居节点未染色，则将其染成绿色（用-1表示），并递归遍历其邻居节点。
     * 如果递归过程中出现任何不满足二分图条件的情况，返回false。
     * 如果所有节点都被染色并满足二分图条件，返回true。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 在最坏情况下，需要遍历图中的所有边和节点，时间复杂度为O(V+E)，其中V是节点数量，E是边的数量。
     * 使用了一个大小为n的数组colors来存储节点的颜色，空间复杂度为O(n)，其中n是节点数量。
     * <p>
     * 请注意，给定的图可能不是连通图，因此我们需要对所有的节点进行遍历，确保每个节点都被染色。
     *
     * @param graph 图的邻接表表示
     * @return true如果图是二分图，否则返回false
     */
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // 记录每个节点的颜色，0表示未染色，1表示染成红色，-1表示染成绿色

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) { // 如果节点未染色，则从该节点开始进行染色
                if (!dfs(graph, colors, i, 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfs(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color; // 将当前节点染色
        int[] neighbors = graph[node];
        for (int neighbor : neighbors) {
            if (colors[neighbor] == color) { // 如果邻居节点已经染成了相同的颜色，说明不是二分图
                return false;
            }
            if (colors[neighbor] == 0 && !dfs(graph, colors, neighbor, -color)) { // 对邻居节点进行染色，并检查是否是二分图
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] graph1 = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        // 预期输出：false
        System.out.println(isBipartite(graph1));

        // 测试用例2
        int[][] graph2 = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        // 预期输出：true
        System.out.println(isBipartite(graph2));
    }
}
