package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 2477. Minimum Fuel Cost to Report to the Capital
 * Medium
 * There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
 *
 * There is a meeting for the representatives of each city. The meeting is in the capital city.
 *
 * There is a car in each city. You are given an integer seats that indicates the number of seats in each car.
 *
 * A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.
 *
 * Return the minimum number of liters of fuel to reach the capital city.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: roads = [[0,1],[0,2],[0,3]], seats = 5
 * Output: 3
 * Explanation:
 * - Representative1 goes directly to the capital with 1 liter of fuel.
 * - Representative2 goes directly to the capital with 1 liter of fuel.
 * - Representative3 goes directly to the capital with 1 liter of fuel.
 * It costs 3 liters of fuel at minimum.
 * It can be proven that 3 is the minimum number of liters of fuel needed.
 * Example 2:
 *
 *
 * Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
 * Output: 7
 * Explanation:
 * - Representative2 goes directly to city 3 with 1 liter of fuel.
 * - Representative2 and representative3 go together to city 1 with 1 liter of fuel.
 * - Representative2 and representative3 go together to the capital with 1 liter of fuel.
 * - Representative1 goes directly to the capital with 1 liter of fuel.
 * - Representative5 goes directly to the capital with 1 liter of fuel.
 * - Representative6 goes directly to city 4 with 1 liter of fuel.
 * - Representative4 and representative6 go together to the capital with 1 liter of fuel.
 * It costs 7 liters of fuel at minimum.
 * It can be proven that 7 is the minimum number of liters of fuel needed.
 * Example 3:
 *
 *
 * Input: roads = [], seats = 1
 * Output: 0
 * Explanation: No representatives need to travel to the capital city.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * roads.length == n - 1
 * roads[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * roads represents a valid tree.
 * 1 <= seats <= 105
 *
 */
class MinimumFuelCostReportToCapital {
    /**
     * 计算到达首都的最少油耗
     *
     * 解题思路：
     * 1. 根据给定的树结构，使用深度优先搜索（DFS）遍历树，并计算最少油耗。
     * 2. 创建邻接表来表示树的结构。
     * 3. 从首都城市（节点0）开始DFS。
     * 4. 对于每个子城市，递归计算从该城市到首都的油耗。
     * 5. 如果车辆座位数大于等于城市中的代表人数，则不需要额外的油耗。否则，计算到达首都的油耗，并更新总油耗。
     * 6. 返回总油耗作为最少油耗。
     *
     * 时间复杂度：O(n)，其中n为城市数。
     *
     * @param roads 二维整数数组，表示城市之间的双向路
     * @param seats 每辆车里面座位的数目
     * @return 到达首都最少的油耗
     */
    class Solution {
        long res = 0;

        public long minimumFuelCost(int[][] roads, int seats) {
            int n = roads.length;
            List<Integer>[] g = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                g[i] = new ArrayList<Integer>();
            }
            for (int[] e : roads) {
                g[e[0]].add(e[1]);
                g[e[1]].add(e[0]);
            }
            dfs(0, -1, seats, g);
            return res;
        }

        public int dfs(int cur, int fa, int seats, List<Integer>[] g) {
            int peopleSum = 1;
            for (int ne : g[cur]) {
                if (ne != fa) {
                    int peopleCnt = dfs(ne, cur, seats, g);
                    peopleSum += peopleCnt;
                    res += (peopleCnt + seats - 1) / seats;
                }
            }
            return peopleSum;
        }
    }

}