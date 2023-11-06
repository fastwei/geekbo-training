package com.geekbo.training.leetcode.daily;

/**
 * 38. Count and Say
 * Medium
 * <p>
 * The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
 * <p>
 * countAndSay(1) = "1"
 * countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1),
 * which is then converted into a different digit string.
 * To determine how you "say" a digit string, split it into the minimal number of substrings
 * such that each substring contains exactly one unique digit.
 * Then for each substring,
 * say the number of digits, then say the digit. Finally, concatenate every said digit.
 * <p>
 * For example, the saying and conversion for digit string "3322251":
 * <p>
 * <p>
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: "1"
 * Explanation: This is the base case.
 * Example 2:
 * <p>
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 * countAndSay(1) = "1"
 * countAndSay(2) = say "1" = one 1 = "11"
 * countAndSay(3) = say "11" = two 1's = "21"
 * countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 30
 */
public class CountAndSay {
    /**
     * 解题思路：
     * <p>
     * 使用循环，从第1项开始计算到第n项；
     * 初始化第1项为字符串 "1"；
     * 对于每一项，使用两个指针来遍历当前项的字符，记录当前字符的出现次数，并将次数和字符拼接到结果中；
     * 更新结果为下一项的字符串；
     * 重复上述步骤，直到计算到第n项。
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n * m)，其中 n 是给定的正整数，m 是计数与读数序列的第n项的长度。
     * 空间复杂度：O(m)，存储计数与读数序列的第n项的字符串。
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be a positive integer");
        }

        String result = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            char prev = result.charAt(0);

            for (int j = 1; j < result.length(); j++) {
                char curr = result.charAt(j);
                if (curr == prev) {
                    count++;
                } else {
                    sb.append(count).append(prev);
                    count = 1;
                    prev = curr;
                }
            }

            sb.append(count).append(prev);
            result = sb.toString();
        }

        return result;
    }

    public static void main(String[] args) {
        // 测试用例1
        int n1 = 1;
        // 第1项是 "1"
        // 所以预期结果是 "1"
        System.out.println("Test Case 1 - Expected: 1, Output: " + countAndSay(n1));

        // 测试用例2
        int n2 = 4;
        // 第4项是 "1211"
        // countAndSay(1) = "1"
        // countAndSay(2) = say "1" = one 1 = "11"
        // countAndSay(3) = say "11" = two 1's = "21"
        // countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
        // 所以预期结果是 "1211"
        System.out.println("Test Case 2 - Expected: 1211, Output: " + countAndSay(n2));

        // 测试用例3，n为0
        int n3 = 0;
        // n必须是正整数，所以会抛出 IllegalArgumentException 异常
        // 所以预期结果是 IllegalArgumentException 异常
        try {
            countAndSay(n3);
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 3 - Expected: IllegalArgumentException, Output: IllegalArgumentException");
        }
    }
}
