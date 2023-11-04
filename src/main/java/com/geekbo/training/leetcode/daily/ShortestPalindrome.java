package com.geekbo.training.leetcode.daily;

/**
 * 214. Shortest Palindrome
 * Hard
 * You are given a string s. You can convert s to a
 * palindrome
 * by adding characters in front of it.
 * <p>
 * Return the shortest palindrome you can find by performing this transformation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * <p>
 * Input: s = "abcd"
 * Output: "dcbabcd"
 */
public class ShortestPalindrome {

    /**
     * 解题思路： 我们可以从字符串的开头开始，依次判断是否存在以当前位置为起点的最长回文子串。
     * 我们使用两个指针，一个指向字符串的开头，一个指向字符串的末尾。
     * 如果两个指针指向的字符相等，我们就将两个指针向中间移动；如果不相等，我们只移动末尾指针。
     * 最后，我们找到了最长的回文子串的起始位置。
     * <p>
     * 接下来，我们将剩余的字符反转，并添加到原字符串的前面，就构成了最短的回文串。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：每次递归调用都会减少字符串的长度，并且在每次递归调用中，我们只需要遍历字符串一次。
     * 因此，总共的时间复杂度为O(n^2)，其中n为字符串的长度。
     * 空间复杂度：每次递归调用都会产生一个新的字符串，因此，递归调用的最大深度为O(n)，其中n为字符串的长度。
     * 因此，总共的空间复杂度为O(n)。
     *
     * @param s
     * @return
     */
    public static String shortestPalindrome(String s) {
        int n = s.length();
        int i = 0;

        // 寻找最长的回文子串
        for (int j = n - 1; j >= 0; j--) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
        }

        // 如果整个字符串是回文，则无需添加任何字符
        if (i == n) {
            return s;
        }

        // 将剩余的字符反转并添加到原字符串的前面，构成最短的回文串
        String suffix = s.substring(i);
        String prefix = new StringBuilder(suffix).reverse().toString();
        String middle = shortestPalindrome(s.substring(0, i));

        return prefix + middle + suffix;
    }

    public static void main(String[] args) {
        // 测试用例
        String s1 = "aacecaaa";
        // 预期输出: "aaacecaaa"
        System.out.println(shortestPalindrome(s1));

        String s2 = "abcd";
        // 预期输出: "dcbabcd"
        System.out.println(shortestPalindrome(s2));
    }
}
