package com.geekbo.training.leetcode.premium;

import java.util.Arrays;

/**
 *
 * 1231. 分享巧克力
 * 困难
 * 你有一大块巧克力，它由一些甜度不完全相同的小块组成。我们用数组 sweetness 来表示每一小块的甜度。
 *
 * 你打算和 K 名朋友一起分享这块巧克力，所以你需要将切割 K 次才能得到 K+1 块，每一块都由一些 连续 的小块组成。
 *
 * 为了表现出你的慷慨，你将会吃掉 总甜度最小 的一块，并将其余几块分给你的朋友们。
 *
 * 请找出一个最佳的切割策略，使得你所分得的巧克力 总甜度最大，并返回这个 最大总甜度。
 *
 *
 *
 * 示例 1：
 *
 * 输入：sweetness = [1,2,3,4,5,6,7,8,9], K = 5
 * 输出：6
 * 解释：你可以把巧克力分成 [1,2,3], [4,5], [6], [7], [8], [9]。
 * 示例 2：
 *
 * 输入：sweetness = [5,6,7,8,9,1,2,3,4], K = 8
 * 输出：1
 * 解释：只有一种办法可以把巧克力分成 9 块。
 * 示例 3：
 *
 * 输入：sweetness = [1,2,2,1,2,2,1,2,2], K = 2
 * 输出：5
 * 解释：你可以把巧克力分成 [1,2,2], [1,2,2], [1,2,2]。
 *
 *
 * 提示：
 *
 * 0 <= K < sweetness.length <= 10^4
 * 1 <= sweetness[i] <= 10^5
 *
 */
public class ChocolateShare {

    public static int maximizeSweetness(int[] sweetness, int k) {
        // 初始化左右边界。
        // left = 1 并且 right = total 甜度 / number of people.
        int numberOfPeople = k + 1;
        int left = Arrays.stream(sweetness).min().getAsInt();
        int right = Arrays.stream(sweetness).sum() / numberOfPeople;

        while (left < right) {
            // 获取左右边界之前的中间值索引。
            // cur_sweetness 表示当前人的总甜度。
            // people_with_chocolate 表示有一块巧克力甜度大于等于 mid 的人数。
            int mid = (left + right + 1) / 2;
            int curSweetness = 0;
            int peopleWithChocolate = 0;

            // 开始为当前人分配块。
            for (int s : sweetness) {
                curSweetness += s;

                // 当他的总甜度不小于 mid，说明我们
                // 已经结束了对他的分配，继续处理下一个人
                if (curSweetness >= mid) {
                    peopleWithChocolate += 1;
                    curSweetness = 0;
                }
            }

            // 检查我们是否成功给了每个人一块甜度不小于 mid 的巧克力
            // 并且缩小一半搜索范围。
            if (peopleWithChocolate >= numberOfPeople) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        // 一旦左右边界相遇，我们找到了目标值
        // 就是我们能够得到的最大甜度。
        return right;
    }

    public static void main(String[] args) {
        // 测试用例
        int[] sweetness1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int K1 = 5;
        System.out.println(maximizeSweetness(sweetness1, K1));  // 输出：6

        int[] sweetness2 = {5, 6, 7, 8, 9, 1, 2, 3, 4};
        int K2 = 8;
        System.out.println(maximizeSweetness(sweetness2, K2));  // 输出：1

        int[] sweetness3 = {1, 2, 2, 1, 2, 2, 1, 2, 2};
        int K3 = 2;
        System.out.println(maximizeSweetness(sweetness3, K3));  // 输出：5
    }
}