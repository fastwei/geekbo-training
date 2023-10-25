package com.geekbo.training.leetcode.daily;


/**
 *
 * 提供的算法是一个普通的字符串拼接比较算法，时间复杂度为O(N+M)，其中N和M分别是两个字符串的长度。要实现更好的时间复杂度，可以考虑以下优化方案：
 *
 * 使用KMP算法：KMP算法是一种高效的字符串匹配算法，可以在O(N+M)的时间内找到两个字符串的最大公因子。首先，将两个字符串拼接起来，然后使用KMP算法找到最长的公共前缀，这个前缀就是最大公因子。KMP算法的时间复杂度为O(N+M)。
 *
 * 求两个字符串长度的最大公约数：可以使用辗转相除法等方法来求两个字符串长度的最大公约数，然后根据最大公约数来截取子串，再进行比较。这样可以减少不必要的拼接操作，从而提高效率。
 *
 * 使用哈希算法：将字符串映射成一个唯一的哈希值，然后比较哈希值是否相等。这种方法可以在常数时间内完成比较，但需要处理哈希冲突。
 *
 * 这些方法中，KMP算法是最常用的，也是时间复杂度最优的方法。它能够在线性时间内完成字符串匹配，适用于解决这类问题。其他方法可能会涉及到更复杂的处理，因此KMP算法是一个值得考虑的优化方案。
 *
 *
 *
 */
public class StringMatch {
    // 计算模式串的next数组
    private static int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int i = 0, j = -1;

        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    // 使用KMP算法进行字符串匹配
    public static boolean kmpMatch(String text, String pattern) {
        int[] next = getNext(pattern);
        int i = 0, j = 0;

        while (i < text.length() && j < pattern.length()) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        return j == pattern.length(); // 如果j等于模式串的长度，表示匹配成功
    }

    public static void main(String[] args) {
        String text1 = "abcbc";
        String text2 = "abcabcabc";
        String pattern = "abc";

        boolean result1 = kmpMatch(text1, pattern);
        boolean result2 = kmpMatch(text2, pattern);

        System.out.println("Result 1: " + result1); // 输出 true
        System.out.println("Result 2: " + result2); // 输出 true
    }
}
