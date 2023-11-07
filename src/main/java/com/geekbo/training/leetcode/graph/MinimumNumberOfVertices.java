package com.geekbo.training.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1557. Minimum Number of Vertices to Reach All Nodes
 * Medium
 * Given a directed acyclic graph, with n vertices numbered from 0 to n-1,
 * and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.
 * <p>
 * Find the smallest set of vertices from which all nodes in the graph are reachable.
 * It's guaranteed that a unique solution exists.
 * <p>
 * Notice that you can return the vertices in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
 * Output: [0,3]
 * Explanation: It's not possible to reach all the nodes from a single vertex.
 * From 0 we can reach [0,1,2,5]. From 3 we can reach [3,4,2,5]. So we output [0,3].
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
 * Output: [0,2,3]
 * Explanation: Notice that vertices 0, 3 and 2 are not reachable from any other node, so we must include them.
 * Also any of these vertices can reach nodes 1 and 4.
 */
public class MinimumNumberOfVertices {

    /**
     * 寻找可以到达所有节点的最小顶点集合
     * 解题思路：
     * <p>
     * 遍历边数组，统计每个节点的入度。
     * 寻找入度为0的节点，即没有任何入边的节点，将其加入结果集合。
     * 返回结果集合。
     * 算法复杂度分析：
     * <p>
     * 遍历边数组，时间复杂度为O(m)，其中m为边的数量。
     * 统计入度为0的节点，时间复杂度为O(n)，其中n为节点的数量。
     * 因此，总的时间复杂度为O(m + n)。
     * 空间复杂度分析：
     * <p>
     * 使用了一个数组来记录每个节点的入度，空间复杂度为O(n)，其中n为节点的数量。
     *
     * @param n     节点数量
     * @param edges 边数组
     * @return 可以到达所有节点的最小顶点集合
     */
    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        List<Integer> result = new ArrayList<>();
        int[] inDegree = new int[n]; // 记录每个节点的入度
        for (List<Integer> edge : edges) {
            int from = edge.get(0); // 边的起点
            int to = edge.get(1); // 边的终点
            inDegree[to]++; // 终点的入度加1
        }
        // 寻找入度为0的节点，即没有任何入边的节点
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // 测试用例1
        int n1 = 6;
        List<List<Integer>> edges1 = new ArrayList<>();
        edges1.add(Arrays.asList(0, 1));
        edges1.add(Arrays.asList(0, 2));
        edges1.add(Arrays.asList(2, 5));
        edges1.add(Arrays.asList(3, 4));
        edges1.add(Arrays.asList(4, 2));
        // 预期输出：[0, 3]
        System.out.println(findSmallestSetOfVertices(n1, edges1));

        // 测试用例2
        int n2 = 5;
        List<List<Integer>> edges2 = new ArrayList<>();
        edges2.add(Arrays.asList(0, 1));
        edges2.add(Arrays.asList(2, 1));
        edges2.add(Arrays.asList(3, 1));
        edges2.add(Arrays.asList(1, 4));
        edges2.add(Arrays.asList(2, 4));
        // 预期输出：[0, 2, 3]
        System.out.println(findSmallestSetOfVertices(n2, edges2));
    }
}
