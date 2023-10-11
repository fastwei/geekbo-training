package com.geekbo.training.leetcode.top75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Array / String
 *
 * There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
 *
 * Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies, they will have the greatest number of candies among all the kids, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: candies = [2,3,5,1,3], extraCandies = 3
 * Output: [true,true,true,false,true]
 * Explanation: If you give all extraCandies to:
 * - Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
 * - Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
 * - Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
 * - Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
 * - Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
 * Example 2:
 *
 * Input: candies = [4,2,1,1,2], extraCandies = 1
 * Output: [true,false,false,false,false]
 * Explanation: There is only 1 extra candy.
 * Kid 1 will always have the greatest number of candies, even if a different kid is given the extra candy.
 * Example 3:
 *
 * Input: candies = [12,1,12], extraCandies = 10
 * Output: [true,false,true]
 *
 * 有n个孩子拥有糖果。给定一个整数数组candies，其中每个candies[i]代表第i个孩子拥有的糖果数量，以及一个整数extraCandies，表示你拥有的额外糖果数量。
 *
 * 返回一个长度为n的布尔数组result，其中result[i]为true，如果在给第i个孩子所有额外的糖果后，他们将拥有所有孩子中最多的糖果数量，否则为false。
 *
 * 请注意，多个孩子可以拥有最多的糖果。
 *
 *
 *要解决这个问题，我们需要遍历所有的孩子，分别判断在给每个孩子额外糖果后是否他们将拥有最多的糖果。为了做到这一点，我们可以进行以下步骤：
 *
 * 遍历一次 candies 数组，找到最大的糖果数量 maxCandies。这可以通过简单的迭代实现。
 *
 * 再次遍历 candies 数组，对于每个孩子，检查是否给他们额外的糖果后，他们的糖果数量大于等于 maxCandies。如果是，将结果数组 result 的相应位置设置为 true，否则设置为 false。
 *
 *时间复杂度分析：
 *
 * 找到最大糖果数量需要一次遍历，时间复杂度为 O(n)。
 * 再次遍历数组，时间复杂度同样为 O(n)。
 * 总的时间复杂度为 O(n) + O(n) = O(n)，其中 n 是孩子的数量。
 * 这个算法的时间复杂度是线性的，效率很高。
 *
 * Array / String
 */
public class kidsWithGreatestOfNumberCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;
        int n = candies.length;

        // 找到最大的糖果数量
        for (int i = 0; i < n; i++) {
            maxCandies = Math.max(maxCandies, candies[i]);
        }

        List<Boolean> result = new ArrayList<>();

        // 判断每个孩子是否拥有最多的糖果
        for (int i = 0; i < n; i++) {
            result.add(candies[i] + extraCandies >= maxCandies);
        }

        return result;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] candies1 = {2, 3, 5, 1, 3};
        int extraCandies1 = 3;
        List<Boolean> expected1 = Arrays.asList(true, true, true, false, true);
        testCandyDistribution(candies1, extraCandies1, expected1);

        // 测试用例2
        int[] candies2 = {4, 2, 1, 1, 2};
        int extraCandies2 = 1;
        List<Boolean> expected2 = Arrays.asList(true, false, false, false, false);
        testCandyDistribution(candies2, extraCandies2, expected2);

        // 测试用例3
        int[] candies3 = {12, 1, 12};
        int extraCandies3 = 10;
        List<Boolean> expected3 = Arrays.asList(true, false, true);
        testCandyDistribution(candies3, extraCandies3, expected3);
    }

    public static void testCandyDistribution(int[] candies, int extraCandies, List<Boolean> expected) {
        kidsWithGreatestOfNumberCandies candyDistribution = new kidsWithGreatestOfNumberCandies();
        List<Boolean> result = candyDistribution.kidsWithCandies(candies, extraCandies);

        if (result.equals(expected)) {
            System.out.println("Test Passed: " + result);
        } else {
            System.out.println("Test Failed. Expected: " + expected + ", Actual: " + result);
        }
    }
}
