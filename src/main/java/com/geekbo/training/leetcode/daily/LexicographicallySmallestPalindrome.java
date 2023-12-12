package com.geekbo.training.leetcode.daily;

/**
 * 2697. Lexicographically Smallest Palindrome
 * Easy
 * You are given a string s consisting of lowercase English letters, and you are allowed to perform operations on it. In one operation, you can replace a character in s with another lowercase English letter.
 * <p>
 * Your task is to make s a palindrome with the minimum number of operations possible. If there are multiple palindromes that can be made using the minimum number of operations, make the lexicographically smallest one.
 * <p>
 * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
 * <p>
 * Return the resulting palindrome string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "egcfe"
 * Output: "efcfe"
 * Explanation: The minimum number of operations to make "egcfe" a palindrome is 1, and the lexicographically smallest palindrome string we can get by modifying one character is "efcfe", by changing 'g'.
 * Example 2:
 * <p>
 * Input: s = "abcd"
 * Output: "abba"
 * Explanation: The minimum number of operations to make "abcd" a palindrome is 2, and the lexicographically smallest palindrome string we can get by modifying two characters is "abba".
 * Example 3:
 * <p>
 * Input: s = "seven"
 * Output: "neven"
 * Explanation: The minimum number of operations to make "seven" a palindrome is 1, and the lexicographically smallest palindrome string we can get by modifying one character is "neven".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consists of only lowercase English letters.
 */
public class LexicographicallySmallestPalindrome {
    public static void main(String[] args) {
        // 测试用例1
        String s1 = "egcfe";
        System.out.println("输入：" + s1);
        System.out.println("预期输出：efcfe");
        System.out.println("实际输出：" + makeSmallestPalindrome(s1));
        System.out.println();

        // 测试用例2
        String s2 = "abcd";
        System.out.println("输入：" + s2);
        System.out.println("预期输出：abba");
        System.out.println("实际输出：" + makeSmallestPalindrome(s2));
        System.out.println();

        // 测试用例3
        String s3 = "seven";
        System.out.println("输入：" + s3);
        System.out.println("预期输出：neven");
        System.out.println("实际输出：" + makeSmallestPalindrome(s3));
        System.out.println();
    }

    /**
     * 解题思路如下：
     * <p>
     * 将字符串s转换为字符数组arr。
     * 使用双指针i和j，初始值分别为0和arr.length-1。
     * 循环条件为i < j，即指针i和j没有相遇。
     * 在每次循环中，比较arr[i]和arr[j]的大小。
     * 如果arr[i] < arr[j]，说明arr[i]的字母顺序比arr[j]小，将arr[i]赋值给arr[j]，并将指针i向右移动一位，指针j向左移动一位。
     * 如果arr[i] >= arr[j]，说明arr[i]的字母顺序比arr[j]大或相等，将arr[j]赋值给arr[i]，并将指针i向右移动一位，指针j向左移动一位。
     * 循环结束后，arr数组就是构成最小回文字符串的字符数组。
     * 将字符数组arr转换为字符串并返回。
     * 该算法的时间复杂度为O(n)，其中n为字符串s的长度。空间复杂度为O(n)，用于存储字符数组arr。
     */
    public static String makeSmallestPalindrome(String s) {
        char arr[] = s.toCharArray();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if (arr[i] < arr[j]) {
                arr[j--] = arr[i++];
            } else {
                arr[i++] = arr[j--];
            }
        }
        return new String(arr);
    }
}
