package com.geekbo.training.leetcode.top150.hard;

import java.util.PriorityQueue;

/**
 * Suppose LeetCode will start its IPO soon.
 * In order to sell a good price of its shares to Venture Capital,
 * LeetCode would like to work on some projects to increase its capital before the IPO.
 * Since it has limited resources, it can only finish at most k distinct projects before the IPO.
 * Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 * <p>
 * You are given n projects where the ith project has a pure profit profits[i]
 * and a minimum capital of capital[i] is needed to start it.
 * <p>
 * Initially, you have w capital.
 * When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 * <p>
 * Pick a list of at most k distinct projects from given projects to maximize your final capital,
 * and return the final maximized capital.
 * <p>
 * The answer is guaranteed to fit in a 32-bit signed integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * Output: 4
 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 * After finishing it you will obtain profit 1 and your capital becomes 1.
 * With capital 1, you can either start the project indexed 1 or the project indexed 2.
 * Since you can choose at most 2 projects,
 * you need to finish the project indexed 2 to get the maximum capital.
 * Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 * Output: 6
 *
 * 对于性能优化，可以考虑以下几点：
 *
 * 使用数组代替堆：堆是一个有序的数据结构，但在本问题中，我们只需要找到最大的k个项目。
 * 因此，可以使用一个数组来存储项目的信息，并通过快速排序或类似的算法对数组进行排序，然后选择前k个项目。
 *
 * 使用快速选择算法：快速选择算法是一种选择第k个最小（或最大）元素的高效算法。
 * 它基于快速排序的分区思想，在每次分区后只选择一个子数组进行进一步处理。
 * 通过快速选择算法，我们可以在平均情况下以O(n)的时间复杂度选择第k个最大元素。
 *
 * 避免重复计算：在当前资本范围内，我们只需要选择纯利润最大的项目，而不需要考虑其他项目。
 * 因此，可以在每次选择项目后，将已选择的项目从数组中移除，避免重复计算。
 *
 */
public class IPOMaximizeCapital {
    /**
     * 计算最大化资本
     * 解题思路：
     * <p>
     * 使用两个堆，一个最大堆用于存储项目的纯利润，一个最小堆用于存储项目的启动所需最小资本。
     * 首先将所有项目加入最小堆。
     * 每次从最小堆中选择在当前资本范围内启动 所需最小资本最小的项目，并将其加入最大堆。
     * 在每次选择项目时，将所有启动所需最小资本小于等于当前资本的项目加入最大堆。 从最大堆中选择纯利润最大的项目，并更新当前资本。
     * 重复步骤4和5，直到完成了k个项目或者最大堆为空。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：将所有项目加入最小堆的时间复杂度为O(nlogn)，每次选择项目的时间复杂度为O(klogn)。因此，总体时间复杂度为O(nlogn + klogn)。
     * 空间复杂度：使用了两个堆来存储项目，堆的大小最多为n。因此，空间复杂度为O(n)。
     *
     * @param k       最多可以完成的项目数量
     * @param w       初始资本
     * @param profits 项目纯利润数组
     * @param capital 项目启动所需最小资本数组
     * @return 最大化的资本
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        // 使用最大堆存储项目的纯利润，堆顶元素是纯利润最大的项目
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        // 使用最小堆存储项目的启动所需最小资本，堆顶元素是启动所需最小资本最小的项目
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // 将所有项目加入最小堆
        for (int i = 0; i < n; i++) {
            minHeap.offer(new int[]{profits[i], capital[i]});
        }

        // 从最小堆中选择在当前资本范围内启动所需最小资本最小的项目
        for (int i = 0; i < k; i++) {
            // 将所有启动所需最小资本小于等于当前资本的项目加入最大堆
            while (!minHeap.isEmpty() && minHeap.peek()[1] <= w) {
                maxHeap.offer(minHeap.poll());
            }

            // 如果最大堆为空，说明没有项目可供选择
            if (maxHeap.isEmpty()) {
                break;
            }

            // 选择纯利润最大的项目，并更新当前资本
            w += maxHeap.poll()[0];
        }

        return w;
    }



    public static void main(String[] args) {
        // 测试用例1
        int k1 = 2;
        int w1 = 0;
        int[] profits1 = {1, 2, 3};
        int[] capital1 = {0, 1, 1};
        int expected1 = 4;
        int result1 = findMaximizedCapital(k1, w1, profits1, capital1);
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);
        System.out.println();

        // 测试用例2
        int k2 = 3;
        int w2 = 0;
        int[] profits2 = {1, 2, 3};
        int[] capital2 = {0, 1, 2};
        int expected2 = 6;
        int result2 = findMaximizedCapital(k2, w2, profits2, capital2);
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);
    }
}

