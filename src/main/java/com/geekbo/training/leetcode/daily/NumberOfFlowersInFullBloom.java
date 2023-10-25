package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 *
 * 2251. Number of Flowers in Full Bloom
 * You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n, where people[i] is the time that the ith person will arrive to see the flowers.
 *
 * Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.
 *
 * Example 1:
 *
 *
 * Input: flowers = [[1,6],[3,7],[9,12],[4,13]], poeple = [2,3,7,11]
 * Output: [1,2,2,2]
 * Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
 * For each person, we return the number of flowers in full bloom during their arrival.
 *
 *
 * Example 2:
 *
 *
 * Input: flowers = [[1,10],[3,3]], poeple = [3,3,2]
 * Output: [2,2,1]
 * Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
 * For each person, we return the number of flowers in full bloom during their arrival.
 *
 */
class NumberOfFlowersInFullBloom {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int maxDay = 0; // Initialize the maximum day

        // Find the maximum day from the flower data
        for (int[] flower : flowers) {
            maxDay = Math.max(maxDay, flower[1]);
        }

        int[] flowerDays = new int[maxDay + 2]; // Initialize an array for flower bloom status
        int[] sum = new int[maxDay + 2]; // Initialize an array to store the cumulative sum

        // Mark bloom days in the flowerDays array
        for (int[] flower : flowers) {
            int start = flower[0];
            int end = flower[1];
            flowerDays[start]++;
            flowerDays[end + 1]--;
        }

        // Calculate the cumulative sum of bloomed flowers
        int cumulativeSum = 0;
        for (int i = 1; i <= maxDay; i++) {
            cumulativeSum += flowerDays[i];
            sum[i] = cumulativeSum;
        }

        // Calculate answers for each person
        int[] result = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            result[i] = sum[people[i]];
        }

        return result;

    }

    public static void main(String[] args) {
        NumberOfFlowersInFullBloom solution = new NumberOfFlowersInFullBloom();

        int[][] flowers1 = {{1, 6}, {3, 7}, {9, 12}, {4, 13}};
        int[] people1 = {2, 3, 7, 11};
        int[] result1 = solution.fullBloomFlowers(flowers1, people1);
        System.out.println(Arrays.toString(result1)); // Expected: [1, 2, 2, 2]

        int[][] flowers2 = {{1, 10}, {3, 3}};
        int[] people2 = {3, 3, 2};
        int[] result2 = solution.fullBloomFlowers(flowers2, people2);
        System.out.println(Arrays.toString(result2)); // Expected: [2, 2, 1]
    }
}
