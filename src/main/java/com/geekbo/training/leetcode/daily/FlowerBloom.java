package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 2251. Number of Flowers in Full Bloom
 * You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where people[i] is the time that the ith person will arrive to see the flowers.
 *
 * Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
 * Output: [1,2,2,2]
 * Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
 * For each person, we return the number of flowers in full bloom during their arrival.
 * Example 2:
 *
 *
 * Input: flowers = [[1,10],[3,3]], people = [3,3,2]
 * Output: [2,2,1]
 * Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
 * For each person, we return the number of flowers in full bloom during their arrival.
 *
 */
public class FlowerBloom {
    /**
     * 计算每个人到达时开放的花朵数量
     *
     * @param flowers 花朵的开始和结束时间数组
     * @param people  每个人到达的时间数组
     * @return 每个人到达时开放的花朵数量数组
     */
    public static int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int m = flowers.length;
        int[] starts = new int[m];
        int[] ends = new int[m];
        for (int i = 0; i < m; i++) {
            starts[i] = flowers[i][0];
            ends[i] = flowers[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int n = people.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int open = 0, close = 0;
            if (people[i] < starts[0]) {
                open = 0;
            } else if (people[i] >= starts[m - 1]) {
                open = m;
            } else {
                int l = 0, r = m - 1;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    if (starts[mid] <= people[i]) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                open = l + 1;
            }
            if (people[i] <= ends[0])
                close = 0;
            else if (people[i] > ends[m - 1]) {
                close = m;
            } else {
                int l = 0, r = m - 1;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    if (ends[mid] < people[i]) {
                        l = mid;

                    } else {
                        r = mid - 1;
                    }
                }
                close = l + 1;
            }
            result[i] = open - close;
        }
        return result;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[][] flowers1 = {{1, 6}, {3, 7}, {9, 12}, {4, 13}};
        int[] people1 = {2, 3, 7, 11};
        int[] result1 = fullBloomFlowers(flowers1, people1);
        System.out.println(Arrays.toString(result1));
        // 预期输出: [1, 2, 2, 2]

        // 测试用例2
        int[][] flowers2 = {{1, 10}, {3, 3}};
        int[] people2 = {3, 3, 2};
        int[] result2 = fullBloomFlowers(flowers2, people2);
        System.out.println(Arrays.toString(result2));
        // 预期输出: [2, 2, 1]
    }
}