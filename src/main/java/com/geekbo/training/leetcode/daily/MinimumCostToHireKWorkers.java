package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 857. Minimum Cost to Hire K Workers
 * Hard
 * There are n workers.
 * You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker
 * and wage[i] is the minimum wage expectation for the ith worker.
 * <p>
 * We want to hire exactly k workers to form a paid group.
 * To hire a group of k workers, we must pay them according to the following rules:
 * <p>
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions.
 * Answers within 10-5 of the actual answer will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: quality = [10,20,5], wage = [70,50,30], k = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
 * Example 2:
 * <p>
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
 */
class MinimumCostToHireKWorkers {
    /**
     * 解题思路： 首先，我们将员工按照他们的期望工资与工作质量的比例进行排序。
     * 然后，我们使用一个最大堆来维护当前已选中的k个员工的最大工作质量。
     * 我们遍历排序后的员工列表，将员工的工作质量添加到最大堆中，并计算堆中的总工作质量。
     * 如果堆的大小超过了k，我们将移除工作质量最大的员工，并更新总工作质量。
     * 当堆的大小等于k时，我们计算当前总工作质量与员工的期望工资与工作质量的比例的乘积，取最小值作为最小成本。
     * 最后，返回最小成本即可。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(nlogn)，其中n是员工的数量。需要对员工进行排序，时间复杂度为O(nlogn)。
     * 遍历排序后的员工列表的时间复杂度为O(n)。
     * <p>
     * 空间复杂度：O(n)，其中n是员工的数量。
     * 需要使用一个数组来存储员工对象，数组的大小为n。
     * 此外，最大堆的大小最多为k，因此需要额外的O(k)空间来存储最大堆。
     *
     * @param quality
     * @param wage
     * @param k
     * @return
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Worker[] workers = new Worker[n];
        for (int i = 0; i < n; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(workers);

        double minCost = Double.MAX_VALUE;
        int sumQuality = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (Worker worker : workers) {
            maxHeap.offer(worker.quality);
            sumQuality += worker.quality;
            if (maxHeap.size() > k) {
                sumQuality -= maxHeap.poll();
            }
            if (maxHeap.size() == k) {
                minCost = Math.min(minCost, (double) sumQuality * worker.ratio);
            }
        }

        return minCost;
    }

    static class Worker implements Comparable<Worker> {
        int quality;
        int wage;
        double ratio;

        public Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
            this.ratio = (double) wage / quality;
        }

        @Override
        public int compareTo(Worker other) {
            return Double.compare(this.ratio, other.ratio);
        }
    }

    public static void main(String[] args) {
        MinimumCostToHireKWorkers minimumCostToHireKWorkers = new MinimumCostToHireKWorkers();

        // 测试用例1
        int[] quality1 = {10, 20, 5};
        int[] wage1 = {70, 50, 30};
        int k1 = 2;
        // 预期输出：105.0
        System.out.println(minimumCostToHireKWorkers.mincostToHireWorkers(quality1, wage1, k1));

        // 测试用例2
        int[] quality2 = {3, 1, 10, 10, 1};
        int[] wage2 = {4, 8, 2, 2, 7};
        int k2 = 3;
        // 预期输出：30.66667
        System.out.println(minimumCostToHireKWorkers.mincostToHireWorkers(quality2, wage2, k2));
    }
}


