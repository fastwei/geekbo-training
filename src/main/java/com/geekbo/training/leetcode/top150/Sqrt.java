package com.geekbo.training.leetcode.top150;

public class Sqrt {
    /**
     * 解题思路：
     * <p>
     * 我们使用二分查找法来逼近平方根的整数部分。
     * 首先，处理特殊情况，当 x 为 0 或 1 时，直接返回 x。
     * 然后，我们将左边界 left 设置为 1，将右边界 right 设置为 x。
     * 在每次循环中，我们计算中间值 mid，并比较 mid 的平方与 x 的关系：
     * 如果 mid 的平方大于 x，则平方根应该在 left 和 mid 之间，将右边界 right 更新为 mid - 1。
     * 如果 mid 的平方小于等于 x，则平方根应该在 mid 和 right 之间，将左边界 left 更新为 mid + 1。
     * 循环终止时，右边界 right 将指向平方根的整数部分。
     * 最后，返回右边界 right 作为结果。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：使用二分查找法，每次将搜索范围缩小一半，因此时间复杂度为 O(logx)。
     * 空间复杂度：除了存储输入和输出变量外，额外使用的空间为常数级别，因此空间复杂度为 O(1)。
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        // 处理特殊情况，当 x 为 0 或 1 时，直接返回 x
        if (x == 0 || x == 1)
            return x;

        int left = 1;
        int right = x;

        // 使用二分查找法来逼近平方根的整数部分
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 如果 mid 的平方大于 x，则平方根应该在 left 和 mid 之间
            if (mid > x / mid) {
                right = mid - 1;
            }
            // 如果 mid 的平方小于等于 x，则平方根应该在 mid 和 right 之间
            else {
                left = mid + 1;
            }
        }

        // 返回平方根的整数部分
        return right;
    }

    public static void main(String[] args) {
        int x1 = 4;
        int result1 = mySqrt(x1);
        System.out.println(result1);
        // 预期输出: 2

        int x2 = 8;
        int result2 = mySqrt(x2);
        System.out.println(result2);
        // 预期输出: 2
    }
}
