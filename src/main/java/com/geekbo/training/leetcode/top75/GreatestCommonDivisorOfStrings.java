package com.geekbo.training.leetcode.top75;

/**
 * Array / String
 *
 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t (i.e., t is concatenated with itself one or more times).
 * <p>
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * Example 2:
 * <p>
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * <p>
 * 解题思路：
 * <p>
 * 首先，我们要判断两个字符串是否具有相同的前缀，因为只有具有相同前缀的字符串才可能存在一个最大的公共字符串。
 * 判断是否具有相同前缀可以通过将两个字符串拼接起来，然后比较拼接后的字符串是否和交换位置后的字符串相等。如果相等，说明有相同前缀；如果不相等，说明没有相同前缀，直接返回空字符串。
 * 如果有相同前缀，我们可以使用最大公约数（GCD）来找到最大的公共字符串。GCD 用于确定两个数的最大公约数，而在这里，我们用它来找到两个字符串长度的最大公约数，因为最大公约数的长度就是最大的公共字符串的长度。
 * 最后，根据最大公共字符串的长度，从一个字符串中截取相应长度的子串并返回。
 * 时间复杂度分析：
 * <p>
 * 判断是否具有相同前缀的时间复杂度为 O(n + m)，其中 n 和 m 分别是两个字符串的长度。
 * 使用辗转相除法计算最大公约数的时间复杂度为 O(log(min(n, m)))，其中 n 和 m 分别是两个字符串的长度。
 * 截取子串的时间复杂度为 O(min(n, m))，其中 n 和 m 分别是两个字符串的长度。
 * 因此，总的时间复杂度为 O(n + m + log(min(n, m)))。
 * 这个算法的时间复杂度是相对较低的，因为它充分利用了最大公约数的性质来寻找最大公共字符串。
 *
 * Array / String
 */
public class GreatestCommonDivisorOfStrings {
    public static String gcdOfStrings(String str1, String str2) {
        // Check if str1 and str2 have a common prefix.
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // Calculate the greatest common divisor using recursion.
        int gcd = gcd(str1.length(), str2.length());

        // Return the substring up to the length of the greatest common divisor.
        return str1.substring(0, gcd);
    }

    /**
     * calculate the greatest common divisor (GCD) of two numbers.
     * It uses the Euclidean algorithm, which repeatedly divides the larger number by the smaller number until the remainder is 0.
     * The GCD is then the non-zero remainder.
     *
     * @param a
     * @param b
     * @return
     */
    // Recursive function to calculate the greatest common divisor (GCD).
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        // Test cases
        String str1 = "ABCABC";
        String str2 = "ABC";
        System.out.println(gcdOfStrings(str1, str2)); // Output: "ABC"

        str1 = "ABABAB";
        str2 = "ABAB";
        System.out.println(gcdOfStrings(str1, str2)); // Output: "AB"

        str1 = "LEET";
        str2 = "CODE";
        System.out.println(gcdOfStrings(str1, str2)); // Output: ""
    }
}
