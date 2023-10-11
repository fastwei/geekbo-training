package com.geekbo.training.leetcode.top75;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 933. 最近的请求次数
 *
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 *
 * Implement the RecentCounter class:
 *
 * RecentCounter() Initializes the counter with zero recent requests.
 * int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
 * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * Output
 * [null, 1, 2, 3, 3]
 *
 * Explanation
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
 * recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
 *
 * 解题思路：
 *
 * 使用队列来存储请求时间。
 * 在每次调用ping方法时，将新请求的时间添加到队列。
 * 然后，移除时间在[t - 3000, t]之外的请求，保留在时间范围内的请求。
 * 最后，返回队列中的请求数量，即在[t - 3000, t]时间范围内的请求数量。
 * 算法复杂度分析：
 *
 * 时间复杂度：ping方法的时间复杂度为O(n)，其中n是队列中的请求数量。
 * 空间复杂度：使用了一个队列来存储请求时间，空间复杂度为O(n)。
 *
 */
public class RecentCounter {

    // 使用队列来存储请求时间
    private Queue<Integer> requests;

    public RecentCounter() {
        // 初始化队列
        requests = new LinkedList<>();
    }

    public int ping(int t) {
        // 将新请求的时间添加到队列
        requests.add(t);

        // 移除时间在[t - 3000, t]之外的请求
        while (!requests.isEmpty() && requests.peek() < t - 3000) {
            requests.poll();
        }

        // 返回队列中的请求数量，即在[t - 3000, t]时间范围内的请求数量
        return requests.size();
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        // 测试用例
        System.out.println(recentCounter.ping(1));    // 输出 1
        System.out.println(recentCounter.ping(100));  // 输出 2
        System.out.println(recentCounter.ping(3001)); // 输出 3
        System.out.println(recentCounter.ping(3002)); // 输出 3
    }
}
