package com.geekbo.training.leetcode.top150;

import java.util.*;

/**
 *
 *There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
 *
 * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique
 *
 *
 *
 * Example 1:
 *
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Output: 3
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * Example 2:
 *
 * Input: gas = [2,3,4], cost = [3,4,3]
 * Output: -1
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 *
 * 需要找到一个起始的加油站，从该加油站出发，绕行整个环路，判断是否能够回到起始点。
 * 如果可以回到起始点，则返回起始加油站的索引；如果无法回到起始点，则返回-1。
 *
 * 解题思路如下：
 *
 * 遍历每个加油站，计算从当前加油站出发，是否能够绕行一圈。
 * 这可以通过模拟的方式，从当前加油站出发，累加加油量，减去花费的油量，判断是否小于零。
 * 如果在遍历的过程中，找到了一个加油站，从该站点出发可以成功绕行一圈
 * （即在遍历过程中始终保持非负油量），则返回该站点的索引。
 * 如果遍历结束后仍未找到符合条件的起始加油站，则返回-1。
 * 这个算法的时间复杂度为O(n)，其中n是加油站的数量，因为需要遍历每个加油站一次，来判断是否能够绕行一圈。
 *
 * 在代码中，你需要使用两个变量来跟踪当前累积的油量和总累积的油量。
 * 然后，通过遍历每个加油站，进行累积的油量计算，并判断是否小于零。
 * 如果小于零，则更新起始加油站的索引。最后，根据起始加油站的索引，判断是否有解决方案。
 *
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;  // 总加油量
        int currentGas = 0;  // 当前加油量
        int startStation = 0;  // 起始加油站
        int debt = 0;  // 欠的油量

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i] - cost[i];  // 计算总加油量和欠的油量
            currentGas += gas[i] - cost[i];  // 更新当前加油量

            if (currentGas < 0) {
                startStation = i + 1;  // 如果当前加油量小于0，将起始加油站更新为下一个站点
                currentGas = 0;  // 重置当前加油量
            }
        }

        return totalGas >= 0 ? startStation : -1;  // 如果总加油量大于等于0，返回起始加油站；否则返回-1
    }

    public static void main(String[] args) {
        GasStation solution = new GasStation();
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Example 1: " + solution.canCompleteCircuit(gas1, cost1));  // 应返回 3

        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Example 2: " + solution.canCompleteCircuit(gas2, cost2));  // 应返回 -1
    }
}
