package com.geekbo.training.leetcode.top150.hard;

import java.util.Arrays;

/**
 *
 *There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * Return the minimum number of candies you need to have to distribute the candies to the children.
 *
 *
 *
 * Example 1:
 *
 * Input: ratings = [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: ratings = [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 * The third child gets 1 candy because it satisfies the above two conditions.
 *
 * 分发糖果问题
 * 
 * 给定一组孩子的评分，满足以下条件分发糖果：
 * 1. 每个孩子至少分配一个糖果。
 * 2. 拥有更高评分的孩子比他们的邻居得到更多的糖果。
 * 
 * 返回分发糖果的最小数量。
 *
 * 解题思路：
 *
 * 初始化每个孩子都分配一个糖果。
 * 从左向右遍历，如果右边孩子的评分比左边高，就让右边孩子的糖果数量等于左边孩子加1。
 * 从右向左遍历，如果左边孩子的评分比右边高，就取左右两边中较大的糖果数量，确保左边孩子得到更多糖果。
 * 计算总糖果数量。
 * 算法复杂度：
 *
 * 时间复杂度：O(N)，其中 N 为孩子的数量。
 * 空间复杂度：O(N)，用于存储每个孩子分配的糖果数量。
 *
 */
public class CandyDistribution {
    public int minCandies(int[] ratings) {
        int n = ratings.length;
        if (n <= 1) {
            return n;
        }

        int[] candies = new int[n];
        // 初始化每个孩子都分配一个糖果
        Arrays.fill(candies, 1);

        // 从左向右遍历，确保右边评分高的孩子得到更多糖果
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // 从右向左遍历，确保左边评分高的孩子得到更多糖果，同时不影响右边的情况
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // 计算总糖果数量
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies;
    }

    public static void main(String[] args) {
        CandyDistribution solution = new CandyDistribution();
        // 测试用例
        int[] ratings1 = {1, 0, 2};
        System.out.println(solution.minCandies(ratings1)); // 输出：5

        int[] ratings2 = {1, 2, 2};
        System.out.println(solution.minCandies(ratings2)); // 输出：4
    }
}
