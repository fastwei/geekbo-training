package com.geekbo.training.leetcode.graph;

/**
 * 1615. Maximal Network Rank
 * Medium
 * There is an infrastructure of n cities with some number of roads connecting these cities.
 * Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.
 * <p>
 * The network rank of two different cities is defined as the total number of
 * directly connected roads to either city.
 * If a road is directly connected to both cities, it is only counted once.
 * <p>
 * The maximal network rank of the infrastructure is the maximum network rank of
 * all pairs of different cities.
 * <p>
 * Given the integer n and the array roads, return the maximal network
 * rank of the entire infrastructure.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
 * Output: 4
 * Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads
 * that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
 * Output: 5
 * Explanation: There are 5 roads that are connected to cities 1 or 2.
 * Example 3:
 * <p>
 * Input: n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
 * Output: 5
 * Explanation: The network rank of 2 and 5 is 5.
 * Notice that all the cities do not have to be connected.
 */
public class MaximalNetworkRank {

    /**
     * 计算最大网络秩
     * 解题思路：
     * <p>
     * 首先，我们需要统计每个城市的道路数目和城市之间是否有直接连接的道路。
     * 创建一个大小为n的degree数组，用来记录每个城市的道路数目。
     * 创建一个大小为n x n的connected数组，用来记录城市之间是否有直接连接的道路。
     * 遍历道路数组，更新degree数组和connected数组。
     * 然后，我们可以计算每对不同城市的网络秩。
     * 遍历城市对(i,j)，计算网络秩为degree[i]+degree[j]。
     * 如果城市i和城市j之间有直接连接的道路，则网络秩减1。
     * 最后，返回所有城市对的最大网络秩。
     * 算法复杂度分析：
     * 数组的时间复杂度为O(M)，其中M是道路数组的长度。
     * <p>
     * 计算每对不同城市的网络秩的时间复杂度为O(n^2)，其中n是城市数量。
     * 因此，总的时间复杂度为O(M+n^2)。
     * 算法的空间复杂度为O(n^2)，因为我们使用了大小为n x n的二维数组来存储城市之间的连接关系。
     *
     * @param n     城市数量
     * @param roads 道路数组
     * @return 最大网络秩
     */
    public static int maximalNetworkRank(int n, int[][] roads) {
        int[] degree = new int[n];
        boolean[][] connected = new boolean[n][n];
        // 统计每个城市的道路数目和城市之间是否有直接连接的道路
        for (int[] road : roads) {
            int city1 = road[0];
            int city2 = road[1];
            degree[city1]++;
            degree[city2]++;
            connected[city1][city2] = true;
            connected[city2][city1] = true;
        }
        // 计算每对不同城市的网络秩
        int maxRank = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = degree[i] + degree[j];
                // 如果城市i和城市j之间有直接连接的道路，则网络秩减1
                if (connected[i][j]) {
                    rank--;
                }
                maxRank = Math.max(maxRank, rank);
            }
        }
        return maxRank;
    }

    public static void main(String[] args) {
        // 测试用例1
        int n1 = 4;
        int[][] roads1 = {{0, 1}, {0, 3}, {1, 2}, {1, 3}};
        // 预期输出：4
        System.out.println(maximalNetworkRank(n1, roads1));

        // 测试用例2
        int n2 = 5;
        int[][] roads2 = {{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}};
        // 预期输出：5
        System.out.println(maximalNetworkRank(n2, roads2));

        // 测试用例3
        int n3 = 8;
        int[][] roads3 = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {5, 6}, {5, 7}};
        // 预期输出：5
        System.out.println(maximalNetworkRank(n3, roads3));
    }
}
