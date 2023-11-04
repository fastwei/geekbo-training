package com.geekbo.training.leetcode.daily;

/**
 * 65. Valid Number
 * Hard
 * A valid number can be split up into these components (in order):
 * <p>
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 * <p>
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * One or more digits, followed by a dot '.'.
 * One or more digits, followed by a dot '.', followed by one or more digits.
 * A dot '.', followed by one or more digits.
 * An integer can be split up into these components (in order):
 * <p>
 * (Optional) A sign character (either '+' or '-').
 * One or more digits.
 * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
 * while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 * <p>
 * Given a string s, return true if s is a valid number.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "0"
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "e"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "."
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 20
 * s consists of only English letters (both uppercase and lowercase),
 * digits (0-9), plus '+', minus '-', or dot '.'.
 */
public class ValidNumber {
    /**
     * 解题思路： 根据题目的要求，一个有效的数字可以分为以下几个部分（顺序如下）：
     * <p>
     * 一个十进制数或整数。
     * （可选）一个'e'或'E'，后面跟着一个整数。
     * 一个小数可以分为以下几个部分（顺序如下）：
     * （可选）一个符号字符（'+'或'-'）。
     * 下列格式之一：
     * 一个或多个数字，后面跟着一个点'.'。
     * 一个或多个数字，后面跟着一个点'.'，再后面跟着一个或多个数字。
     * 一个点'.'，后面跟着一个或多个数字。
     * 一个整数可以分为以下几个部分（顺序如下）：
     * （可选）一个符号字符（'+'或'-'）。
     * 一个或多个数字。
     * 根据上述规则，我们可以使用有限状态机的方法来判断一个字符串是否为有效的数字。
     * <p>
     * 我们使用四个布尔类型的变量来记录我们是否已经遇到了数字、小数点、指数符号以及指数符号后面的数字。
     * <p>
     * 我们遍历字符串的每个字符，根据当前字符的情况来更新上述四个变量。具体的判断条件如下：
     * <p>
     * 如果当前字符是数字，则将numberSeen设置为true，并将numberAfterE 设置为true。
     * <p>
     * 如果当前字符是小数点，需要检查小数点之前是否已经出现过小数点或指数符号。
     * 如果是，则返回false；否则，将pointSeen设置为true。
     * 如果当前字符是指数符号，则需要检查指数符号之前是否已经出现过指数符号或数字。
     * 如果是，则返回false；否则，将eSeen设置为true，并将numberAfterE设置为false。
     * 如果当前字符是正负号，则需要检查正负号是否出现在最开头或者指数符号的后面。如果不满足条件，则返回false。
     * 如果当前字符是其他字符，则返回false。
     * 最后，我们需要保证至少出现过一个数字，并且在指数符号之后必须要有数字。
     * 如果满足这两个条件，则返回true；否则，返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历字符串的时间复杂度为O(n)，其中n为字符串的长度。
     * 空间复杂度：只使用了常量级别的额外空间。因此，空间复杂度为O(1)。
     *
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
        // 去除字符串两端的空格
        s = s.trim();

        boolean numberSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberAfterE = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                numberSeen = true;
                numberAfterE = true;
            } else if (c == '.') {
                // 小数点前面不能出现小数点和e
                if (pointSeen || eSeen) {
                    return false;
                }
                pointSeen = true;
            } else if (c == 'e' || c == 'E') {
                // e前面必须要有数字，且只能出现一次
                if (eSeen || !numberSeen) {
                    return false;
                }
                eSeen = true;
                numberAfterE = false;
            } else if (c == '+' || c == '-') {
                // 正负号只能出现在最开头或者e的后面
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else {
                // 其他字符都是非法的
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }

    public static void main(String[] args) {
        // 测试用例
        String s1 = "0";
        // 预期输出: true
        System.out.println(isNumber(s1));

        String s2 = "e";
        // 预期输出: false
        System.out.println(isNumber(s2));

        String s3 = ".";
        // 预期输出: false
        System.out.println(isNumber(s3));
    }
}
