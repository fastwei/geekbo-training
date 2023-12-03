package com.geekbo.training.leetcode.daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 761. Special Binary String
 * Hard
 * <p>
 * Special binary strings are binary strings with the following two properties:
 * <p>
 * The number of 0's is equal to the number of 1's.
 * Every prefix of the binary string has at least as many 1's as 0's.
 * You are given a special binary string s.
 * <p>
 * A move consists of choosing two consecutive, non-empty, special substrings of s, and swapping them.
 * Two strings are consecutive if the last character of the first string is exactly one index before the first character of the second string.
 * <p>
 * Return the lexicographically largest resulting string possible after applying the mentioned operations on the string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "11011000"
 * Output: "11100100"
 * Explanation: The strings "10" [occuring at s[1]] and "1100" [at s[3]] are swapped.
 * This is the lexicographically largest string possible after some number of swaps.
 * Example 2:
 * <p>
 * Input: s = "10"
 * Output: "10"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 50
 * s[i] is either '0' or '1'.
 * s is a special binary string.
 */
public class SpecialBinaryString {

    public static void main(String[] args) {
        SpecialBinaryString solution = new SpecialBinaryString();

        // 测试用例1
        // 预期输出: "11100100"
        String s1 = "11011000";
        String result1 = solution.makeLargestSpecial(s1);
        System.out.println(result1);

        // 测试用例2
        // 预期输出: "10"
        String s2 = "10";
        String result2 = solution.makeLargestSpecial(s2);
        System.out.println(result2);
    }

    public String makeLargestSpecial(String S) {
        int count = 0, i = 0;
        List<String> res = new ArrayList<String>();
        for (int j = 0; j < S.length(); ++j) {
            if (S.charAt(j) == '1') count++;
            else count--;
            if (count == 0) {
                res.add('1' + makeLargestSpecial(S.substring(i + 1, j)) + '0');
                i = j + 1;
            }
        }
        Collections.sort(res, Collections.reverseOrder());
        return String.join("", res);
    }
}