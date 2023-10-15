package com.geekbo.training.leetcode.top150;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * <p>
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 * Example 3:
 * <p>
 * Input: n = 0
 * Output: 0
 * <p>
 * Follow up: Could you write a solution that works in logarithmic time complexity?
 */
public class FactorialTrailingZeroes {
    /**
     * 优化版本
     * 观察阶乘中末尾零的个数与数字中因子5的个数有关。
     * 我们可以发现，阶乘中的数字可以分解为一系列的因子，
     * 其中有些因子是5的倍数，有些因子是25的倍数，有些因子是125的倍数，以此类推。
     * <p>
     * 因此，我们可以通过不断地除以5的幂次来计算阶乘中因子5的个数。
     * 这个优化的算法使用一个循环，每次将n除以5，并将结果累加到count中。
     * 这样做的原因是，每个除以5的操作相当于计算了n中因子5的个数，并且每个较大的5的幂次因子都已经被计算过了。
     * 最终得到的count就是阶乘中末尾零的个数。
     * <p>
     * 这个优化的算法的时间复杂度是O(logn)，因为我们每次将n除以5，并且每次除法操作都会减小n的规模。
     * 因此，算法的时间复杂度符合题目要求的对数时间复杂度要求。
     */
    public int trailingZeroes(int n) {
        int count = 0;

        // 计算n中因子5的个数
        while (n > 0) {
            n /= 5;
            count += n;
        }

        return count;
    }


    /**
     * 为了以对数时间复杂度解决这个问题，我们可以观察阶乘中末尾的零的个数与数字中因子5的个数有关。
     * 因为每个2都能与一个5相乘得到一个10，并且阶乘中2的个数远多于5的个数，所以我们只需要计算阶乘中5的个数即可。
     * <p>
     * 算法的思路是从2到n，依次计算每个数字中因子5的个数。
     * 对于每个数字，我们通过不断地除以5，并累加因子5的个数，直到数字不能再被5整除为止。
     * 最终得到的累加值就是阶乘中末尾零的个数。
     * <p>
     * 算法的时间复杂度是O(nlogn)，因为我们需要遍历从2到n的所有数字，并对每个数字进行不断的除法操作。
     * 这个时间复杂度符合题目要求的对数时间复杂度要求。
     */
    public int trailingZeroes2(int n) {
        int count = 0;

        // 从2到n，依次计算每个数字中因子5的个数
        for (int i = 2; i <= n; i++) {
            int num = i;
            while (num % 5 == 0) {
                count++;
                num /= 5;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        FactorialTrailingZeroes factorialTrailingZeroes = new FactorialTrailingZeroes();

        // 测试用例 1
        int n1 = 3;
        System.out.println(factorialTrailingZeroes.trailingZeroes(n1));  // 输出: 0

        // 测试用例 2
        int n2 = 5;
        System.out.println(factorialTrailingZeroes.trailingZeroes(n2));  // 输出: 1

        // 测试用例 3
        int n3 = 0;
        System.out.println(factorialTrailingZeroes.trailingZeroes(n3));  // 输出: 0
    }
}
