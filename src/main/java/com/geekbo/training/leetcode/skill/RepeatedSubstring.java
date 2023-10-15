package com.geekbo.training.leetcode.skill;

/**
 *
 * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 * Example 2:
 *
 * Input: s = "aba"
 * Output: false
 * Example 3:
 *
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 *
 */
public class RepeatedSubstring {
    /**
     * 解题思路：
     * <p>
     * 我们遍历字符串 s 的长度的一半，从 1 到 n/2。
     * 对于每个长度 i，如果 n 整除 i，则将字符串 s 分割成长度为 i 的子串。
     * 然后，我们用子串重复拼接构造一个新的字符串，判断这个构造的字符串是否与原字符串 s 相同。
     * 如果相同，则返回 true，表示可以通过取子串并多次拼接构造出原字符串。
     * 如果遍历结束后都没有找到符合条件的子串，则返回 false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历的次数为 n/2，每次构造字符串的时间复杂度为 O(n)，因此总时间复杂度为 O(n^2)。
     * 空间复杂度：额外使用了常数级别的空间，因此空间复杂度为 O(1)。
     * todo:String Match需要加强
     * @param s
     * @return
     */
    public static boolean repeatedSubstringPattern(String s) {
        int n = s.length();

        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                String substring = s.substring(0, i);
                StringBuilder sb = new StringBuilder();

                // 构造由子串重复拼接而成的字符串
                for (int j = 0; j < n / i; j++) {
                    sb.append(substring);
                }

                // 判断构造的字符串是否与原字符串相同
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s1 = "abab";
        boolean result1 = repeatedSubstringPattern(s1);
        System.out.println(result1);
        // 预期输出: true

        String s2 = "aba";
        boolean result2 = repeatedSubstringPattern(s2);
        System.out.println(result2);
        // 预期输出: false

        String s3 = "abcabcabcabc";
        boolean result3 = repeatedSubstringPattern(s3);
        System.out.println(result3);
        // 预期输出: true
    }
}