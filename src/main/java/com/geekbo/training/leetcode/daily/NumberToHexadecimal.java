package com.geekbo.training.leetcode.daily;

/**
 * 405. Convert a Number to Hexadecimal
 * Easy
 * Given an integer num, return a string representing its hexadecimal representation. For negative integers, two’s complement method is used.
 * <p>
 * All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in the answer except for the zero itself.
 * <p>
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 26
 * Output: "1a"
 * Example 2:
 * <p>
 * Input: num = -1
 * Output: "ffffffff"
 */
public class NumberToHexadecimal {
    /**
     * 解题思路：
     * <p>
     * 这个问题可以使用位运算来解决。
     * 对于正整数，直接将其转换为16进制表示即可。
     * 对于负整数，需要使用补码的形式，即将其加上2的补码，然后再转换为16进制表示。
     * 使用位运算，每次取出num的低4位，将其转换为对应的16进制字符，然后将该字符插入到结果字符串的最前面。
     * 将num右移4位（无符号右移），继续处理剩下的位，直到num为0。
     * 最后返回结果字符串。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：由于num的位数是固定的，所以时间复杂度为O(1)。
     * 空间复杂度：使用了一个StringBuilder来存储结果字符串，所以空间复杂度为O(1)。
     * 总结：
     * <p>
     * 使用位运算的思路，将整数转换为对应的16进制表示。
     * 时间复杂度为O(1)，空间复杂度为O(1)。
     *
     * @param num
     * @return
     */
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder hex = new StringBuilder();

        while (num != 0) {
            int digit = num & 15;
            char ch = (digit < 10) ? (char) ('0' + digit) : (char) ('a' + digit - 10);
            hex.insert(0, ch);
            num >>>= 4; // 无符号右移4位
        }

        return hex.toString();
    }

    public static void main(String[] args) {
        NumberToHexadecimal solution = new NumberToHexadecimal();

        // 测试用例1
        int num1 = 26;
        String result1 = solution.toHex(num1);
        System.out.println("测试用例1:");
        System.out.println(result1); // 预期输出: "1a"

        // 测试用例2
        int num2 = -1;
        String result2 = solution.toHex(num2);
        System.out.println("测试用例2:");
        System.out.println(result2); // 预期输出: "ffffffff"
    }
}
