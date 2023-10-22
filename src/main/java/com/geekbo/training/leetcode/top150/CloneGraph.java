package com.geekbo.training.leetcode.top150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Node2 {
    public int val;
    public List<Node2> neighbors;
    public Node2() {
        val = 0;
        neighbors = new ArrayList<Node2>();
    }
    public Node2(int _val) {
        val = _val;
        neighbors = new ArrayList<Node2>();
    }
    public Node2(int _val, ArrayList<Node2> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
/**
 * Given a reference of a node in a connected undirected graph.
 * <p>
 * Return a deep copy (clone) of the graph.
 * <p>
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * Test case format:
 * <p>
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 * <p>
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 * <p>
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation: There are 4 nodes in the graph.
 * 1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * 3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
 * 4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
 * Example 2:
 * <p>
 * <p>
 * Input: adjList = [[]]
 * Output: [[]]
 * Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
 * Example 3:
 * <p>
 * Input: adjList = []
 * Output: []
 * Explanation: This an empty graph, it does not have any nodes.
 */
public class CloneGraph {
    public static Node2 cloneGraph(Node2 node) {
        if (node == null) {
            return null;
        }

        // Create a map to store the cloned nodes
        Map<Node2, Node2> clonedMap = new HashMap<>();

        // Perform DFS to clone the graph
        dfs(node, clonedMap);

        // Return the cloned node
        return clonedMap.get(node);
    }

    /**
     * 解题思路： 本题可以使用深度优先搜索（DFS）来进行图的克隆。
     * 我们使用一个哈希表 clonedMap 来存储已经克隆过的节点，键为原始节点，值为克隆节点。
     * 对于给定的节点 node，我们首先创建一个克隆节点 clone，并将其添加到 clonedMap 中。
     * 然后我们遍历 node 的邻居节点，并递归克隆每个邻居节点。
     * 最后，将克隆节点的邻居列表更新为克隆的邻居节点列表，即可完成图的克隆。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N + M)，其中 N 是图中的节点数，M 是图中的边数。需要遍历所有的节点和边。
     * 空间复杂度：O(N)，使用了哈希表来存储克隆过的节点。
     *
     * @param node
     * @param clonedMap
     */
    private static void dfs(Node2 node, Map<Node2, Node2> clonedMap) {
        // Create a clone of the current node
        Node2 clone = new Node2(node.val);

        // Add the clone to the map
        clonedMap.put(node, clone);

        // Recursively clone the neighbors
        for (Node2 neighbor : node.neighbors) {
            if (!clonedMap.containsKey(neighbor)) {
                dfs(neighbor, clonedMap);
            }

            // Add the cloned neighbor to the clone's neighbors list
            clone.neighbors.add(clonedMap.get(neighbor));
        }
    }

    public static void main(String[] args) {
        // Example 1
        Node2 node1 = new Node2(1);
        Node2 node2 = new Node2(2);
        Node2 node3 = new Node2(3);
        Node2 node4 = new Node2(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        Node2 clonedNode1 = cloneGraph(node1);
        System.out.println(clonedNode1.val); // Output: 1
        System.out.println(clonedNode1.neighbors.size()); // Output: 2

        // Example 2
        Node2 node5 = new Node2(1);
        Node2 clonedNode2 = cloneGraph(node5);
        System.out.println(clonedNode2.val); // Output: 1
        System.out.println(clonedNode2.neighbors.size()); // Output: 0

        // Example 3
        Node2 clonedNode3 = cloneGraph(null);
        System.out.println(clonedNode3); // Output: null
    }
}
