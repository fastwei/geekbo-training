package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 2646. Minimize the Total Price of the Trips
 * Hard
 * <p>
 * There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 * <p>
 * Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith node.
 * <p>
 * The price sum of a given path is the sum of the prices of all nodes lying on that path.
 * <p>
 * Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi] indicates that you start the ith trip from the node starti and travel to the node endi by any path you like.
 * <p>
 * Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.
 * <p>
 * Return the minimum total price sum to perform all the given trips.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
 * Output: 23
 * Explanation: The diagram above denotes the tree after rooting it at node 2. The first part shows the initial tree and the second part shows the tree after choosing nodes 0, 2, and 3, and making their price half.
 * For the 1st trip, we choose path [0,1,3]. The price sum of that path is 1 + 2 + 3 = 6.
 * For the 2nd trip, we choose path [2,1]. The price sum of that path is 2 + 5 = 7.
 * For the 3rd trip, we choose path [2,1,3]. The price sum of that path is 5 + 2 + 3 = 10.
 * The total price sum of all trips is 6 + 7 + 10 = 23.
 * It can be proven, that 23 is the minimum answer that we can achieve.
 * Example 2:
 * <p>
 * <p>
 * Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
 * Output: 1
 * Explanation: The diagram above denotes the tree after rooting it at node 0. The first part shows the initial tree and the second part shows the tree after choosing node 0, and making its price half.
 * For the 1st trip, we choose path [0]. The price sum of that path is 1.
 * The total price sum of all trips is 1. It can be proven, that 1 is the minimum answer that we can achieve.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 50
 * edges.length == n - 1
 * 0 <= ai, bi <= n - 1
 * edges represents a valid tree.
 * price.length == n
 * price[i] is an even integer.
 * 1 <= price[i] <= 1000
 * 1 <= trips.length <= 100
 * 0 <= starti, endi <= n - 1
 */
class MinimizeTotalPriceOfTrips {
    HashMap<Integer, ArrayList<Integer>> graph;
    int[] freq;
    int[] price;
    int[][] dp;


    /**
     * 使用递归和记忆化搜索来计算最小价格的解决方案。
     * 它使用DFS来找到每个节点在旅程中出现的频率，并使用递归来计算最小价格。
     * <p>
     * 在这个解决方案中，graph是一个HashMap，用于存储节点之间的连接关系。
     * freq是一个数组，用于存储每个节点在旅程中出现的频率。dp是一个二维数组，用于存储计算过的最小价格。
     * <p>
     * dfs函数用于找到每个节点在旅程中的频率。
     * 它使用深度优先搜索来遍历节点，并在遍历过程中累加频率。recursion函数用于计算每个节点的最小价格。
     * 它使用递归来遍历节点，并根据节点是否选择半价进行计算。它还使用记忆化搜索来避免重复计算。
     *
     * @param n
     * @param edges
     * @param price
     * @param trips
     * @return
     */
    public int minimizeTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        graph = new HashMap<>();
        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) graph.put(edge[0], new ArrayList<>());
            if (!graph.containsKey(edge[1])) graph.put(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        freq = new int[n];
        for (int[] trip : trips) {
            dfs(trip[0], trip[1], -1);
        }

        dp = new int[n][2];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        this.price = price;
        return recursion(0, -1, 0);
    }
    boolean dfs(int src, int dest, int parent) {
        if (src == dest) {
            freq[src]++;
            return true;
        }
        boolean found = false;
        if (graph.containsKey(src)) {
            for (int nei : graph.get(src)) {
                if (nei != parent) {
                    found |= dfs(nei, dest, src);
                }
                if (found) break;
            }
        }
        if (found) freq[src]++;
        return found;
    }

    int recursion(int node, int parent, int half) {
        if (dp[node][half] != -1) return dp[node][half];
        int res1 = (price[node] * freq[node]) / 2, res2 = price[node] * freq[node];
        if (graph.containsKey(node)) {
            for (int i : graph.get(node)) {
                if (i != parent) {
                    res2 += recursion(i, node, 0);
                }
            }

            if (half == 1) {
                dp[node][half] = res2;
                return res2;
            }

            for (int i : graph.get(node)) {
                if (i != parent) {
                    res1 += recursion(i, node, 1);
                }
            }
        }
        return dp[node][half] = Math.min(res1, res2);
    }



    public static void main(String[] args) {
        MinimizeTotalPriceOfTrips solution = new MinimizeTotalPriceOfTrips();
        // Test case 1
        int n1 = 4;
        int[][] edges1 = {{0, 1}, {1, 2}, {1, 3}};
        int[] price1 = {2, 2, 10, 6};
        int[][] trips1 = {{0, 3}, {2, 1}, {2, 3}};
        // Expected output: 23
        System.out.println(solution.minimizeTotalPrice(n1, edges1, price1, trips1));

        // Test case 2
        int n2 = 2;
        int[][] edges2 = {{0, 1}};
        int[] price2 = {2, 2};
        int[][] trips2 = {{0, 0}};
        // Expected output: 1
        System.out.println(solution.minimizeTotalPrice(n2, edges2, price2, trips2));
    }
}
