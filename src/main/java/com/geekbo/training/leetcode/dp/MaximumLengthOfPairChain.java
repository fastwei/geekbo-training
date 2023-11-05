package com.geekbo.training.leetcode.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 646. Maximum Length of Pair Chain
 * Medium
 * Topics
 * Companies
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 * <p>
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c.
 * A chain of pairs can be formed in this fashion.
 * <p>
 * Return the length longest chain which can be formed.
 * <p>
 * You do not need to use up all the given intervals. You can select pairs in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: pairs = [[1,2],[2,3],[3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4].
 * Example 2:
 * <p>
 * Input: pairs = [[1,2],[7,8],[4,5]]
 * Output: 3
 * Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].
 */
public class MaximumLengthOfPairChain {
    /**
     * 计算可以形成的最长链的长度
     * 解题思路：
     * <p>
     * 首先，我们根据每个区间的右边界对数组进行排序。
     * 这样做的目的是为了保证在形成链时，我们总是选择右边界最小的区间。
     * 然后，我们从左到右遍历数组，对于每个区间，如果它的左边界大于当前链的右边界，
     * 说明可以将该区间加入到链中，并更新当前链的右边界为该区间的右边界。
     * 最终，我们得到的链的长度就是可以形成的最长链的长度。
     * 算法复杂度分析：
     * <p>
     * 排序时间复杂度为 O(n log n)，其中 n 是数组的长度。
     * 遍历数组的时间复杂度为 O(n)。
     * <p>
     * 注意：
     * <p>
     * 在测试用例中，我们使用二维数组来表示区间，其中每个区间为 [left, right]。
     * 题目中给出的区间对满足 left < right 的条件。
     *
     * @param pairs 原始数组
     * @return 最长链的长度
     */
    public int findLongestChain(int[][] pairs) {
        // 根据右边界对数组进行排序
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int currentEnd = pairs[0][1];

        // 遍历数组，找到可以形成链的最大长度
        for (int i = 1; i < pairs.length; i++) {
            int[] pair = pairs[i];
            if (pair[0] > currentEnd) {
                count++;
                currentEnd = pair[1];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // 创建测试用例
        int[][] pairs1 = {{1, 2}, {2, 3}, {3, 4}};
        int[][] pairs2 = {{1, 2}, {7, 8}, {4, 5}};

        // 创建解法对象
        MaximumLengthOfPairChain maximumLengthOfPairChain = new MaximumLengthOfPairChain();

        // 测试用例1
        // 预期输出: 2
        System.out.println(maximumLengthOfPairChain.findLongestChain(pairs1));

        // 测试用例2
        // 预期输出: 3
        System.out.println(maximumLengthOfPairChain.findLongestChain(pairs2));
    }
}
