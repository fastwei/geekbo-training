package com.geekbo.training.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 802. Find Eventual Safe States
 * There is a directed graph of n nodes with each node labeled from 0 to n - 1.
 * The graph is represented by a 0-indexed 2D integer array graph where graph[i]
 * is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
 * <p>
 * A node is a terminal node if there are no outgoing edges.
 * A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
 * <p>
 * Return an array containing all the safe nodes of the graph.
 * The answer should be sorted in ascending order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Illustration of graph
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: The given graph is shown above.
 * Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
 * Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
 * Example 2:
 * <p>
 * Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * Output: [4]
 * Explanation:
 * Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
 */
public class FindEventualSafeStates {
    /**
     * Find all the safe nodes in the directed graph.
     * 解题思路：
     * <p>
     * 遍历图中的每个节点，使用深度优先搜索(DFS)来判断该节点是否安全。
     * 使用一个数组 color 来标记节点的状态，初始时为 0，表示未访问过。
     * 在 DFS 过程中，如果遇到已经标记为安全或不安全的节点，则直接返回该节点的状态。
     * 如果遇到未访问过的节点，将其标记为不安全，并递归地检查其所有邻居节点。
     * 如果所有邻居节点都是安全的，将当前节点标记为安全，并返回 true。
     * 最终，安全节点即为所有标记为安全的节点。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N+E)，其中 N 是节点的数量，E 是边的数量。需要遍历所有的节点和边。
     * 空间复杂度：O(N)，需要使用一个数组来存储节点的状态。
     *
     * @param graph The directed graph represented as a 2D integer array.
     * @return A list of safe nodes in ascending order.
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> safeNodes = new ArrayList<>();
        int n = graph.length;
        int[] color = new int[n];

        // DFS to check if a node is safe
        for (int i = 0; i < n; i++) {
            if (isSafe(graph, color, i)) {
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }

    /**
     * Check if a node is safe in the directed graph using DFS.
     *
     * @param graph The directed graph represented as a 2D integer array.
     * @param color The array to mark the color of each node during DFS.
     * @param node  The current node being visited.
     * @return True if the node is safe, false otherwise.
     */
    private boolean isSafe(int[][] graph, int[] color, int node) {
        // If the node has been visited and marked safe or unsafe, return the result
        if (color[node] != 0) {
            return color[node] == 1;
        }

        // Mark the node as visited but unsafe
        color[node] = -1;

        // DFS to check if all the neighbors of the node are safe
        for (int neighbor : graph[node]) {
            if (!isSafe(graph, color, neighbor)) {
                return false;
            }
        }

        // Mark the node as safe
        color[node] = 1;

        return true;
    }

    public static void main(String[] args) {
        FindEventualSafeStates solution = new FindEventualSafeStates();

        // Test case 1
        int[][] graph1 = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        List<Integer> result1 = solution.eventualSafeNodes(graph1);
        System.out.println("Test Case 1:");
        System.out.println(result1); // Expected: [2, 4, 5, 6]

        // Test case 2
        int[][] graph2 = {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}};
        List<Integer> result2 = solution.eventualSafeNodes(graph2);
        System.out.println("Test Case 2:");
        System.out.println(result2); // Expected: [4]
        // Test case 3
        int[][] graph3 = {{}, {0, 2, 3, 4}, {3}, {4}, {}};
        List<Integer> result3 = solution.eventualSafeNodes(graph3);
        System.out.println("Test Case 3:");
        System.out.println(result3); // Expected: [0, 1, 2, 3, 4]
    }
}



