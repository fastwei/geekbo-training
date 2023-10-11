package com.geekbo.training.leetcode.top75;

/**
 * Two Pointers
 *
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * <p>
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 * <p>
 * <p>
 * 解决思路：
 * <p>
 * 我们使用两个指针，一个指向字符串s的当前字符sPointer，另一个指向字符串t的当前字符tPointer。
 * 遍历字符串t，比较当前字符是否等于s中的字符，如果相等，则将sPointer递增，继续比较下一个字符。
 * 最后，如果sPointer等于s的长度，说明已经遍历完了s，即s是t的子序列，返回true；否则返回false。
 * 算法复杂度分析：
 * <p>
 * 时间复杂度：O(max(len(s), len(t)))，其中len(s)和len(t)分别是字符串s和t的长度。我们最多需要遍历一次较长的字符串。
 * 空间复杂度：O(1)，我们只使用了常量级的额外空间。
 *
 * Two Pointers
 */
public class IsSubsequence {

    public boolean isSubsequence(String s, String t) {
        // 初始化两个指针，sPointer指向s的当前字符，tPointer指向t的当前字符
        int sPointer = 0;
        int tPointer = 0;

        // 遍历字符串s和字符串t，比较字符是否匹配
        while (sPointer < s.length() && tPointer < t.length()) {
            // 如果当前字符匹配，将sPointer递增，继续比较下一个字符
            if (s.charAt(sPointer) == t.charAt(tPointer)) {
                sPointer++;
            }
            // 移动tPointer，继续比较下一个字符
            tPointer++;
        }

        // 如果sPointer等于s的长度，说明s是t的子序列，返回true；否则返回false
        return sPointer == s.length();
    }


    public static void main(String[] args) {
        IsSubsequence solution = new IsSubsequence();

        System.out.println(solution.isSubsequence("abc", "ahbgdc")); // 应输出 true
        System.out.println(solution.isSubsequence("axc", "ahbgdc")); // 应输出 false
        System.out.println(solution.isSubsequence("", "ahbgdc")); // 应输出 true
        System.out.println(solution.isSubsequence("abc", "")); // 应输出 false
    }


}
