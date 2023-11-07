package com.geekbo.training.leetcode.graph;

import java.util.*;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
 * find all possible paths from node 0 to node n - 1 and return them in any order.
 * <p>
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
 * (i.e., there is a directed edge from node i to node graph[i][j]).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Example 2:
 * <p>
 * <p>
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 */
public class AllPathsFromSourceToTarget {

    /**
     * Find all possible paths from node 0 to node n-1 in a directed acyclic graph (DAG).
     * 解题思路：
     * <p>
     * 使用深度优先搜索 (DFS) 来遍历图，找到所有从节点0到节点n-1的路径。
     * 从节点0开始进行DFS，将当前路径存储在一个列表中，如果当前节点是目标节点，则将当前路径添加到结果列表中。
     * 对于当前节点的每个邻居节点，将邻居节点添加到当前路径中，并递归调用DFS来探索邻居节点。
     * 在递归调用结束后，需要将邻居节点从当前路径中移除，以便在探索其他路径时不会出错。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(2^N * N)
     *
     * @param graph The adjacency list representation of the graph.
     * @return A list of all possible paths from node 0 to node n-1.
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0); // Start at node 0
        dfs(graph, 0, currentPath, paths);
        return paths;
    }

    /**
     * Depth-first search to find all paths from the current node to the target node.
     *
     * @param graph       The adjacency list representation of the graph.
     * @param currentNode The current node in the DFS.
     * @param currentPath The current path from node 0 to the current node.
     * @param paths       The list to store all paths from node 0 to node n-1.
     */
    private void dfs(int[][] graph, int currentNode, List<Integer> currentPath, List<List<Integer>> paths) {
        if (currentNode == graph.length - 1) {
            paths.add(new ArrayList<>(currentPath)); // Found a path to the target node
            return;
        }

        for (int neighbor : graph[currentNode]) {
            currentPath.add(neighbor); // Add the neighbor to the current path
            dfs(graph, neighbor, currentPath, paths); // Recursively explore the neighbor
            currentPath.remove(currentPath.size() - 1); // Remove the neighbor from the current path
        }
    }


    /**
     * Find all possible paths from node 0 to node n-1 in a directed acyclic graph (DAG) using BFS.
     *
     * @param graph The adjacency list representation of the graph.
     * @return A list of all possible paths from node 0 to node n-1.
     */
    public List<List<Integer>> allPathsSourceTargetBFS(int[][] graph) {
        List<List<Integer>> paths = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(Arrays.asList(0)); // Start at node 0

        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int currentNode = currentPath.get(currentPath.size() - 1);

            if (currentNode == graph.length - 1) {
                paths.add(currentPath);
                continue;
            }

            for (int neighbor : graph[currentNode]) {
                List<Integer> newPath = new ArrayList<>(currentPath);
                newPath.add(neighbor);
                queue.offer(newPath);
            }
        }

        return paths;
    }
    public static void main(String[] args) {
        AllPathsFromSourceToTarget solution = new AllPathsFromSourceToTarget();

        // Test case 1
        int[][] graph1 = {{1, 2}, {3}, {3}, {}};
        List<List<Integer>> paths1 = solution.allPathsSourceTarget(graph1);
        System.out.println("Test Case 1:");
        System.out.println(paths1); // Expected: [[0, 1, 3], [0, 2, 3]]

        // Test case 2
        int[][] graph2 = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        List<List<Integer>> paths2 = solution.allPathsSourceTarget(graph2);
        System.out.println("Test Case 2:");
        System.out.println(paths2); // Expected: [[0, 4], [0, 3, 4], [0, 1, 3, 4], [0, 1, 2, 3, 4], [0, 1, 4]]
    }
}
