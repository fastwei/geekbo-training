package com.geekbo.training.leetcode.crackinterview109;

import java.util.Arrays;

/**
 *
 * 面试题 08.13. 堆箱子
 * 已解答
 * 困难
 * 堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高 hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。箱堆的高度为每个箱子高度的总和。
 *
 * 输入使用数组[wi, di, hi]表示每个箱子。
 *
 * 示例1:
 *
 *  输入：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
 *  输出：6
 * 示例2:
 *
 *  输入：box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]]
 *  输出：10
 * 提示:
 *
 * 箱子的数目不大于3000个。
 *
 */
class BoxPile {
    public int pileBox(int[][] box) {
        Arrays.sort(box, (x, y) -> x[0] - y[0]);
        int[] dp = new int[box.length];
        int res = 0;
        for (int i = 0; i < box.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += box[i][2];
            res = Math.max(dp[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
        BoxPile solution = new BoxPile();

        // 测试用例1
        int[][] box1 = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        int result1 = solution.pileBox(box1);
        System.out.println("测试用例1的输出：" + result1 + "，预期结果：6");

        // 测试用例2
        int[][] box2 = {{1, 1, 1}, {2, 3, 4}, {2, 6, 7}, {3, 4, 5}};
        int result2 = solution.pileBox(box2);
        System.out.println("测试用例2的输出：" + result2 + "，预期结果：10");
    }
}