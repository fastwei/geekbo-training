package com.geekbo.training.leetcode.top75;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of k.
 * Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * <p>
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 * Example 2:
 * <p>
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 * Example 3:
 * <p>
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 */
public class KokoEatingBananas {


    /**
     * 解题思路：
     * <p>
     * 首先，确定吃香蕉速度的搜索范围。最小可能的速度是1，最大可能的速度是所有香蕉堆中最大的堆的数量。
     * 使用二分查找在搜索范围内查找最小速度，以便在给定时间内吃完所有香蕉。
     * 在二分查找中，对每个速度尝试是否可以在规定时间内吃完所有香蕉，如果可以，将搜索范围缩小到左半部分，否则，将搜索范围缩小到右半部分。
     * 返回最终找到的最小速度作为答案。
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(n * log(maxPile))，其中n是香蕉堆的数量，maxPile是堆中香蕉的最大数量。
     * 空间复杂度：O(1)。没有使用额外的数据结构。
     *
     * @param piles
     * @param h
     * @return
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1; // 最小可能的吃香蕉速度
        int right = getMaxPile(piles); // 最大可能的吃香蕉速度

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canEatAll(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private static boolean canEatAll(int[] piles, int h, int k) {
        int hoursNeeded = 0;
        for (int pile : piles) {
            hoursNeeded += (pile + k - 1) / k; // 向上取整
        }
        return hoursNeeded <= h;
    }

    private static int getMaxPile(int[] piles) {
        int max = 0;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        int result1 = minEatingSpeed(piles1, h1);
        System.out.println(result1); // 预期输出：4

        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        int result2 = minEatingSpeed(piles2, h2);
        System.out.println(result2); // 预期输出：30

        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        int result3 = minEatingSpeed(piles3, h3);
        System.out.println(result3); // 预期输出：23
    }
}
