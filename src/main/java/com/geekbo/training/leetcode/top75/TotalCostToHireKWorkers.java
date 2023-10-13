package com.geekbo.training.leetcode.top75;

import java.util.PriorityQueue;

/**
 * 2462. Total Cost to Hire K Workers
 * <p>
 * You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.
 * <p>
 * You are also given two integers k and candidates. We want to hire exactly k workers according to the following rules:
 * <p>
 * You will run k sessions and hire exactly one worker in each session.
 * In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the last candidates workers. Break the tie by the smallest index.
 * For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the 4th worker because they have the lowest cost [3,2,7,7,1,2].
 * In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but they have the smallest index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
 * If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
 * A worker can only be chosen once.
 * Return the total cost to hire exactly k workers.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
 * Output: 11
 * Explanation: We hire 3 workers in total. The total cost is initially 0.
 * - In the first hiring round we choose the worker from [17,12,10,2,7,2,11,20,8]. The lowest cost is 2, and we break the tie by the smallest index, which is 3. The total cost = 0 + 2 = 2.
 * - In the second hiring round we choose the worker from [17,12,10,7,2,11,20,8]. The lowest cost is 2 (index 4). The total cost = 2 + 2 = 4.
 * - In the third hiring round we choose the worker from [17,12,10,7,11,20,8]. The lowest cost is 7 (index 3). The total cost = 4 + 7 = 11. Notice that the worker with index 3 was common in the first and last four workers.
 * The total hiring cost is 11.
 * Example 2:
 * <p>
 * Input: costs = [1,2,4,1], k = 3, candidates = 3
 * Output: 4
 * Explanation: We hire 3 workers in total. The total cost is initially 0.
 * - In the first hiring round we choose the worker from [1,2,4,1]. The lowest cost is 1, and we break the tie by the smallest index, which is 0. The total cost = 0 + 1 = 1. Notice that workers with index 1 and 2 are common in the first and last 3 workers.
 * - In the second hiring round we choose the worker from [2,4,1]. The lowest cost is 1 (index 2). The total cost = 1 + 1 = 2.
 * - In the third hiring round there are less than three candidates. We choose the worker from the remaining workers [2,4]. The lowest cost is 2 (index 0). The total cost = 2 + 2 = 4.
 * The total hiring cost is 4.
 */
public class TotalCostToHireKWorkers {

    /**
     * 实现思路：
     * <p>
     * 定义两个指针 i 和 j，分别指向数组的开头和结尾。
     * 创建两个优先队列 pq1 和 pq2，用于保存选中的工人的工资。
     * 使用变量 ans 保存总工资的累加和。
     * 进行 k 次循环，表示选中 k 个工人：
     * 在每次循环中，将工资较小的工人加入到 pq1 中，直到 pq1 的大小等于 candidates 或指针 i 超出数组范围。
     * 将工资较大的工人加入到 pq2 中，直到 pq2 的大小等于 candidates 或指针 j 超出数组范围。
     * 获取 pq1 和 pq2 的堆顶元素，取较小的工资并将其累加到 ans 中，然后从对应的优先队列中移除该工资。
     * 返回 ans 作为结果。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：循环 k 次，每次循环都要将工资加入优先队列并进行堆顶元素的比较和移除操作，时间复杂度为 O(klogk)。
     * 空间复杂度：除了输入数组外，需要使用两个优先队列来保存选中的工人的工资，因此空间复杂度为 O(k)。
     *
     * @param costs
     * @param k
     * @param candidates
     * @return
     */
    public long totalCost(int[] costs, int k, int candidates) {
        // 定义两个指针i和j，分别指向数组的开头和结尾
        int i = 0;
        int j = costs.length - 1;

        // 创建两个优先队列，分别用于保存选中的工人的工资
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        // 保存总工资的累加和
        long ans = 0;

        // 进行k次循环，表示选中k个工人
        while (k-- > 0) {
            // 将工资较小的工人加入到pq1中，直到pq1的大小等于candidates或者指针i超出数组范围
            while (pq1.size() < candidates && i <= j) {
                pq1.offer(costs[i++]);
            }

            // 将工资较大的工人加入到pq2中，直到pq2的大小等于candidates或者指针j超出数组范围
            while (pq2.size() < candidates && i <= j) {
                pq2.offer(costs[j--]);
            }

            // 获取pq1和pq2的堆顶元素，取较小的工资并将其累加到ans中，然后从对应的优先队列中移除该工资
            int t1 = pq1.size() > 0 ? pq1.peek() : Integer.MAX_VALUE;
            int t2 = pq2.size() > 0 ? pq2.peek() : Integer.MAX_VALUE;

            if (t1 <= t2) {
                ans += t1;
                pq1.poll();
            } else {
                ans += t2;
                pq2.poll();
            }
        }

        // 返回总工资
        return ans;
    }

    public static void main(String[] args) {
        TotalCostToHireKWorkers totalCostToHireKWorkers = new TotalCostToHireKWorkers();

        // 测试用例1
        int[] costs1 = {17, 12, 10, 2, 7, 2, 11, 20, 8};
        int k1 = 3;
        int candidates1 = 4;
        int expected1 = 11;
        long result1 = totalCostToHireKWorkers.totalCost(costs1, k1, candidates1);
        System.out.println(result1 == expected1 ? "Pass" : "Fail");

        // 测试用例2
        int[] costs2 = {1, 2, 4, 1};
        int k2 = 3;
        int candidates2 = 3;
        int expected2 = 4;
        long result2 = totalCostToHireKWorkers.totalCost(costs2, k2, candidates2);
        System.out.println(result2 == expected2 ? "Pass" : "Fail");
    }
}