package com.geekbo.training.leetcode.daily;

/**
 * 828. Count Unique Characters of All Substrings of a Given String
 * Hard
 * Let's define a function countUniqueChars(s) that returns the number of unique characters in s.
 * <p>
 * For example, calling countUniqueChars(s) if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s, therefore countUniqueChars(s) = 5.
 * Given a string s, return the sum of countUniqueChars(t) where t is a substring of s. The test cases are generated such that the answer fits in a 32-bit integer.
 * <p>
 * Notice that some substrings can be repeated so in this case you have to count the repeated ones too.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Every substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 * Example 2:
 * <p>
 * Input: s = "ABA"
 * Output: 8
 * Explanation: The same as example 1, except countUniqueChars("ABA") = 1.
 * Example 3:
 * <p>
 * Input: s = "LEETCODE"
 * Output: 92
 */
public class CountUniqueCharactersOfAllSubstringsGivenString {
    /**
     * 解题思路：
     * <p>
     * 首先，我们定义一个函数countUniqueChars(s)，用于计算字符串s中出现的唯一字符的数量。
     * 然后，我们遍历字符串s，并使用两个数组prevIndex和prevPrevIndex来跟踪字符的上一次和上上次出现的索引。
     * 对于每个字符，我们计算以该字符结尾的所有子字符串的长度，并计算出其中不重复字符的数量。
     * 最后，我们将所有子字符串的长度相加，得到结果。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：遍历字符串s需要O(n)的时间，其中n是字符串的长度。
     * 空间复杂度：使用了两个大小为26的数组来存储字符的索引，所以空间复杂度为O(1)。
     * 总结：
     * <p>
     * 首先计算字符串s中每个字符的唯一字符数量，然后计算所有子字符串的长度并将其相加，返回结果。
     * 时间复杂度为O(n)，空间复杂度为O(1)。
     *
     * @param s
     * @return
     */
    public int uniqueLetterString(String s) {
        int n = s.length();
        int mod = (int) 1e9 + 7;
        int[] prevIndex = new int[26];
        int[] prevPrevIndex = new int[26];
        int total = 0;
        int result = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int index = c - 'A';
            int count = i - prevIndex[index] + 1;
            int prevCount = prevIndex[index] - prevPrevIndex[index];
            result = (result + count - prevCount) % mod;
            total = (total + result) % mod;
            prevPrevIndex[index] = prevIndex[index];
            prevIndex[index] = i + 1;
        }

        return total;
    }

    public static void main(String[] args) {
        CountUniqueCharactersOfAllSubstringsGivenString solution = new CountUniqueCharactersOfAllSubstringsGivenString();

        // 测试用例1
        String s1 = "ABC";
        int result1 = solution.uniqueLetterString(s1);
        System.out.println("测试用例1:");
        System.out.println(result1); // 预期输出: 10

        // 测试用例2
        String s2 = "ABA";
        int result2 = solution.uniqueLetterString(s2);
        System.out.println("测试用例2:");
        System.out.println(result2); // 预期输出: 8

        // 测试用例3
        String s3 = "LEETCODE";
        int result3 = solution.uniqueLetterString(s3);
        System.out.println("测试用例3:");
        System.out.println(result3); // 预期输出: 92
    }
}
