package com.geekbo.training.leetcode.premium;

/**
 * 265. 粉刷房子 II
 * 困难
 * <p>
 * 假如有一排房子共有 n 幢，每个房子可以被粉刷成 k 种颜色中的一种。房子粉刷成不同颜色的花费成本也是不同的。你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 每个房子粉刷成不同颜色的花费以一个 n x k 的矩阵表示。
 * 例如，costs[0][0] 表示第 0 幢房子粉刷成 0 号颜色的成本；costs[1][2] 表示第 1 幢房子粉刷成 2 号颜色的成本，以此类推。
 * 返回 粉刷完所有房子的最低成本。
 * <p>
 * 示例 1：
 * 输入: costs = [[1,5,3],[2,9,4]]
 * 输出: 5
 * 解释:
 * 将房子 0 刷成 0 号颜色，房子 1 刷成 2 号颜色。花费: 1 + 4 = 5;
 * 或者将 房子 0 刷成 2 号颜色，房子 1 刷成 0 号颜色。花费: 3 + 2 = 5.
 * <p>
 * 示例 2:
 * 输入: costs = [[1,3],[2,4]]
 * 输出: 5
 * <p>
 * 提示：
 * costs.length == n
 * costs[i].length == k
 * 1 <= n <= 100
 * 2 <= k <= 20
 * 1 <= costs[i][j] <= 20
 * <p>
 * 进阶：您能否在 O(nk) 的时间复杂度下解决此问题？
 */
public class PaintHouseII {

    /**
     * 解题思路：
     * - 使用动态规划来解决问题。
     * - 创建一个二维数组`dp`，其中`dp[i][j]`表示将第`i`幢房子刷成颜色`j`的最低成本。
     * - 初始化第一幢房子的成本为其对应颜色的成本。
     * - 从第二幢房子开始，对于每幢房子，找出上一幢房子刷成的最低成本和次低成本，然后更新当前房子刷成不同颜色的最低成本。
     * - 最后，找出最后一幢房子刷成各种颜色的最低成本，并返回最小值。
     * <p>
     * 算法复杂度分析：
     * - 时间复杂度为O(nk^2)，其中n为房子的数量，k为颜色的数量。
     * - 空间复杂度为O(nk)。
     *
     * @param costs
     * @return
     */
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int n = costs.length;
        int k = costs[0].length;

        // dp[i][j]表示将第i幢房子刷成颜色j的最低成本
        int[][] dp = new int[n][k];

        // 初始化第一幢房子的成本
        for (int i = 0; i < k; i++) {
            dp[0][i] = costs[0][i];
        }

        // 动态规划
        for (int i = 1; i < n; i++) {
            // 找出上一幢房子刷成的最低成本和次低成本
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                if (dp[i - 1][j] < min1) {
                    min2 = min1;
                    min1 = dp[i - 1][j];
                } else if (dp[i - 1][j] < min2) {
                    min2 = dp[i - 1][j];
                }
                // 更新当前房子刷成不同颜色的最低成本

            }
            for (int j = 0; j < k; j++) {
                if (dp[i - 1][j] == min1) {
                    dp[i][j] = min2 + costs[i][j];
                } else {
                    dp[i][j] = min1 + costs[i][j];
                }
            }
        }

        // 找出最后一幢房子刷成各种颜色的最低成本，并返回最小值
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            minCost = Math.min(minCost, dp[n - 1][i]);
        }

        return minCost;
    }

    public static void main(String[] args) {
        PaintHouseII paintHouseII = new PaintHouseII();

        int[][] costs1 = {{1, 5, 3}, {2, 9, 4}};
        System.out.println(paintHouseII.minCostII(costs1)); // Output: 5

        int[][] costs2 = {{1, 3}, {2, 4}};
        System.out.println(paintHouseII.minCostII(costs2)); // Output: 5
    }
}


