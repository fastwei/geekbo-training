package com.geekbo.training.leetcode.top150;

import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 * <p>
 * For each step, you may move to an adjacent number of the row below.
 * More formally, if you are on index i on the current row,
 * you may move to either index i or index i + 1 on the next row.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 * <p>
 * Input: triangle = [[-10]]
 * Output: -10
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 * <p>
 * <p>
 * Follow up: Could you do this using only O(n) extra space,
 * where n is the total number of rows in the triangle?
 */
public class TriangleMinimumPathSum {
    /**
     * 解题思路：
     * <p>
     * 题目要求计算从三角形的顶部到底部的最小路径和。
     * 从底部开始，使用动态规划的思想，计算每一层的最小路径和。
     * 创建一个大小为n的数组dp，其中n是三角形的行数。dp[i]表示从当前层到底部的最小路径和。
     * 初始化dp数组为三角形的最后一层的值。
     * 从倒数第二层开始，遍历三角形的每一行，计算当前位置的最小路径和。
     * 最小路径和等于当前位置的值加上下一层相邻位置的最小路径和的较小值。
     * 最终，dp数组的第一个元素即为从顶部到底部的最小路径和。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 三角形的行数为n，需要遍历每一层，所以时间复杂度为O(n)。
     * 使用了一个大小为n的dp数组来存储最小路径和，所以空间复杂度为O(n)。
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // Create an array to store the minimum path sum for each level of the triangle
        int[] dp = new int[n];

        // Initialize the dp array with the values from the last level
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        // Traverse the triangle from bottom to top
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // Calculate the minimum path sum for the current position
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        // The minimum path sum is stored in the first element of the dp array
        return dp[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle1 = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        );
        System.out.println(minimumTotal(triangle1)); // Expected: 11

        List<List<Integer>> triangle2 = Arrays.asList(
                Arrays.asList(-10)
        );
        System.out.println(minimumTotal(triangle2)); // Expected: -10
    }
}
