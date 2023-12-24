package com.geekbo.training.leetcode.premium;

import java.util.Arrays;

/**
 * 1235. 规划兼职工作
 * 困难
 * 你打算利用空闲时间来做兼职工作赚些零花钱。
 * <p>
 * 这里有 n 份兼职工作，每份工作预计从 startTime[i] 开始到 endTime[i] 结束，报酬为 profit[i]。
 * <p>
 * 给你一份兼职工作表，包含开始时间 startTime，结束时间 endTime 和预计报酬 profit 三个数组，请你计算并返回可以获得的最大报酬。
 * <p>
 * 注意，时间上出现重叠的 2 份工作不能同时进行。
 * <p>
 * 如果你选择的工作在时间 X 结束，那么你可以立刻进行在时间 X 开始的下一份工作。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * 输出：120
 * 解释：
 * 我们选出第 1 份和第 4 份工作，
 * 时间范围是 [1-3]+[3-6]，共获得报酬 120 = 50 + 70。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * 输出：150
 * 解释：
 * 我们选择第 1，4，5 份工作。
 * 共获得报酬 150 = 20 + 70 + 60。
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入：startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * 输出：6
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 */
public class JobScheduling {

    /**
     * 解题思路：
     * - 首先，我们将每份工作保存为一个 Job 对象，并根据结束时间对工作进行排序。
     * - 创建一个 dp 数组来保存每个时间点的最大报酬。初始化 dp[0] 为第一份工作的报酬。
     * - 遍历每个工作，对于当前工作，有两种选择：
     * - 不选择当前工作，即当前时间点的最大报酬为前一个时间点的最大报酬。
     * - 选择当前工作，即当前时间点的最大报酬为当前工作的报酬加上前一个时间点之前最晚可以选择的工作的最大报酬。
     * - 找到前一个时间点之前最晚可以选择的工作，可以使用二分查找。
     * - 最后，返回最后一个时间点的最大报酬。
     * <p>
     * 算法复杂度：
     * - 时间复杂度：O(nlogn)，其中 n 是工作的数量。需要对工作进行排序，并遍历每个工作。
     * - 空间复杂度：O(n)，需要创建一个 dp 数组来保存每个时间点的最大报酬。
     *
     * @param startTime
     * @param endTime
     * @param profit
     * @return
     */
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;

        // 创建一个 Job 类来保存每份工作的开始时间、结束时间和报酬
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        // 根据结束时间对工作进行排序
        Arrays.sort(jobs, (a, b) -> a.endTime - b.endTime);

        // 创建一个 dp 数组来保存每个时间点的最大报酬
        int[] dp = new int[n];

        // 初始化 dp 数组
        dp[0] = jobs[0].profit;

        // 遍历每个工作，计算当前工作的最大报酬
        for (int i = 1; i < n; i++) {
            // 不选择当前工作
            int profit1 = dp[i - 1];

            // 选择当前工作
            int profit2 = jobs[i].profit;

            // 找到当前工作结束时间之前的最晚可以选择的工作
            int prevJobIndex = binarySearch(jobs, i);

            // 如果找到了可以选择的工作，加上该工作的报酬
            if (prevJobIndex != -1) {
                profit2 += dp[prevJobIndex];
            }

            // 取两种选择中的较大值作为当前时间点的最大报酬
            dp[i] = Math.max(profit1, profit2);
        }

        // 返回最后一个时间点的最大报酬
        return dp[n - 1];
    }

    private static int binarySearch(Job[] jobs, int index) {
        int left = 0;
        int right = index - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (jobs[mid].endTime <= jobs[index].startTime) {
                if (jobs[mid + 1].endTime <= jobs[index].startTime) {
                    left = mid + 1;
                } else {
                    return mid;
                }
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    static class Job {
        int startTime;
        int endTime;
        int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {
        int[] startTime1 = {1, 2, 3, 3};
        int[] endTime1 = {3, 4, 5, 6};
        int[] profit1 = {50, 10, 40, 70};
        System.out.println(jobScheduling(startTime1, endTime1, profit1)); // Expected output: 120

        int[] startTime2 = {1, 2, 3, 4, 6};
        int[] endTime2 = {3, 5, 10, 6, 9};
        int[] profit2 = {20, 20, 100, 70, 60};
        System.out.println(jobScheduling(startTime2, endTime2, profit2)); // Expected output: 150

        int[] startTime3 = {1, 1, 1};
        int[] endTime3 = {2, 3, 4};
        int[] profit3

                = {5, 6, 4};
        System.out.println(jobScheduling(startTime3, endTime3, profit3)); // Expected output: 6
    }
}

