package com.geekbo.training.leetcode.daily;

/**
 * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * <p>
 * For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
 * Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1, k = 1
 * Output: 0
 * Explanation: row 1: 0
 * Example 2:
 * <p>
 * Input: n = 2, k = 1
 * Output: 0
 * Explanation:
 * row 1: 0
 * row 2: 01
 * Example 3:
 * <p>
 * Input: n = 2, k = 2
 * Output: 1
 * Explanation:
 * row 1: 0
 * row 2: 01
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 30
 * 1 <= k <= 2n - 1
 *
 * todo:加深理解
 */
public class KthSymbolInGrammar {
    /**
     * 返回第 n 行的第 k 个(1-indexed)符号。
     * 解题思路：
     * <p>
     * 根据题目描述，第 n 行的字符串由第 n-1 行的字符串生成。
     * 观察可以发现，第 n 行的前半部分和第 n-1 行完全相同，后半部分和第 n-1 行完全相反。
     * 因此，可以采用递归的方式来计算第 n 行的第 k 个符号。
     * 如果 k 小于等于第 n-1 行的长度的一半（即 mid = 2^(n-2)），则第 k 个符号和第 n-1 行的第 k 个符号相同。
     * 否则，第 k 个符号和第 n-1 行的第 k-mid 个符号取反。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 由于每次递归将问题规模缩小一半，递归深度为 O(log n)。
     * 在每一层递归中，只有一个数字需要计算，时间复杂度为 O(1)。
     * 因此，整体的时间复杂度为 O(log n)。
     *
     * @param n 第 n 行
     * @param k 第 k 个符号
     * @return 第 k 个符号的值 (0 或 1)
     */
    public static int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }

        int mid = (int) Math.pow(2, n - 2); // 计算前一行的中间位置
        if (k <= mid) {
            return kthGrammar(n - 1, k); // 在前一行的前半部分，递归计算
        } else {
            return 1 - kthGrammar(n - 1, k - mid); // 在前一行的后半部分，递归计算并取反
        }
    }

    public static void main(String[] args) {
        int n1 = 1, k1 = 1;
        // 预期输出: 0
        System.out.println(kthGrammar(n1, k1));

        int n2 = 2, k2 = 1;
        // 预期输出: 0
        System.out.println(kthGrammar(n2, k2));

        int n3 = 2, k3 = 2;
        // 预期输出: 1
        System.out.println(kthGrammar(n3, k3));
    }
}
