package com.geekbo.training.leetcode.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2642. Design Graph With Shortest Path Calculator
 * Hard
 * There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1.
 * The edges of the graph are initially represented by the given array edges
 * where edges[i] = [fromi, toi, edgeCosti] meaning that there is an edge from fromi to toi
 * with the cost edgeCosti.
 * <p>
 * Implement the Graph class:
 * <p>
 * Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.
 * addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost].
 * It is guaranteed that there is no edge between the two nodes before adding this one.
 * int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2.
 * If no path exists, return -1. The cost of a path is the sum of the costs of the edges in the path.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
 * [[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
 * Output
 * [null, 6, -1, null, 6]
 * <p>
 * Explanation
 * Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
 * g.shortestPath(3, 2); // return 6. The shortest path from 3 to 2 in the first diagram
 * above is 3 -> 0 -> 1 -> 2 with a total cost of 3 + 2 + 1 = 6.
 * g.shortestPath(0, 3); // return -1. There is no path from 0 to 3.
 * g.addEdge([1, 3, 4]); // We add an edge from node 1 to node 3, and we get the second diagram above.
 * g.shortestPath(0, 3); // return 6. The shortest path from 0 to 3 now is 0 -> 1 -> 3
 * with a total cost of 2 + 4 = 6.
 */

/**
 *
 * 在构造函数中，我们根据输入的节点数量和边的数组来初始化邻接表。
 * 然后，我们实现了addEdge方法，用于向邻接表中添加新的边。
 *
 * 最重要的是实现shortestPath方法。
 * 该方法使用Dijkstra算法来计算从一个节点到另一个节点的最短路径。
 * 我们使用一个优先队列来存储待处理的节点，并根据节点的距离进行排序。
 * 我们还使用一个距离数组来记录每个节点的最短距离。
 *
 * 算法的步骤如下：
 *
 * 初始化距离数组，将源节点的距离设置为0，其他节点的距离设置为无穷大。
 * 将源节点添加到优先队列中。
 * 从优先队列中取出节点，如果该节点是目标节点，则返回其距离。
 * 遍历当前节点的所有邻居节点，计算新的距离，并更新距离数组和优先队列。
 * 重复步骤3和步骤4，直到优先队列为空或找到目标节点为止。
 * 在main方法中，我们创建了一个Graph对象，并使用提供的测试用例进行测试。
 *
 * 算法复杂度分析：
 *
 * 构造函数的时间复杂度是O(m)，其中m是边的数量。
 * addEdge方法的时间复杂度是O(1)。
 * shortestPath方法的时间复杂度是O((n+m)logn)，其中n是节点的数量，m是边的数量。
 * 因为我们最多遍历每个节点一次，并对每个节点的邻居进行更新操作，而优先队列的插入和删除操作的时间复杂度是logn。
 * 总结： 本题中，我们实现了一个有向加权图的类，通过构造函数初始化图，并实现了添加边和计算最短路径的方法。
 * 我们使用Dijkstra算法来寻找最短路径，使用邻接表来存储图的结构。
 *
 * todo:待加深理解
 */
class Graph {
    private int n; // number of nodes
    private List<List<int[]>> adjList; // adjacency list to store the graph

    public Graph(int n, int[][] edges) {
        this.n = n;
        adjList = new ArrayList<>();

        // Initialize the adjacency list
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Add edges to the adjacency list
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];
            adjList.get(from).add(new int[]{to, cost});
        }
    }

    public void addEdge(int[] edge) {
        int from = edge[0];
        int to = edge[1];
        int cost = edge[2];
        adjList.get(from).add(new int[]{to, cost});
    }

    public int shortestPath(int node1, int node2) {
        // Initialize distances array with infinity except for the source node
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[node1] = 0;

        // Create a priority queue to store nodes based on their distances
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{node1, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0];
            int currDist = curr[1];

            // If the current node is the destination node, return the distance
            if (currNode == node2) {
                return currDist;
            }

            // If the distance to the current node is greater than the current distance, skip it
            if (currDist > distances[currNode]) {
                continue;
            }

            // Iterate through the neighbors of the current node
            for (int[] neighbor : adjList.get(currNode)) {
                int nextNode = neighbor[0];
                int edgeCost = neighbor[1];
                int newDist = currDist + edgeCost;

                // If the new distance is shorter,
                // update the distance and add the neighbor to the priority queue
                if (newDist < distances[nextNode]) {
                    distances[nextNode] = newDist;
                    pq.offer(new int[]{nextNode, newDist});
                }
            }
        }

        // If no path exists from node1 to node2, return -1
        return -1;
    }
}