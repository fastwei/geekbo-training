package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 1569. Number of Ways to Reorder Array to Get Same BST
 * Hard
 * Given an array nums that represents a permutation of integers from 1 to n.
 * We are going to construct a binary search tree (BST) by inserting the elements of nums
 * in order into an initially empty BST. Find the number of different ways to reorder nums
 * so that the constructed BST is identical to that formed from the original array nums.
 * <p>
 * For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child,
 * and 3 as a right child. The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
 * Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.
 * <p>
 * Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: nums = [2,1,3]
 * Output: 1
 * Explanation: We can reorder nums to be [2,3,1] which will yield the same BST.
 * There are no other ways to reorder nums which will yield the same BST.
 * Example 2:
 * <p>
 * <p>
 * Input: nums = [3,4,5,1,2]
 * Output: 5
 * Explanation: The following 5 arrays will yield the same BST:
 * [3,1,2,4,5]
 * [3,1,4,2,5]
 * [3,1,4,5,2]
 * [3,4,1,2,5]
 * [3,4,1,5,2]
 * Example 3:
 * <p>
 * <p>
 * Input: nums = [1,2,3]
 * Output: 0
 * Explanation: There are no other orderings of nums that will yield the same BST.
 */
public class NumberOfWaysToReorderArray {
    private static final int MOD = 1000000007;

    /**
     * 使用了递归的方式计算了排列方式的数量。
     * comb 方法被用来计算组合数。
     * 在 countWays 方法中，我们将数组 nums 拆分成左子树和右子树，并递归计算它们的排列方式数量。
     * 最后，我们使用组合数的计算结果，乘以左子树和右子树的排列方式数量，得到最终的结果。注意，最终结果需要模 MOD 取余。
     * <p>
     * 请注意，这个问题的解决方案已经是一个优化的算法，因此在大多数情况下，它应该已经足够快速和高效。
     *
     * @param nums
     * @return
     */
    public int numOfWays(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        return countWays(list) - 1;
    }

    private int countWays(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = nums.get(0);

        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) {
                left.add(nums.get(i));
            } else {
                right.add(nums.get(i));
            }
        }

        long leftCount = countWays(left);
        long rightCount = countWays(right);

        return (int) ((comb(nums.size() - 1, left.size()) % MOD) * (leftCount % MOD) % MOD * (rightCount % MOD) % MOD);
    }

    private long comb(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, k); j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 1, 3};
        NumberOfWaysToReorderArray solution = new NumberOfWaysToReorderArray();
        System.out.println("Input: nums = [2, 1, 3]");
        System.out.println("Output: " + solution.numOfWays(nums1)); // Expected Output: 1

        int[] nums2 = {3, 4, 5, 1, 2};
        System.out.println("Input: nums = [3, 4, 5, 1, 2]");
        System.out.println("Output: " + solution.numOfWays(nums2)); // Expected Output: 5

        int[] nums3 = {1, 2, 3};
        System.out.println("Input: nums = [1, 2, 3]");
        System.out.println("Output: " + solution.numOfWays(nums3)); // Expected Output: 0
    }
}