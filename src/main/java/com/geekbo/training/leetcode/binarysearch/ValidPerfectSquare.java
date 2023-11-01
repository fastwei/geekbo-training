package com.geekbo.training.leetcode.binarysearch;

/**
 * Given a positive integer num, return true if num is a perfect square or false otherwise.
 * <p>
 * A perfect square is an integer that is the square of an integer. In other words,
 * it is the product of some integer with itself.
 * <p>
 * You must not use any built-in library function, such as sqrt.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 16
 * Output: true
 * Explanation: We return true because 4 * 4 = 16 and 4 is an integer.
 * Example 2:
 * <p>
 * Input: num = 14
 * Output: false
 * Explanation: We return false because 3.742 * 3.742 = 14 and 3.742 is not an integer.
 */
public class ValidPerfectSquare {
    /**
     * 解题思路：
     * <p>
     * 首先，我们处理一些特殊情况。如果num为0或1，它们都是完全平方数，因此直接返回true。
     * 接下来，我们使用二分搜索来查找平方根。我们的搜索范围是从1到num的一半。
     * 我们将左边界初始化为1，将右边界初始化为num的一半。
     * 在每次迭代中，我们计算中间值mid，并计算mid的平方。如果mid的平方等于num，则找到了完全平方数，返回true。
     * 如果mid的平方小于num，则说明完全平方数在mid的右侧，将left更新为mid + 1。
     * 如果mid的平方大于num，则说明完全平方数在mid的左侧，将right更新为mid - 1。
     * 如果循环结束后仍然没有找到完全平方数，则返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：二分搜索的时间复杂度为O(logn)，其中n是num的大小。
     * 空间复杂度：算法的空间复杂度为O(1)，只使用了常数级别的额外空间。
     *
     * @param num
     * @return
     */
    public static boolean isPerfectSquare(int num) {
        // 特殊情况处理
        if (num == 0 || num == 1) {
            return true;
        }

        // 使用二分搜索查找平方根
        long left = 1; // 左边界为1
        long right = num / 2; // 右边界为num的一半

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;

            if (square == num) {
                return true; // 找到了平方根
            } else if (square < num) {
                left = mid + 1; // 平方根在右侧
            } else {
                right = mid - 1; // 平方根在左侧
            }
        }

        return false; // 没有找到平方根
    }

    public static void main(String[] args) {
        int num1 = 16;
        // 预期输出: true
        System.out.println(isPerfectSquare(num1));

        int num2 = 14;
        // 预期输出: false
        System.out.println(isPerfectSquare(num2));
    }
}
