package com.geekbo.training.leetcode.daily;

/**
 * 1423. Maximum Points You Can Obtain from Cards
 * Medium
 * <p>
 * There are several cards arranged in a row, and each card has an associated number of points.
 * The points are given in the integer array cardPoints.
 * <p>
 * In one step, you can take one card from the beginning or from the end of the row.
 * You have to take exactly k cards.
 * <p>
 * Your score is the sum of the points of the cards you have taken.
 * <p>
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1.
 * However, choosing the rightmost card first will maximize your total score.
 * The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 * Example 2:
 * <p>
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 * Example 3:
 * <p>
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 */
public class MaximumPointsFromCards {
    /**
     * 解题思路：
     * 给定一个整数数组cardPoints和一个整数k，表示要从数组中取出k张卡片。
     * 每次只能从数组的开头或末尾取出一张卡片，并且取出的卡片数必须为k。
     * 最终得分是取出的卡片的点数之和。
     * 我们需要找出一种取卡片的策略，使得最终得分最大。
     * <p>
     * 为了最大化得分，我们可以使用滑动窗口的技巧。
     * 我们先计算从左侧取出i张卡片的点数之和，再计算从右侧取出k-i张卡片的点数之和。
     * 我们遍历所有的i，找出最大的点数之和即可。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(k)，我们需要计算k次滑动窗口的点数之和。
     * 空间复杂度：O(1)，我们只需要常数级别的额外空间。
     *
     * @param cardPoints 卡片的点数数组
     * @param k          取卡片的数量
     * @return 最大得分
     */
    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k; // 窗口大小
        int windowSum = 0; // 窗口内卡片的点数之和
        int minWindowSum = Integer.MAX_VALUE; // 最小的窗口点数之和
        int totalSum = 0; // 所有卡片的点数之和

        // 计算窗口内卡片的点数之和
        for (int i = 0; i < n; i++) {
            totalSum += cardPoints[i];
            if (i < windowSize) {
                windowSum += cardPoints[i];
            }
        }

        // 通过滑动窗口找到最小的窗口点数之和
        for (int i = windowSize; i < n; i++) {
            minWindowSum = Math.min(minWindowSum, windowSum);
            windowSum += cardPoints[i] - cardPoints[i - windowSize];
        }
        minWindowSum = Math.min(minWindowSum, windowSum);

        return totalSum - minWindowSum;
    }

    public static void main(String[] args) {
        int[] cardPoints1 = {1, 2, 3, 4, 5, 6, 1};
        int k1 = 3;
        System.out.println("Test Case 1: " + maxScore(cardPoints1, k1)); // Output: 12

        int[] cardPoints2 = {2, 2, 2};
        int k2 = 2;
        System.out.println("Test Case 2: " + maxScore(cardPoints2, k2)); // Output: 4

        int[] cardPoints3 = {9, 7, 7, 9, 7, 7, 9};
        int k3
                = 7;
        System.out.println("Test Case 3: " + maxScore(cardPoints3, k3)); // Output: 55
    }
}