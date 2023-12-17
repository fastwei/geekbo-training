package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 506. Relative Ranks
 * Easy
 * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.
 *
 * The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
 *
 * The 1st place athlete's rank is "Gold Medal".
 * The 2nd place athlete's rank is "Silver Medal".
 * The 3rd place athlete's rank is "Bronze Medal".
 * For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
 * Return an array answer of size n where answer[i] is the rank of the ith athlete.
 *
 *
 *
 * Example 1:
 *
 * Input: score = [5,4,3,2,1]
 * Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
 * Example 2:
 *
 * Input: score = [10,3,8,9,4]
 * Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].
 *
 *
 *
 * Constraints:
 *
 * n == score.length
 * 1 <= n <= 104
 * 0 <= score[i] <= 106
 * All the values in score are unique.
 *
 */
public class RelativeRanks {
    /**
     * 返回运动员的获奖情况
     * 解题思路： 首先，创建一个HashMap来存储每位运动员的得分和对应的索引。
     * 然后，将得分数组进行降序排序。接下来，根据排序后的得分数组给运动员分配获奖情况，最后将获奖情况存储到结果数组中。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：排序得分数组需要 O(nlogn) 的时间，遍历得分数组并分配获奖情况需要 O(n) 的时间，因此总体时间复杂度为 O(nlogn)。
     * 空间复杂度：需要使用一个 HashMap 和一个结果数组来存储数据，因此空间复杂度为 O(n)。
     *
     * @param score 运动员得分数组
     * @return 运动员的获奖情况数组
     */
    public String[] findRelativeRanks(int[] score) {
        // 创建一个HashMap来存储每位运动员的得分和对应的索引
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < score.length; i++) {
            map.put(score[i], i);
        }

        // 将得分数组进行降序排序
        Arrays.sort(score);

        // 创建一个结果数组，用于存储获奖情况
        String[] result = new String[score.length];

        // 根据排序后的得分数组给运动员分配获奖情况
        for (int i = score.length - 1; i >= 0; i--) {
            int index = map.get(score[i]);
            if (i == score.length - 1) {
                result[index] = "Gold Medal";
            } else if (i == score.length - 2) {
                result[index] = "Silver Medal";
            } else if (i == score.length - 3) {
                result[index] = "Bronze Medal";
            } else {
                result[index] = String.valueOf(score.length - i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        RelativeRanks solution = new RelativeRanks();

        // 测试用例
        int[] score1 = {5, 4, 3, 2, 1};
        String[] result1 = solution.findRelativeRanks(score1);
        System.out.println(Arrays.toString(result1));
        // 预期输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]

        int[] score2 = {10, 3, 8, 9, 4};
        String[] result2 = solution.findRelativeRanks(score2);
        System.out.println(Arrays.toString(result2));
        // 预期输出: ["Gold Medal", "5", "Bronze Medal", "Silver Medal", "4"]
    }
}
