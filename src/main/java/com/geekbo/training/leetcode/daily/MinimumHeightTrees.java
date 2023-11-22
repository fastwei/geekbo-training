package com.geekbo.training.leetcode.daily;

import java.util.*;

/**
 * 310. Minimum Height Trees
 * Medium
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
 * <p>
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 * <p>
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * <p>
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 * Example 2:
 * <p>
 * <p>
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 */
class MinimumHeightTrees {
    /**
     * 解题思路：
     * <p>
     * 这道题可以使用拓扑排序和广度优先搜索（BFS）来解决。
     * 首先，我们需要构建一个邻接表来表示树的连接关系。对于每个节点，我们使用一个集合来存储与其相邻的节点。
     * 我们还需要一个数组来存储每个节点的度数（连接的边数）。
     * 我们从叶节点开始，将度数为1的节点加入到队列中。
     * 在每一轮中，我们移除队列中的叶节点，并更新其相邻节点的度数。
     * 如果更新后的度数为1，说明该节点也成为了新的叶节点。我们将这些新的叶节点加入到队列中，并继续下一轮的操作。
     * 当只剩下1个或2个节点时，它们就是最小高度树的根节点。我们将它们加入到结果列表中。
     * 最后，返回结果列表。
     * <p>
     * 算法 复杂度分析：
     * <p>
     * 构建邻接表的时间复杂度为O(n)，其中n是节点的个数。
     * 构建度数数组的时间复杂度为O(n-1)，遍历边的数组。
     * 执行BFS的时间复杂度为O(n-2)，最多需要遍历n-2个节点。
     * 总时间复杂度为O(n)。
     * 空间复杂度为O(n)，需要额外的空间来存储邻接表、度数数组和结果列表。
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return Collections.singletonList(0); // If there is only one node, it is the root of the minimum height tree
        }

        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }

        int[] degrees = new int[n]; // Array to store the degree of each node

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            graph.get(node1).add(node2);
            graph.get(node2).add(node1);

            degrees[node1]++;
            degrees[node2]++;
        }

        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degrees[i] == 1) { // Add the leaf nodes to the queue
                leaves.offer(i);
            }
        }

        while (n > 2) {
            int numLeaves = leaves.size();
            n -= numLeaves;

            for (int i = 0; i < numLeaves; i++) {
                int leaf = leaves.poll();

                Set<Integer> neighbors = graph.get(leaf);
                for (int neighbor : neighbors) {
                    degrees[neighbor]--;
                    if (degrees[neighbor] == 1) { // Add the new leaf nodes to the queue
                        leaves.offer(neighbor);
                    }
                }
            }
        }

        List<Integer> minHeightTrees = new ArrayList<>();
        while (!leaves.isEmpty()) {
            minHeightTrees.add(leaves.poll());
        }

        return minHeightTrees;
    }

    public static void main(String[] args) {
        MinimumHeightTrees solution = new MinimumHeightTrees();

        int n1 = 4;
        int[][] edges1 = {{1, 0}, {1, 2}, {1, 3}};
        System.out.println(solution.findMinHeightTrees(n1, edges1)); // Expected output: [1]

        int n2 = 6;
        int[][] edges2 = {{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        System.out.println(solution.findMinHeightTrees(n2, edges2)); // Expected output: [3, 4]
    }
}
