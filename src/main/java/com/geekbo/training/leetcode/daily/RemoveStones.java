package com.geekbo.training.leetcode.daily;

import java.util.Arrays;

/**
 * 1962. Remove Stones to Minimize the Total
 * Medium
 * You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile, and an integer k. You should apply the following operation exactly k times:
 * <p>
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 * <p>
 * Return the minimum possible total number of stones remaining after applying the k operations.
 * <p>
 * floor(x) is the greatest integer that is smaller than or equal to x (i.e., rounds x down).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: piles = [5,4,9], k = 2
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [5,4,5].
 * - Apply the operation on pile 0. The resulting piles are [3,4,5].
 * The total number of stones in [3,4,5] is 12.
 * Example 2:
 * <p>
 * Input: piles = [4,3,6,7], k = 3
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [4,3,3,7].
 * - Apply the operation on pile 3. The resulting piles are [4,3,3,4].
 * - Apply the operation on pile 0. The resulting piles are [2,3,3,4].
 * The total number of stones in [2,3,3,4] is 12.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= piles.length <= 105
 * 1 <= piles[i] <= 104
 * 1 <= k <= 105
 */
public class RemoveStones {
    /**
     * Removes stones from piles to minimize the total number of stones.
     * 解题思路：
     * <p>
     * 首先，我们对数组进行排序，以便每次操作时能够取到最大的堆。
     * 然后，我们进行k次操作，每次从数组的后半部分（即堆的较大值）开始，将堆的值除以2并向上取整，即减少堆中的石头数量。
     * 每次操作后，我们将k的值减1，直到k为0或者操作循环结束。
     * 最后，我们计算数组中所有堆的值的总和，并将其返回作为结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 排序数组的时间复杂度为O(nlogn)，n为堆的数量。
     * 执行k次操作，每次操作需要重新排序数组，所以总共的时间复杂度为O(knlogn)。
     * 计算堆中值的总和的时间复杂度为O(n)。
     * 因此，总的时间复杂度为O(knlogn + n)。
     * 空间复杂度是O(1)，因为除了存储输入参数和临时变量之外，没有使用额外的数据结构。
     * 输入参数piles和k是原地修改的，不需要额外的空间。
     * 临时变量sum用于计算总和，也不需要额外的空间。因此，空间复杂度是常数级别的。
     *
     * @param piles The array representing the number of stones in each pile.
     * @param k     The number of operations to be performed.
     * @return The minimum possible total number of stones remaining.
     */
    public static int minStoneSum(int[] piles, int k) {
        while (k > 0) {
            Arrays.sort(piles);
            for (int i = piles.length - 1; i >= piles.length / 2; i--) {
                if (k == 0) {
                    break;
                }
                float value = piles[i] / 2f;
                piles[i] = (int) Math.ceil(value);
                k--;
            }
        }

        int sum = 0;
        for (int val : piles) {
            sum += val;
        }
        return sum;
    }

    public static void main(String[] args) {
        // Test cases
        int[] piles1 = {5, 4, 9};
        int k1 = 2;
        System.out.println("Input: piles = [5,4,9], k = 2");
        System.out.println("Output: " + minStoneSum(piles1, k1));
        // Expected output: 12

        int[] piles2 = {4, 3, 6, 7};
        int k2 = 3;
        System.out.println("Input: piles = [4,3,6,7], k = 3");
        System.out.println("Output: " + minStoneSum(piles2, k2));
        // Expected output: 12
    }
}

