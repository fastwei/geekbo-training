package com.geekbo.training.leetcode.daily;

/**
 * 233. Number of Digit One
 * Hard
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers
 * less than or equal to n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 13
 * Output: 6
 * Example 2:
 * <p>
 * Input: n = 0
 * Output: 0
 */
class NumberOfDigitOne {
    /**
     * 解题思路：
     * <p>
     * 遍历数字的每一位，从低位到高位。
     * 对于当前位的数字cur，根据cur的值和高位、低位的情况来计算出现1的次数。
     * 当cur为0时，出现1的次数由高位决定。
     * 当cur为1时，出现1的次数由高位和低位决定。
     * 当cur大于1时，出现1的次数由高位决定。
     * 最后累加所有位上出现1的次数即可。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 数字n的位数为O(logn)。
     * 在每一位上的计算时间复杂度为O(1)。
     * 因此，总的时间复杂度为O(logn)。
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int count = 0;
        long factor = 1;

        while (n >= factor) {
            // 将数字分成高位和低位两部分
            long high = n / (factor * 10);
            long cur = (n / factor) % 10;
            long low = n % factor;

            if (cur == 0) {
                // 当前位的数字为0，出现1的次数由高位决定
                count += high * factor;
            } else if (cur == 1) {
                // 当前位的数字为1，出现1的次数由高位和低位决定
                count += high * factor + low + 1;
            } else {
                // 当前位的数字大于1，出现1的次数由高位决定
                count += (high + 1) * factor;
            }

            factor *= 10;
        }

        return count;
    }

    public static void main(String[] args) {
        NumberOfDigitOne numberOfDigitOne = new NumberOfDigitOne();

        // 测试用例1
        int n1 = 13;
        int expected1 = 6;
        int result1 = numberOfDigitOne.countDigitOne(n1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);

        // 测试用例2
        int n2 = 0;
        int expected2 = 0;
        int result2 = numberOfDigitOne.countDigitOne(n2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);
    }
}

