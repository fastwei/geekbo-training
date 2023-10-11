package com.geekbo.training.leetcode.top150;
/**
 * 给定一个整数数组citations，其中citations[i]表示研究者第i篇论文的引用次数，
 * 返回研究者的h指数。
 *
 * 根据维基百科对h指数的定义：h指数是一个研究者的最大值，使得该研究者至少发表h篇论文，
 * 每篇论文被引用至少h次。
 *
 * 解题思路：
 * 1. 首先对citations数组进行排序。
 * 2. 然后从后向前遍历排序后的数组，找到最大的h指数。
 * 3. h指数的计算规则是：在排序后的数组中，从第i篇论文开始，如果citations[i] >= i + 1，
 *    则当前的h指数是i + 1，否则继续向前。
 * 4. 返回找到的最大h指数。
 *
 * 时间复杂度分析：
 * 对数组进行排序的时间复杂度是O(nlogn)，遍历数组的时间复杂度是O(n)，总体时间复杂度是O(nlogn)。
 *
 * 空间复杂度分析：
 * 除了输入参数外，没有使用额外空间，空间复杂度是O(1)。
 */

import java.util.Arrays;

public class HIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        int h = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (citations[i] >= n - i) {
                h = n - i;
            } else {
                break;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        HIndex solution = new HIndex();
        int[] citations1 = {3, 0, 6, 1, 5};
        System.out.println("Test Case 1: " + solution.hIndex(citations1)); // Output: 3

        int[] citations2 = {1, 3, 1};
        System.out.println("Test Case 2: " + solution.hIndex(citations2)); // Output: 1
    }
}
