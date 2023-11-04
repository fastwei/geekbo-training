package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. Permutation Sequence
 * Hard
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 * <p>
 * Input: n = 4, k = 9
 * Output: "2314"
 * Example 3:
 * <p>
 * Input: n = 3, k = 1
 * Output: "123"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 9
 * 1 <= k <= n!
 */
public class PermutationSequence {

    /**
     * 解题思路： 根据题目的要求，我们需要找到第k个排列。我们可以根据数字的排列规律来逐位确定每一位的数字。
     * <p>
     * 首先，我们构建一个原始的数字列表，从1到n。然后，我们计算出每个位置的阶乘数，即factorials数组。
     * <p>
     * 接下来，我们将k转换为从0开始的索引，即k--。
     * <p>
     * 然后，我们从最高位开始向最低位遍历，对于每一位，我们计算出当前位的索引index和余数k。
     * 我们将nums列表中的第index个数字添加到结果字符串中，并将该数字从列表中移除。
     * <p>
     * 最后，我们将结果字符串转换为字符串形式并返回。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：构建原始数字列表的时间复杂度为O(n)，计算阶乘数的时间复杂度为O(n)，构建结果字符串的时间复杂度为O(n)。
     * 因此，总的时间复杂度为O(n)。
     * 空间复杂度：除了存储结果字符串的空间外，额外使用了一个数字列表和一个整数数组，它们的大小都为n。
     * 因此，总的空间复杂度为O(n)。
     *
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n, int k) {
        // 构建原始的数字列表
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        // 计算阶乘数
        int[] factorials = new int[n];
        factorials[0] = 1;
        for (int i = 1; i < n; i++) {
            factorials[i] = factorials[i - 1] * i;
        }

        // 将k转换为从0开始的索引
        k--;

        // 构建结果字符串
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            int index = k / factorials[i];
            k %= factorials[i];
            sb.append(nums.remove(index));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // 测试用例
        int n1 = 3;
        int k1 = 3;
        // 预期输出: "213"
        System.out.println(getPermutation(n1, k1));

        int n2 = 4;
        int k2 = 9;
        // 预期输出: "2314"
        System.out.println(getPermutation(n2, k2));

        int n3 = 3;
        int k3 = 1;
        // 预期输出: "123"
        System.out.println(getPermutation(n3, k3));
    }
}
