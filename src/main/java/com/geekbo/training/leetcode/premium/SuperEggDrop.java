package com.geekbo.training.leetcode.premium;

/**
 *
 *
 * 887. 鸡蛋掉落
 * 困难
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 *
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 *
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 *
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 *
 *
 * 示例 1：
 *
 * 输入：k = 1, n = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
 * 如果它没碎，那么肯定能得出 f = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
 * 示例 2：
 *
 * 输入：k = 2, n = 6
 * 输出：3
 * 示例 3：
 *
 * 输入：k = 3, n = 14
 * 输出：4
 *
 *
 * 提示：
 *
 * 1 <= k <= 100
 * 1 <= n <= 104
 */

public class SuperEggDrop {

    /**
     * 思路是固定鸡蛋的数量，利用二分查找来确定最优的楼层高度。
     * 具体来说，对于给定的鸡蛋数量 k 和楼层数量 n，我们在 [1, n] 的范围内进行二分查找，
     * 找到一个楼层高度 x，使得使用 k 个鸡蛋在这个楼层高度进行测试所需要的最小操作次数最小。
     * 然后，我们更新 dp[i][j] 的值为 Math.max(dp[i - 1][x - 1], dp[i][j - x]) + 1，
     * 其中 x 是通过二分查找得到的最优楼层高度。最后，返回 dp[k][n] 即可。
     * <p>
     * 时间复杂度是 O(knlogn)，空间复杂度是 O(kn)。
     * 由于使用了二分查找优化，所以可以在给定的时间和空间限制下解决这个问题。
     *
     * @param k
     * @param n
     * @return
     */
    public static int superEggDrop(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            dp[1][i] = i;
        }

        for (int i = 2; i <= k; i++) {
            int x = 1;
            for (int j = 1; j <= n; j++) {
                while (x < j && Math.max(dp[i - 1][x - 1], dp[i][j - x]) > Math.max(dp[i - 1][x], dp[i][j - x - 1])) {
                    x++;
                }
                dp[i][j] = Math.max(dp[i - 1][x - 1], dp[i][j - x]) + 1;
            }
        }

        return dp[k][n];
    }

    public static void main(String[] args) {
        int k1 = 1, n1 = 2;
        System.out.println(superEggDrop(k1, n1));  // 预期输出: 2

        int k2 = 2, n2 = 6;
        System.out.println(superEggDrop(k2, n2));  // 预期输出: 3

        int k3 = 3, n3 = 14;
        System.out.println(superEggDrop(k3, n3));  // 预期输出: 4
    }

}
