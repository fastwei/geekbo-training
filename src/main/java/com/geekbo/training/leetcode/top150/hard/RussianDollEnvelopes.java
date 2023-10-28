package com.geekbo.training.leetcode.top150.hard;

import java.util.Arrays;

/**
 *
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi]
 * represents the width and the height of an envelope.
 *
 * One envelope can fit into another if and only if both the width
 * and height of one envelope are greater than the other envelope's width and height.
 *
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 *
 * Note: You cannot rotate an envelope.
 *
 *
 *
 * Example 1:
 *
 * Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * Example 2:
 *
 * Input: envelopes = [[1,1],[1,1],[1,1]]
 * Output: 1
 *
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {
        int[][] envelopes1 = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(maxEnvelopes(envelopes1)); // Output: 3

        int[][] envelopes2 = {{1, 1}, {1, 1}, {1, 1}};
        System.out.println(maxEnvelopes(envelopes2)); // Output: 1
    }

    /**
     * 解题思路：
     * 首先，对信封按照宽度进行升序排序，如果宽度相同，则按照高度进行降序排序。
     * 排序后，我们要找到高度序列的最长递增子序列的长度。
     * 这是因为如果一个信封能够包含另一个信封，那么这两个信封按照宽度排序后，高度也是递增的。
     * 所以我们只需要找到高度序列的最长递增子序列的长度即可。
     * <p>
     * 算法复杂度分析：
     * 时间复杂度：O(nlogn)，其中 n 是信封的数量。对信封进行排序的时间复杂度是 O(nlogn)，
     * 查找最长递增子序列的时间复杂度是 O(nlogn)，所以总时间复杂度是 O(nlogn)。
     * 空间复杂度：O(n)，需要使用一个临时数组来存储信封的高度序列。
     *
     * @param envelopes 信封数组
     * @return 最大的俄罗斯套娃信封数量
     */
    public static int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 对信封按照宽度进行升序排序，如果宽度相同，则按照高度进行降序排序
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        // 对高度序列进行查找最长递增子序列的长度
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = envelopes[i][1];
        }
        return lengthOfLIS(heights);
    }

    private static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int len = 0;
        for (int num : nums) {
            int left = 0, right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (dp[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            dp[left] = num;
            if (left == len) {
                len++;
            }
        }
        return len;
    }
}