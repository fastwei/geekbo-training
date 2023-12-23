package com.geekbo.training.leetcode.premium;

import java.util.Arrays;
import java.util.List;

/**
 * 2218. 从栈中取出 K 个硬币的最大面值和
 * 困难
 * 一张桌子上总共有 n 个硬币 栈 。每个栈有 正整数 个带面值的硬币。
 * <p>
 * 每一次操作中，你可以从任意一个栈的 顶部 取出 1 个硬币，从栈中移除它，并放入你的钱包里。
 * <p>
 * 给你一个列表 piles ，其中 piles[i] 是一个整数数组，分别表示第 i 个栈里 从顶到底 的硬币面值。同时给你一个正整数 k ，请你返回在 恰好 进行 k 次操作的前提下，你钱包里硬币面值之和 最大为多少 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：piles = [[1,100,3],[7,8,9]], k = 2
 * 输出：101
 * 解释：
 * 上图展示了几种选择 k 个硬币的不同方法。
 * 我们可以得到的最大面值为 101 。
 * 示例 2：
 * <p>
 * 输入：piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7
 * 输出：706
 * 解释：
 * 如果我们所有硬币都从最后一个栈中取，可以得到最大面值和。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == piles.length
 * 1 <= n <= 1000
 * 1 <= piles[i][j] <= 105
 * 1 <= k <= sum(piles[i].length) <= 2000
 */
public class MaximumCoinValue {
    /**
     * 计算在恰好进行k次操作的前提下，钱包里硬币面值之和的最大值
     * 解题思路： 首先，我们需要找到每个栈的最大值，即每个栈的顶部硬币面值。
     * 然后，我们将这些最大值进行排序，从大到小。 接下来，我们从最大值开始选择，连续选择k次，将选择的硬币面值累加到sum中。
     * 最后，返回sum作为结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 遍历每个栈找到最大值的时间复杂度为O(n*m)，其中n是栈的数量，m是每个栈的平均长度。
     * 对最大值进行排序的时间复杂度为O(n*logn)，其中n是栈的数量。
     * 选择k次硬币的时间复杂度为O(k)。
     * 因此，总的时间复杂度为O(nm + nlogn + k)。
     * 空间复杂度是O(n)，用于存储每个栈的最大值。
     * 请注意，此解决方案假设每个栈都至少有一个硬币。如果某个栈为空，则需要添加额外的逻辑来处理这种情况。
     *
     * @param piles 每个栈的硬币面值
     * @param k     操作次数
     * @return 钱包里硬币面值之和的最大值
     */
    public static int maxCoinValue(int[][] piles, int k) {
        int n = piles.length;
        int[] maxValues = new int[n];
        for (int i = 0; i < n; i++) {
            maxValues[i] = piles[i][piles[i].length - 1];
        }
        Arrays.sort(maxValues);

        int sum = 0;
        for (int i = n - 1; i >= n - k; i--) {
            sum += maxValues[i];
        }
        return sum;
    }

    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int pilesNum = piles.size();
        int[][] dp = new int[pilesNum][k + 1];

        for (int j = 1; j <= piles.get(0).size() && j <= k; j++) {
            dp[0][j] = dp[0][j - 1] + piles.get(0).get(j - 1);
        }

        for (int i = 1; i < pilesNum; i++) {
            for (int j = 1; j <= k; j++) {
                int sum = 0;
                for (int m = 0; m <= piles.get(i).size(); m++) {
                    if (m >= 1) {
                        sum += piles.get(i).get(m - 1);
                    }
                    if (j >= m) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - m] + sum);
                    }
                }
            }
        }

        return dp[pilesNum - 1][k];
    }

    public static void main(String[] args) {
        int[][] piles1 = {{1, 100, 3}, {7, 8, 9}};
        int k1 = 2;
        System.out.println("Input: piles = [[1,100,3],[7,8,9]], k = 2");
        System.out.println("Output: " + maxCoinValue(piles1, k1));
        // Expected output: 101

        int[][] piles2 = {{100}, {100}, {100}, {100}, {100}, {100}, {1, 1, 1, 1, 1, 1, 700}};
        int k2 = 7;
        System.out.println("Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k = 7");
        System.out.println("Output: " + maxCoinValue(piles2, k2));
        // Expected output: 706
    }
}
