package com.geekbo.training.leetcode.top150;

public class ReverseInteger {
    /**
     * 解题思路：
     * <p>
     * 我们使用循环来逐位反转整数 x。
     * 在每次循环中，我们取 x 的个位数 digit，然后将 reversed 乘以 10 并加上 digit，得到反转后的整数。
     * 在每次循环之前，我们检查反转后的整数是否会溢出，如果溢出则返回 0。
     * 最后，返回反转后的整数 reversed。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：需要遍历整数 x 的每一位数字，因此时间复杂度为 O(logx)。
     * 空间复杂度：除了存储输入和输出变量外，额外使用的空间为常数级别，因此空间复杂度为 O(1)。
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            int digit = x % 10;
            // 检查反转后的整数是否会溢出
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > 7))
                return 0;
            if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10 && digit < -8))
                return 0;

            reversed = reversed * 10 + digit;
            x /= 10;
        }

        return reversed;
    }

    public static void main(String[] args) {
        int x1 = 123;
        int result1 = reverse(x1);
        System.out.println(result1);
        // 预期输出: 321

        int x2 = -123;
        int result2 = reverse(x2);
        System.out.println(result2);
        // 预期输出: -321

        int x3 = 120;
        int result3 = reverse(x3);
        System.out.println(result3);
        // 预期输出: 21
    }
}
