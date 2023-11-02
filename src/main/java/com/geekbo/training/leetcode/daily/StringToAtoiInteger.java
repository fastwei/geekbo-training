package com.geekbo.training.leetcode.daily;

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 * <p>
 * The algorithm for myAtoi(string s) is as follows:
 * <p>
 * Read in and ignore any leading whitespace.
 * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
 * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
 * Return the integer as the final result.
 * Note:
 * <p>
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "42"
 * Output: 42
 * Explanation: The underlined characters are what is read in, the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "42" ("42" is read in)
 * ^
 * The parsed integer is 42.
 * Since 42 is in the range [-231, 231 - 1], the final result is 42.
 * Example 2:
 * <p>
 * Input: s = "   -42"
 * Output: -42
 * Explanation:
 * Step 1: "   -42" (leading whitespace is read and ignored)
 * ^
 * Step 2: "   -42" ('-' is read, so the result should be negative)
 * ^
 * Step 3: "   -42" ("42" is read in)
 * ^
 * The parsed integer is -42.
 * Since -42 is in the range [-231, 231 - 1], the final result is -42.
 * Example 3:
 * <p>
 * Input: s = "4193 with words"
 * Output: 4193
 * Explanation:
 * Step 1: "4193 with words" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
 * ^
 * The parsed integer is 4193.
 * Since 4193 is in the range [-231, 231 - 1], the final result is 4193.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 * <p>
 * 解题思路：
 * <p>
 * 我们可以使用有限状态机来解决这个问题。
 * 首先，我们需要定义一个有限状态机的状态集合，其中包括以下几种状态：
 * - 开始状态：初始状态，没有读取任何字符。
 * - 符号状态：读取到符号字符（'+'或'-'）的状态。
 * - 数字状态：读取到数字字符的状态。
 * - 结束状态：读取到非数字字符或字符串结束的状态。
 * <p>
 * 然后，我们需要定义有限状态机的转移规则，即在不同状态下读取不同字符时的状态转移。
 * - 在开始状态下，如果读取到空格字符，则保持在开始状态；如果读取到符号字符，则进入符号状态；如果读取到数字字符，则进入数字状态。
 * - 在符号状态下，如果读取到数字字符，则进入数字状态；否则，进入结束状态。
 * - 在数字状态下，如果读取到数字字符，则保持在数字状态；否则，进入结束状态。
 * - 在结束状态下，保持在结束状态。
 * <p>
 * 最后，我们需要根据状态机的状态得到最终的结果。
 * - 如果状态机在数字状态下结束，则将读取到的数字转换为整数，并根据符号状态决定正负；
 * - 如果状态机在其他状态下结束，则返回0。
 * <p>
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：算法遍历字符串一次，时间复杂度为O(n)，其中n是字符串的长度。
 * 空间复杂度：算法只使用了有限的额外空间，空间复杂度为O(1)。
 */
public class StringToAtoiInteger {
    public int myAtoi(String s) {
        int state = 0; // 有限状态机的初始状态
        int sign = 1; // 符号，默认为正数
        int result = 0; // 结果，默认为0

        // 遍历字符串
        for (char c : s.toCharArray()) {
            switch (state) {
                case 0: // 开始状态
                    if (c == ' ') {
                        state = 0; // 保持在开始状态
                    } else if (c == '+' || c == '-') {
                        state = 1; // 进入符号状态
                        sign = c == '+' ? 1 : -1; // 记录符号
                    } else if (Character.isDigit(c)) {
                        state = 2; // 进入数字状态
                        result = c - '0'; // 初始化结果
                    } else {
                        state = 3; // 进入结束状态
                    }
                    break;
                case 1: // 符号状态
                    if (Character.isDigit(c)) {
                        state = 2; // 进入数字状态
                        result = c - '0'; // 初始化结果
                    } else {
                        state = 3; // 进入结束状态
                    }
                    break;
                case 2: // 数字状态
                    if (Character.isDigit(c)) {
                        // 判断结果是否溢出
                        if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (c - '0') > Integer.MAX_VALUE % 10)) {
                            return sign == 1 ?
                                    Integer.MAX_VALUE : Integer.MIN_VALUE;

                        } else if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && (c - '0') > -(Integer.MIN_VALUE % 10))) {
                            return Integer.MIN_VALUE;
                        }
                        result = result * 10 + (c - '0'); // 更新结果
                    } else {
                        state = 3; // 进入结束状态
                    }
                    break;
                case 3: // 结束状态
// 保持在结束状态
                    break;
            }
        }

// 根据状态机的状态得到最终的结果
        return sign * result;
    }

    public static void main(String[] args) {
        StringToAtoiInteger stringToAtoiInteger = new StringToAtoiInteger();

// Test case 1
        String s1 = "42";
        int expected1 = 42;
        int result1 = stringToAtoiInteger.myAtoi(s1);
        System.out.println(result1 == expected1 ? "Pass" : "Fail");

// Test case 2
        String s2 = "   -42";
        int expected2 = -42;
        int result2 = stringToAtoiInteger.myAtoi(s2);
        System.out.println(result2 == expected2 ? "Pass" : "Fail");

// Test case 3
        String s3 = "4193 with words";
        int expected3 = 4193;
        int result3 = stringToAtoiInteger.myAtoi(s3);
        System.out.println(result3 == expected3 ? "Pass" : "Fail");
    }
}
