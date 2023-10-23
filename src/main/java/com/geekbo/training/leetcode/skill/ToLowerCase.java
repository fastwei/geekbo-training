package com.geekbo.training.leetcode.skill;

/**
 * Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "Hello"
 * Output: "hello"
 */
public class ToLowerCase {
    /**
     * 解题思路： 遍历字符串中的每个字符，判断是否为大写字母。
     * 如果是，将其转换为小写字母（利用 ASCII 码的差值进行转换），并将转换后的字符添加到一个新的字符串中。
     * 如果不是大写字母，则直接添加到新的字符串中。最后返回新的字符串。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(N)，其中 N 是字符串的长度。需要遍历字符串中的每个字符。
     * 空间复杂度：O(N)，需要创建一个新的字符串来保存转换后的结果。在最坏的情况下，新的字符串的长度与原始字符串相同。
     *
     * @param s
     * @return
     */
    public static String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                sb.append((char) (c + 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "Hello";
        System.out.println(toLowerCase(s1)); // Output: "hello"

        String s2 = "here";
        System.out.println(toLowerCase(s2)); // Output: "here"

        String s3 = "LOVELY";
        System.out.println(toLowerCase(s3)); // Output: "lovely"
    }
}