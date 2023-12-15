package com.geekbo.training.leetcode.codeinterview109;

/**
 * 阶乘尾数
 * 解题思路：
 * 尾随零的个数取决于阶乘中因子5的个数，因为2和5相乘才会产生10，而2的个数远多于5的个数。
 * 因此，计算阶乘中因子5的个数即可得到尾随零的个数。
 * 对于一个数n，n!中因子5的个数为n/5 + n/25 + n/125 + ...，依次计算直到n/5^k为0。
 * 算法复杂度分析：
 * 时间复杂度：O(log n)。每次除以5，需要进行log n次操作。
 * 空间复杂度：O(1)。只使用了常数个额外变量。
 */
public class TrailingZeroes {
    public static int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    public static void main(String[] args) {
        // 测试用例
        System.out.println(trailingZeroes(3)); // 0
        System.out.println(trailingZeroes(5)); // 1
    }
}