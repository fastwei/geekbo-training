package com.geekbo.training.leetcode.daily;


import java.util.Arrays;

/**
 *
 * 1561. Maximum Number of Coins You Can Get
 * Medium
 * There are 3n piles of coins of varying size,
 * you and your friends will take piles of coins as follows:
 *
 * In each step, you will choose any 3 piles of coins (not necessarily consecutive).
 * Of your choice, Alice will pick the pile with the maximum number of coins.
 * You will pick the next pile with the maximum number of coins.
 * Your friend Bob will pick the last pile.
 * Repeat until there are no more piles of coins.
 * Given an array of integers piles where piles[i] is the number of coins in the ith pile.
 *
 * Return the maximum number of coins that you can have.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [2,4,1,2,7,8]
 * Output: 9
 * Explanation: Choose the triplet (2, 7, 8), Alice Pick the pile with 8 coins, you the pile with 7 coins and Bob the last one.
 * Choose the triplet (1, 2, 4), Alice Pick the pile with 4 coins, you the pile with 2 coins and Bob the last one.
 * The maximum number of coins which you can have are: 7 + 2 = 9.
 * On the other hand if we choose this arrangement (1, 2, 8), (2, 4, 7) you only get 2 + 4 = 6 coins which is not optimal.
 * Example 2:
 *
 * Input: piles = [2,4,5]
 * Output: 4
 *
 */
class MaximumNumberOfCoin {
    /**
     * To find the maximum number of coins you can have,
     * you need to follow the strategy described in the problem:
     * <p>
     * Sort the piles array in descending order.
     * Initialize variables totalCoins = 0 and index = 1.
     * While index < 2n:
     * Add piles[index] to totalCoins.
     * Increment index by 2.
     * Return the value of totalCoins.
     * The time complexity of this algorithm is O(n log n),
     * where n is the length of the piles array.
     * This is because sorting the array takes O(n log n)time.
     * The space complexity is O(1)as we are not using any extra space that grows with the input size.
     */
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int totalCoins = 0;
        int index = piles.length - 2;

        while (index >= piles.length / 3) {
            totalCoins += piles[index];
            index -= 2;
        }

        return totalCoins;
    }
}
