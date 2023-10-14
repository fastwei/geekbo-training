package com.geekbo.training.leetcode.top150;

/**
 * Given two integers left and right that represent the range [left, right],
 * return the bitwise AND of all numbers in this range, inclusive.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: left = 5, right = 7
 * Output: 4
 * Example 2:
 * <p>
 * Input: left = 0, right = 0
 * Output: 0
 * Example 3:
 * <p>
 * Input: left = 1, right = 2147483647
 * Output: 0
 * <p>
 * 解题思路：
 * <p>
 * 题目要求计算[left, right]范围内所有数字的按位与结果。
 * 如果[left, right]范围内的数字的二进制表示中，某一位上至少有一个0和一个1，则此位的按位与结果为0。
 * 只有当[left, right]范围内的数字的二进制表示的相同位都为1时，按位与结果才为1。
 * 因此，我们可以通过逐位比较left和right的二进制表示，找到最长的公共前缀，然后将这个公共前缀左移相应的位数得到结果。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(log(right))，其中right是右边界。我们需要比较left和right的二进制表示的每一位，最多需要log(right)次操作。
 * 空间复杂度：O(1)。除了存储结果的变量，我们没有使用额外的空间。
 */
public class BitwiseANDRange {
    /**
     * 计算[left, right]范围内所有数字的按位与结果
     *
     * @param left  左边界
     * @param right 右边界
     * @return 按位与结果
     */
    public static int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        // 当left和right不相等时，右移一位，并将shift加1
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        // 将left左移shift位得到结果
        return left << shift;
    }

    public static void main(String[] args) {
        // 测试用例1
        int left1 = 5;
        int right1 = 7;
        // 预期输出：4
        System.out.println(rangeBitwiseAnd(left1, right1));

        // 测试用例2
        int left2 = 0;
        int right2 = 0;
        // 预期输出：0
        System.out.println(rangeBitwiseAnd(left2, right2));

        // 测试用例3
        int left3 = 1;
        int right3 = 2147483647;
        // 预期输出：0
        System.out.println(rangeBitwiseAnd(left3, right3));
    }
}